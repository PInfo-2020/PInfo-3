
CREATE TABLE ITEMCART(
	userID VARCHAR(50) not null,
	ingredientID BIGINT not null,
	quantity FLOAT(53) not null
);

CREATE TABLE ITEMFRIDGE(
	userID VARCHAR(50) not null,
	ingredientID BIGINT not null,
	quantity FLOAT(53) not null
);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('1', 1, 1);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('2', 2, 2);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('3', 3, 3);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('1', 1, 3);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('1', 8, 5);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('1', 7, 3);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('2', 2, 2);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('3', 3, 3);
