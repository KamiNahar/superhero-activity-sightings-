Use SuperHeroSightings;



INSERT INTO Location (LocationName, LocationDescription, LocationAddress, LocationLatitude, LocationLongitude)
VALUES ('Whole Foods', 'Grocery store with a cafe and lots of crazies.', '123-45 Elk St', 12.3009988, 10.122334),
('Starbucks', 'Cafe that sells awful coffee for ridiculous prices.', '12 Lana St', 34.9090909, 7.837383),
('La Nueva Bakery', 'Queens bakery that serves the best empanadas in town', '34-10 12th St', 45.787979, 89.494994),
('Bohemian Beer Garden', 'Outdoor beer garden ', '45-12 Crescent St', 34.3746464,  12.354646),
('Queens Tea Room', 'Cute little dive bar, free wifi and lots of outlets', '12-886 Ditmars Ave', 25.8776896, 12.858474),
('Museum of Illusions', 'Museum that displays trippy artwork', '23-14 Detroit st', 57.98484774, 65.74747447),
('Sephora', 'Make-up store with nice workers', '12-73 Broadway st', 45.88888890, 89.74736833);

SELECT * FROM Location;


INSERT INTO Person (PersonName, PersonDescription, IsHero)
VALUES ('Wolverine', 'Mutant self healing powers ', 1),
('Batman', 'Dark Knight', 1),
('Thor', 'God of Thunder', 1),
('Bane', 'Batmans enemy', 0),
('Daredevil', ' has radar senses', 1),
('Storm', 'mutant can control the weather', 1),
('Spiderman', 'has spider like powers', 1),
('MichaelAngelo', 'masked turtles, uses nunchucks', 1),
('Iron Man', 'engineering superhero', 1);

SELECT * FROM Person;

INSERT INTO Superpower (SuperpowerName)
VALUES('Psychokinesis'),
('Telepathy'),
('Invisibility'),
('Flight'),
('Fire'),
('Healing powers'),
('Immortality'),
('Superhuman speed'),
('Breath underwater'),
('Superhuman Strength');

SELECT * FROM Superpower;

INSERT INTO PersonSuperpower (PersonID, SuperpowerID)
VALUES (1, 6),
(1, 7),
(2,4),
(3,9),
(5, 10),
(4, 10),
(6, 4);

SELECT * FROM PersonSuperpower;


INSERT INTO Org(OrgName, OrgContactInfo, OrgDescription, IsHeroOrg)
VALUES ('X-Men', '555-213-4567', 'group of mutants', 1),
('Justice League of America', '555-123-6748', 'Fight to save America from Aliens', 1),
('Marvel comics', '555-343-3334', 'well-known group of superheroes', 1),
('Avengers', '555-464-8348', ' first defense group against the most powerful threats in the universe', 1),
('Ninja Turtles', '555-345-4567', 'fighting turtles', 1);

SELECT * FROM Org;

INSERT INTO PersonOrganization(PersonID, OrganizationID)
VALUES (1,1),
(3, 4),
(8,6),
(9, 5),
(2, 3),
(7, 4),
(7,4);

SELECT * FROM PersonOrganization;


SELECT Superpower.SuperpowerID, SuperpowerName
FROM Superpower
INNER JOIN PersonSuperpower
ON Superpower.SuperpowerID = PersonSuperpower.SuperpowerID
WHERE PersonID = 1;

SELECT Person.PersonID, PersonName, PersonDescription, IsHero
FROM Person
INNER JOIN PersonSuperpower
ON Person.PersonID = PersonSuperpower.PersonID
WHERE SuperpowerID = 2;

SELECT Person.PersonID, PersonName, PersonDescription, IsHero
FROM Person
INNER JOIN PersonSuperpower
ON Person.PersonID = PersonSuperpower.PersonID
INNER JOIN Superpower
ON Superpower.SuperpowerID = PersonSuperpower.SuperpowerID
WHERE Person.PersonName = 'Wolverine';

INSERT INTO Sightings (SightingsDate, PersonID, LocationID)
VALUES ('2018-01-01', 1, 4),
('2019-01-19', 2, 2),
('2015-02-13', 3, 3),
('2012-03-12', 5,4),
('2019-05-22', 6,5);

SELECT * FROM Sightings;



