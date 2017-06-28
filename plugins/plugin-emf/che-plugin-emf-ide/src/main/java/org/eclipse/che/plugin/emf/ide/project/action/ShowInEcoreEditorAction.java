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
package org.eclipse.che.plugin.emf.ide.project.action;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.che.ide.api.action.Action;
import org.eclipse.che.ide.api.action.ActionEvent;
import org.eclipse.che.ide.api.parts.PartStackType;
import org.eclipse.che.ide.api.parts.WorkspaceAgent;
import org.eclipse.che.plugin.emf.ide.editor.client.EcoreEditorPresenter;

/**
 * Action which opens the selected file using the ecore editor.
 *
 * @author Mat Hansen <mhansen@eclipsesource.com>
 */
@Singleton
public class ShowInEcoreEditorAction extends Action {

    public final static String ACTION_ID = "showInEcoreEditorAction";

    private final WorkspaceAgent workspaceAgent;
    private final EcoreEditorPresenter ecoreEditorPresenter;

    @Inject
    public ShowInEcoreEditorAction(WorkspaceAgent workspaceAgent, EcoreEditorPresenter ecoreEditorPresenter) {
        super("Show in Ecore editor");
        this.workspaceAgent = workspaceAgent;
        this.ecoreEditorPresenter = ecoreEditorPresenter;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        workspaceAgent.openPart(ecoreEditorPresenter, PartStackType.EDITING);
        workspaceAgent.setActivePart(ecoreEditorPresenter);
    }
}
