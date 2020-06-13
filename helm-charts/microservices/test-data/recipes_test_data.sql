CREATE USER rec WITH PASSWORD 'rec';
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO rec;
--drop table Recipe if exists;
--create table Recipe (
--        name varchar(31) not null,
--        id bigint not null,
--        primary key (id)
--);
--CREATE USER rec WITH PASSWORD 'rec';
--drop table Recipe if exists;
 create table Recipe (
 		id bigint not null,
         name varchar(255) not null,
         description varchar(255) not null,
         instructions varchar(255) not null,
         minutes bigint not null,
         personnes bigint not null,
         userId varchar(255) not null,
         primary key (id)
 );

-- drop table Ingredient if exists;
 create table Ingredient (
 		id bigint not null,
 		recipeId bigint not null,
 		ingredientId bigint not null,
 		quantite FLOAT(53) not null,
         vegetarien bigint not null,
         vegan bigint not null,
         primary key (id)
 );

 --drop table Comment if exists;
 create table Comment (
 		id bigint not null,
 		recipeId bigint not null,
 		commentRecipe varchar(255) not null,
         primary key (id)
 );

 --drop table Grade if exists;
 create table Grade (
 		id bigint not null,
 		recipeId bigint not null,
 		userId varchar(255) not null,
 		gradeRecipe bigint not null,
         primary key (id)
 );

GRANT SELECT, DELETE, UPDATE, INSERT ON ALL TABLES IN SCHEMA public to rec;
INSERT INTO Recipe (id, name, description, instructions, minutes, personnes, userId) values (1, 'Salade aux fruits', 'Vous aimez les fruits ? Alors venez !', 'i1', 10, 1, 'd9f96efd-68ca-40e1-8a52-642f9cc482c1');
INSERT INTO Recipe (id, name, description, instructions, minutes, personnes, userId) values (2, 'Poulet au curry: is it really though?', 'La recette thaïlandaise originale', 'i2', 10, 1, '9ada320a-7245-4e68-a9bc-03baeec8bbb1');
INSERT INTO Recipe (id, name, description, instructions, minutes, personnes, userId) values (3, 'Poulet au curry', 'Une recette asiatique absolument délicieuse', 'i3', 10, 1, '2d119cee-5521-4850-96d0-cccebe1614ec');
INSERT INTO Recipe (id, name, description, instructions, minutes, personnes, userId) values (4, 'spaghetti', 'une recette simple et BON', 'i3', 10, 4, 'd9f96efd-68ca-40e1-8a52-642f9cc482c1');
INSERT INTO Recipe (id, name, description, instructions, minutes, personnes, userId) values (5, 'Pizza', 'mettre dans le foure et BASTA!', 'i3', 10, 2, '9ada320a-7245-4e68-a9bc-03baeec8bbb1');
INSERT INTO Recipe (id, name, description, instructions, minutes, personnes, userId) values (6, 'hamburger', 'SO awesome!', 'i3', 10, 4, '2d119cee-5521-4850-96d0-cccebe1614ec');
INSERT INTO Recipe (id, name, description, instructions, minutes, personnes, userId) values (7, 'fondue', 'super facile!', 'i3', 2, 8, 'c2b98917-4057-4486-943c-f25a315a6e2d');
INSERT INTO Recipe (id, name, description, instructions, minutes, personnes, userId) values (8, 'raclette', 'pour un hiver plus chaleureux!', 'i3', 10, 4, 'ad7dfd1d-de79-418a-a63f-c91b562f70ee');
INSERT INTO Recipe (id, name, description, instructions, minutes, personnes, userId) values (9, 'macaron', 'un desert pour le jour des Mères', 'i3', 10, 4, 'b23e572e-d167-456d-9d31-05f505f4d1c9');

INSERT INTO Ingredient (id, recipeId, ingredientId, quantite, vegetarien, vegan) values (1, 3, 7, 3.0, 0, 0);
INSERT INTO Ingredient (id, recipeId, ingredientId, quantite, vegetarien, vegan) values (2, 3, 8, 5.0, 0, 0);
INSERT INTO Ingredient (id, recipeId, ingredientId, quantite, vegetarien, vegan) values (3, 3, 1, 3.0, 0, 0);

INSERT INTO Comment (id, recipeId, commentRecipe) values (1, 1, 'C''est bon YEEEESSSS');
INSERT INTO Comment (id, recipeId, commentRecipe) values (2, 3, 'C''est pas bon');
INSERT INTO Comment (id, recipeId, commentRecipe) values (3, 3, 'C''est delicieux');
INSERT INTO Grade (id, recipeId, userId, gradeRecipe) values (1, 1, '10', 5);
INSERT INTO Grade (id, recipeId, userId, gradeRecipe) values (2, 3, '10', 5);
INSERT INTO Grade (id, recipeId, userId, gradeRecipe) values (3, 3, '8', 4);
