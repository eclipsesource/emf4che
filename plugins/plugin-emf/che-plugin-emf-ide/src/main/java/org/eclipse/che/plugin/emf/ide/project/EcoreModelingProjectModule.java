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

import com.google.gwt.inject.client.AbstractGinModule;
import org.eclipse.che.ide.api.extension.ExtensionGinModule;
import org.eclipse.che.plugin.emf.ide.project.client.perspective.ModelSettingsPageView;
import org.eclipse.che.plugin.emf.ide.project.client.perspective.ModelSettingsPageViewImpl;

/**
 * @author Mat Hansen <mhansen@eclipsesource.com>
 */
@ExtensionGinModule
public class EcoreModelingProjectModule extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(ModelSettingsPageView.class).to(ModelSettingsPageViewImpl.class);
    }

}
