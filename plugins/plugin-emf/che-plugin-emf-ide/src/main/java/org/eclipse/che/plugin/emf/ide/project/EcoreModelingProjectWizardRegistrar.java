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
package org.eclipse.che.plugin.emf.ide.project;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.che.ide.api.project.MutableProjectConfig;
import org.eclipse.che.ide.api.project.type.wizard.ProjectWizardRegistrar;
import org.eclipse.che.ide.api.wizard.WizardPage;
import org.eclipse.che.plugin.emf.ide.project.client.ModelSettingsWizardPage;
import org.eclipse.che.plugin.emf.shared.Constants;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


/**
 * The project wizard for creating a new Ecore Modeling Project.
 *
 * @author Mat Hansen <mhansen@eclipsesource.com>
 */
public class EcoreModelingProjectWizardRegistrar implements ProjectWizardRegistrar {

    private final List<Provider<? extends WizardPage<MutableProjectConfig>>> wizardPagesProviders;

    /**
     * Constructor for JSON Example project wizard.
     *
     * @param schemaUrlWizardPageProvider
     *         the schema URL wizard page provider
     */
    @Inject
    public EcoreModelingProjectWizardRegistrar(Provider<ModelSettingsWizardPage> schemaUrlWizardPageProvider) {
        wizardPagesProviders = new ArrayList<>();
        wizardPagesProviders.add(schemaUrlWizardPageProvider);
    }

    @NotNull
    @Override
    public String getProjectTypeId() {
        return Constants.ECORE_MODELING_PROJECT_TYPE_ID;
    }

    @NotNull
    @Override
    public String getCategory() {
        return Constants.EMF_CATEGORY;
    }

    @NotNull
    @Override
    public List<Provider<? extends WizardPage<MutableProjectConfig>>> getWizardPages() {
        return wizardPagesProviders;
    }

}
