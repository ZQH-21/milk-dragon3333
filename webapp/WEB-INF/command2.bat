@echo off
setlocal

if exist classes rmdir /s /q classes
mkdir classes

javac -classpath "G:\Tomcat\lib\servlet-api.jar;classes;." -d classes src\model\*.java
if errorlevel 1 exit /b 1

javac -classpath "G:\Tomcat\lib\servlet-api.jar;classes;." -d classes src\store\*.java
if errorlevel 1 exit /b 1

javac -classpath "G:\Tomcat\lib\servlet-api.jar;classes;." -d classes src\controller\*.java
if errorlevel 1 exit /b 1

exit /b 0
