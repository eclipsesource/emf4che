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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

/**
 * JavaScript overlay over JSONForms2.
 */
public class JSONGraphOverlay extends JavaScriptObject {

    protected JSONGraphOverlay() {
    }

    public final static native void setContents(final Element element, final JavaScriptObject contents) /*-{
        new $wnd.updateGraphEditorData(element, contents);
    }-*/;
}
