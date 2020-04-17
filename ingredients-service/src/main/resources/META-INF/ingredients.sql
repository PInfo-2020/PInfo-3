--DROP TABLE ingredients IF EXISTS;
--
--CREATE TABLE ingredients (
--        id not null,
--        name varchar(255) not null,
--        unit varchar(255) not null,
--        primary key (id)
--);
--
--INSERT INTO ingredients(1, tomato, unit);
--INSERT INTO ingredients(2, flour, kg);
--INSERT INTO ingredients(3, milk, l);
--INSERT INTO ingredients(4, egg, unit);
--INSERT INTO ingredients(5, mozzarella, g);
--INSERT INTO ingredients(6, chicken, g);
--INSERT INTO ingredients(7, avocado, unit);

INSERT INTO ingredients (id, name, unit) VALUES (1, 'tomato', 'kg')
INSERT INTO ingredients (id, name, unit) VALUES (2, 'milk', 'l')
INSERT INTO ingredients (id, name, unit) VALUES (3, 'sugar', 'g')
INSERT INTO ingredients (id, name, unit) VALUES (4, 'chicken', 'g')
INSERT INTO ingredients (id, name, unit) VALUES (5, 'avocado', 'unit')