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
 * Action to create new Ecore file.
 *
 */
@Singleton
public class NewEcoreFileAction extends AbstractNewResourceAction {

    public final static String ACTION_ID = "newEcoreFileAction";

    private static final String DEFAULT_CONTENT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "   xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"task\" nsURI=\"http://eclipse/org/emf/ecp/makeithappen/model/task\"\n" +
            "   nsPrefix=\"org.eclipse.emf.ecp.makeithappen.model.task\">\n" +
            " <eClassifiers xsi:type=\"ecore:EClass\" name=\"Task\">\n" +
            "   <eOperations name=\"hasName\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EBoolean\">\n" +
            "     <eParameters name=\"chain\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EDiagnosticChain\"/>\n" +
            "     <eParameters name=\"context\">\n" +
            "       <eGenericType eClassifier=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EMap\">\n" +
            "         <eTypeArguments/>\n" +
            "         <eTypeArguments/>\n" +
            "       </eGenericType>\n" +
            "     </eParameters>\n" +
            "   </eOperations>\n" +
            "   <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"name\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" +
            "   <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"description\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" +
            "   <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"assignee\" eType=\"#//User\"\n" +
            "       eOpposite=\"#//User/tasks\"/>\n" +
            "   <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"dueDate\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EDate\"/>\n" +
            "   <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"subTasks\" upperBound=\"-1\"\n" +
            "       eType=\"#//Task\" containment=\"true\"/>\n" +
            "   <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"done\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EBoolean\"/>\n" +
            " </eClassifiers>\n" +
            " <eClassifiers xsi:type=\"ecore:EClass\" name=\"User\">\n" +
            "   <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"firstName\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" +
            "   <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"lastName\" lowerBound=\"1\"\n" +
            "       eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" +
            "   <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"gender\" eType=\"#//Gender\"/>";

    @Inject
    public NewEcoreFileAction(DialogFactory dialogFactory,
                              CoreLocalizationConstant coreLocalizationConstant,
                              EventBus eventBus,
                              AppContext appContext,
                              NotificationManager notificationManager) {
        super("New Ecore File",
              "Creates new Ecore file",
              null, dialogFactory, coreLocalizationConstant, eventBus, appContext, notificationManager);

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
