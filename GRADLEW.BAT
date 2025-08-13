@echo off
set DIRNAME=%~dp0
set APP_HOME=%DIRNAME%
"%JAVA_HOME%\bin\java.exe" -jar "%APP_HOME%\gradle\wrapper\gradle-wrapper.jar" %*
