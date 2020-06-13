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

--GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO pro;
GRANT DELETE, UPDATE, SELECT, INSERT ON ALL TABLES IN SCHEMA public to pro;
INSERT INTO plannedrecipe (rowID,usernameID,recipeID) VALUES (1,'b23e572e-d167-456d-9d31-05f505f4d1c9',1);
INSERT INTO plannedrecipe (rowID,usernameID,recipeID) VALUES (2,'ad7dfd1d-de79-418a-a63f-c91b562f70ee',1);
INSERT INTO plannedrecipe (rowID,usernameID,recipeID) VALUES (3,'c2b98917-4057-4486-943c-f25a315a6e2d',2);
INSERT INTO plannedrecipe (rowID,usernameID,recipeID) VALUES (4,'2d119cee-5521-4850-96d0-cccebe1614ec',5);
INSERT INTO plannedrecipe (rowID,usernameID,recipeID) VALUES (5,'9ada320a-7245-4e68-a9bc-03baeec8bbb1',6);
INSERT INTO plannedrecipe (rowID,usernameID,recipeID) VALUES (6,'d9f96efd-68ca-40e1-8a52-642f9cc482c1',6);
INSERT INTO profile (usernameID,username,score) VALUES ('b23e572e-d167-456d-9d31-05f505f4d1c9','roxane',5);
INSERT INTO profile (usernameID,username,score) VALUES ('ad7dfd1d-de79-418a-a63f-c91b562f70ee','salma',0.5);
INSERT INTO profile (usernameID,username,score) VALUES ('c2b98917-4057-4486-943c-f25a315a6e2d','luka',3);
INSERT INTO profile (usernameID,username,score) VALUES ('2d119cee-5521-4850-96d0-cccebe1614ec','benjamin',2);
INSERT INTO profile (usernameID,username,score) VALUES ('9ada320a-7245-4e68-a9bc-03baeec8bbb1','nathan',4);
INSERT INTO profile (usernameID,username,score) VALUES ('d9f96efd-68ca-40e1-8a52-642f9cc482c1','elham',5);
