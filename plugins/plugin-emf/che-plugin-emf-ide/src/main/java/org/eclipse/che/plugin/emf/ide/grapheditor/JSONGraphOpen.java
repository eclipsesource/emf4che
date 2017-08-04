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
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.api.resources.File;
import com.google.common.base.Optional;

import com.google.web.bindery.event.shared.*;
import org.eclipse.che.api.promises.client.*;
import org.eclipse.che.ide.api.editor.*;
import org.eclipse.che.ide.resources.reveal.RevealResourceEvent;
import org.eclipse.che.ide.util.loging.Log;

/**
 * Action for opening the JSONForms2Presenter.
 */
@Singleton
public class JSONGraphOpen extends Action {

    public final static String ACTION_ID = "jsonGraphOpenAction";

    private WorkspaceAgent workspaceAgent;
    private JSONGraphPresenter jsonGraphPresenter;
    private AppContext context;
    private final EventBus eventBus;

    private static final String FILE_PARAM_ID = "file";
    private static final String PROJECT_PARAM_ID = "project";
    private static final String LINE_PARAM_ID = "line";
    private static final String COL_PARAM_ID = "col";
    private static final String WORKSPACE = "workspace";
    private final EditorAgent editorAgent;

    @Inject
    public JSONGraphOpen(WorkspaceAgent workspaceAgent, JSONGraphPresenter jsonGraphPresenter, AppContext context, EventBus eventBus, EditorAgent editorAgent) {
        super("Show JSONGraph View");
        this.workspaceAgent = workspaceAgent;
        this.jsonGraphPresenter = jsonGraphPresenter;
        this.context = context;
        this.eventBus = eventBus;
        this.editorAgent = editorAgent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    //    workspaceAgent.openPart(jsonGraphPresenter, PartStackType.EDITING);
    //    workspaceAgent.setActivePart(jsonGraphPresenter);
        if (e.getParameters() == null) {
            Log.error(getClass(), "no params");
            return;
        }
        final String pathToOpen = e.getParameters().get(FILE_PARAM_ID);
        final String projectToOpen = e.getParameters().get(PROJECT_PARAM_ID);

        context.getWorkspaceRoot().getFile(projectToOpen + "/" + pathToOpen).then(new Operation<Optional<File>>() {
            @Override
            public void apply(Optional<File> optionalFile) throws OperationException {
                if (optionalFile.isPresent()) {
                    eventBus.fireEvent(new RevealResourceEvent(optionalFile.get()));
                    editorAgent.openEditor(optionalFile.get(), new OpenEditorCallbackImpl() {
                        @Override
                        public void onEditorOpened(EditorPartPresenter editor) {
                            editorAgent.activateEditor(editor);
                        }
                    });
                }
            }
        });
    }

}
