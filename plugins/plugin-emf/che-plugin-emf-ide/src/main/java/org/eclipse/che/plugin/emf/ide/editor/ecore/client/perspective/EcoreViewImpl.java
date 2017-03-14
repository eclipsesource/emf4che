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
package org.eclipse.che.plugin.emf.ide.editor.ecore.client.perspective;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.Widget;
import org.eclipse.che.plugin.emf.ide.editor.ecore.client.jso.EcoreEditorViewOverlay;

import javax.validation.constraints.NotNull;

/**
 * The class contains methods to display terminal.
 *
 * @author Dmitry Shnurenko
 */
public final class EcoreViewImpl extends Composite implements EcoreView, RequiresResize{

    interface TerminalViewImplUiBinder extends UiBinder<Widget, EcoreViewImpl> {
    }

    private final static TerminalViewImplUiBinder UI_BINDER = GWT.create(TerminalViewImplUiBinder.class);

    @UiField
    FlowPanel editorPanel;

    private ActionDelegate delegate;

    public EcoreViewImpl() {
        initWidget(UI_BINDER.createAndBindUi(this));
    }

    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }

    /** {@inheritDoc} */
    @Override
    public void setContent(JavaScriptObject content) {

        EcoreEditorViewOverlay.setContents(editorPanel.getElement(), content);
        editorPanel.setVisible(true);

    }

    /** {@inheritDoc} */
    @Override
    public void showErrorMessage(@NotNull String message) {
//        unavailableLabel.setText(message);
//        unavailableLabel.setVisible(true);

        editorPanel.setVisible(false);
    }

    @Override
    public void onResize() {
        resizeTimer.cancel();
        resizeTimer.schedule(200);
    }

    private Timer resizeTimer = new Timer() {
        @Override
        public void run() {
            resizeTerminal();
        }
    };

    private void resizeTerminal() {
        int offsetWidth = editorPanel.getOffsetWidth();
        int offsetHeight = editorPanel.getOffsetHeight();
        if (offsetWidth <= 0 || offsetHeight <= 0) {
            return;
        }

        int x = (int)(Math.floor(offsetWidth / 6.6221374));
        int y = (int)Math.floor(offsetHeight / 13);
        delegate.setTerminalSize(x, y);
    }

}
