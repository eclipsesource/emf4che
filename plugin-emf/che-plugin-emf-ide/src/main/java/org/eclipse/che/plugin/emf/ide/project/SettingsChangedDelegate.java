/*******************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.plugin.emf.ide.project;

import org.eclipse.che.api.workspace.shared.dto.ProjectConfigDto;
import org.eclipse.che.ide.api.project.MutableProjectConfig;
import org.eclipse.che.plugin.emf.shared.Constants;

import java.util.Collections;

/**
 * Simple delegate for updating the value of wizard input fields.
 */
public class SettingsChangedDelegate {

    private MutableProjectConfig dataObject;


    /**
     * Updates the Codenvy, S.A.current value of the model package.
     *
     * @param modelPackage
     *         the new model package
     */
    public void modelPackageChanged(String modelPackage) {
        dataObject.getAttributes().put(
                Constants.MODEL_PACKAGE_ATTRIBUTE,
                Collections.singletonList(modelPackage));

        // TODO: update ns uri and prefix as long as they are unchanged

    }

    /**
     * Constructor that expects the {@link ProjectConfigDto} data object
     * of the project being created.
     *
     * @param dataObject
     *         the {@link ProjectConfigDto} data object that holds the current value
     *         of the schema URL
     */
    public SettingsChangedDelegate(MutableProjectConfig dataObject) {
        this.dataObject = dataObject;
    }

    /**
     * Updates the current value of the NS URI.
     *
     * @param nsUri
     *         the new NS URI
     */
    public void nsUriChanged(String nsUri) {
        dataObject.getAttributes().put(
                Constants.ECORE_NS_URI_ATTRIBUTE,
                Collections.singletonList(nsUri));
    }

    /**
     * Updates the current value of the NS Prefix.
     *
     * @param nsPrefix
     *         the new NS Prefix
     */
    public void nsPrefixChanged(String nsPrefix) {
        dataObject.getAttributes().put(
                Constants.ECORE_NS_PREFIX_ATTRIBUTE,
                Collections.singletonList(nsPrefix));
    }

}
