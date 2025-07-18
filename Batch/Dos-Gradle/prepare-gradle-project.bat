@Echo off


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void main()
@rem ---
@rem ---   Creates a project structiure for gradle.
@rem ---

:main

	call:defineMacros
	call:resetErrorlevel
	call:defineConstants

	@rem create the directory structure

	echo create root directory...
	call:createDirectory %tempDir% %TRUE%
	%ifError% (
	
		%return% 3
	)

	echo change to root directory
	cd /D %tempDir%
	%ifError% (

		%return% 4
	)


	call:createDirectory Numbers %TRUE%
	cd Numbers
	call:createDirectory lib %TRUE%
	cd lib
	call:createDirectory libs %TRUE%
	cd libs
	call:createDirectory Jmul %TRUE%
	cd ..
	call:createDirectory src %TRUE%
	cd src
	call:createDirectory main %TRUE%
	cd main
	call:createDirectory java %TRUE%
	call:createDirectory resources %TRUE%
	cd ..
	call:createDirectory test %TRUE%
	cd test
	call:createDirectory java %TRUE%
	call:createDirectory resources %TRUE%
	cd ..
	cd ..
	cd ..

	@rem copy all needed files

	call:copySources %sources[1]% %gradleDirectories[8]%
	call:copyResources %sources[2]% %gradleDirectories[9]%
	call:copyResources %sources[4]% %gradleDirectories[9]%
	
	call:copySources %sources[3]% %gradleDirectories[11]%

	call:copyLibraries %sources[5]% %gradleDirectories[5]%
	call:copyResources %sources[5]% %gradleDirectories[5]%

	@rem create the gradle project files

	@rem call:deleteFile %gradleSettingsFile%
	@rem call:deleteFile %gradleBuildFile%

	echo initialize gradle settings ^(%gradleSettingsFile%^)
	(
		echo /*
		echo  * The file settings.gradle was generated with NetBeans 25.
		echo  */
		echo.
		echo /*
		echo  * This file was generated by the Gradle 'init' task.
		echo  *
		echo  * The settings file is used to specify which projects to include in your build.
		echo  * For more detailed information on multi-project builds, please refer to https://docs.gradle.org/8.10/userguide/multi_project_builds.html in the Gradle documentation.
		echo  */
		echo.
		echo plugins {
		echo     // Apply the foojay-resolver plugin to allow automatic download of JDKs
		echo     id 'org.gradle.toolchains.foojay-resolver-convention' version '0.8.0'
		echo }
		echo.
		echo rootProject.name ^= 'Numbers'
		echo include^('lib'^)
	) > "%gradleSettingsFile%"


	@rem https://docs.gradle.org/current/samples/sample_building_java_applications.html
	@rem https://docs.gradle.org/current/samples/sample_building_java_libraries.html
	@rem https://discuss.gradle.org/t/how-to-link-a-static-library-in-build-gradle-file-for-a-nativelibraryspec/29205

	echo initialize gradle buildfile ^(%gradleBuildFile%^)
	(
		echo /*
		echo  * The file gradle.build was generated with CoPilot.
		echo  */
		echo.
		echo plugins {
		echo     id 'java'
		echo }
		echo.
		@rem echo // Load project version from properties file
		@rem echo def versionProps ^= new Properties^(^)
		@rem echo file^('src/main/resources/numbers_version.properties'^).withInputStream { stream -^>
		@rem echo     versionProps.load^(stream^)
		@rem echo }
		@rem echo project.version = versionProps.getProperty^('version'^)
		echo project.version = '1.0.0'
		echo.
		echo group ^= 'jmul.math.numbers'
		echo.
		echo.
		echo java {
		echo     sourceCompatibility ^= JavaVersion.VERSION_1_8
		echo     targetCompatibility ^= JavaVersion.VERSION_1_8
		echo }
		echo.
		echo repositories {
		echo     mavenCentral^(^)
		echo     flatDir {
		echo         dirs 'libs/Jmul'
		echo     }
		echo }
		echo.
		echo dependencies {
		echo     implementation name: 'jmul-2.1.2'
		echo.
		echo     testImplementation 'junit:junit:4.11'
		echo }
	) > "%gradleBuildFile%"

	@rem initialize the gradle project
	
	call gradle.bat wrapper
	%ifError% (
	
		%return% 5
	)
	
	call gradlew.bat build
	%ifError% (
	
		%return% 6
	)

%return%

@rem ================================================================================
@rem ===
@rem ===   Internal Subroutines
@rem ===

@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void defineMacros()
@rem ---
@rem ---   The subroutine defines required macros.
@rem ---

:defineMacros

	set "ifError=set foundErr=1&(if errorlevel 0 if not errorlevel 1 set foundErr=)&if defined foundErr"
	
	set "cprintln=echo"
	set "cprint=echo|set /p="
	
	set "return=exit /b"

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void resetErrorlevel()
@rem ---
@rem ---   The subroutine resets the errorlevel.
@rem ---

:resetErrorlevel

%return% 0

@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void defineConstants()
@rem ---
@rem ---   Declares and initializes variables and constants.
@rem ---

