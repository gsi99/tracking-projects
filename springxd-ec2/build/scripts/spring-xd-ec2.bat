@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  spring-xd-ec2 startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

@rem Add default JVM options here. You can also use JAVA_OPTS and SPRING_XD_EC_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windowz variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\config;%APP_HOME%\lib\spring-xd-ec2-1.0.jar;%APP_HOME%\lib\log4j-over-slf4j-1.7.5.jar;%APP_HOME%\lib\commons-collections-3.2.jar;%APP_HOME%\lib\jclouds-sshj-1.7.1.jar;%APP_HOME%\lib\jclouds-all-1.7.1.jar;%APP_HOME%\lib\spring-core-4.0.0.RELEASE.jar;%APP_HOME%\lib\spring-context-4.0.0.RELEASE.jar;%APP_HOME%\lib\spring-web-4.0.0.RELEASE.jar;%APP_HOME%\lib\spring-test-4.0.0.RELEASE.jar;%APP_HOME%\lib\logback-classic-1.0.13.jar;%APP_HOME%\lib\slf4j-api-1.7.5.jar;%APP_HOME%\lib\jsr311-api-1.1.1.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\asm-3.1.jar;%APP_HOME%\lib\cglib-2.2.1-v20090111.jar;%APP_HOME%\lib\guice-3.0.jar;%APP_HOME%\lib\guice-assistedinject-3.0.jar;%APP_HOME%\lib\rocoto-6.2.jar;%APP_HOME%\lib\jsr250-api-1.0.jar;%APP_HOME%\lib\gson-2.2.4.jar;%APP_HOME%\lib\guava-15.0.jar;%APP_HOME%\lib\jclouds-core-1.7.1.jar;%APP_HOME%\lib\jclouds-scriptbuilder-1.7.1.jar;%APP_HOME%\lib\jclouds-compute-1.7.1.jar;%APP_HOME%\lib\jclouds-slf4j-1.7.1.jar;%APP_HOME%\lib\bcprov-ext-jdk15on-1.49.jar;%APP_HOME%\lib\jclouds-bouncycastle-1.7.1.jar;%APP_HOME%\lib\sshj-0.8.1.jar;%APP_HOME%\lib\bcpkix-jdk15on-1.49.jar;%APP_HOME%\lib\openstack-keystone-1.7.1.jar;%APP_HOME%\lib\rackspace-cloudidentity-1.7.1.jar;%APP_HOME%\lib\openstack-cinder-1.7.1.jar;%APP_HOME%\lib\rackspace-clouddns-1.7.1.jar;%APP_HOME%\lib\sts-1.7.1.jar;%APP_HOME%\lib\cloudwatch-1.7.1.jar;%APP_HOME%\lib\aws-cloudwatch-1.7.1.jar;%APP_HOME%\lib\rackspace-cloudblockstorage-us-1.7.1.jar;%APP_HOME%\lib\rackspace-cloudblockstorage-uk-1.7.1.jar;%APP_HOME%\lib\rackspace-clouddns-us-1.7.1.jar;%APP_HOME%\lib\rackspace-clouddns-uk-1.7.1.jar;%APP_HOME%\lib\openstack-trove-1.7.1.jar;%APP_HOME%\lib\rackspace-clouddatabases-us-1.7.1.jar;%APP_HOME%\lib\rackspace-clouddatabases-uk-1.7.1.jar;%APP_HOME%\lib\sqs-1.7.1.jar;%APP_HOME%\lib\aws-sqs-1.7.1.jar;%APP_HOME%\lib\aws-sts-1.7.1.jar;%APP_HOME%\lib\route53-1.7.1.jar;%APP_HOME%\lib\aws-route53-1.7.1.jar;%APP_HOME%\lib\ultradns-ws-1.7.1.jar;%APP_HOME%\lib\dynect-1.7.1.jar;%APP_HOME%\lib\jclouds-loadbalancer-1.7.1.jar;%APP_HOME%\lib\rackspace-cloudloadbalancers-1.7.1.jar;%APP_HOME%\lib\rackspace-cloudloadbalancers-uk-1.7.1.jar;%APP_HOME%\lib\rackspace-cloudloadbalancers-us-1.7.1.jar;%APP_HOME%\lib\jclouds-allloadbalancer-1.7.1.jar;%APP_HOME%\lib\java-xmlbuilder-0.4.jar;%APP_HOME%\lib\vcloud-1.7.1.jar;%APP_HOME%\lib\greenhousedata-element-vcloud-1.7.1.jar;%APP_HOME%\lib\ec2-1.7.1.jar;%APP_HOME%\lib\aws-ec2-1.7.1.jar;%APP_HOME%\lib\openstack-nova-1.7.1.jar;%APP_HOME%\lib\openstack-nova-ec2-1.7.1.jar;%APP_HOME%\lib\snakeyaml-1.11.jar;%APP_HOME%\lib\byon-1.7.1.jar;%APP_HOME%\lib\openstack-common-1.7.1.jar;%APP_HOME%\lib\cloudservers-1.7.1.jar;%APP_HOME%\lib\cloudservers-us-1.7.1.jar;%APP_HOME%\lib\cloudservers-uk-1.7.1.jar;%APP_HOME%\lib\cloudsigma-1.7.1.jar;%APP_HOME%\lib\bluelock-vcloud-zone01-1.7.1.jar;%APP_HOME%\lib\gogrid-1.7.1.jar;%APP_HOME%\lib\elasticstack-1.7.1.jar;%APP_HOME%\lib\elastichosts-lon-p-1.7.1.jar;%APP_HOME%\lib\elastichosts-sat-p-1.7.1.jar;%APP_HOME%\lib\elastichosts-lon-b-1.7.1.jar;%APP_HOME%\lib\openhosting-east1-1.7.1.jar;%APP_HOME%\lib\serverlove-z1-man-1.7.1.jar;%APP_HOME%\lib\skalicloud-sdg-my-1.7.1.jar;%APP_HOME%\lib\cloudsigma-zrh-1.7.1.jar;%APP_HOME%\lib\cloudsigma-lvs-1.7.1.jar;%APP_HOME%\lib\go2cloud-jhb1-1.7.1.jar;%APP_HOME%\lib\softlayer-1.7.1.jar;%APP_HOME%\lib\cloudstack-1.7.1.jar;%APP_HOME%\lib\ninefold-compute-1.7.1.jar;%APP_HOME%\lib\hpcloud-compute-1.7.1.jar;%APP_HOME%\lib\rackspace-cloudservers-us-1.7.1.jar;%APP_HOME%\lib\rackspace-cloudservers-uk-1.7.1.jar;%APP_HOME%\lib\jclouds-allcompute-1.7.1.jar;%APP_HOME%\lib\jclouds-blobstore-1.7.1.jar;%APP_HOME%\lib\s3-1.7.1.jar;%APP_HOME%\lib\aws-s3-1.7.1.jar;%APP_HOME%\lib\atmos-1.7.1.jar;%APP_HOME%\lib\ninefold-storage-1.7.1.jar;%APP_HOME%\lib\cloudonestorage-1.7.1.jar;%APP_HOME%\lib\azure-common-1.7.1.jar;%APP_HOME%\lib\azureblob-1.7.1.jar;%APP_HOME%\lib\swift-1.7.1.jar;%APP_HOME%\lib\cloudfiles-1.7.1.jar;%APP_HOME%\lib\cloudfiles-us-1.7.1.jar;%APP_HOME%\lib\cloudfiles-uk-1.7.1.jar;%APP_HOME%\lib\filesystem-1.7.1.jar;%APP_HOME%\lib\hpcloud-objectstorage-1.7.1.jar;%APP_HOME%\lib\jclouds-allblobstore-1.7.1.jar;%APP_HOME%\lib\commons-logging-1.1.1.jar;%APP_HOME%\lib\spring-beans-4.0.0.RELEASE.jar;%APP_HOME%\lib\spring-aop-4.0.0.RELEASE.jar;%APP_HOME%\lib\spring-expression-4.0.0.RELEASE.jar;%APP_HOME%\lib\logback-core-1.0.13.jar

@rem Execute spring-xd-ec2
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %SPRING_XD_EC_OPTS%  -classpath "%CLASSPATH%" org.springframework.xd.ec2.Main %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable SPRING_XD_EC_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%SPRING_XD_EC_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
