DROP table if exists recipe;
create table recipe (
                        id SERIAL,
                        name varchar(255) not null,
                        description text not null,
                        ingredients text not null,
                        instructions text not null,
                        categoryid int,
                        primary key(id)

);
DROP table if exists category;
create table category(
                        id serial primary key,
                        name varchar(255) not null
);