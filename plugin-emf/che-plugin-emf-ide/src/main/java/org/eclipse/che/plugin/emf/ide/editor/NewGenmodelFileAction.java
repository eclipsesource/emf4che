/*******************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - Initial implementation
 *******************************************************************************/
package org.eclipse.che.plugin.emf.ide.editor;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import org.eclipse.che.ide.CoreLocalizationConstant;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.api.dialogs.DialogFactory;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.ide.newresource.AbstractNewResourceAction;
import org.eclipse.che.plugin.emf.shared.Constants;

/**
 * @author Mathias Hansen <mhansen@eclipsesource.com>
 */
@Singleton
public class NewGenmodelFileAction extends AbstractNewResourceAction {

    public final static String ACTION_ID = "newGenmodelFileAction";

    @Inject
    public NewGenmodelFileAction(DialogFactory dialogFactory,
                              CoreLocalizationConstant coreLocalizationConstant,
                              EventBus eventBus,
                              AppContext appContext,
                              NotificationManager notificationManager) {
        super("New Genmodel File",
                "Creates new Genmodel file",
                null, dialogFactory, coreLocalizationConstant, eventBus, appContext, notificationManager);

    }

    @Override
    protected String getExtension() {
        return Constants.GENMODEL_FILE_EXT;
    }

    @Override
    protected String getDefaultContent() {
        return "";
    }
}
