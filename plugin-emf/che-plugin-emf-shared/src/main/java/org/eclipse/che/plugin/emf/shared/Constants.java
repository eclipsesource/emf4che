/**
 * ****************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Codenvy, S.A. - initial API and implementation
 * *****************************************************************************
 */
package org.eclipse.che.plugin.emf.shared;

/**
 * Holds EMFForms example related constants used across the server and the IDE bundle.
 */

public final class Constants {

    public static final String ECORE_MODELING_PROJECT_TYPE_ID = "emf-ecore-modeling";
    public static final String EMF_CATEGORY = "Eclipse Modeling Framework";


    public static final String LANGUAGE = "language";
    public static final String ECORE_FILE_EXT = "ecore";
    public static final String GENMODEL_FILE_EXT = "genmodel";
    public static final String DEFAULT_NS_BASE_URI = "http://www.example.org/";


    public static String CONTAINS_GENMODEL_FILES = "containsGenModelFiles";

    public static final String MENU_GROUP = "EMF";

    public static final String REST_ROOT = "emf/";

    public static final String MODEL_PACKAGE_ATTRIBUTE = "model-package";
    public static final String ECORE_NS_URI_ATTRIBUTE = "ecore-ns-uri";
    public static final String ECORE_NS_PREFIX_ATTRIBUTE = "ecore-ns-prefix";

    public static final class CodeGenerator {

        public final static String PATH = "codegen";

    }

    public static final class Rest {

        public static final class ConverterService {

            public static final String _ID = "ecoreconverter";

            public static final String XMI_TO_JSON_PATH = "/xmi2json";
            public static final String JSON_TO_XMI_PATH = "/json2xmi";

        }

    }

    public final static String restPath(String serviceName) {
        return REST_ROOT + serviceName;
    }

    private Constants() {
    }

