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
package org.eclipse.che.plugin.emf.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.che.plugin.emf.shared.Constants;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emfjson.jackson.module.EMFModule;
import org.emfjson.jackson.resource.JsonResourceFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


/**
 * Ecore XMI/JSON converter service.
 *
 * @author Mathias Hansen <mhansen@eclipsesource.com>
 */
@Path(Constants.Rest.ConverterService._ID)
public class EcoreConverterService {

    @Path(Constants.Rest.ConverterService.XMI_TO_JSON_PATH)
    @POST
    public String convertEcoreToJson(String ecore) {
        return ecoreToJsonHelper(ecore);
    }

    @Path(Constants.Rest.ConverterService.JSON_TO_XMI_PATH)
    @POST
    public String convertJsonToEcore(String json) {
        return jsonToEcoreHelper(json);
    }

    private String ecoreToJsonHelper(String ecore) {
        InputStream is = new ByteArrayInputStream(ecore.getBytes(StandardCharsets.UTF_8));

        final ResourceSetImpl ecoreResourceSet = new ResourceSetImpl();
        ecoreResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("*", new XMIResourceFactoryImpl());
        Resource ecoreResource = ecoreResourceSet.createResource(URI.createURI("VIRTUAL_ECORE_URI"));
        try {
            ecoreResource.load(is, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        EPackage passedEPackage=(EPackage) ecoreResource.getContents().get(0);
        final ObjectMapper mapper = new ObjectMapper();
        final ResourceSetImpl jsonResourceSet = new ResourceSetImpl();
        mapper.registerModule(new EMFModule(jsonResourceSet));

        EPackage.Registry.INSTANCE
                .put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);

        EPackage.Registry.INSTANCE
                .put(passedEPackage.getNsURI(), passedEPackage);

        jsonResourceSet.getResourceFactoryRegistry()
                .getExtensionToFactoryMap()
                .put("*", new JsonResourceFactory());

        jsonResourceSet.getResourceFactoryRegistry()
                .getExtensionToFactoryMap()
                .put("ecore", new XMIResourceFactoryImpl());

        final Map<String, Object> saveOptions = new HashMap<String, Object>();
        saveOptions.put("OPTION_USE_ID", true);
        final Resource resource = jsonResourceSet.createResource(URI.createURI("VIRTUAL_JSON-URI"));
        resource.getContents().add(EcoreUtil.copy(passedEPackage));
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            resource.save(os,saveOptions);
        } catch (final IOException ex) {
            ex.printStackTrace();
        }

        try {
            return os.toString(StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error";
    }

    private String jsonToEcoreHelper(String json) {
        InputStream is = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));


        final ObjectMapper mapper = new ObjectMapper();
        final ResourceSetImpl jsonResourceSet = new ResourceSetImpl();
        mapper.registerModule(new EMFModule(jsonResourceSet));

        EPackage.Registry.INSTANCE
                .put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);

        jsonResourceSet.getResourceFactoryRegistry()
                .getExtensionToFactoryMap()
                .put("*", new JsonResourceFactory());

        final Map<String, Object> saveOptions = new HashMap<String, Object>();
        saveOptions.put("OPTION_USE_ID", true);
        final Resource resource = jsonResourceSet.createResource(URI.createURI("VIRTUAL_JSON-URI"));
        try {
            resource.load(is, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        EPackage passedEPackage=(EPackage) resource.getContents().get(0);


        EPackage.Registry.INSTANCE
                .put(passedEPackage.getNsURI(), passedEPackage);


        final ResourceSetImpl ecoreResourceSet = new ResourceSetImpl();
        ecoreResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("*", new XMIResourceFactoryImpl());
        Resource ecoreResource = ecoreResourceSet.createResource(URI.createURI("VIRTUAL_ECORE_URI"));
        ecoreResource.getContents().add(EcoreUtil.copy(passedEPackage));

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            ecoreResource.save(os,saveOptions);
        } catch (final IOException ex) {
            ex.printStackTrace();
        }

        try {
            return os.toString(StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error";
    }
}
