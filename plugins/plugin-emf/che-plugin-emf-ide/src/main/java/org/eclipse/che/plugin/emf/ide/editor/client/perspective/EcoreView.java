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
package org.eclipse.che.plugin.emf.ide.editor.client.perspective;

import com.google.gwt.core.client.JavaScriptObject;
import org.eclipse.che.ide.api.mvp.View;
import org.eclipse.che.ide.api.parts.base.BaseActionDelegate;

/**
 * The class defines methods to display a ecore editor instance.
 *
 * @author Mat Hansen <mhansen@eclipsesource.com>
 */
public interface EcoreView extends View<EcoreView.ActionDelegate> {

    interface ActionDelegate extends BaseActionDelegate {
    }

    /**
     * Change visibility state of panel.
     *
     * @param visible
     *         <code>true</code> panel is visible,<code>false</code> panel is not visible
     */
    void setVisible(boolean visible);

    /**
     * Opens current terminal.
     *
     * @param content
     *         content which will be shown
     *         @NotNull JavaScriptObject content
     */
    void setContent(JavaScriptObject content);

    interface InitializedCallback {
        void initialized(EcoreView editorWidget, String content);
    }
}
