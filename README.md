# EMF4Che
This plugin adds support for the Eclipse Modeling Framework (EMF) in Eclipse Che. 

Currently it is in early alpha state, there is support for viewing .ecore files and running the EMF code generator. Please have a look at the open issues to get a better impression of the current state.

## Architecture

To include EMF4Che in Eclipse Che it must be integrated into its maven build. Instead of doing this manually we re-use the maven build of Eclipse Che and integrate our plugin and Eclipse Che modifications where necessary. This results in a slightly more complex setup but is more convenient to use and takes a lot less time to compile.

Of course you can still integrate EMF4Che manually if you want to. For this you need to copy the plugin folder of the EMF4Che repository into the plugins directory of the Che repository and modify its maven build to include EMF4Che.

# Build
We build the EMF4Che plugin together with a customized Eclipse Che and the EMF stack using an automated maven build.

## Prerequisites
 * You need to be able to run Eclipse Che on your system. Follow this [Getting Started Guide](https://www.eclipse.org/che/docs/setup/getting-started/index.html) and [Configuration Guide](https://www.eclipse.org/che/docs/setup/configuration/index.html) if you did not already do so and make sure you can start Eclipse Che, create and use workspaces etc. Do not forget to stop Eclipse Che again.
  * You need to be able to build Eclipse Che on your system. Make sure you have [all software listed here](https://github.com/eclipse/che/wiki/Development-Workflow#dependencies)  installed in the correct version.

## Get the code
First you have to clone this repository.
```
git clone git@github.com:eclipsesource/emf4che.git
```

## Create the EMF stack

To use the Code Generation we require a custom stack. We prepared a Dockerfile and a helper script to build our stack image. You can find it under */plugins/plugin-emf/emf-stack*.

All you need to do is run the *build.sh* script.

## Build

Finally we need to build Eclipse Che and include the EMF Stack and the EMF4Che plugin. To do so switch to the root directory of the repository and run the following command.
```
mvn clean install
```

For a faster build you can also run ```mvn -T 1C -DskipTests -Dskip-validate-sources clean install```

# Run

Next we want to start the customized Eclipse Che we built above. For this we need to use the [Eclipse Che CLI](https://www.eclipse.org/che/docs/setup/getting-started/index.html#volume-mounts). Use the following command and replace the `<>` tags with your local paths and version.
```bash
docker run -it --rm -v /var/run/docker.sock:/var/run/docker.sock
                    -v <path-to-emf4che-repo>:/repo
                    -v <path-to-local-data>:/data
                       eclipse/che:<version> start
```

This is how the command looks like for our testuser setup: ```docker run -it --rm -v /var/run/docker.sock:/var/run/docker.sock -v /home/testuser/git/emf4che:/repo -v /home/testuser/che-data/:/data eclipse/che:5.5.0 start```

Make sure to use an empty data directory otherwise Eclipse Che might complain or not properly include the customizations.

## EMF Stack

To play with this plugin you need to create a new workspace based on the "EMF Stack". You can still use most of the features on a different stack (i.e. the Java stack) but the code generation will be unavailable.

## Make it happen example

When creating a workspace you can already choose "emfforms-makeithappen" as a template and it will be included automatically. If your workspace is already running you can select "Workspace > Create Project..." and choose "emfforms-makeithappen" in the samples category. 

## Run Code Generation

We predefined two commands "Generate Model Code" and "Generate Edit Code" which will create the model and edit code for the "Make it happen" example. You can inspect and run them within the "CMD" dropdown of Eclipse Che.

You can also start the code generation manually via the terminal (or create your own commands) if desired. Use the following command and replace the `<>` with your local paths.
```
/eclipse/eclipse -noSplash -data <path-to-your-project> -application org.eclipse.emf.codegen.ecore.Generator -model <path-to-your-genmodel>
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
