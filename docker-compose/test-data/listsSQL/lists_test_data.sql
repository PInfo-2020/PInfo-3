CREATE USER lis WITH PASSWORD 'lis';
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
-- INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('1', 1, 1);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('2', 2, 2);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('3', 3, 3);
-- INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('1', 1, 3);
-- INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('1', 8, 5);
-- INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('1', 7, 3);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('2', 2, 2);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('3', 3, 3);

INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('b23e572e-d167-456d-9d31-05f505f4d1c9', 3, 3);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('b23e572e-d167-456d-9d31-05f505f4d1c9', 50, 4);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('b23e572e-d167-456d-9d31-05f505f4d1c9', 253, 7);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('b23e572e-d167-456d-9d31-05f505f4d1c9', 133, 1);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('b23e572e-d167-456d-9d31-05f505f4d1c9', 7, 8);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('b23e572e-d167-456d-9d31-05f505f4d1c9', 10, 5);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('b23e572e-d167-456d-9d31-05f505f4d1c9', 256, 8);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('b23e572e-d167-456d-9d31-05f505f4d1c9', 89, 3);


INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('ad7dfd1d-de79-418a-a63f-c91b562f70ee', 281, 7);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('ad7dfd1d-de79-418a-a63f-c91b562f70ee', 285, 100);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('ad7dfd1d-de79-418a-a63f-c91b562f70ee', 235, 3);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('ad7dfd1d-de79-418a-a63f-c91b562f70ee', 205, 5);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('ad7dfd1d-de79-418a-a63f-c91b562f70ee', 80, 8);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('ad7dfd1d-de79-418a-a63f-c91b562f70ee', 300, 4);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('ad7dfd1d-de79-418a-a63f-c91b562f70ee', 64, 2);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('ad7dfd1d-de79-418a-a63f-c91b562f70ee', 20, 5);

INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('c2b98917-4057-4486-943c-f25a315a6e2d', 255, 10);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('c2b98917-4057-4486-943c-f25a315a6e2d', 289, 200);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('c2b98917-4057-4486-943c-f25a315a6e2d', 247, 5);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('c2b98917-4057-4486-943c-f25a315a6e2d', 238, 3);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('c2b98917-4057-4486-943c-f25a315a6e2d', 67, 6);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('c2b98917-4057-4486-943c-f25a315a6e2d', 46, 8);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('c2b98917-4057-4486-943c-f25a315a6e2d', 98, 1);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('c2b98917-4057-4486-943c-f25a315a6e2d', 50, 9);

INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('2d119cee-5521-4850-96d0-cccebe1614ec', 278, 10);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('2d119cee-5521-4850-96d0-cccebe1614ec', 252, 4);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('2d119cee-5521-4850-96d0-cccebe1614ec', 142, 15);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('2d119cee-5521-4850-96d0-cccebe1614ec', 88, 10);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('2d119cee-5521-4850-96d0-cccebe1614ec', 237, 3);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('2d119cee-5521-4850-96d0-cccebe1614ec', 90, 7);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('2d119cee-5521-4850-96d0-cccebe1614ec', 35, 2);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('2d119cee-5521-4850-96d0-cccebe1614ec', 10, 3);

INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('9ada320a-7245-4e68-a9bc-03baeec8bbb1', 5, 9);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('9ada320a-7245-4e68-a9bc-03baeec8bbb1', 68, 4);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('9ada320a-7245-4e68-a9bc-03baeec8bbb1', 234, 2);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('9ada320a-7245-4e68-a9bc-03baeec8bbb1', 302, 6);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('9ada320a-7245-4e68-a9bc-03baeec8bbb1', 46, 7);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('9ada320a-7245-4e68-a9bc-03baeec8bbb1', 302, 4);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('9ada320a-7245-4e68-a9bc-03baeec8bbb1', 265, 2);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('9ada320a-7245-4e68-a9bc-03baeec8bbb1', 68, 6);

INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('d9f96efd-68ca-40e1-8a52-642f9cc482c1', 5, 17);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('d9f96efd-68ca-40e1-8a52-642f9cc482c1', 54, 3);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('d9f96efd-68ca-40e1-8a52-642f9cc482c1', 134, 4);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('d9f96efd-68ca-40e1-8a52-642f9cc482c1', 300, 10);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('d9f96efd-68ca-40e1-8a52-642f9cc482c1', 40, 4);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('d9f96efd-68ca-40e1-8a52-642f9cc482c1', 301, 22);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('d9f96efd-68ca-40e1-8a52-642f9cc482c1', 260, 1);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('d9f96efd-68ca-40e1-8a52-642f9cc482c1', 58, 2);

INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('1', 98, 2);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('1', 70, 5);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('1', 45, 2);
INSERT INTO ITEMFRIDGE (userID, ingredientID, quantity) VALUES ('1', 208, 8);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('1', 78, 3);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('1', 278, 7);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('1', 2, 3);
INSERT INTO ITEMCART (userID, ingredientID, quantity) VALUES ('1', 35, 6);
