--drop table Recipe if exists;
--create table Recipe (
--        name varchar(31) not null,
--        id bigint not null,
--        primary key (id)
--);
INSERT INTO Recipe (id, name, description, instructions, minutes, personnes, userId) values (1, 'nom1', 'd1', 'i1', 10, 1, 3);
INSERT INTO Recipe (id, name, description, instructions, minutes, personnes, userId) values (2, 'nom3', 'd2', 'i2', 10, 1, 3);
INSERT INTO Recipe (id, name, description, instructions, minutes, personnes, userId) values (3, 'nom3', 'd3', 'i3', 10, 1, 3);
INSERT INTO Ingredient (id, recipeId, ingredientId, quantite) values (1, 3, 7, 3.0);
INSERT INTO Ingredient (id, recipeId, ingredientId, quantite) values (2, 3, 8, 5.0);
INSERT INTO Comment (id, recipeId, commentRecipe) values (1, 1, 'C''est bon');
INSERT INTO Comment (id, recipeId, commentRecipe) values (2, 3, 'C''est pas bon');
INSERT INTO Comment (id, recipeId, commentRecipe) values (3, 3, 'C''est delicieux');
INSERT INTO Grade (id, recipeId, userId, gradeRecipe) values (1, 1, 10, 5);
INSERT INTO Grade (id, recipeId, userId, gradeRecipe) values (2, 3, 10, 5);
INSERT INTO Grade (id, recipeId, userId, gradeRecipe) values (3, 3, 8, 4);


