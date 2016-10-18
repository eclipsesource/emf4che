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