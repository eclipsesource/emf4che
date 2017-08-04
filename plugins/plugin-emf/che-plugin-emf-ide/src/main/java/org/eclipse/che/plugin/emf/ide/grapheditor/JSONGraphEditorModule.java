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
package org.eclipse.che.plugin.emf.ide.grapheditor;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.eclipse.che.ide.api.extension.ExtensionGinModule;
import org.eclipse.che.ide.api.filetypes.FileType;
import org.eclipse.che.plugin.emf.ide.*;
import org.eclipse.che.plugin.emf.shared.Constants;

@ExtensionGinModule
public class JSONGraphEditorModule extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(JSONGraphView.class).to(JSONGraphViewImpl.class);
    }

    @Provides
    @Singleton
    @javax.inject.Named("JSONGraphFileType")
    protected FileType provideJSONGraphFile(EMFResources res) {
        return new FileType(res.icon(), Constants.JSONGRAPH_FILE_EXT);
    }
}
