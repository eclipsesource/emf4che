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
package org.eclipse.che.plugin.emf.projecttype;

import com.google.inject.Inject;
import org.eclipse.che.api.project.server.type.ProjectTypeDef;

import static org.eclipse.che.plugin.emf.shared.Constants.*;

/**
 * The  EMF/ECore project type.
 */
public class EcoreProjectType extends ProjectTypeDef {

    /**
     * Constructor for the EMF/ECore project type.
     */
    @Inject
    public EcoreProjectType(EMFValueProviderFactory valueProviderFactory) {
        super(ECORE_MODELING_PROJECT_TYPE_ID, "Ecore Modeling Project", true, true);
        addConstantDefinition(LANGUAGE, LANGUAGE, ECORE_FILE_EXT);

        addVariableDefinition(CONTAINS_GENMODEL_FILES, "contains genmodel files", true, valueProviderFactory);
    }
}
