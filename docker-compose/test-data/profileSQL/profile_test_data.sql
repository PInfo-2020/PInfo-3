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
INSERT INTO profile (usernameID,username,score) VALUES ('1','Roxy',5);
INSERT INTO profile (usernameID,username,score) VALUES ('2','Eli',6);
INSERT INTO profile (usernameID,username,score) VALUES ('3','Yassine',5);
INSERT INTO profile (usernameID,username,score) VALUES ('4','Mouslim',6);
INSERT INTO profile (usernameID,username,score) VALUES ('5','Ilyas',5);
INSERT INTO profile (usernameID,username,score) VALUES ('6','Amine',1);
INSERT INTO profile (usernameID,username,score) VALUES ('7','Sarra',5);
INSERT INTO profile (usernameID,username,score) VALUES ('8','Luka',3);
INSERT INTO profile (usernameID,username,score) VALUES ('9','Benjamin',2.5);
INSERT INTO profile (usernameID,username,score) VALUES ('10','Nathan',1);
INSERT INTO profile (usernameID,username,score) VALUES ('11','Noah',5.5);
INSERT INTO profile (usernameID,username,score) VALUES ('12','Anas',0);



	