:defineConstants

	set TRUE=1
	set FALSE=0

	set rootDir=%~dp0..\..\
	set projectDir=%rootDir%Sources\

	set tempDir=%rootDir%tmp\

	set gradleDirectories.length=12

	set gradleDirectories[1]=%tempDir%
	set gradleDirectories[2]=%tempDir%Numbers\
	set gradleDirectories[3]=%tempDir%Numbers\lib\
	set gradleDirectories[4]=%tempDir%Numbers\lib\libs\
	set gradleDirectories[5]=%tempDir%Numbers\lib\libs\Jmul\
	set gradleDirectories[6]=%tempDir%Numbers\lib\src\
	set gradleDirectories[7]=%tempDir%Numbers\lib\src\main\
	set gradleDirectories[8]=%tempDir%Numbers\lib\src\main\java\
	set gradleDirectories[9]=%tempDir%Numbers\lib\src\main\resources\
	set gradleDirectories[10]=%tempDir%Numbers\lib\src\test\
	set gradleDirectories[11]=%tempDir%Numbers\lib\src\test\java\
	set gradleDirectories[12]=%tempDir%Numbers\lib\src\test\resources\

	set sources.length=5

	set sources[1]=%projectDir%Numbers\src\
	set sources[2]=%projectDir%Numbers\properties\
	set sources[3]=%projectDir%Numbers-Tests\src\
	set sources[4]=%projectDir%Version\properties\
	set sources[5]=%rootDir%Libraries\Jmul\

	set gradleSettingsFile=%gradleDirectories[2]%settings.gradle
	set gradleBuildFile=%gradleDirectories[3]%build.gradle

%return%

@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void createDirectory(String _path, boolean _clean)
@rem ---
@rem ---   The subroutine creates a directory according to the specified path.
@rem ---
@rem ---
@rem ---   @param _path
@rem ---          a directory
@rem ---   @param _clean
@rem ---          a flag which indicates that the directory's content should be
@rem ---          deleted
@rem ---

:createDirectory

	set "_path=%1"
	if '%_path%'=='' (

		echo No directory has been specified! >&2
		%return% 2
	)
	set "_path=%_path:"=%"

	set "_clean=%2"
	if '%_clean%'=='' (

		echo No flag has been specified! >&2
		%return% 2
	)
	set "_clean=%_clean:"=%"


	if exist "%_path%" (

		if %_clean%==%FALSE% (

			%return%
		)
	)


	if exist "%_path%" (

		rmdir /S /Q "%_path%" >nul 2>&1
	)

	if exist "%_path%" (

		%cprintln% Error: Unable to delete the directory "%_path%"! >&2
		%return% 2
	)

	mkdir "%_path%" >nul 2>&1

	if not exist "%_path%" (

		%cprintln% Error: Unable to create the directory "%_path%"! >&2
		%return% 2
	)


	set _path=
	set _clean=

%return%

@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void copySources(String _sourcePath, String _destinationPath)
@rem ---
@rem ---   The subroutine copies the specified source files to the destination path.
@rem ---
@rem ---
@rem ---   @param _sourcePath
@rem ---          a source directory
@rem ---   @param _destinationPath
@rem ---          a destination path
@rem ---

:copySources

	set "_sourcePath=%1"
	if '%_sourcePath%'=='' (

		echo No source directory has been specified! >&2
		%return% 2
	)
	set "_sourcePath=%_sourcePath:"=%"

	set "_destinationPath=%2"
	if '%_destinationPath%'=='' (

		echo No destination path has been specified! >&2
		%return% 2
	)
	set "_destinationPath=%_destinationPath:"=%"


	echo xcopy /S /Y "%_sourcePath%*.java" "%_destinationPath%"
	xcopy /S /Y "%_sourcePath%*.java" "%_destinationPath%"
	echo.


	set _sourcePath
	set _destinationPath

%return%

@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void copyResources(String _sourcePath, String _destinationPath)
@rem ---
@rem ---   The subroutine copies the specified resource files to the destination path.
@rem ---
@rem ---
@rem ---   @param _sourcePath
@rem ---          a source directory
@rem ---   @param _destinationPath
@rem ---          a destination path
@rem ---

:copyResources

	set "_sourcePath=%1"
	if '%_sourcePath%'=='' (

		echo No source directory has been specified! >&2
		%return% 2
	)
	set "_sourcePath=%_sourcePath:"=%"

	set "_destinationPath=%2"
	if '%_destinationPath%'=='' (

		echo No destination path has been specified! >&2
		%return% 2
	)
	set "_destinationPath=%_destinationPath:"=%"


	echo xcopy /S /Y "%_sourcePath%*.properties" "%_destinationPath%"
	xcopy /S /Y "%_sourcePath%*.properties" "%_destinationPath%"
	echo.


	set _sourcePath
	set _destinationPath

%return%

@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void copyLibraries(String _sourcePath, String _destinationPath)
@rem ---
@rem ---   The subroutine copies the specified library files to the destination path.
@rem ---
@rem ---
@rem ---   @param _sourcePath
@rem ---          a source directory
@rem ---   @param _destinationPath
@rem ---          a destination path
@rem ---

:copyLibraries

	set "_sourcePath=%1"
	if '%_sourcePath%'=='' (

		echo No source directory has been specified! >&2
		%return% 2
	)
	set "_sourcePath=%_sourcePath:"=%"

	set "_destinationPath=%2"
	if '%_destinationPath%'=='' (

		echo No destination path has been specified! >&2
		%return% 2
	)
	set "_destinationPath=%_destinationPath:"=%"


	echo xcopy /S /Y "%_sourcePath%*.jar" "%_destinationPath%"
	xcopy /S /Y "%_sourcePath%*.jar" "%_destinationPath%"
	echo.


	set _sourcePath
	set _destinationPath

%return%

@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void deleteFile(String _path)
@rem ---
@rem ---   Deletes the specified file.
@rem ---
@rem ---
@rem ---   @param _path
@rem ---          the specified path represents a file
@rem ---

:deleteFile

	set "_path=%1"
	if '%_path%'=='' (

		echo No file path has been specified! >&2
		%return% 2
	)
	set "_path=%_path:"=%"


	if exist %_path% (

		echo deleting %_path%
		del /S /Q %_path%

	) else (

		echo %_path% doesn't exist.
	)


	set _path=

%return%
