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
package org.eclipse.che.plugin.emf.ide.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LinkElement;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.eclipse.che.ide.api.action.ActionManager;
import org.eclipse.che.ide.api.action.DefaultActionGroup;
import org.eclipse.che.ide.api.editor.EditorRegistry;
import org.eclipse.che.ide.api.extension.Extension;
import org.eclipse.che.ide.api.filetypes.FileType;
import org.eclipse.che.ide.api.filetypes.FileTypeRegistry;
import org.eclipse.che.plugin.emf.ide.project.action.ShowInEcoreEditorAction;

/**
 * The editor extension for Ecore files
 *
 * @author Mat Hansen <mhansen@eclipsesource.com>
 */
@Extension(title = "Ecore Editor")
@Singleton
public class EcoreEditorExtension {

    @Inject
    public EcoreEditorExtension(final EditorRegistry editorRegistry,
                                final FileTypeRegistry fileTypeRegistry,
                                final @Named("EcoreFileType") FileType ecoreFile,
                                final EcoreEditorProvider ecoreEditorProvider) {

        fileTypeRegistry.registerFileType(ecoreFile);
        editorRegistry.registerDefaultEditor(ecoreFile, ecoreEditorProvider);

        injectCssLink(GWT.getModuleBaseForStaticFiles() + "/Roboto-Regular.ttf");
        injectCssLink(GWT.getModuleBaseForStaticFiles() + "/jsonforms-example.css");
        injectCssLink(GWT.getModuleBaseForStaticFiles() + "/ecore-editor.css");
    }

    private static void injectCssLink(final String url) {
        final LinkElement link = Document.get().createLinkElement();
        link.setRel("stylesheet");
        link.setHref(url);
        Document.get().getHead().appendChild(link);
    }

    @Inject
    private void configureActions(final ActionManager actionManager,
                                  final ShowInEcoreEditorAction showAsEcoreAction) {

        DefaultActionGroup mainContextMenuGroup = (DefaultActionGroup)actionManager.getAction("resourceOperation");
        DefaultActionGroup openViewGroup = new DefaultActionGroup("Open With View", true, actionManager);
        mainContextMenuGroup.add(openViewGroup);

        actionManager.registerAction(ShowInEcoreEditorAction.ACTION_ID, showAsEcoreAction);
        openViewGroup.addAction(showAsEcoreAction);
        mainContextMenuGroup.addAction(showAsEcoreAction);
    }

}
