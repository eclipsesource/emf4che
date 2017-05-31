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
package org.eclipse.che.plugin.emf.project.type;

import org.eclipse.che.api.core.ServerException;
import org.eclipse.che.api.project.server.FolderEntry;
import org.eclipse.che.api.project.server.type.ReadonlyValueProvider;
import org.eclipse.che.api.project.server.type.ValueProvider;
import org.eclipse.che.api.project.server.type.ValueProviderFactory;
import org.eclipse.che.api.project.server.type.ValueStorageException;
import org.eclipse.che.plugin.emf.shared.Constants;

import java.util.Collections;
import java.util.List;

/**
 * {@link ValueProviderFactory} for GenModel project type.
 * Factory creates a class which provides values of EMF project's attributes.
 *
 * @author gazarenkov
 * @author Florent Benoit
 */
public class EMFValueProviderFactory implements ValueProviderFactory {

    @Override
    public ValueProvider newInstance(FolderEntry projectFolder) {
        return new GenModelValueProvider(projectFolder);
    }

    static class GenModelValueProvider extends ReadonlyValueProvider {

        /**
         * If true, it means that there are some GenModel files in this folder or in its children.
         */
        private boolean containsGenmodelFiles;

        /**
         * Try to perform the check on GenModel files only once with lazy check.
         */
        private boolean initialized = false;

        /**
         * The root folder of this project.
         */
        private final FolderEntry rootFolder;

        public GenModelValueProvider(final FolderEntry projectFolder) {
            this.rootFolder = projectFolder;
            this.initialized = false;
        }

        /**
         * Check recursively if the given folder contains GenModel files or any of its children
         *
         * @param folderEntry
         *         the initial folder to check
         * @return true if the folder or a subfolder contains GenModel files
         */
        protected boolean hasGenModelFilesInFolder(final FolderEntry folderEntry) {
            try {
                return folderEntry.getChildFolders().stream().anyMatch(this::hasGenModelFilesInFolder) ||
                       folderEntry.getChildFiles().stream().anyMatch(fileEntry -> fileEntry.getName().endsWith("."+Constants.GENMODEL_FILE_EXT));
            } catch (ServerException e) {
                throw new IllegalStateException(String.format("Unable to get files from ''%s''", folderEntry.getName()), e);
            }
        }

        /**
         * Checks if GenModel files are available in the root folder or in any children of the root folder
         *
         * @throws ValueStorageException
         *         if there is an error when checking
         */
        protected void init() throws ValueStorageException {
            try {
                this.containsGenmodelFiles = hasGenModelFilesInFolder(rootFolder);
            } catch (IllegalStateException e) {
                throw new ValueStorageException(String.format("Unable to get files from ''%s''", rootFolder.getName()) + e.getMessage());
            }
            this.initialized = true;

        }

        @Override
        public List<String> getValues(String attributeName) throws ValueStorageException {
            if (!initialized) {
                init();
            }
            if (Constants.CONTAINS_GENMODEL_FILES.equals(attributeName)) {
                return Collections.singletonList(String.valueOf(containsGenmodelFiles));
            }
            return null;
        }
    }
}
