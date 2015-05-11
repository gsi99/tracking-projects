DROP KEYSPACE IF EXISTS eventsdb;

CREATE KEYSPACE eventsdb WITH replication = {'class':'SimpleStrategy','replication_factor':2};

USE eventsdb;

CREATE TABLE address  (
  PropertyNumber VARCHAR,
  Street VARCHAR,
  Locality VARCHAR,
  Town VARCHAR,
  County VARCHAR,
  PostCode VARCHAR,
  Country VARCHAR,
  PRIMARY KEY (PropertyNumber, Street, PostCode)
);

CREATE TABLE event (
  EventCode VARCHAR,
  TimeStamp TIMESTAMP,
  ScannerId VARCHAR,
  LocationUnitCode VARCHAR,
  Longitude FLOAT,
  Lattitude FLOAT,
  PRIMARY KEY (TimeStamp, EventCode)
);

CREATE TABLE MailItem (
  UniqueItemId VARCHAR,
  Status VARCHAR,
  PreviousStatus VARCHAR,
  EventCode VARCHAR,
  WeightInGrammes INT,
  LengthInMillimetres INT,
  WidthInMillimetres INT,
  HeightInMillimetres INT,
  DeclaredLengthInMillimetres INT,
  DeclaredWidthInMillimetres INT,
  DeclaredHeightInMillimetres INT,
  ShapeType VARCHAR,
  DestinationPostCode VARCHAR,
  SourcePostCode VARCHAR,
  CustomerAccountNumber VARCHAR
  PRIMARY KEY (UniqueItemId)
);

CREATE TABLE events_by_mailitem (
  UniqueItemId VARCHAR,
  EventCode VARCHAR,
  TimeStamp TIMESTAMP,
  ScannerId VARCHAR,
  LocationUnitCode VARCHAR,
  Longitude FLOAT,
  Lattitude FLOAT,
  PRIMARY KEY (UniqueItemId, TimeStamp, EventCode)
) WITH CLUSTERING ORDER BY (TimeStamp DESC, EventCode ASC);

CREATE TABLE addresses_by_mailitem (
  UniqueItemId VARCHAR,
  PropertyNumber VARCHAR,
  Street VARCHAR,
  Locality VARCHAR,
  Town VARCHAR,
  County VARCHAR,
  PostCode VARCHAR,
  Country VARCHAR,
  PRIMARY KEY (performer, year, title)
) WITH CLUSTERING ORDER BY (PostCode ASC, Street ASC, PropertyNumber ASC);
