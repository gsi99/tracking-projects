Narrative: In order to process events for mail items I would like to calculate the new status of a mail item when a new event is received and update the stored mail item accordingly.

Scenario:1: No mail item and PREADVICED event received

Given no existing mail item
When an event:<scanevent> is processed with uniqueitemid:<id>, accountnumber:<accountnumber>, location:<locationunitcode>, time from now in minutes:<timefromnowinminutes>, scannerid:<scannerid>, destination:<destinationpostcode>
Then the mail item is created with status:<newstatus>, previous status:<newpreviousstatus> and event code:<neweventcode>

Examples:
|eventcode|destinationpostcode|accountnumber|scanevent|locationunitcode|timefromnowinminutes|scannerid|id|newstatus|newpreviousstatus|neweventcode|
|null|BD21ET|0019433078|PREADVICED|LOC01|0|SCANNER01|mailitemid-100|PREADVICED|null|PREADVICED|


Scenario:2: Initial Status of null with ADVICED event

Given a mail item of <status>, <previousstatus>, <eventcode>, <widthmm>, <lengthmm>, <heightmm>, <sourcepostcode>, <destinationpostcode> and <accountnumber>
When a <scanevent> is processed with <accountnumber>, <locationunitcode>, <timefromnowinminutes> and <scannerid>
Then the mail item status is updated to <newstatus>, <newpreviousstatus> and <neweventcode>

Examples:
|status|previousstatus|eventcode|widthmm|lengthmm|heightmm|sourcepostcode|destinationpostcode|accountnumber|scanevent|locationunitcode|timefromnowinminutes|scannerid|newstatus|newpreviousstatus|neweventcode|
|null|null|null|10|20|5|W138UH|BD21ET|0019433078|ADVICED|LOC01|0|SCANNER01|ADVICED|null|ADVICED|