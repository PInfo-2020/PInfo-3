drop table recipe if exists;
create table recipe (
        name varchar(31) not null,
        id bigint not null,
        primary key (id)
);
INSERT INTO recipe (id, name) values (1, 'nom1');

