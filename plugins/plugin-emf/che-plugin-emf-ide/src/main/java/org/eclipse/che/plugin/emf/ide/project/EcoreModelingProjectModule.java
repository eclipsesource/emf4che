package org.eclipse.che.plugin.emf.ide.project;

import com.google.gwt.inject.client.AbstractGinModule;
import org.eclipse.che.ide.api.extension.ExtensionGinModule;
import org.eclipse.che.plugin.emf.ide.project.client.perspective.ModelSettingsPageView;
import org.eclipse.che.plugin.emf.ide.project.client.perspective.ModelSettingsPageViewImpl;

/**
 * @author Mat Hansen <mhansen@eclipsesource.com>
 */
@ExtensionGinModule
public class EcoreModelingProjectModule extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(ModelSettingsPageView.class).to(ModelSettingsPageViewImpl.class);
    }

}