    public final static String DUMMY_DATA = "{\n" +
            "    \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EPackage\",\n" +
            "    \"_id\": \"/\",\n" +
            "    \"name\": \"task\",\n" +
            "    \"nsURI\": \"http://eclipse/org/emf/ecp/makeithappen/model/task\",\n" +
            "    \"nsPrefix\": \"org.eclipse.emf.ecp.makeithappen.model.task\",\n" +
            "    \"eClassifiers\": [{\n" +
            "        \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EClass\",\n" +
            "        \"_id\": \"//Task\",\n" +
            "        \"name\": \"Task\",\n" +
            "        \"eOperations\": [{\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EOperation\",\n" +
            "            \"_id\": \"//Task/hasName\",\n" +
            "            \"name\": \"hasName\",\n" +
            "            \"eType\": {\n" +
            "                \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EDataType\",\n" +
            "                \"$ref\": \"http://www.eclipse.org/emf/2002/Ecore#//EBoolean\"\n" +
            "            },\n" +
            "            \"eParameters\": [{\n" +
            "                \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EParameter\",\n" +
            "                \"_id\": \"//Task/hasName/chain\",\n" +
            "                \"name\": \"chain\",\n" +
            "                \"eType\": {\n" +
            "                    \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EDataType\",\n" +
            "                    \"$ref\": \"http://www.eclipse.org/emf/2002/Ecore#//EDiagnosticChain\"\n" +
            "                }\n" +
            "            }, {\n" +
            "                \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EParameter\",\n" +
            "                \"_id\": \"//Task/hasName/context\",\n" +
            "                \"name\": \"context\",\n" +
            "                \"eGenericType\": {\n" +
            "                    \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EGenericType\",\n" +
            "                    \"_id\": \"//Task/hasName/context/@eGenericType\",\n" +
            "                    \"eClassifier\": {\n" +
            "                        \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EDataType\",\n" +
            "                        \"$ref\": \"http://www.eclipse.org/emf/2002/Ecore#//EMap\"\n" +
            "                    },\n" +
            "                    \"eTypeArguments\": [{\n" +
            "                        \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EGenericType\",\n" +
            "                        \"_id\": \"//Task/hasName/context/@eGenericType/@eTypeArguments.0\"\n" +
            "                    }, {\n" +
            "                        \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EGenericType\",\n" +
            "                        \"_id\": \"//Task/hasName/context/@eGenericType/@eTypeArguments.1\"\n" +
            "                    }]\n" +
            "                }\n" +
            "            }]\n" +
            "        }],\n" +
            "        \"eStructuralFeatures\": [{\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EAttribute\",\n" +
            "            \"_id\": \"//Task/name\",\n" +
            "            \"name\": \"name\",\n" +
            "            \"eType\": {\n" +
            "                \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EDataType\",\n" +
            "                \"$ref\": \"http://www.eclipse.org/emf/2002/Ecore#//EString\"\n" +
            "            }\n" +
            "        }, {\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EAttribute\",\n" +
            "            \"_id\": \"//Task/description\",\n" +
            "            \"name\": \"description\",\n" +
            "            \"eType\": {\n" +
            "                \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EDataType\",\n" +
            "                \"$ref\": \"http://www.eclipse.org/emf/2002/Ecore#//EString\"\n" +
            "            }\n" +
            "        }, {\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EReference\",\n" +
            "            \"_id\": \"//Task/assignee\",\n" +
            "            \"name\": \"assignee\",\n" +
            "            \"eType\": {\n" +
            "                \"$ref\": \"//User\"\n" +
            "            },\n" +
            "            \"eOpposite\": {\n" +
            "                \"$ref\": \"//User/tasks\"\n" +
            "            }\n" +
            "        }, {\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EAttribute\",\n" +
            "            \"_id\": \"//Task/dueDate\",\n" +
            "            \"name\": \"dueDate\",\n" +
            "            \"eType\": {\n" +
            "                \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EDataType\",\n" +
            "                \"$ref\": \"http://www.eclipse.org/emf/2002/Ecore#//EDate\"\n" +
            "            }\n" +
            "        }, {\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EReference\",\n" +
            "            \"_id\": \"//Task/subTasks\",\n" +
            "            \"name\": \"subTasks\",\n" +
            "            \"upperBound\": -1,\n" +
            "            \"containment\": true,\n" +
            "            \"resolveProxies\": false,\n" +
            "            \"eType\": {\n" +
            "                \"$ref\": \"//Task\"\n" +
            "            }\n" +
            "        }, {\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EAttribute\",\n" +
            "            \"_id\": \"//Task/done\",\n" +
            "            \"name\": \"done\",\n" +
            "            \"eType\": {\n" +
            "                \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EDataType\",\n" +
            "                \"$ref\": \"http://www.eclipse.org/emf/2002/Ecore#//EBoolean\"\n" +
            "            }\n" +
            "        }]\n" +
            "    }, {\n" +
            "        \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EClass\",\n" +
            "        \"_id\": \"//User\",\n" +
            "        \"name\": \"User\",\n" +
            "        \"eStructuralFeatures\": [{\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EAttribute\",\n" +
            "            \"_id\": \"//User/firstName\",\n" +
            "            \"name\": \"firstName\",\n" +
            "            \"eType\": {\n" +
            "                \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EDataType\",\n" +
            "                \"$ref\": \"http://www.eclipse.org/emf/2002/Ecore#//EString\"\n" +
            "            }\n" +
            "        }, {\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EAttribute\",\n" +
            "            \"_id\": \"//User/lastName\",\n" +
            "            \"name\": \"lastName\",\n" +
            "            \"lowerBound\": 1,\n" +
            "            \"eType\": {\n" +
            "                \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EDataType\",\n" +
            "                \"$ref\": \"http://www.eclipse.org/emf/2002/Ecore#//EString\"\n" +
            "            }\n" +
            "        }, {\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EAttribute\",\n" +
            "            \"_id\": \"//User/gender\",\n" +
            "            \"name\": \"gender\",\n" +
            "            \"eType\": {\n" +
            "                \"$ref\": \"//Gender\"\n" +
            "            }\n" +
            "        }, {\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EAttribute\",\n" +
            "            \"_id\": \"//User/active\",\n" +
            "            \"name\": \"active\",\n" +
            "            \"eType\": {\n" +
            "                \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EDataType\",\n" +
            "                \"$ref\": \"http://www.eclipse.org/emf/2002/Ecore#//EBoolean\"\n" +
            "            }\n" +
            "        }, {\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EAttribute\",\n" +
            "            \"_id\": \"//User/timeOfRegistration\",\n" +
            "            \"name\": \"timeOfRegistration\",\n" +
            "            \"eType\": {\n" +
            "                \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EDataType\",\n" +
            "                \"$ref\": \"http://www.eclipse.org/emf/2002/Ecore#//EDate\"\n" +
            "            }\n" +
            "        }, {\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EAttribute\",\n" +
            "            \"_id\": \"//User/weight\",\n" +
            "            \"name\": \"weight\",\n" +
            "            \"eType\": {\n" +
            "                \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EDataType\",\n" +
            "                \"$ref\": \"http://www.eclipse.org/emf/2002/Ecore#//EDouble\"\n" +
            "            }\n" +
            "        }, {\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EAttribute\",\n" +
            "            \"_id\": \"//User/heigth\",\n" +
            "            \"name\": \"heigth\",\n" +
            "            \"eType\": {\n" +
            "                \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EDataType\",\n" +
            "                \"$ref\": \"http://www.eclipse.org/emf/2002/Ecore#//EInt\"\n" +
            "            }\n" +
            "        }, {\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EAttribute\",\n" +
            "            \"_id\": \"//User/nationality\",\n" +
            "            \"name\": \"nationality\",\n" +
            "            \"eType\": {\n" +
            "                \"$ref\": \"//Nationality\"\n" +
            "            }\n" +
            "        }, {\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EAttribute\",\n" +
            "            \"_id\": \"//User/dateOfBirth\",\n" +
            "            \"name\": \"dateOfBirth\",\n" +
            "            \"eType\": {\n" +
            "                \"$ref\": \"//DateOfBirth\"\n" +
            "            }\n" +
            "        }, {\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EAttribute\",\n" +
            "            \"_id\": \"//User/email\",\n" +
            "            \"name\": \"email\",\n" +
            "            \"lowerBound\": 1,\n" +
            "            \"eType\": {\n" +
            "                \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EDataType\",\n" +
            "                \"$ref\": \"http://www.eclipse.org/emf/2002/Ecore#//EString\"\n" +
            "            }\n" +
            "        }, {\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EReference\",\n" +
            "            \"_id\": \"//User/tasks\",\n" +
            "            \"name\": \"tasks\",\n" +
            "            \"upperBound\": -1,\n" +
            "            \"eType\": {\n" +
            "                \"$ref\": \"//Task\"\n" +
            "            },\n" +
            "            \"eOpposite\": {\n" +
            "                \"$ref\": \"//Task/assignee\"\n" +
            "            }\n" +
            "        }]\n" +
            "    }, {\n" +
            "        \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EClass\",\n" +
            "        \"_id\": \"//UserGroup\",\n" +
            "        \"name\": \"UserGroup\",\n" +
            "        \"eStructuralFeatures\": [{\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EAttribute\",\n" +
            "            \"_id\": \"//UserGroup/name\",\n" +
            "            \"name\": \"name\",\n" +
            "            \"eType\": {\n" +
            "                \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EDataType\",\n" +
            "                \"$ref\": \"http://www.eclipse.org/emf/2002/Ecore#//EString\"\n" +
            "            }\n" +
            "        }, {\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EReference\",\n" +
            "            \"_id\": \"//UserGroup/users\",\n" +
            "            \"name\": \"users\",\n" +
            "            \"upperBound\": -1,\n" +
            "            \"containment\": true,\n" +
            "            \"resolveProxies\": false,\n" +
            "            \"eType\": {\n" +
            "                \"$ref\": \"//User\"\n" +
            "            }\n" +
            "        }]\n" +
            "    }, {\n" +
            "        \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EEnum\",\n" +
            "        \"_id\": \"//Gender\",\n" +
            "        \"name\": \"Gender\",\n" +
            "        \"eLiterals\": [\"Male\", \"Female\"]\n" +
            "    }, {\n" +
            "        \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EEnum\",\n" +
            "        \"_id\": \"//Nationality\",\n" +
            "        \"name\": \"Nationality\",\n" +
            "        \"eLiterals\": [\"German\", \"French\", \"UK\", \"US\", \"Spanish\", \"Italian\", \"Russian\"]\n" +
            "    }, {\n" +
            "        \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EDataType\",\n" +
            "        \"_id\": \"//DateOfBirth\",\n" +
            "        \"name\": \"DateOfBirth\",\n" +
            "        \"instanceClassName\": \"javax.xml.datatype.XMLGregorianCalendar\",\n" +
            "        \"eAnnotations\": [{\n" +
            "            \"eClass\": \"http://www.eclipse.org/emf/2002/Ecore#//EAnnotation\",\n" +
            "            \"_id\": \"//DateOfBirth/%http:%2F%2F%2Forg%2Feclipse%2Femf%2Fecore%2Futil%2FExtendedMetaData%\",\n" +
            "            \"source\": \"http:///org/eclipse/emf/ecore/util/ExtendedMetaData\",\n" +
            "            \"details\": {\n" +
            "                \"baseType\": \"http://www.eclipse.org/emf/2003/XMLType#date\"\n" +
            "            }\n" +
            "        }]\n" +
            "    }]\n" +
            "}";

}
