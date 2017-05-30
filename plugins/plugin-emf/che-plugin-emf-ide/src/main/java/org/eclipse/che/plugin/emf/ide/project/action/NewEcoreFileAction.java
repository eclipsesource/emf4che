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
package org.eclipse.che.plugin.emf.ide.project.action;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import org.eclipse.che.ide.CoreLocalizationConstant;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.api.dialogs.DialogFactory;
import org.eclipse.che.ide.api.editor.EditorAgent;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.ide.newresource.AbstractNewResourceAction;
import org.eclipse.che.plugin.emf.shared.Constants;

/**
 * Action to create an empty ecore file.
 *
 * @author Mat Hansen <mhansen@eclipsesource.com>
 */
@Singleton
public class NewEcoreFileAction extends AbstractNewResourceAction {

    public final static String ACTION_ID = "newEcoreFileAction";

    private static final String DEFAULT_CONTENT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"\"/>\n";

    @Inject
    public NewEcoreFileAction(DialogFactory dialogFactory,
                              CoreLocalizationConstant coreLocalizationConstant,
                              EventBus eventBus,
                              AppContext appContext,
                              NotificationManager notificationManager,
                              Provider<EditorAgent> editorAgentProvider) {
        super("New Ecore File",
              "Creates new Ecore file",
              null, dialogFactory, coreLocalizationConstant, eventBus, appContext, notificationManager, editorAgentProvider);

    }

    @Override
    protected String getExtension() {
        return Constants.ECORE_FILE_EXT;
    }

    @Override
    protected String getDefaultContent() {
        return DEFAULT_CONTENT;
    }
}
