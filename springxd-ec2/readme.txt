Added the spring-xd-ec2 project to eclipe and created a pom.xml based on the build.gradle file.
We can now build the spring-xd-ec2-1.0.jar file and add additional logging / or other changes to this and then drop it into the build lib dir.

5/5/2015 On Mac. Run as maven->install, fails with error on unit test. 'Unable to find wget command...'. 
Used 'brew install wget' to install the wget package. hmmm... This was not needed code generate the wget command (it doesn't use the variable in the code either). 
 

Then do the gradle build as per the README.md and then install the zip and run. On windows go to the eclipse dir and run:
	gradlew.bat distZip
	
Gotcha:
	I've edited the src/main/resources/xd-ec2.properties file to have the correct values. This is being added to the build jar and it seems to 
	override the values you set in the other xd-ec2.properties file.
	 	

See below: we get an authorisation error when we run the deploy script. We are able to connect to aws and create and start up an instance.
================================================================================================================================================

Need to set the aws authorisation policies for the parcels-user:
http://docs.aws.amazon.com/AWSEC2/latest/APIReference/api-error-codes.html#api-error-common-causes
http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/query-api-troubleshooting.html#unauthorized-operation
http://docs.aws.amazon.com/AWSEC2/latest/APIReference/ec2-api-permissions.html
http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/iam-policies-for-amazon-ec2.html
http://docs.aws.amazon.com/IAM/latest/UserGuide/ManagingPolicies.html
http://docs.aws.amazon.com/IAM/latest/UserGuide/Using_WorkingWithGroupsAndUsers.html

Latest: 1st July 2015. Added Group to Parcels user, gave group full ec2 access. Simulation now show user can run instance. Running run as->maven test in eclipse
gives error as below:
	Tests in error: 
  		testDeployment(org.springframework.xd.ec2.Ec2InstallerTest): The security group 'CentOS 7 -x86_64- with Updates HVM-7 2014-09-29-AutogenByAWSMP-1' does not exist in default VPC 'vpc-582afb3d'

Latest: 14th July 2015. Set the security group to default (this is what the default is in the US west 2 region). Also created a key pair for the EC2 region. 
Still getting an authorisation error
org.jclouds.rest.AuthorizationException: POST https://ec2.us-west-2.amazonaws.com/ HTTP/1.1 -> HTTP/1.1 401 Unauthorized
...
Caused by: org.jclouds.http.HttpResponseException: request: POST https://ec2.us-west-2.amazonaws.com/ HTTP/1.1  [Action=RunInstances
&ImageId=ami-c7d092f7
&InstanceType=t2.micro
&KeyName=parcels-dev
&MaxCount=1
&MinCount=1
&Placement.AvailabilityZone=us-west-2b
&SecurityGroup.1=default
&Signature=IvkvjuXfq6BlSAL4HpHIHKElaTs0xg1ygldACkPGHDo%3D
&SignatureMethod=HmacSHA256
&SignatureVersion=2
&Timestamp=2015-07-14T20%3A46%3A06.889Z
&UserData=IyEvYmluL2Jhc2gKc2V0ICt1CnNob3B0IC1zIHhwZ19lY2hvCnNob3B0IC1zIGV4cGFuZF9hbGlhc2VzCnVuc2V0IFBBVEggSkFWQV9IT01FIExEX0xJQlJBUllfUEFUSApmdW5jdGlvbiBhYm9ydCB7CiAgIGVjaG8gImFib3J0aW5nOiAkQCIgMT4mMgogICBleGl0IDEKfQpmdW5jdGlvbiBkZWZhdWx0IHsKICAgZXhwb3J0IFhEX0hPTUU9Ii9ob21lL2NlbnRvcy9zcHJpbmcteGQtMS4wLjIuQlVJTEQtU05BUFNIT1QiCiAgIHJldHVybiAkPwp9CmV4cG9ydCBQQVRIPS91c3IvdWNiL2JpbjovYmluOi9zYmluOi91c3IvYmluOi91c3Ivc2JpbgovZXRjL2luaXQuZC9yZWRpcy1zZXJ2ZXIgc3RhcnQKL2V0Yy9pbml0LmQvcmFiYml0bXEtc2VydmVyIHN0YXJ0Ci9ob21lL3VidW50dS9zdGFydFpvb0tlZXBlci5zaApleGl0ICQ/Cg%3D%3D
&Version=2012-06-01
&AWSAccessKeyId=AKIAJP2LC5IPI7TDUB6Q] failed with response: HTTP/1.1 401 Unauthorized
	at org.jclouds.aws.handlers.ParseAWSErrorFromXmlContent.handleError(ParseAWSErrorFromXmlContent.java:65)
	... 38 more

	



Use the policy simulator to check if the changes allow the user to perform RunInstances action - Done this (see above)
https://policysim.aws.amazon.com/home/index.jsp?#

