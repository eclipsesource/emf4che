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
package org.eclipse.che.plugin.emf.ide.editor;

import org.eclipse.che.api.promises.client.Promise;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.rest.AsyncRequestFactory;
import org.eclipse.che.ide.rest.StringUnmarshaller;
import org.eclipse.che.ide.ui.loaders.request.LoaderFactory;

import javax.inject.Inject;

/**
 * @author Mathias Hansen <mhansen@eclipsesource.com>
 */
public class EcoreEditorServiceClient {

    private AppContext appContext;
    private AsyncRequestFactory asyncRequestFactory;
    private LoaderFactory loaderFactory;

    /**
     * Constructor.
     *
     * @param appContext the {@link AppContext}
     * @param asyncRequestFactory the {@link AsyncRequestFactory} that is used to create requests
     * @param loaderFactory the {@link LoaderFactory} for displaying a message while waiting for a response
     */
    @Inject
    public EcoreEditorServiceClient(
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
     * @param name a parameter
     * @return a Promise containing the server response
     */
    public Promise<String> getHello(String name) {
        return asyncRequestFactory.createGetRequest(appContext.getDevMachine().getWsAgentBaseUrl() + "/hello/" + name)
                .loader(loaderFactory.newLoader("Waiting for hello..."))
                .send(new StringUnmarshaller());
    }

}
