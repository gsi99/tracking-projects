Narrative: In order to process mail items I would like to calculate the new status of a mail item when a new event for the mail item is received.

Scenario:1: Initial Status of PREADVICED
Given a mail item at status null
When a PREADVICED scan event is processed
Then the mail item status is PREADVICED


Scenario:2: Initial Status of ADVICED
Given a mail item at status PREADVICED
When a ADVICED scan event is processed
Then the mail item status is ADVICED

Given a mail item at status null
When a ADVICED scan event is processed
Then the mail item status is ADVICED


Scenario:3: Status of OUTBOUND
Given a mail item at status null
When a RECEIVEDOUTBOUND scan event is processed
Then the mail item status is OUTWARDPROCESSING

Given a mail item at status ADVICED
When a RECEIVEDOUTBOUND scan event is processed
Then the mail item status is OUTWARDPROCESSING

Given a mail item at status OUTWARDPROCESSING
When a PROCESSED scan event is processed
Then the mail item status is OUTWARDPROCESSING


Scenario:4: Status of ONROUTE
Given a mail item at status OUTWARDPROCESSING
When a EXITOUTBOUND scan event is processed
Then the mail item status is ONROUTE

Given a mail item at status INWARDPROCESSING
When a EXITINBOUND scan event is processed
Then the mail item status is ONROUTE


Scenario:5: Status of INBOUND
Given a mail item at status ONROUTE
When a RECEIVEDINBOUND scan event is processed
Then the mail item status is INWARDPROCESSING

Given a mail item at status INWARDPROCESSING
When a PROCESSED scan event is processed
Then the mail item status is INWARDPROCESSING


Scenario:6: Status of at DELIVERY
Given a mail item at status ONROUTE
When a RECEIVEDATDELIVERY scan event is processed
Then the mail item status is RECEIVEDFORDELIVERY


Scenario:7: Status of being DELIVERED
Given a mail item at status RECEIVEDFORDELIVERY
When a EXITDELIVERY scan event is processed
Then the mail item status is BEINGDELIVERED


Scenario:8: Status of DELIVERED
Given a mail item at status BEINGDELIVERED
When a DELIVERED scan event is processed
Then the mail item status is DELIVERED