Below is old:
D:\tmp\spring-xd-ec2-1.0>.\bin\spring-xd-ec2.bat
                 __   _______         ______ _____ ___    _____           _        _ _
                 \ \ / /  __ \       |  ____/ ____|__ \  |_   _|         | |      | | |
                  \ V /| |  | |______| |__ | |       ) |   | |  _ __  ___| |_ __ _| | | ___ _ __
                   > < | |  | |______|  __|| |      / /    | | | '_ \/ __| __/ _` | | |/ _ \ '__|
                  / . \| |__| |      | |___| |____ / /_   _| |_| | | \__ \ || (_| | | |  __/ |
                 /_/ \_\_____/       |______\_____|____| |_____|_| |_|___/\__\__,_|_|_|\___|_|
Using the following host to obtain XD Distribution: https://repo.spring.io/libs-snapshot-local/org/springframework/xd/spring-xd/1.0.
2.BUILD-SNAPSHOT/spring-xd-1.0.2.BUILD-SNAPSHOT-dist.zip


************************************************************************
*Deploying Admin Node
************************************************************************
Exception in thread "main" org.jclouds.rest.AuthorizationException: You are not authorized to perform this operation.
        at org.jclouds.aws.handlers.ParseAWSErrorFromXmlContent.refineException(ParseAWSErrorFromXmlContent.java:124)
	
To fix above added super user role to the parcels-dev user (although the simulation screen doesn't work). We can now run and we get a different error - see below
================================================================================================================================================
	Need to work out where the amid id of ami-a94349c0 comes from.

org.jclouds.rest.ResourceNotFoundException: The image id '[ami-a94349c0]' does not exist
	at org.jclouds.aws.handlers.ParseAWSErrorFromXmlContent.refineException(ParseAWSErrorFromXmlContent.java:110)
	at org.jclouds.aws.handlers.ParseAWSErrorFromXmlContent.handleError(ParseAWSErrorFromXmlContent.java:91)
	at org.jclouds.http.handlers.DelegatingErrorHandler.handleError(DelegatingErrorHandler.java:67)
	at org.jclouds.http.internal.BaseHttpCommandExecutorService.shouldContinue(BaseHttpCommandExecutorService.java:180)
	at org.jclouds.http.internal.BaseHttpCommandExecutorService.invoke(BaseHttpCommandExecutorService.java:150)
	at org.jclouds.rest.internal.InvokeHttpMethod.invoke(InvokeHttpMethod.java:93)
	at org.jclouds.rest.internal.InvokeHttpMethod.apply(InvokeHttpMethod.java:76)
	at org.jclouds.rest.internal.InvokeHttpMethod.apply(InvokeHttpMethod.java:47)
	at org.jclouds.reflect.FunctionalReflection$FunctionalInvocationHandler.handleInvocation(FunctionalReflection.java:117)
	at com.google.common.reflect.AbstractInvocationHandler.invoke(AbstractInvocationHandler.java:79)
	at com.sun.proxy.$Proxy64.runInstancesInRegion(Unknown Source)
	at org.springframework.xd.ec2.cloud.AWSInstanceProvisioner.runInstance(AWSInstanceProvisioner.java:90)
	at org.springframework.xd.ec2.cloud.AWSDeployer.deployAdminServer(AWSDeployer.java:211)
	at org.springframework.xd.ec2.cloud.AWSDeployer.deploy(AWSDeployer.java:162)
	at org.springframework.xd.ec2.Ec2InstallerTest.testDeployment(Ec2InstallerTest.java:73)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)
	at org.junit.internal.runners.statements.RunAfters.evaluate(RunAfters.java:27)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:309)
	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:50)
	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:467)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:683)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:390)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:197)
Caused by: org.jclouds.aws.AWSResponseException: request POST https://ec2.us-west-2.amazonaws.com/ HTTP/1.1 failed with code 400, error: AWSError{requestId='a4127aff-12d1-4ce1-8931-c29dc6613e16', requestToken='null', code='InvalidAMIID.NotFound', message='The image id '[ami-a94349c0]' does not exist', context='{Response=, Errors=}'}
	at org.jclouds.aws.handlers.ParseAWSErrorFromXmlContent.handleError(ParseAWSErrorFromXmlContent.java:77)
	... 38 more


Script created is below (AWSDeployer.deployAdminServer() )
================================================================================================================================================
#!/bin/bash
set +u
shopt -s xpg_echo
shopt -s expand_aliases
unset PATH JAVA_HOME LD_LIBRARY_PATH
function abort {
   echo "aborting: $@" 1>&2
   exit 1
}
function default {
   export XD_HOME="/home/ubuntu/spring-xd-1.0.2.BUILD-SNAPSHOT"
   return $?
}
export PATH=/usr/ucb/bin:/bin:/sbin:/usr/bin:/usr/sbin
/etc/init.d/redis-server start
/etc/init.d/rabbitmq-server start
/home/ubuntu/startZooKeeper.sh
exit $?


================================================================================================================================================
Commented out checking for rddis, rabbitmq and zookeeper. Deploy code seems to assume they will be already installed? Need to check if their is any installation script that should have run : possibly the use of /home/ubuntu in various directory names means they were never installed?

org.springframework.xd.ec2.cloud.AWSDeployer.sshCopy(File, String) - this copying a jar file up to the server. Need to check if the jar file is available (and where it is stored locally).
