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
package org.eclipse.che.plugin.emf.ide.editor.client;

import org.eclipse.che.api.promises.client.Promise;
import org.eclipse.che.ide.MimeType;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.rest.AsyncRequestFactory;
import org.eclipse.che.ide.rest.HTTPHeader;
import org.eclipse.che.ide.rest.StringUnmarshaller;
import org.eclipse.che.ide.ui.loaders.request.LoaderFactory;
import org.eclipse.che.plugin.emf.shared.Constants;

import javax.inject.Inject;

/**
 * Client for consuming the ecore/XMI converter service.
 *
 * @author Mat Hansen <mhansen@eclipsesource.com>
 */
public class EcoreConverterClient {

    private AppContext appContext;
    private AsyncRequestFactory asyncRequestFactory;
    private LoaderFactory loaderFactory;

    /**
     * Constructor.
     *
     * @param appContext          the {@link AppContext}
     * @param asyncRequestFactory the {@link AsyncRequestFactory} that is used to create requests
     * @param loaderFactory       the {@link LoaderFactory} for displaying a message while waiting for a response
     */
    @Inject
    public EcoreConverterClient(
            final AppContext appContext,
            final AsyncRequestFactory asyncRequestFactory,
            final LoaderFactory loaderFactory) {

        this.appContext = appContext;
        this.asyncRequestFactory = asyncRequestFactory;
        this.loaderFactory = loaderFactory;
    }

    /**
     * Invoke the sample server service.
     *
     * @param converterName a parameter
     * @return a Promise containing the server response
     */
    private Promise<String> convert(String converterName, String content) {

        final StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(appContext.getDevMachine().getWsAgentBaseUrl())
                .append("/").append(Constants.Rest.ConverterService._ID)
                .append(converterName);

        return asyncRequestFactory
                .createPostRequest(urlBuilder.toString(), null)
                .header(HTTPHeader.CONTENT_TYPE, MimeType.TEXT_PLAIN)
                .data(content)
                .loader(loaderFactory.newLoader("Waiting for converter..."))
                .send(new StringUnmarshaller());
    }


    public Promise<String> convertXmiToJson(String content) {

        return convert(Constants.Rest.ConverterService.XMI_TO_JSON_PATH, content);

    }

    public Promise<String> convertJsonToXmi(String content) {

        return convert(Constants.Rest.ConverterService.JSON_TO_XMI_PATH, content);
    }

}