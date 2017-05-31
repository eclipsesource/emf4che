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
package org.eclipse.che.plugin.emf.ide.action;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.che.ide.api.action.Action;
import org.eclipse.che.ide.api.action.ActionEvent;
import org.eclipse.che.ide.api.command.CommandManager;
import org.eclipse.che.ide.dto.DtoFactory;
import org.eclipse.che.ide.util.loging.Log;


/**
 * Action for opening a part which embeds javascript code.
 */
@Singleton
public class RunCodeGeneratorAction extends Action {

    public final static String ACTION_ID = "runCodeGeneratorAction";

    private final DtoFactory dtoFactory;
    private CommandManager commandManager;

    @Inject
    public RunCodeGeneratorAction(DtoFactory dtoFactory,
                                  CommandManager commandManager) {
        super("Run code generator", "Run the EMF code generator on this project");
        this.dtoFactory = dtoFactory;
        this.commandManager = commandManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Log.info(getClass(), "Executing native command...");

        // TODO: remove find expression and actually run the generator using the selected genmodel file
        String commandLine = "/eclipse/eclipse -noSplash -data \"${current.project.path}\" " +
                "-application org.eclipse.emf.codegen.ecore.Generator -model -edit \"$( find ${current.project.path} -iname *.genmodel)\"";

        // TODO adapt API change
//        final CommandConfiguration configuration =  customCommandType.getConfigurationFactory()
//                .createFromDto(dtoFactory.createDto(CommandDto.class)
//                .withName("EMF Code Generator")
//                .withCommandLine(commandLine)
//                .withType("arbitrary-type"));
//
//        commandManager.execute(configuration);
    }

}
