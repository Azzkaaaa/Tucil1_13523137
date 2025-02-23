@echo off
setlocal

if not exist bin mkdir bin

:: Pakai /S /B agar mencantumkan path lengkap
dir /S /B src\*.java > sources.txt

javac -d bin -sourcepath src @sources.txt

del sources.txt

if %ERRORLEVEL% neq 0 (
    echo Compilation failed.
    exit /b %ERRORLEVEL%
)

echo Build complete!
endlocal
