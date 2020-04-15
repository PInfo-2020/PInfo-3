DROP TABLE Ingredients IF EXISTS;

CREATE TABLE Ingredients (
        id not null,
        name varchar(255) not null,
        unit varchar(255) not null,
        primary key (id)
);

INSERT INTO Ingredients(1, tomato, unit);
INSERT INTO Ingredients(2, flour, kg);
INSERT INTO Ingredients(3, milk, l);
INSERT INTO Ingredients(4, egg, unit);
INSERT INTO Ingredients(5, mozzarella, g);
INSERT INTO Ingredients(6, chicken, g);
INSERT INTO Ingredients(7, avocado, unit);