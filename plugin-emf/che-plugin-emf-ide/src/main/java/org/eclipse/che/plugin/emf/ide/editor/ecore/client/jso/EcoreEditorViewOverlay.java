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
package org.eclipse.che.plugin.emf.ide.editor.ecore.client.jso;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

/**
 * JavaScript overlay over Orion EditorView object.
 *
 * @author Artem Zatsarynnyi
 */
public class EcoreEditorViewOverlay extends JavaScriptObject {

    protected EcoreEditorViewOverlay() {
    }

    public final static native void setContents(final Element element, final JavaScriptObject contents) /*-{
        new $wnd.updateEcoreEditorData(element, contents);
    }-*/;

//
//    public final native EcoreEditorOverlay getEditor() /*-{
//        return this.editor;
//    }-*/;
//
//    public final native void updateSettings(JavaScriptObject settings) /*-{
//        return this.updateSettings(settings);
//    }-*/;
//
//    public final native void setReadonly(final boolean readonly) /*-{
//        this.readonly = readonly;
//    }-*/;
}
