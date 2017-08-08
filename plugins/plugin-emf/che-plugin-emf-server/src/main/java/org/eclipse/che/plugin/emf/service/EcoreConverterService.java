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

import org.eclipse.che.plugin.emf.shared.Constants;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emfjson.jackson.module.EMFModule;
import org.emfjson.jackson.resource.JsonResourceFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path(Constants.Rest.ConverterService._ID)
public class EcoreConverterService {

	private ResourceSet resourceSetXMI;
	private ResourceSet resourceSetJSON;
	private Map<Object, Object> options;

	public EcoreConverterService(){
		ObjectMapper mapper = new ObjectMapper();
		EMFModule module = new EMFModule();
		module.configure(EMFModule.Feature.OPTION_USE_ID, true);
		mapper.registerModule(module);
		
		resourceSetXMI = new ResourceSetImpl();
		resourceSetXMI.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		
		resourceSetJSON = new ResourceSetImpl();
		resourceSetJSON.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new JsonResourceFactory(mapper));
		
		options=new LinkedHashMap<>();
		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
		
		EPackage.Registry.INSTANCE.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
	}

	private String serialize(Resource resource, EObject data) {
		resource.getContents().add(data);
		String result;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			resource.save(bos, options);
			result = new String(bos.toByteArray(),"UTF-8");
		} catch (IOException e) {
			result=e.getMessage();
		}
		return result;
	}
	
	@Path(Constants.Rest.ConverterService.JSON_TO_XMI_PATH)
	@POST
	public String setJSON(String json){
		Resource resourceJSON = resourceSetJSON.createResource(URI.createURI("VIRTUAL_URI_"+System.currentTimeMillis()));
		EObject data = deserialize(json, resourceJSON);
		if(data == null) {
			System.err.println("SOMETHING WENT WRONG!!");
			return null;
		}
		Resource resourceXML = resourceSetXMI.createResource(URI.createURI("VIRTUAL_URI_"+System.currentTimeMillis()));
		return serialize(resourceXML, data);
	}
	@Path(Constants.Rest.ConverterService.XMI_TO_JSON_PATH)
	@POST
	public String setXML(String xml){
		Resource resourceXML = resourceSetXMI.createResource(URI.createURI("VIRTUAL_URI_"+System.currentTimeMillis()));
		EObject data = deserialize(xml, resourceXML);
		if(data == null) {
			System.err.println("SOMETHING WENT WRONG!!");
			return null;
		}
		Resource resourceJSON = resourceSetJSON.createResource(URI.createURI("VIRTUAL_URI_"+System.currentTimeMillis()));
		return serialize(resourceJSON, data);
	}

	private EObject deserialize(String xml, Resource resource) {
		ByteArrayInputStream bis = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
		try {
			resource.load(bis, options);
			return resource.getContents().get(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
