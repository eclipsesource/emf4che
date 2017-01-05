/*******************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - Initial implementation
 *******************************************************************************/
package org.eclipse.che.plugin.emf.ide.editor.ecore.client;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import org.eclipse.che.api.promises.client.Operation;
import org.eclipse.che.api.promises.client.OperationException;
import org.eclipse.che.api.promises.client.Promise;
import org.eclipse.che.ide.api.dialogs.CancelCallback;
import org.eclipse.che.ide.api.dialogs.ConfirmCallback;
import org.eclipse.che.ide.api.dialogs.DialogFactory;
import org.eclipse.che.ide.api.editor.AbstractEditorPresenter;
import org.eclipse.che.ide.api.editor.EditorAgent.OpenEditorCallback;
import org.eclipse.che.ide.api.editor.EditorInput;
import org.eclipse.che.ide.api.editor.EditorLocalizationConstants;
import org.eclipse.che.ide.api.editor.document.DocumentStorage;
import org.eclipse.che.ide.api.event.FileEvent;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.ide.api.parts.WorkspaceAgent;
import org.eclipse.che.ide.util.loging.Log;
import org.eclipse.che.plugin.emf.ide.action.EcoreConverterClient;
import org.eclipse.che.plugin.emf.ide.editor.ecore.client.perspective.EcoreView;
import org.eclipse.che.plugin.emf.shared.Constants;
import org.vectomatic.dom.svg.ui.SVGResource;

import javax.validation.constraints.NotNull;

/**
 * Is used for displaying images in editor area.
 *
 * @author Ann Shumilova
 */
public class EcoreEditorPresenter extends AbstractEditorPresenter implements FileEvent.FileEventHandler {

    private EcoreConverterClient ecoreConverterClient;
    private WorkspaceAgent workspaceAgent;
    private EcoreView view;
    private NotificationManager notificationManager;
    private final DialogFactory dialogFactory;
    private final DocumentStorage documentStorage;
    private final EditorLocalizationConstants constant;

    @Inject
    public EcoreEditorPresenter(final EcoreConverterClient ecoreConverterClient,
                                final DialogFactory dialogFactory,
                                final NotificationManager notificationManager,
                                final DocumentStorage documentStorage,
                                final EditorLocalizationConstants constant,
                                EventBus eventBus,
                                WorkspaceAgent workspaceAgent,
                                final EcoreView ecoreView) {

        this.ecoreConverterClient = ecoreConverterClient;
        this.dialogFactory = dialogFactory;
        this.notificationManager = notificationManager;
        this.documentStorage = documentStorage;
        this.constant = constant;

        this.view = ecoreView;
        this.workspaceAgent = workspaceAgent;

        Log.error(EcoreEditorPresenter.class, "GWT baseurl " + GWT.getModuleBaseURL());

        ScriptInjector.fromUrl(GWT.getModuleBaseURL() + "ecore-editor.js")
                .setWindow(ScriptInjector.TOP_WINDOW)
                .setCallback(new Callback<Void, Exception>() {
                    @Override
                    public void onSuccess(final Void result) {

                        Log.info(EcoreEditorPresenter.class, "Ecore editor loaded.");
                        new EditorWidgetInitializedCallback().initialized(EcoreEditorPresenter.this.view);

                    }

                    @Override
                    public void onFailure(final Exception e) {
                        Log.error(EcoreEditorPresenter.class, "Unable to load ecore-editor.js", e);
                    }
                }).inject();

        eventBus.addHandler(FileEvent.TYPE, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doSave() {
    }

    @Override
    public void doSave(AsyncCallback<EditorInput> callback) {
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
        return null;
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
        container.setWidget(view);
        notificationManager.notify("WENT");
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close(final boolean save) {
        // nothing to do
    }

    /**
     * {@inheritDoc}
     */
    public void setVisible(boolean visible) {
        view.setVisible(visible);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public IsWidget getView() {
        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFileOperation(FileEvent event) {
//        if (event.getOperationType() != FileEvent.FileOperation.CLOSE) {
//            return;
//        }
//
//        final String eventFilePath = event.getFile().getPath();
//        final String filePath = input.getFile().getPath();
//        if (filePath.equals(eventFilePath)) {
//            workspaceAgent.removePart(this);
//        }
    }


    private class EditorWidgetInitializedCallback
            implements EcoreView.WidgetInitializedCallback {

        private EditorWidgetInitializedCallback() {
        }

        @Override
        public void initialized(final EcoreView view) {

            final Promise<String> ecoreContents = input.getFile().getContent();

            try {

                ecoreContents.then(new Operation<String>() {
                    @Override
                    public void apply(final String xmiData) throws OperationException {

                        // FIXME: wrap xmiData in JsonSerializable
                        Promise<String> jsonData = ecoreConverterClient.convertXmiToJson(xmiData);

                        jsonData.then(new Operation<String>() {
                            @Override
                            public void apply(String jsonData) throws OperationException {
                                view.setContent(JsonUtils.safeEval(jsonData));
                            }
                        });

                    }

                });

            } catch (Exception ex) {
                view.setContent(JsonUtils.safeEval(Constants.DUMMY_DATA));
            }

        }
    }

}
