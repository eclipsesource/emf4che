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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import org.eclipse.che.ide.api.parts.PartStackUIResources;
import org.eclipse.che.ide.api.parts.base.BaseView;
import org.eclipse.che.plugin.emf.ide.editor.client.jso.EcoreEditorViewOverlay;

/**
 * The class contains methods to display a ecore editor instance.
 *
 * @author Mat Hansen <mhansen@eclipsesource.com>
 */
public final class EcoreViewImpl extends BaseView<EcoreView.ActionDelegate> implements EcoreView {

    interface EcoreViewImplUiBinder extends UiBinder<Widget, EcoreViewImpl> {
    }

    private final static EcoreViewImplUiBinder UI_BINDER = GWT.create(EcoreViewImplUiBinder.class);

    @UiField
    FlowPanel editorPanel;

    @Inject
    public EcoreViewImpl(PartStackUIResources partStackUIResources) {
        super(partStackUIResources);
        setContentWidget(UI_BINDER.createAndBindUi(this));
    }

    @Override
    public void setContent(JavaScriptObject content) {
        EcoreEditorViewOverlay.setContents(editorPanel.getElement(), content);
        editorPanel.setVisible(true);
    }

}
