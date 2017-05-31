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
package org.eclipse.che.plugin.emf.ide.editor;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.multibindings.GinMultibinder;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.eclipse.che.ide.api.extension.ExtensionGinModule;
import org.eclipse.che.ide.api.filetypes.FileType;
import org.eclipse.che.ide.api.project.type.wizard.ProjectWizardRegistrar;
import org.eclipse.che.plugin.emf.ide.EMFResources;
import org.eclipse.che.plugin.emf.ide.editor.client.perspective.EcoreView;
import org.eclipse.che.plugin.emf.ide.editor.client.perspective.EcoreViewImpl;
import org.eclipse.che.plugin.emf.ide.project.EcoreModelingProjectWizardRegistrar;
import org.eclipse.che.plugin.emf.shared.Constants;

/**
 * @author Mat Hansen <mhansen@eclipsesource.com>
 */
@ExtensionGinModule
public class EcoreEditorModule extends AbstractGinModule {

    @Override
    protected void configure() {
        GinMultibinder
                .newSetBinder(binder(), ProjectWizardRegistrar.class)
                .addBinding()
                .to(EcoreModelingProjectWizardRegistrar.class);

        bind(EcoreView.class).to(EcoreViewImpl.class);
    }

    @Provides
    @Singleton
    @javax.inject.Named("EcoreFileType")
    protected FileType provideEcoreFile(EMFResources res) {
        return new FileType(res.icon(), Constants.ECORE_FILE_EXT);
    }

}
