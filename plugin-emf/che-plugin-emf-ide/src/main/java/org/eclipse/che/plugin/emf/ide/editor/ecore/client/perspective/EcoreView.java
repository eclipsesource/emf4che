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
package org.eclipse.che.plugin.emf.ide.editor.ecore.client.perspective;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.inject.ImplementedBy;
import org.eclipse.che.ide.api.mvp.View;

import javax.validation.constraints.NotNull;

/**
 * The interface defines methods to control displaying of terminal.
 *
 * @author Mathias Hansen
 */
@ImplementedBy(EcoreViewImpl.class)
public interface EcoreView extends View<EcoreView.ActionDelegate> {

    interface ActionDelegate{
        void setTerminalSize(int x, int y);
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

    /**
     * Shows special error message when terminal is failed.
     *
     * @param message
     *         message which will be shown
     */
    void showErrorMessage(@NotNull String message);

    public interface WidgetInitializedCallback {
        void initialized(EcoreView editorWidget);
    }
}
