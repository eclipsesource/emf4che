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
package org.eclipse.che.plugin.emf.ide.project.client;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import org.eclipse.che.ide.api.project.MutableProjectConfig;
import org.eclipse.che.ide.api.wizard.AbstractWizardPage;
import org.eclipse.che.plugin.emf.ide.project.client.perspective.ModelSettingsPageView;

/**
 * Simple wizard page that contains the {@link ModelSettingsPageView}.
 *
 * @author Mat Hansen <mhansen@eclipsesource.com>
 */
public class ModelSettingsWizardPage extends AbstractWizardPage<MutableProjectConfig> {

    private final ModelSettingsPageView view;

    /**
     * Constructor.
     *
     * @param view
     *         the view to be displayed.
     */
    @Inject
    public ModelSettingsWizardPage(ModelSettingsPageView view) {
        this.view = view;
    }

    @Override
    public void go(AcceptsOneWidget container) {
        container.setWidget(view);
        view.setDelegate(new SettingsChangedDelegate(dataObject));
    }
}
