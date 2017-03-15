# EMF4Che
This plugin adds support for the Eclipse Modeling Framework (EMF) in Eclipse Che. 

Currently it is in early alpha state, there is support for viewing .ecore files and running the EMF code generator. Please have a look at the open issues to get a better impression of the current state.

# Build
We build the EMF4Che plugin together with Eclipse Che and the EMF stack using an automated maven build.

## Prerequisites
 * You need to be able to run Eclipse Che on your system. Follow this [Getting Started Guide](https://www.eclipse.org/che/docs/setup/getting-started/index.html) and [Configuration Guide](https://www.eclipse.org/che/docs/setup/configuration/index.html) if you did not already do so and make sure you can start Eclipse Che, create and use workspaces etc. Do not forget to stop Eclipse Che again.
  * You need to be able to build Eclipse Che on your system. Make sure you have [all software listed here](https://www.eclipse.org/che/docs/plugins/setup-che-workspace/#pre-requisites)  installed in the correct version.

## Get the code
First you have to clone this repository.
```
git clone git@github.com:eclipsesource/emf4che.git
```

## Create the EMF stack

To use the Code Generation we require a custom stack. We prepared a Dockerfile and a helper script to build our stack image. You can find it under */plugins/plugin-emf/emf-stack*.

All you need to do is run the *build.sh* script. Brace yourself - the packaging of the container image may take a while.

## Build

Finally we need to build Eclipse Che, the EMF Stack and the EMF4Che Plugin. To do so switch to the root directory of the repository and run the following command.
```
mvn clean install
```

For a faster build you can also run ```mvn -T 1C -DskipTests -Dskip-validate-sources clean install```

# Run

Start the Eclipse Che we built above. To do so switch to the *assembly/assembly-main/target/eclipse-che-\*/eclipse-che-\*/bin* directory and execute ```./che.sh start```

## EMF Stack

To play with this plugin you need to create a new workspace based on the "EMF Stack". You can still use most of the features on a different stack (i.e. the Java stack) but the code generation will be unavailable.

## Make it happen example

For an easy start you can import the *make it happen* example from the ECP repository. To do so select *Import Project... > GIT* using the URL ```http://git.eclipse.org/gitroot/emfclient/org.eclipse.emf.ecp.core.git```. We recommend to enable *Keep following directory* and enter *examples*.

## Run Code Generation

You can start the code generation manually via the terminal if desired. If you imported the *make it happen* example, the following command will generate the EMF model code into a new *code-gen* directory
```
/eclipse/eclipse -noSplash -data /eclipse/ws -application org.eclipse.emf.codegen.ecore.Generator -model /projects/org.eclipse.emf.ecp.core/examples/org.eclipse.emf.ecp.makeithappen.model/model/task.genmodel /projects/org.eclipse.emf.ecp.core/code-gen/org.eclipse.emf.ecp.makeithappen.model
```
If the generated directory (or subdirectories) are missing after the generation make sure to refresh the Project Explorer.

# Troubleshooting

## Build Errors

In the past the build sometimes failed because new development dependencies (for example *npm typings* or *go*) were introduced. Check the build log for errors complaining about missing commands. 

## Runtime Errors

The root cause of being unable to connect to the workspace or non starting workspace agents are often closed ports. Disable your firewall momentarily to test for these kind of errors.

# Contact
If you have any questions or run into trouble please feel free to create an issue.

If you are interested in sponsoring this project or building your own extension for Che feel free to [get in touch with us](mailto:munich@eclipsesource.com). Have fun!

The EclipseSource Team.
