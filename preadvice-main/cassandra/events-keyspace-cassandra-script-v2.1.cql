DROP KEYSPACE IF EXISTS eventsdb;

CREATE KEYSPACE eventsdb WITH replication = {'class':'SimpleStrategy','replication_factor':2};

USE eventsdb;

CREATE TYPE address  (
  PropertyNumber VARCHAR,
  Street VARCHAR,
  Locality VARCHAR,
  Town VARCHAR,
  County VARCHAR,
  PostCode VARCHAR,
  Country VARCHAR
);

CREATE TYPE event (
  TimeStamp TIMESTAMP,
  EventCode VARCHAR,
  ScannerId VARCHAR,
  LocationUnitCode VARCHAR,
  Longitude FLOAT,
  Lattitude FLOAT
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
  addresses map<TEXT, address>
  events map<TIMESTAMP, event>
  PRIMARY KEY (UniqueItemId)
);

