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
package org.eclipse.che.plugin.emf.ide.editor.ecore.client.inject;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import org.eclipse.che.plugin.emf.ide.editor.ecore.client.jso.EcoreCodeEditWidgetOverlay;

/**
 * Provider of Orion CodeEdit widget instance.
 *
 * @author Artem Zatsarynnyi
 */
@Singleton
public class EcoreEditorWidgetProvider implements Provider<EcoreCodeEditWidgetOverlay> {

    private EcoreCodeEditWidgetOverlay ecoreCodeEditWidgetOverlay;

    public EcoreEditorWidgetProvider() {

    }

    @Override
    public EcoreCodeEditWidgetOverlay get() {
        if (ecoreCodeEditWidgetOverlay == null) {
//            ecoreCodeEditWidgetOverlay = codeEditWidgetModule.create();
        }
        return ecoreCodeEditWidgetOverlay;
    }
}
