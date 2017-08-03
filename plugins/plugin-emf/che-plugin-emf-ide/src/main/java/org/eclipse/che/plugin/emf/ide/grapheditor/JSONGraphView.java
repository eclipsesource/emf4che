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
import org.eclipse.che.ide.api.mvp.View;
import org.eclipse.che.ide.api.parts.base.BaseActionDelegate;

/**
 * @author Mathias Schaefer <mathias.schaefer@eclipsesource.com>
 */
public interface JSONGraphView extends View<JSONGraphView.ActionDelegate> {

    interface ActionDelegate extends BaseActionDelegate {
    }

    void setContent(JavaScriptObject content);

    void setVisible(boolean visible);
}
