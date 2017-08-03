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

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.che.ide.api.editor.EditorPartPresenter;
import org.eclipse.che.ide.api.editor.EditorProvider;

/**
 * The Ecore {@link EditorProvider}.
 */
public class JSONGraphEditorProvider implements EditorProvider {

    private Provider<JSONGraphPresenter> editorPesenter;

    @Inject
    public JSONGraphEditorProvider(Provider<JSONGraphPresenter> editorPesenter) {
        super();
        this.editorPesenter = editorPesenter;
    }

    @Override
    public String getId() {
        return "jsonGraphViewer";
    }

    @Override
    public String getDescription() {
        return "JSONGraph Viewer";
    }

    @Override
    public EditorPartPresenter getEditor() {
        return editorPesenter.get();
    }
}
