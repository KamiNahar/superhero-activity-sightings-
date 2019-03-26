USE SuperheroSightings;

Delete from Users 
where UserID >0;

INSERT INTO Users (Username, UserPassword, Enabled) 
VALUES ('ZeeZee', 'password', 1),
('Randall', 'password', 1),
('Amy123', 'password', 1),
('Sara', 'password', 1);

UPDATE `SuperHeroSightings`.`Users`
SET
`UserPassword` = '$2a$10$gE859V4G7oK5/FkLsSJArOQtEo5Bn7v2saG3E73WcF5t0xQpl.RKq'
WHERE `UserID` >0;

Delete from Authorities
where AuthoritiesID >0;


INSERT INTO Authorities (Username, Authority)
VALUES ('ZeeZee', 'ROLE_ADMIN'),
('ZeeZee', 'ROLE_USER'),
('Randall', 'ROLE_USER'),
('Sara', 'ROLE_ADMIN'),
('Sara', 'ROLE_USER');

SELECT * FROM Authorities;

Select * from Users; 