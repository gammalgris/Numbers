@Echo off


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void main()
@rem ---
@rem ---   Creates a project structiure for maven.
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

	@rem copy all needed files

	call:copySources %sources[1]% %mavenDirectories[7]%
	call:copyResources %sources[2]% %mavenDirectories[8]%
	call:copyResources %sources[4]% %mavenDirectories[8]%
	
	call:copySources %sources[3]% %mavenDirectories[10]%

	call:copyLibraries %sources[5]% %mavenDirectories[4]%
	call:copyResources %sources[5]% %mavenDirectories[4]%

	@rem create the maven project files

	echo initialize maven settings ^(%mavenProjectFile%^)
	(
		echo ^<?xml version="1.0" encoding="UTF-8"?^>
		echo.
		echo ^<!--
		echo.
		echo  The file pom.xml was generated with NetBeans 25 and modified
		echo  with CoPilot.
		echo.
		echo --^>
		echo.
		echo ^<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd"^>
		echo   ^<modelVersion^>4.0.0^</modelVersion^>
		echo   ^<groupId^>jmul^</groupId^>
		echo   ^<artifactId^>Numbers^</artifactId^>
		echo   ^<version^>1.0-SNAPSHOT^</version^>
		echo   ^<packaging^>jar^</packaging^>
		echo   ^<properties^>
		echo     ^<project.build.sourceEncoding^>UTF-8^</project.build.sourceEncoding^>
		@rem echo     ^<maven.compiler.release^>21^</maven.compiler.release^>
		echo     ^<maven.compiler.release^>8^</maven.compiler.release^>
		echo     ^<exec.mainClass^>jmul.math.numbers.Numbers^</exec.mainClass^>
		echo   ^</properties^>
		echo   ^<dependencies^>
		echo     ^<!-- Local static library --^>
		echo     ^<dependency^>
		echo       ^<groupId^>jmul^</groupId^>
		echo       ^<artifactId^>Utilities^</artifactId^>
		echo       ^<version^>2.1.2^</version^>
		echo     ^</dependency^>
		echo     ^<dependency^>
		echo       ^<groupId^>junit^</groupId^>
		echo       ^<artifactId^>junit^</artifactId^>
		echo       ^<version^>4.11^</version^>
		echo       ^<scope^>test^</scope^>
		echo     ^</dependency^>
		echo   ^</dependencies^>
		echo   ^<build^>
		echo     ^<resources^>
		echo       ^<!-- Include local config files --^>
		echo       ^<resource^>
		echo         ^<directory^>${project.basedir}/libs/Jmul/properties^</directory^>
		echo         ^<targetPath^>config^</targetPath^>
		echo         ^<includes^>
		echo           ^<include^>**/*.*^</include^>
		echo         ^</includes^>
		echo       ^</resource^>
		echo       ^<resource^>
		echo         ^<directory^>src/main/resources^</directory^>
		echo         ^<includes^>
		echo           ^<include^>**/*^</include^>
		echo         ^</includes^>
		echo       ^</resource^>
		echo     ^</resources^>
		echo     ^<testResources^>
		echo       ^<!-- Add properties to test classpath --^>
		echo       ^<testResource^>
		echo         ^<directory^>${project.basedir}/libs/Jmul/properties^</directory^>
		echo         ^<targetPath^>config^</targetPath^>
		echo         ^<includes^>
		echo           ^<include^>**/*.*^</include^>
		echo         ^</includes^>
		echo       ^</testResource^>
		echo       ^<testResource^>
		echo         ^<directory^>src/main/resources^</directory^>
		echo         ^<includes^>
		echo           ^<include^>**/*^</include^>
		echo         ^</includes^>
		echo       ^</testResource^>
		echo     ^</testResources^>
		echo     ^<plugins^>
		echo       ^<plugin^>
		echo         ^<groupId^>org.apache.maven.plugins^</groupId^>
		echo         ^<artifactId^>maven-compiler-plugin^</artifactId^>
		echo          ^<version^>3.10.1^</version^>
		echo         ^<configuration^>
		echo           ^<source^>1.8^</source^>
		echo           ^<target^>1.8^</target^>
		echo         ^</configuration^>
		echo       ^</plugin^>
		echo     ^</plugins^>
		echo   ^</build^>
		echo ^</project^>
	) > "%mavenProjectFile%"

	@rem initialize the maven project
	
	call mvn install:install-file -Dfile=libs/Jmul/jmul-2.1.2.jar -Dsources=libs/Jmul/jmul-2.1.2-Sources.jar -Djavadoc=libs/Jmul/jmul-2.1.2-JavaDoc.jar -DgroupId=jmul -DartifactId=Utilities -Dversion=2.1.2 -Dpackaging=jar -DgeneratePom=true
	%ifError% (
	
		%return% 5
	)

	call mvn validate
	%ifError% (
	
		%return% 6
	)

	call mvn clean install
	%ifError% (
	
		%return% 5
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

	set mavenDirectories.length=11

	set mavenDirectories[1]=%tempDir%
	set mavenDirectories[2]=%tempDir%Numbers\
	set mavenDirectories[3]=%tempDir%Numbers\libs\
	set mavenDirectories[4]=%tempDir%Numbers\libs\Jmul\
	set mavenDirectories[5]=%tempDir%Numbers\src\
	set mavenDirectories[6]=%tempDir%Numbers\src\main\
	set mavenDirectories[7]=%tempDir%Numbers\src\main\java\
	set mavenDirectories[8]=%tempDir%Numbers\src\main\resources\
	set mavenDirectories[9]=%tempDir%Numbers\src\test\
	set mavenDirectories[10]=%tempDir%Numbers\src\test\java\
	set mavenDirectories[11]=%tempDir%Numbers\src\test\resources\

	set sources.length=5

	set sources[1]=%projectDir%Numbers\src\
	set sources[2]=%projectDir%Numbers\properties\
	set sources[3]=%projectDir%Numbers-Tests\src\
	set sources[4]=%projectDir%Version\properties\
	set sources[5]=%rootDir%Libraries\Jmul\

	set mavenProjectFile=%mavenDirectories[2]%pom.xml

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
