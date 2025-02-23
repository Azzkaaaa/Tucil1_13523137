@echo off
setlocal

:: Create output directory if it doesn't exist
if not exist bin mkdir bin

:: Find all Java files and compile
dir src\*.java > sources.txt
javac -d bin -sourcepath src @sources.txt
del sources.txt

if %ERRORLEVEL% neq 0 (
    echo Compilation failed.
    exit /b %ERRORLEVEL%
)

echo Build complete!

endlocal
