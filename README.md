# EMF4Che
This plugin adds support for the Eclipse Modeling Framework (EMF) in Eclipse Che. 
Currently it is in early alpha state, there is support for viewing .ecore files and running the EMF code generator. Please have a look at the open issues to get a better impression of the current state.

# Setup
To enable this plugin in your local Che installation the following steps are necessary.

## Get the code
First you have to clone this repository into the plugins directory.
```
cd /path/to/my/che/repo
cd plugins
git clone git@github.com:eclipsesource/emf4che.git
```
## Add plugin to assemblies 
Che currently requires you to add a plugin at multiple locations. The following files have to be edited (all paths are relative to the Che project root):

*/pom.xml* (Che parent POM)
```
...
<dependency>
    <groupId>org.eclipse.che.plugin</groupId>
    <artifactId>che-plugin-emf-shared</artifactId>
    <version>${che.version}</version>
</dependency>
<dependency>
    <groupId>org.eclipse.che.plugin</groupId>
    <artifactId>che-plugin-emf-ide</artifactId>
    <version>${che.version}</version>
</dependency>
<dependency>
    <groupId>org.eclipse.che.plugin</groupId>
    <artifactId>che-plugin-emf-server</artifactId>
    <version>${che.version}</version>
</dependency>
...
```

*/plugins/pom.xml* (Plugins POM)
```
...
<module>plugin-emf</module>
...
```

*/assembly/assembly-ide-war/pom.xml*
```
...
<dependency>
    <groupId>org.eclipse.che.plugin</groupId>
    <artifactId>che-plugin-emf-ide</artifactId>
</dependency>
<dependency>
    <groupId>org.eclipse.che.plugin</groupId>
    <artifactId>che-plugin-emf-shared</artifactId>
</dependency>
...
```

*/assembly/assembly-wsagent-war/pom.xml*
```
...
<dependency>
    <groupId>org.eclipse.che.plugin</groupId>
    <artifactId>che-plugin-emf-server</artifactId>
</dependency>
<dependency>
    <groupId>org.eclipse.che.plugin</groupId>
    <artifactId>che-plugin-emf-shared</artifactId>
</dependency>
...
```

Finally you need to make sure the &lt;version&gt;s in all the POM files under */plugins/plugin-emf* match with your Che version (i.e. compare with your parent POM). This includes the files:
- */plugins/plugin-emf/pom.xml*
- */plugins/plugin-emf/che-plugin-emf-ide/pom.xml*
- */plugins/plugin-emf/che-plugin-emf-server/pom.xml*
- */plugins/plugin-emf/che-plugin-emf-shared/pom.xml*

## Add EMF makeithappen sample

This step is optional. However we recommend to add the sample as it will help you to explore the features of this plugin more easily. To add the example just edit the */ide/che-core-ide-templates/src/main/resources/samples.json* file.

```
...
 {
    "name": "emfforms-makeithappen",
    "displayName": "eemfforms-makeithappen",
    "path": "/emfforms-makeithappen",
    "description": "EMFForms makeithappen Example",
    "projectType": "blank",
    "mixins": [],
    "attributes": {
      "language": [
        "java"
      ]
    },
    "modules": [],
    "problems": [],
    "source": {
      "type": "git",
      "location": "https://github.com/mathansen/emfforms-makeithappen-blank",
      "parameters": {
        "branch":"master"
      }
    },
    "commands": [
      {
        "name": "Generate Model Code",
        "type": "custom",
        "commandLine": "mvn -f ${current.project.path} clean install -DskipTests && cp ${current.project.path}/target/*.war $TOMCAT_HOME/webapps/ROOT.war && $TOMCAT_HOME/bin/catalina.sh run",
        "attributes": {
          "previewUrl": "http://${server.port.8080}"
        }
      },
      {
        "name": "Generate Edit Code",
        "type": "custom",
        "commandLine": "mvn -f ${current.project.path} clean install -DskipTests && cp ${current.project.path}/target/*.war $TOMCAT_HOME/webapps/ROOT.war && $TOMCAT_HOME/bin/catalina.sh jpda run",
        "attributes": {
          "previewUrl": "http://${server.port.8080}"
        }
      }
    ],
    "links": [],
    "category": "Samples",
    "tags": [
      "Java 1.8, Tomcat 8, MySQL 5.7"
    ]
  }
...
```

## Add EMF stack

EMF4Che depends on the EMF stack which bundles an eclipse installation to be able to run the EMF code generator.
You need to add a stack configuration in */ide/che-core-ide-stacks/src/main/resources/stacks.json*

```
...
 {
    "id": "emf-default",
    "creator": "EclipseSource Munich GmbH",
    "name": "EMF",
    "description": "Default EMF stack with Eclipse Neon.1",
    "scope": "advanced",
    "tags": [
      "Ubuntu",
      "Java",
      "EMF"
    ],
    "components": [
      {
        "name": "OpenJDK",
        "version": "1.8.0"
      },
      {
        "name": "Ubuntu",
        "version": "14.04"
      },
      {
        "name": "Eclipse Neon",
        "version": "4.6.0.I20160913-0900"
      }
    ],
    "source": {
      "type": "image",
      "origin": "eclipsesource/emfneon_jdk8"
    },
    "workspaceConfig": {
      "environments": {
        "default": {
          "machines": {
            "dev-machine": {
              "agents": [
                "org.eclipse.che.terminal", "org.eclipse.che.ws-agent", "org.eclipse.che.ssh"
              ],
              "servers": {},
              "attributes" : {
                "memoryLimitBytes": "2147483648"
              }
            }
          },
          "recipe": {
            "location": "eclipsesource/emfneon_jdk8",
            "type": "dockerimage"
          }
        }
      },
      "name": "default",
      "defaultEnv": "default",
      "description": null,
      "commands": []
    },
    "stackIcon": {
      "name": "type-go.svg",
      "mediaType": "image/svg+xml"
    }
  }
...
```
## Create the EMF stack

We prepared a Dockerfile and a helper script to build our stack image. You can find it under */plugins/plugin-emf/emf-stack*.
All you need to do is run the *build.sh* script. Brace yourself - the packaging of the container image may take a while.

## Build

Finally you need to re-build Che which should include the EMF4Che plugin in the required assemblies. You can safely run the build in parallel with the docker stack creation (previous step) if you like.

# Run it

To play with this plugin you need to create a new workspace based on the "EMF Stack". You can still use most of the features on a different stack (i.e. the Java stack) but the code generation will be unavailable.

Try creating a new Ecore file or create a new project using the "makeithappen" example.

If you have any questions or run into trouble please feel free to create an issue.
If you are interested in sponsoring this project or building your own extension for Che feel free to [get in touch with us](mailto:munich@eclipsesource.com). Have fun!

The EclipseSource Team.
