CREATE USER pro WITH PASSWORD 'pro';
CREATE TABLE profile(
	usernameID VARCHAR(50) not null,
	username VARCHAR(50) not null,
	score FLOAT(53) not null
);

 CREATE TABLE plannedrecipe(
           rowID BIGINT not null,
           usernameID VARCHAR(50) not null,
           recipeID BIGINT not null
  );
INSERT INTO plannedrecipe (rowID,usernameID,recipeID) VALUES (1,'1',001);
INSERT INTO plannedrecipe (rowID,usernameID,recipeID) VALUES (2,'2',001);
INSERT INTO plannedrecipe (rowID,usernameID,recipeID) VALUES (3,'1',002);
INSERT INTO plannedrecipe (rowID,usernameID,recipeID) VALUES (4,'2',005);
INSERT INTO plannedrecipe (rowID,usernameID,recipeID) VALUES (5,'1',006);
INSERT INTO plannedrecipe (rowID,usernameID,recipeID) VALUES (6,'2',006);
INSERT INTO profile (usernameID,username,score) VALUES ('b23e572e-d167-456d-9d31-05f505f4d1c9','Roxy',0);
INSERT INTO profile (usernameID,username,score) VALUES ('ad7dfd1d-de79-418a-a63f-c91b562f70ee','Salma',0);
INSERT INTO profile (usernameID,username,score) VALUES ('c2b98917-4057-4486-943c-f25a315a6e2d','Luka',0);
INSERT INTO profile (usernameID,username,score) VALUES ('2d119cee-5521-4850-96d0-cccebe1614ec','Benjamin',0);
INSERT INTO profile (usernameID,username,score) VALUES ('9ada320a-7245-4e68-a9bc-03baeec8bbb1','Nathan',0);
INSERT INTO profile (usernameID,username,score) VALUES ('d9f96efd-68ca-40e1-8a52-642f9cc482c1','Elham',0);
