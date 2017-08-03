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

/**
 * The editor extensions for json files
 */
@Extension(title = "JSONGraph Editor")
@Singleton
public class JSONGraphExtension {

    @Inject
    public JSONGraphExtension(final EditorRegistry editorRegistry,
                              FileTypeRegistry fileTypeRegistry,
                              final @Named("JsonFileType") FileType jsonFile,
                              final JSONGraphEditorProvider ecoreEditorProvider) {

        fileTypeRegistry.registerFileType(jsonFile);
        editorRegistry.register(jsonFile, ecoreEditorProvider);

        injectCssLink(GWT.getModuleBaseForStaticFiles() + "/joint.min.css");
        injectCssLink(GWT.getModuleBaseForStaticFiles() + "/json-graph-editor-core.css");
        //injectCssLink(GWT.getModuleBaseForStaticFiles() + "/materialize.min.css");
        injectCssLink(GWT.getModuleBaseForStaticFiles() + "/forms.dark.css");
    }

    private static void injectCssLink(final String url) {
        final LinkElement link = Document.get().createLinkElement();
        link.setRel("stylesheet");
        link.setHref(url);
        Document.get().getHead().appendChild(link);
    }

    @Inject
    private void configureActions(final ActionManager actionManager,
                                  final JSONGraphOpen jsonGraphOpen) {

        DefaultActionGroup mainContextMenuGroup = (DefaultActionGroup)actionManager.getAction("resourceOperation");
        DefaultActionGroup openViewGroup = new DefaultActionGroup("Open With View", true, actionManager);
        mainContextMenuGroup.add(openViewGroup);

        actionManager.registerAction(JSONGraphOpen.ACTION_ID, jsonGraphOpen);
        openViewGroup.addAction(jsonGraphOpen);
        mainContextMenuGroup.addAction(jsonGraphOpen);
    }
}
