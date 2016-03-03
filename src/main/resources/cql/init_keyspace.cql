-- Creation of sportit keyspace
create KEYSPACE sportit WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 2} ;

-- Creation of 'auteur' table
create TABLE auteur(
    email text primary key,
    prenom text,
    nom text,
    creation_date timestamp
);