-- Creation of sportit keyspace
create KEYSPACE sportit WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 2} ;

-- Creation of 'auteur' table
create TABLE auteur(
    email text primary key,
    prenom text,
    nom text,
    creation_date timestamp
);

--injecting sample data
insert into auteur(email, prenom, nom, creation_date) values ('pdoe@sportitjava.com', 'Peter', 'Doe', dateOf(now()));
insert into auteur(email, prenom, nom, creation_date) values ('jdoe@sportitjava.com', 'John', 'Doe', dateOf(now()));
insert into auteur(email, prenom, nom, creation_date) values ('jsanders@sportitjava.com', 'Jessica', 'Sanders', dateOf(now()));