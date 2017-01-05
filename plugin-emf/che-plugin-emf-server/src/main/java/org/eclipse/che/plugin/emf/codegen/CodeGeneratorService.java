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
package org.eclipse.che.plugin.emf.codegen;

import org.eclipse.che.plugin.emf.shared.Constants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author Mathias Hansen <mathias.schaefer@eclipsesource.com>
 */
@Path("emf/"+Constants.CodeGenerator.PATH+"/{ws-id}")
public class CodeGeneratorService {

    @GET
    @Path("{projectPath}")
        public String sayHello(@PathParam("projectPath") String projectPath) {
        return "Hello from workspace" + projectPath+ " !";
    }

}