DROP DATABASE IF EXISTS SuperHeroSightingsTest;
CREATE DATABASE  SuperHeroSightingsTest; 

USE SuperHeroSightingsTest; 

CREATE TABLE Person (
PersonID INT NOT NULL AUTO_INCREMENT, 
PersonName VARCHAR(50) NOT NULL,
Descrip VARCHAR(50) NOT NULL, 
IsHero BIT NULL,

PRIMARY KEY (PersonID)
);

CREATE TABLE Superpower (
SuperpowerID INT NOT NULL AUTO_INCREMENT, 
SuperpowerName VARCHAR(50) NOT NULL,

PRIMARY KEY (SuperpowerID)
);

CREATE TABLE PersonSuperpower (
PersonSuperpowerID INT NOT NULL AUTO_INCREMENT, 
PersonID INT NOT NULL, 
SuperpowerID INT NOT NULL,

PRIMARY KEY (PersonSuperpowerID)
); 

ALTER TABLE PersonSuperpower 
ADD CONSTRAINT FK_Person_PersonSuperpower
FOREIGN KEY (PersonID) REFERENCES Person(PersonID),
ADD CONSTRAINT FK_Superpower_PersonSuperpower
FOREIGN KEY (SuperpowerID) REFERENCES Superpower(SuperpowerID);

CREATE TABLE Org (
OrgID INT NOT NULL AUTO_INCREMENT, 
OrgName VARCHAR(50) NOT NULL, 
ContactInfo VARCHAR(50) NOT NULL,
IsHeroOrg BIT, 

PRIMARY KEY (OrgID)
);

CREATE TABLE PersonOrganization (
PersonOrganizationID INT NOT NULL AUTO_INCREMENT,
PersonID INT NOT NULL,
OrgID INT NOT NULL,

PRIMARY KEY (PersonOrganizationID)
); 

ALTER TABLE PersonOrganization
ADD CONSTRAINT FK_Person_PersonOrganization
FOREIGN KEY (PersonID) REFERENCES Person(PersonID), 
ADD CONSTRAINT FK_Org_PersonOrganization
FOREIGN KEY (OrgID) REFERENCES Org(OrgID);

CREATE TABLE Location (
LocationID INT NOT NULL AUTO_INCREMENT, 
LocationName VARCHAR(50) NOT NULL, 
Descrip VARCHAR(50) NOT NULL,
Address VARCHAR(50) NOT NULL,
Latitude DECIMAL(8,2) NOT NULL, 
Longitude DECIMAL(8,2) NOT NULL,

PRIMARY KEY (LocationID)
);

CREATE TABLE Sightings (
SightingsID INT NOT NULL AUTO_INCREMENT, 
SightingDate DATE NOT NULL, 
PersonID INT NOT NULL,
LocationID INT NOT NULL,

PRIMARY KEY (SightingsID)
); 

ALTER TABLE Sightings
ADD CONSTRAINT FK_Person_Sightings
FOREIGN KEY (PersonID) REFERENCES Person(PersonID),
ADD CONSTRAINT FK_Location_Sightings
FOREIGN KEY (LocationID) REFERENCES Location(LocationID); 


