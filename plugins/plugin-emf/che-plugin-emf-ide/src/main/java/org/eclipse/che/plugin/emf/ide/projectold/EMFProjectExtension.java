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
package org.eclipse.che.plugin.emf.ide.projectold;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.che.ide.api.action.ActionManager;
import org.eclipse.che.ide.api.action.DefaultActionGroup;
import org.eclipse.che.ide.api.constraints.Constraints;
import org.eclipse.che.ide.api.extension.Extension;
import org.eclipse.che.plugin.emf.ide.EMFResources;
import org.eclipse.che.plugin.emf.ide.action.RunCodeGeneratorAction;
import org.eclipse.che.plugin.emf.ide.action.TestEcoreConverterAction;
import org.eclipse.che.plugin.emf.ide.editor.NewEcoreFileAction;
import org.eclipse.che.plugin.emf.shared.Constants;

import static org.eclipse.che.ide.api.action.IdeActions.GROUP_FILE_NEW;

/**
 * @author Mathias Hansen <mathias.schaefer@eclipsesource.com>
 */
// TODO: delete/refactor
@Extension(title = "EMF Project")
@Singleton
public class EMFProjectExtension {

    @Inject
    private void prepareActions(//final CreateEMFProjectAction projectAction,
                                final EMFResources resources,
                                final ActionManager actionManager,
                                final NewEcoreFileAction newEcoreFileAction,
                                final RunCodeGeneratorAction runCodeGeneratorAction,
                                final TestEcoreConverterAction testEcoreConverterAction) {
        // register actions
        actionManager.registerAction(NewEcoreFileAction.ACTION_ID, newEcoreFileAction);
        actionManager.registerAction(RunCodeGeneratorAction.ACTION_ID, runCodeGeneratorAction);
//        actionManager.registerAction(TestEcoreConverterAction.ACTION_ID, testEcoreConverterAction);

//        actionManager.registerAction(CreateEMFProjectAction.ACTION_ID, projectAction);

        // set icons
//        newEcoreFileAction.getTemplatePresentation().setSVGResource(resources.ecoreIcon());
//        runCodeGeneratorAction.getTemplatePresentation().setSVGResource(resources.codeGeneratorIcon());

        // add actions in main menu
        DefaultActionGroup newGroup = (DefaultActionGroup)actionManager.getAction(GROUP_FILE_NEW);
        newGroup.addAction(newEcoreFileAction, Constraints.FIRST);

        // add actions in context menu
        DefaultActionGroup emfContextMenuGroup = new DefaultActionGroup(Constants.MENU_GROUP, true, actionManager);
        actionManager.registerAction("emfContextMenu", emfContextMenuGroup);
        emfContextMenuGroup.add(runCodeGeneratorAction);
        emfContextMenuGroup.add(testEcoreConverterAction);

        DefaultActionGroup mainContextMenuGroup = (DefaultActionGroup)actionManager.getAction("resourceOperation");
        mainContextMenuGroup.add(emfContextMenuGroup);
    }

}
