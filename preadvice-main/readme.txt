This replaces the pre-advice-main-original. Its on github remote at second-sandpit.

Copied preadvicesplitter.xml and preadvicetransformer.xml to D:\spring\spring-xd-1.0.0.M4\xd\modules\processor

Started xdnode

xd:>stream create --name filereader --definition "file | preadvicesplitter | preadvicetransformer | log"
---------Correction-----------
Use
xd:>stream create --name filereader --definition "file --outputType=text/plain | preadvicesplitter | preadvicetransformer | log"
Otherwise we now get the raw bytes read from file and we end up with a file into the splitter which has something like: "48,51,44,48,51,44,90,90,49,50,51,52,53,54,55,56,57,71,66....."	


Next piece is a stream to process Track Scans
file:///D:/spring/spring-xd-1.0.0.M4/docs/index.html#jms

Copied trackedeventsplitter.xml and trackedeventtransformer.xml to D:\spring\spring-xd-1.0.0.M4\xd\modules\processor

xd:> stream create --name trackscans --definition "jms | trackedeventsplitter | trackedeventtransformer | scanprocessor | log"


Then create
	crosscheck tap
	billing tap


	
	
To Do:
Creating TrackedEventParser - Done
	Creating ProcessItemEvent object with ProcessedMailItem which has name/value pairs. - Done
	Need to change the ArrayList which holds a name value to a HashMap - this will eliminate need to have a name/value object - SimpleAttribute - Done

Test the event splitter and parser using a filereader to provide the inbound messages.
 xd:> stream create --name trackscans --definition "file --outputType=text/plain | trackedeventsplitter | trackedeventtransformer | log"
	- Done.

Add a tap on this to do some simple analytics
	- No of mail items per processing location

Create mail item event processor:
	Retrieve mail item by id (from mySQL db)
		Create datasource & sqlsession factory - done
		Create ibatis mappings interface for mail item (get and update) - refer to https://mybatis.github.io/spring/getting-started.html & https://mybatis.github.io/spring/factorybean.html	 
		Test individually (need to work out how to set-up the data in the db for the tests)
			Get - Done
			Update - Done
	Determine mail item status
		Create the status calculator (with config based scan model) - use jbehave to test it - Done. Config is currently in the status class. - Done
	Write main method of scan processor to call the get, determine status and update - Done
	Update to include address insert & updates.
	
	Run  a test by adding the eventprocessor to the end of a stream. e.g.
		(On mac its XD_HOME=/usr/local/Cellar/springxd/1.0.0.M4/libexec/xd)
		Create folders D:\spring\spring-xd-1.0.0.M4\xd\modules\processor\mailitemeventprocessor\config, lib
		Copy mailitemeventprocessor.xml to D:\spring\spring-xd-1.0.0.M4\xd\modules\processor\maileventprocessor\config
		Copy mybatis-spring-1.2.2.jar & mybatis-3.2.3.jar from the maven dependencies folder in eclipse to D:\spring\spring-xd-1.0.0.M4\xd\modules\processor\mailitemeventprocessor\lib
			- Problem. Doing this means the ibatis code can't find the mappers which are in the preadvice-main jar. So copy the preadvice main here solves this problem. 
			  In the end needed to move all these jars to the main lib folder of XD; otherwise we have two places we have to put the preadvice main jar (lots of possibilities for mismatches).
		Copy mysqldatabase.properties D:\spring\spring-xd-1.0.0.M4\xd\config
		Copy the latest preadvice-main-n.n.n.jar to D:\spring\spring-xd-1.0.0.M4\xd\lib
		xd:> stream create --name trackscans --definition "file --outputType=text/plain | trackedeventsplitter | trackedeventtransformer | mailitemeventprocessor | log"



Create a wiretap to store location in / out counts. 

Current Work:
	Need to complete the test for the ProcesItemEventTest.testSetValue().
	Doing the event processor (ibatis set-up etc...)


Running spring xd:

On windows, navigate to D:\spring\spring-xd-1.0.0.M4\xd\bin>
Run the xd-singlenode script.

Open a second command window and navigate to D:\spring\spring-xd-1.0.0.M4\xd\shell\bin
Run xd-shell script.
We're ready to create our streams.


Log4J and Running Stories.
	Add the following vm arg to the run config for the story junit java test class.
		-Dlog4j.debug
		-Dlog4j.configuration=log4j.xml	
	
	
Gradle
	https://github.com/spring-projects/eclipse-integration-gradle
	Followed Spring XD instructions to create a single node in eclipse ; http://docs.spring.io/autorepo/docs/spring-xd/1.0.0.M4/reference/html/#creating-a-source-module