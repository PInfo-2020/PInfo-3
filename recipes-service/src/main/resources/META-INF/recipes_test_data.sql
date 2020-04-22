--drop table Recipe if exists;
--create table Recipe (
--        name varchar(31) not null,
--        id bigint not null,
--        primary key (id)
--);
INSERT INTO Recipe (id, name) values (1, 'nom1');
INSERT INTO Recipe (id, name) values (2, 'nom3');
INSERT INTO Recipe (id, name) values (3, 'nom3');
INSERT INTO Comment (id, recipeId, commentRecipe) values (1, 1, 'C''est bon');
INSERT INTO Comment (id, recipeId, commentRecipe) values (2, 3, 'C''est pas bon');
INSERT INTO Comment (id, recipeId, commentRecipe) values (3, 3, 'C''est delicieux');
INSERT INTO Grade (id, recipeId, userId, gradeRecipe) values (1, 1, 10, 5);
INSERT INTO Grade (id, recipeId, userId, gradeRecipe) values (2, 3, 10, 5);
INSERT INTO Grade (id, recipeId, userId, gradeRecipe) values (3, 3, 8, 4);


