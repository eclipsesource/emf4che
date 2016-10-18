/**
 * ****************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Codenvy, S.A. - initial API and implementation
 * *****************************************************************************
 */
package org.eclipse.che.plugin.emf.ide.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LinkElement;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.eclipse.che.ide.api.editor.EditorRegistry;
import org.eclipse.che.ide.api.extension.Extension;
import org.eclipse.che.ide.api.filetypes.FileType;
import org.eclipse.che.ide.api.filetypes.FileTypeRegistry;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.plugin.emf.ide.EMFResources;
import org.eclipse.che.plugin.emf.ide.editor.ecore.EcoreEditorProvider;

import java.util.logging.Logger;

/**
 * The editor extension for ecore files
 */
@Extension(title = "Ecore Editor")
public class EcoreEditorExtension {

    private static final Logger LOG = Logger.getLogger(EcoreEditorExtension.class.getSimpleName());

    private final NotificationManager notificationManager;

    @Inject
    public EcoreEditorExtension(final NotificationManager notificationManager,
                                final EditorRegistry editorRegistry,
                                FileTypeRegistry fileTypeRegistry,
                                final @Named("EcoreFileType") FileType ecoreFile,
                                final EcoreEditorProvider ecoreEditorProvider,
                                final EMFResources emfResources) {

        this.notificationManager = notificationManager;

//        this.requireJsLoader = requireJsLoader;

//        editorModule.setEditorInitializer(new AbstractEditorModule.EditorInitializer() {
//            @Override
//            public void initialize(final AbstractEditorModule.InitializerCallback callback) {
//                // add code-splitting of the whole orion editor
//                GWT.runAsync(new RunAsyncCallback() {
//                    @Override
//                    public void onSuccess() {
//                        injectEcoreEditor(callback);
//                    }
//
//                    @Override
//                    public void onFailure(final Throwable reason) {
//                        callback.onFailure(reason);
//                    }
//                });
//            }
//        });

        fileTypeRegistry.registerFileType(ecoreFile);
        editorRegistry.registerDefaultEditor(ecoreFile, ecoreEditorProvider);

        injectCssLink("https://fonts.googleapis.com/icon?family=Material+Icons");
        injectCssLink(GWT.getModuleBaseForStaticFiles() + "/ecore-editor.css");
    }

    private static void injectCssLink(final String url) {
        final LinkElement link = Document.get().createLinkElement();
        link.setRel("stylesheet");
        link.setHref(url);
        Document.get().getHead().appendChild(link);
    }

}
