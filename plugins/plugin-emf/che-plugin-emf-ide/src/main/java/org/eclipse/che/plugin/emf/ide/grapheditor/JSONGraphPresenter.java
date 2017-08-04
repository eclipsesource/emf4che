/*******************************************************************************
 * Copyright (c) 2012-2017 EclipseSource
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - Initial implementation
 *******************************************************************************/
package org.eclipse.che.plugin.emf.ide.grapheditor;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import org.eclipse.che.api.promises.client.Operation;
import org.eclipse.che.api.promises.client.OperationException;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.api.editor.AbstractEditorPresenter;
import org.eclipse.che.ide.api.editor.EditorAgent;
import org.eclipse.che.ide.api.editor.EditorAgent.OpenEditorCallback;
import org.eclipse.che.ide.api.editor.EditorInput;
import org.eclipse.che.ide.api.mvp.View;
import org.eclipse.che.ide.api.parts.WorkspaceAgent;
import org.eclipse.che.ide.util.loging.Log;
import org.eclipse.che.plugin.emf.ide.EMFResources;
import org.eclipse.che.plugin.emf.ide.editor.client.EcoreConverterClient;
import org.vectomatic.dom.svg.ui.SVGResource;
import org.eclipse.che.ide.api.resources.Resource;
import org.eclipse.che.ide.api.resources.File;
import org.eclipse.che.ide.api.resources.Folder;
import org.eclipse.che.ide.resource.Path;
import com.google.common.base.Optional;

public class JSONGraphPresenter extends AbstractEditorPresenter {

    private final JSONGraphView jsonGraphView;
    private WorkspaceAgent workspaceAgent;
    private AppContext context;
    private EventBus eventBus;
    private EcoreConverterClient ecoreConverterClient;

    private JavaScriptObject editorContent;

    @Inject
    public JSONGraphPresenter(final JSONGraphView jsonGraphView, WorkspaceAgent workspaceAgent, AppContext context, EventBus eventBus, EditorAgent editorAgent, EcoreConverterClient ecoreConverterClient) {
        this.jsonGraphView = jsonGraphView;
        this.workspaceAgent = workspaceAgent;
        this.context = context;
        this.eventBus = eventBus;
        this.ecoreConverterClient = ecoreConverterClient;
    }

    private void setContent(JavaScriptObject content) {
        this.jsonGraphView.setContent(content);
    }

    @Override
    public String getTitle() {
        return "JSON Graph Editor";
    }

    @Override
    public SVGResource getTitleImage() {
        return (EMFResources.INSTANCE.icon());
    }

    @Override
    public View getView() {
        return jsonGraphView;
    }

    @Override
    public String getTitleToolTip() {
        return getTitle();
    }

    @Override
    public void go(AcceptsOneWidget container) {
        container.setWidget(jsonGraphView);
    }

    @Override
    public void doSave() {
      doSave(new AsyncCallback<EditorInput>() {
          @Override
          public void onSuccess(final EditorInput result) {
              // do nothing
          }

          @Override
          public void onFailure(final Throwable caught) {
              // do nothing
          }
      });
    }

    @Override
    public void doSave(AsyncCallback<EditorInput> callback) {
      String json = JsonUtils.stringify(this.editorContent);
      input.getFile().updateContent(json);
      if (callback != null) {
        callback.onSuccess(getEditorInput());
      }
    }

    @Override
    public void doSaveAs() {
    }

    @Override
    public void activate() {
    }
    @Override
    protected void initializeEditor(OpenEditorCallback callback) {
        Log.info(JSONGraphPresenter.class, "Initialize JSONGraphPresenter");
      ScriptInjector.fromUrl(GWT.getModuleBaseURL() + "che_jsongraph.js")
        .setWindow(ScriptInjector.TOP_WINDOW)
        .setCallback(new Callback<Void, Exception>() {
            @Override
            public void onSuccess(final Void result) {
                Path location = input.getFile().getLocation();
                Path ecoreLocation = location.removeFileExtension().addFileExtension("ecore");
                Log.info(JSONGraphPresenter.class, "Determined Ecore Location: " + ecoreLocation.toString());

                context.getWorkspaceRoot().getFile(ecoreLocation).then(new Operation<Optional<File>>() {
                    @Override
                    public void apply(Optional<File> optionalFile) throws OperationException {
                        if (optionalFile.isPresent()) {
                            Log.info(JSONGraphPresenter.class, "Ecore File is present");
                            optionalFile.get().getContent().then(new Operation<String>() {
                                @Override
                                public void apply(String content) throws OperationException {
                                    Log.info(JSONGraphPresenter.class, "Call Ecore Converter");
                                    try {
                                        ecoreConverterClient.convertXmiToJson(content).then(new Operation<String>() {
                                            @Override
                                            public void apply(String jsonData) throws OperationException {
                                                Log.info(JSONGraphPresenter.class, "Converter successfull. Set content");
                                                new EditorWidgetInitializedCallback().initialized(JSONGraphPresenter.this.jsonGraphView, jsonData);
                                            }
                                        });

                                    } catch (Exception ex) {
                                        Log.error(JSONGraphPresenter.class,ex);
                                        // Fallback
                                        Log.info(JSONGraphPresenter.class, "Fallback");
                                        input.getFile().getContent().then(new Operation<String>() {
                                            @Override
                                            public void apply(String content) throws OperationException {
                                                new EditorWidgetInitializedCallback().initialized(JSONGraphPresenter.this.jsonGraphView, content);
                                            }
                                        });
                                    }
                                }
                            });
                        }else{
                            Log.info(JSONGraphPresenter.class, "Ecore File is not present");
                            Log.info(JSONGraphPresenter.class, "Fallback");
                            // Fallback
                            input.getFile().getContent().then(new Operation<String>() {
                                @Override
                                public void apply(String content) throws OperationException {
                                    new EditorWidgetInitializedCallback().initialized(JSONGraphPresenter.this.jsonGraphView, content);
                                }
                            });
                        }
                    }
                });


            }

            @Override
            public void onFailure(final Exception e) {
                Log.error(JSONGraphPresenter.class, "Unable to load che_jsongraph.js", e);
            }
        }).inject();

    }

    private void traverseChildren(Resource[] resources){
        for(Resource resource: resources){
            Log.info(JSONGraphPresenter.class, "Resource: " + resource.getName());
            if(resource.isFile()){
                File file = resource.asFile();
                Log.info(JSONGraphPresenter.class, "File: " + file.getDisplayName());
                if(resource.isFolder()){
                    Folder folder = (Folder) resource;
                    folder.getChildren(true).then(new Operation<Resource[]>() {
                        @Override
                        public void apply(Resource[] children) throws OperationException {
                            traverseChildren(children);
                        }
                    });
                }
            }
        }
    }

    @Override
    public void close(final boolean save) {
      doSave();
      workspaceAgent.removePart(this);
    }

    private class EditorWidgetInitializedCallback {

        private EditorWidgetInitializedCallback() {
        }

        public void initialized(final JSONGraphView view, String content) {
          Log.info(JSONGraphPresenter.class, "Set content: " + content);
          String data = content;
          if(data == null || data.length()==0) {
            data = "{}";
          }
          JSONGraphPresenter.this.editorContent = JsonUtils.safeEval(data);
          view.setContent(JSONGraphPresenter.this.editorContent);
        }
    }
}
