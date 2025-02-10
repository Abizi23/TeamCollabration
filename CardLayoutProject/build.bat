@echo off
echo Compiling Java files...

:: Create the output directory if it doesn't exist
if not exist out (
    mkdir out
)

:: Compile all .java files in the src\main\java\cms directory
javac -d out src\main\java\cms\*.java

:: Check if the compilation was successful
if errorlevel 1 (
    echo Compilation failed. Exiting.
    exit /b
)

echo Compilation successful.

:: Run the program
echo Running Contact Manager...
java -cp out cms.ContactManager
