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
package org.eclipse.che.plugin.emf.ide.project;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import org.eclipse.che.plugin.emf.shared.Constants;

/**
 * Implementation of the {@link ModelSettingsPageView}.
 */
class ModelSettingsPageViewImpl extends Composite implements ModelSettingsPageView {

    @UiField
    TextBox modelPackage;

    @UiField
    TextBox nsUri;

    @UiField
    TextBox nsPrefix;

    private SettingsChangedDelegate delegate;

    /**
     * Constructor.
     *
     * @param uiBinder
     *         the UI binder that initializes the page
     */
    @Inject
    public ModelSettingsPageViewImpl(ModelSettingsPageViewUiBinder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        nsUri.setText(Constants.DEFAULT_NS_BASE_URI);
    }

    /** {@inheritDoc} */
    @Override
    public void setDelegate(SettingsChangedDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * Update handler for the model package field.
     *
     * @param event
     *         the event that caused the model package field to update
     */
    @UiHandler("modelPackage")
    void onModelPackageChanged(KeyUpEvent event) {
        delegate.modelPackageChanged(modelPackage.getValue());
    }

    /**
     * Update handler for the NS URI field.
     *
     * @param event
     *         the event that caused the NS URI field to update
     */
    @UiHandler("nsUri")
    void onNsUriChanged(KeyUpEvent event) {
        delegate.nsUriChanged(nsUri.getValue());
    }

    /**
     * Update handler for the NS Prefix field.
     *
     * @param event
     *         the event that caused the NS Prefix field to update
     */
    @UiHandler("nsPrefix")
    void onNsPrefixChanged(KeyUpEvent event) {
        delegate.nsPrefixChanged(nsPrefix.getValue());
    }

    /**
     * UI binder for our page.
     */
    interface ModelSettingsPageViewUiBinder extends UiBinder<DockLayoutPanel, ModelSettingsPageViewImpl> {
    }
}
