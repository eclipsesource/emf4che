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
package org.eclipse.che.plugin.emf.generator;

import org.eclipse.che.WorkspaceIdProvider;
import org.eclipse.che.api.core.*;
import org.eclipse.che.api.core.model.machine.Command;
import org.eclipse.che.api.environment.server.MachineProcessManager;
import org.eclipse.che.api.machine.server.exception.MachineException;
import org.eclipse.che.api.machine.shared.dto.CommandDto;
import org.eclipse.che.api.project.server.handlers.CreateProjectHandler;
import org.eclipse.che.api.project.server.type.AttributeValue;
import org.eclipse.che.api.vfs.Path;
import org.eclipse.che.api.workspace.server.WorkspaceService;
import org.eclipse.che.dto.server.DtoFactory;
import org.eclipse.che.plugin.emf.shared.Constants;

import java.util.Map;

import static org.eclipse.che.plugin.emf.shared.Constants.ECORE_MODELING_PROJECT_TYPE_ID;

/**
 * Generates a new Ecore Modeling Project using a headless eclipse application.
 *
 * @author Mathias Hansen <mhansen@eclipsesource.com>
 */
public class EcoreModelingProjectCreateHandler implements CreateProjectHandler {

    private MachineProcessManager machineProcessManager;
    private WorkspaceService workspaceService;
//
//    @Inject
//    public EcoreModelingProjectCreateHandler(MachineProcessManager machineProcessManager, WorkspaceService workspaceService) {
//        this.machineProcessManager = machineProcessManager;
//        this.workspaceService = workspaceService;
//    }


    @Override
    public void onCreateProject(Path projectPath, Map<String, AttributeValue> attributes, Map<String, String> options) throws ForbiddenException, ConflictException, ServerException {
//        runEclipseCreateProjectApplication(attributes);
    }

    private void runEclipseCreateProjectApplication(Map<String, AttributeValue> attributes) {

        DtoFactory dtoFactory = DtoFactory.getInstance();
        String workspace = WorkspaceIdProvider.getWorkspaceId();

        String projectName = "project"; //TODO: get project name
        String modelName = attributes.get(Constants.MODEL_PACKAGE_ATTRIBUTE).getString();
        String packageName = attributes.get(Constants.MODEL_PACKAGE_ATTRIBUTE).getString();
        String nsPrefix = attributes.get(Constants.ECORE_NS_PREFIX_ATTRIBUTE).getString();
        String nsURI = attributes.get(Constants.ECORE_NS_URI_ATTRIBUTE).getString();

        String commandLine = "/eclipse/eclipse -noSplash -data \"${current.project.path}\" " +
                "-application org.eclipse.emf.codegen.ecore.Generator " +
                "-projectLocation \"${current.project.path}\" " +
                "-projectName "+projectName+" " +
                "-modelName "+modelName+" " +
                "-packageName "+packageName+" " +
                "-nsPrefix "+nsPrefix+" " +
                "-nsURI "+nsURI;

        System.out.println("Running command: "+commandLine);

        Command command = dtoFactory.createDto(CommandDto.class)
                .withName("EMF Code Generator")
                .withCommandLine(commandLine)
                .withType("arbitrary-type");

        String devMachineId = null;
        try {
            devMachineId = workspaceService.getByKey(workspace).getRuntime().getDevMachine().getId();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ForbiddenException e) {
            e.printStackTrace();
        } catch (BadRequestException e) {
            e.printStackTrace();
        }

        try {
            machineProcessManager.exec(workspace, devMachineId, command, null);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (MachineException e) {
            e.printStackTrace();
        } catch (BadRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getProjectType() {
        return ECORE_MODELING_PROJECT_TYPE_ID;
    }

}
