DROP table if exists recipe;
create table recipe (
                        id SERIAL,
                        name varchar(255) not null,
                        description text not null,
                        ingredients text not null,
                        instructions text not null,
                        primary key(id)

);