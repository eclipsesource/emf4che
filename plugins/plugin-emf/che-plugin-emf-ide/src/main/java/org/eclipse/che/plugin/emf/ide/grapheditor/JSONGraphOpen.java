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
import com.google.inject.Singleton;
import org.eclipse.che.ide.api.action.Action;
import org.eclipse.che.ide.api.action.ActionEvent;
import org.eclipse.che.ide.api.parts.PartStackType;
import org.eclipse.che.ide.api.parts.WorkspaceAgent;

/**
 * Action for opening the JSONForms2Presenter.
 */
@Singleton
public class JSONGraphOpen extends Action {

    public final static String ACTION_ID = "jsonGraphOpenAction";

    private WorkspaceAgent workspaceAgent;
    private JSONGraphPresenter jsonGraphPresenter;

    @Inject
    public JSONGraphOpen(WorkspaceAgent workspaceAgent, JSONGraphPresenter jsonGraphPresenter) {
        super("Show JSONGraph View");
        this.workspaceAgent = workspaceAgent;
        this.jsonGraphPresenter = jsonGraphPresenter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        workspaceAgent.openPart(jsonGraphPresenter, PartStackType.EDITING);
        workspaceAgent.setActivePart(jsonGraphPresenter);
    }

}
