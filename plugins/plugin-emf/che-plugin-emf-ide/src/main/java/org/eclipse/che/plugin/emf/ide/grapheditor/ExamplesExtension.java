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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ScriptElement;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.che.ide.api.extension.Extension;

/**
 * The editor extensions for json files
 */
@Extension(title = "Examples")
@Singleton
public class ExamplesExtension {

    @Inject
    public ExamplesExtension() {
        injectJS(GWT.getModuleBaseForStaticFiles() + "/native-shim.js");
    }

    private static void injectJS(final String url) {
        final ScriptElement link = Document.get().createScriptElement();
        link.setSrc(url);
        Document.get().getHead().appendChild(link);
    }
}
