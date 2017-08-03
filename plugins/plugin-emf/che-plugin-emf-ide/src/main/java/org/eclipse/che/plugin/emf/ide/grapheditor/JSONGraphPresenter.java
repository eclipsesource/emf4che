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
import org.eclipse.che.api.promises.client.Operation;
import org.eclipse.che.api.promises.client.OperationException;
import org.eclipse.che.ide.api.editor.AbstractEditorPresenter;
import org.eclipse.che.ide.api.editor.EditorAgent.OpenEditorCallback;
import org.eclipse.che.ide.api.editor.EditorInput;
import org.eclipse.che.ide.api.mvp.View;
import org.eclipse.che.ide.api.parts.WorkspaceAgent;
import org.eclipse.che.ide.util.loging.Log;
import org.eclipse.che.plugin.emf.ide.EMFResources;
import org.vectomatic.dom.svg.ui.SVGResource;

public class JSONGraphPresenter extends AbstractEditorPresenter {

    private final JSONGraphView jsonGraphView;
    private WorkspaceAgent workspaceAgent;

    private JavaScriptObject editorContent;

    @Inject
    public JSONGraphPresenter(final JSONGraphView jsonGraphView, WorkspaceAgent workspaceAgent) {
        this.jsonGraphView = jsonGraphView;
        this.workspaceAgent = workspaceAgent;
    }

    private void setContent(JavaScriptObject content) {
        this.jsonGraphView.setContent(content);
    }

    @Override
    public String getTitle() {
        return input.getName();
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
      ScriptInjector.fromUrl(GWT.getModuleBaseURL() + "che_jsongraph.js")
        .setWindow(ScriptInjector.TOP_WINDOW)
        .setCallback(new Callback<Void, Exception>() {
            @Override
            public void onSuccess(final Void result) {
              input.getFile().getContent().then(new Operation<String>() {
                  @Override
                  public void apply(String content) throws OperationException {
                    new EditorWidgetInitializedCallback().initialized(JSONGraphPresenter.this.jsonGraphView, content);
                  }
                });
            }

            @Override
            public void onFailure(final Exception e) {
                Log.error(JSONGraphPresenter.class, "Unable to load che_jsongraph.js", e);
            }
        }).inject();

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
          Log.info(JSONGraphPresenter.class, content);
          String data = content;
          if(data == null || data.length()==0) {
            data = "{}";
          }
          JSONGraphPresenter.this.editorContent = JsonUtils.safeEval(data);
          view.setContent(JSONGraphPresenter.this.editorContent);
        }
    }
}
