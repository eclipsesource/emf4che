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
package org.eclipse.che.plugin.emf.ide.editor.ecore.client.jso;

import com.google.gwt.core.client.JavaScriptObject;

public class EcoreEditorOverlay extends JavaScriptObject {

    protected EcoreEditorOverlay() {
    }

    public final native String getText() /*-{
        return this.getText();
    }-*/;

    public final native void setText(final String newValue) /*-{
        this.setText(newValue);
    }-*/;

    public final native void focus() /*-{
        this.focus();
    }-*/;

    public final native void setCaretOffset(int offset) /*-{
        this.setCaretOffset(offset, true);
    }-*/;

    public final native int getCaretOffset() /*-{
        return this.getCaretOffset();
    }-*/;

    public final native void setText(String text, int start, int end) /*-{
        this.setText(text, start, end);
    }-*/;

    /**
     * Sets the selection.
     *
     * @param start
     *         offset of the start of range
     * @param end
     *         offset of the end of range (can be before the start)
     */
    public final native void setSelection(int start, int end) /*-{
        this.setSelection(start, end);
    }-*/;

    /**
     * Sets the selection.
     *
     * @param start
     *         offset of the start of range
     * @param end
     *         offset of the end of range (can be before the start)
     * @param show
     *         scroll to show the range iff the value is true
     */
    public final native void setSelection(int start, int end, boolean show) /*-{
        this.setSelection(start, end, show);
    }-*/;

    /**
     * Sets the selection.
     *
     * @param start
     *         offset of the start of range
     * @param end
     *         offset of the end of range (can be before the start)
     * @param show
     *         additional percentage ([0,1] that must also be shown
     */
    public final native void setSelection(int start, int end, double show) /*-{
         this.setSelection(start, end, show);
    }-*/;

    public final native boolean isDirty() /*-{
        return this.isDirty();
    }-*/;

    public final native void setDirty(final boolean newValue) /*-{
        this.setDirty(newValue);
    }-*/;

    /**
     * Report the message to the user.
     *
     * @param message
     *         the message
     */
    public final native void reportStatus(String message) /*-{
        this.reportStatus(message);
    }-*/;

    /**
     * Report the message to the user.
     *
     * @param message
     *         the message
     * @param type
     *         either normal or "progress" or "error";
     */
    public final native void reportStatus(String message, String type) /*-{
        this.reportStatus(message, type);
    }-*/;

    /**
     * Report the message to the user.
     *
     * @param message
     *         the message
     * @param type
     *         either normal or "progress" or "error"
     * @param accessible
     *         if true, a screen reader will read this message
     */
    public final native void reportStatus(String message, String type, boolean accessible) /*-{
        this.reportStatus(message, type, accessible);
    }-*/;

    public final native void setZoomRulerVisible(boolean visible) /*-{
        this.setZoomRulerVisible(visible);
    }-*/;

}
