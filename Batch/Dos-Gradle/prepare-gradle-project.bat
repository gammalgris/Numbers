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

	call:copySources %sources[1]% %gradleDirectories[6]%
	call:copyResources %sources[2]% %gradleDirectories[7]%
	call:copyResources %sources[4]% %gradleDirectories[7]%
	
	call:copySources %sources[3]% %gradleDirectories[9]%

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

	set directories.length=10

	set gradleDirectories[1]=%tempDir%
	set gradleDirectories[2]=%tempDir%Numbers\
	set gradleDirectories[3]=%tempDir%Numbers\lib\
	set gradleDirectories[4]=%tempDir%Numbers\lib\src\
	set gradleDirectories[5]=%tempDir%Numbers\lib\src\main\
	set gradleDirectories[6]=%tempDir%Numbers\lib\src\main\java\
	set gradleDirectories[7]=%tempDir%Numbers\lib\src\main\resources\
	set gradleDirectories[8]=%tempDir%Numbers\lib\src\test\
	set gradleDirectories[9]=%tempDir%Numbers\lib\src\test\java\
	set gradleDirectories[10]=%tempDir%Numbers\lib\src\test\resources\

	set sources.length=4

	set sources[1]=%projectDir%Numbers\src\
	set sources[2]=%projectDir%Numbers\properties\
	set sources[3]=%projectDir%Numbers-Tests\src\
	set sources[4]=%projectDir%Version\properties\

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


	echo xcopy /S /Y "%_sourcePath%\*.java" "%_destinationPath%"
	xcopy /S /Y "%_sourcePath%\*.java" "%_destinationPath%"
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


	echo xcopy /S /Y "%_sourcePath%\*.properties" "%_destinationPath%"
	xcopy /S /Y "%_sourcePath%\*.prperties" "%_destinationPath%"
	echo.


	set _sourcePath
	set _destinationPath

%return%
