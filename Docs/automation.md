
#Overview

The subdirectory 'Batch' contains various automation scripts. For windows
environments a script is provided to create a clean batch environment.

## Requirement(s)

A summary of requirements regarding build and test automation:

1) The automation should run independently of an IDE.

2) The automation should run isolated from an IDE so you can continue working
   with the IDE while the automation runs.

## Clean batch/ shell environment

### Windows environment

You have to update following batch files first. These declare new environment
variables which are needed to check and set up the environment.

* properties-java.bat
* properties-ant.bat
* properties-gradle.bat

Open a DOS console (see link). Set up the environment by calling one of the
following scripts:

* setEnv-Java-Ant.bat
* setEnv-Java-Gradle.bat


The scripts checks some dependencies and cleans the PATH environment variable.
You can check if the changes were retained by calling the script

* checkEnv.bat

These scripts are a necessity if you have several JVMs, JDKs/ SDKs installed on
your windows development machine.

### Linux environment

I never had a need for a similar script under linux/ unix environments since
there I only had one JVM, JDK/ SDK installation. Should there be more
installations then it's easier to just define environment variables that point
to the actual path or configure your shell environment.

## Clean the repository

Go to './Batch/Dos-Misc' and call the clean script. The script will clean all
temporary files created by the IDE, compile output, etc.. Cleaning the local
repository before checking in changes is recommended.

## ANT - Build, run tests and deploy

Various subdirectories start with the prefix 'Ant-*'. Go to the respective
subdirectory and invoke an ant script. The ant scripts are interdependent
but can be executed on their own.

To invoke an ant script just use following command:

> ant -f {script file}

The scripts have a defined default target which is executed.

There is a hierarchy regarding script execution:

* Common (for informational purposes and cotnains various definitions: paths,
  classpaths, etc.)
* Build (i.e. compile sources and tests)
* Execute (i.e. test execution)
* Deploy (i.e. generating a jar file)

## Gradle - Set up gradle project

Following steps describe how to set up a gradle project:

1) Start a batch console and go to the project subdirectory '.\Batch\'

2) Call the script setEnv-Java-Gradle.bat

3) Go to the subdirectory '.\Dos-Gradle\'

4) Call the script prepare-gradle-project.bat
   When the script is finished the console is in the gradle project directory.

The script creates the required directory structure and copies all required
files. Gradle project files are genereated. Gradle is used to generate a
wrapper according to the gradle project files and an initial build is
triggered.
