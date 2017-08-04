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
package org.eclipse.che.plugin.emf.ide.editor.client;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import org.eclipse.che.api.promises.client.Operation;
import org.eclipse.che.api.promises.client.OperationException;
import org.eclipse.che.api.promises.client.Promise;
import org.eclipse.che.api.promises.client.PromiseError;
import org.eclipse.che.ide.api.dialogs.CancelCallback;
import org.eclipse.che.ide.api.dialogs.ConfirmCallback;
import org.eclipse.che.ide.api.dialogs.DialogFactory;
import org.eclipse.che.ide.api.editor.AbstractEditorPresenter;
import org.eclipse.che.ide.api.editor.EditorAgent.OpenEditorCallback;
import org.eclipse.che.ide.api.editor.EditorInput;
import org.eclipse.che.ide.api.editor.EditorLocalizationConstants;
import org.eclipse.che.ide.api.parts.WorkspaceAgent;
import org.eclipse.che.ide.util.loging.Log;
import org.eclipse.che.plugin.emf.ide.editor.client.perspective.EcoreView;
import org.eclipse.che.plugin.emf.shared.Constants;
import org.vectomatic.dom.svg.ui.SVGResource;

import javax.validation.constraints.NotNull;

/**
 * Handles the lifecycle of the ecore editor.
 *
 * @author Mat Hansen <mhansen@eclipsesource.com>
 */
public class EcoreEditorPresenter extends AbstractEditorPresenter {

    private EcoreConverterClient ecoreConverterClient;
    private WorkspaceAgent workspaceAgent;
    private EcoreView ecoreView;
    private final DialogFactory dialogFactory;
    private final EditorLocalizationConstants constant;

    private JavaScriptObject editorContent;

    @Inject
    public EcoreEditorPresenter(final EcoreConverterClient ecoreConverterClient,
                                final DialogFactory dialogFactory,
                                final EditorLocalizationConstants constant,
                                final WorkspaceAgent workspaceAgent,
                                final EcoreView ecoreView) {

        this.ecoreConverterClient = ecoreConverterClient;
        this.dialogFactory = dialogFactory;
        this.constant = constant;

        this.ecoreView = ecoreView;
        this.workspaceAgent = workspaceAgent;
    }

    /**
     * {@inheritDoc}
     */
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
    public void doSave(final AsyncCallback<EditorInput> callback) {

        String json = JsonUtils.stringify(this.editorContent);
        input.getFile().updateContent(json);
        if (callback != null) {
            callback.onSuccess(getEditorInput());
        }

//        String json = JsonUtils.stringify(this.editorContent);
//        Promise<String> jsonData = ecoreConverterClient.convertJsonToXmi(json);
//
//        jsonData.then(new Operation<String>() {
//            @Override
//            public void apply(String xmiData) throws OperationException {
//                input.getFile().updateContent(xmiData);
//            }
//        }).then(new Operation<String>() {
//            @Override
//            public void apply(String s) throws OperationException {
//                if (callback != null) {
//                    callback.onSuccess(getEditorInput());
//                }
//            }
//        }).catchError(new Operation<PromiseError>() {
//            @Override
//            public void apply(PromiseError promiseError) throws OperationException {
//                if (callback != null) {
//                    callback.onFailure(promiseError.getCause());
//                }
//            }
//        });

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doSaveAs() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activate() {
    }

    /**
     * {@inheritDoc}
     */
    @NotNull
    @Override
    public String getTitle() {
        return input.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SVGResource getTitleImage() {
        return input.getSVGResource();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitleToolTip() {
        return getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClose(@NotNull final AsyncCallback<Void> callback) {
        if (isDirty()) {
            dialogFactory.createConfirmDialog(
                    constant.askWindowCloseTitle(),
                    constant.askWindowSaveChangesMessage(getEditorInput().getName()),
                    new ConfirmCallback() {
                        @Override
                        public void accepted() {
                            doSave();
                            handleClose();
                            callback.onSuccess(null);
                        }
                    },
                    new CancelCallback() {
                        @Override
                        public void cancelled() {
                            handleClose();
                            callback.onSuccess(null);
                        }
                    }).show();
        } else {
            handleClose();
            callback.onSuccess(null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void go(AcceptsOneWidget container) {
        container.setWidget(ecoreView);
    }


    /**
     * Image to display file with image type.
     *
     * @return {@link Image}
     */
//    private Image getImage() {
//        String contentLink = input.getFile().getContentUrl();
//        Image image = (contentLink != null) ? new Image(contentLink) : new Image();
//        image.setStyleName(resources.imageViewerCss().imageViewer());
//        return image;
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeEditor(OpenEditorCallback callback) {
        ScriptInjector.fromUrl(GWT.getModuleBaseURL() + "ecore-editor.js")
                .setWindow(ScriptInjector.TOP_WINDOW)
                .setCallback(new Callback<Void, Exception>() {
                    @Override
                    public void onSuccess(final Void result) {

                        input.getFile().getContent()
                                .then(new Operation<String>() {
                                    @Override
                                    public void apply(String content) throws OperationException {
                                        Log.info(EcoreEditorPresenter.class, "Ecore editor loaded.");
                                        new EditorWidgetInitializedCallback().initialized(EcoreEditorPresenter.this.ecoreView, content);
                                        updateDirtyState(true);
                                    }
                                });

                    }

                    @Override
                    public void onFailure(final Exception e) {
                        Log.error(EcoreEditorPresenter.class, "Unable to load ecore-editor.js", e);
                    }
                }).inject();
    }

    @Override
    public void close(final boolean save) {
//        if (save) { // TODO: set dirty state
            doSave();
//        }
        workspaceAgent.removePart(this);
    }

    @Override
    public IsWidget getView() {
        return ecoreView;
    }

    private class EditorWidgetInitializedCallback
            implements EcoreView.InitializedCallback {

        private EditorWidgetInitializedCallback() {
        }

        @Override
        public void initialized(final EcoreView view, String content) {

            Log.info(EcoreEditorPresenter.class, content);
            String data = content;
            if(data == null || data.length()==0) {
                data = "{}";
            }

            try {

                Promise<String> jsonData = ecoreConverterClient.convertXmiToJson(data);

                jsonData.then(new Operation<String>() {
                    @Override
                    public void apply(String jsonData) throws OperationException {
                        EcoreEditorPresenter.this.editorContent = JsonUtils.safeEval(jsonData);
                        view.setContent(EcoreEditorPresenter.this.editorContent);
                    }
                });

            } catch (Exception ex) {
                view.setContent(JsonUtils.safeEval(Constants.DUMMY_DATA));
            }

        }
    }

}
