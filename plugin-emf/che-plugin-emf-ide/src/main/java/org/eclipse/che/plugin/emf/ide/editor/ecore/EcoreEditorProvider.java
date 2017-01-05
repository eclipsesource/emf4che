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
package org.eclipse.che.plugin.emf.ide.editor.ecore;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.che.ide.api.editor.EditorPartPresenter;
import org.eclipse.che.ide.api.editor.EditorProvider;
import org.eclipse.che.plugin.emf.ide.editor.ecore.client.EcoreEditorPresenter;

/**
 * The Ecore {@link EditorProvider}.
 */
public class EcoreEditorProvider implements EditorProvider {

    private Provider<EcoreEditorPresenter> editorPesenter;

    @Inject
    public EcoreEditorProvider(Provider<EcoreEditorPresenter> editorPesenter) {
        super();
        this.editorPesenter = editorPesenter;
    }

    @Override
    public String getId() {
        return "emfEcoreViewer";
    }

    @Override
    public String getDescription() {
        return "EMF Ecore Viewer";
    }

    @Override
    public EditorPartPresenter getEditor() {
        return editorPesenter.get();
    }
}
