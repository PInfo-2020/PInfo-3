drop table Recipe if exists;
create table Recipe (
        name varchar(31) not null,
        id bigint not null,
        primary key (id)
);
INSERT INTO Recipe (id, name) values (1, 'nom1');


