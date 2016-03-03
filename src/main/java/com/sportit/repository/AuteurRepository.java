package com.sportit.repository;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.sportit.model.Auteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Repository Cassandra pour l'entité Auteur.
 *
 */
@Repository
public class AuteurRepository {

    @Inject
    private Session session;

    private Mapper<Auteur> mapper;

    private PreparedStatement insertAuteurStmt;

    private PreparedStatement findAllAuteurStmt;

    @PostConstruct
    private void init(){

        //mapper initialization
        this.mapper = new MappingManager(session).mapper(Auteur.class);

        //pre-load statements
        this.findAllAuteurStmt = this.session.prepare("SELECT * from auteur");

        this.insertAuteurStmt = this.session.prepare("insert into insert into auteur(email, prenom, nom, creation_date) values" +
                " (:email, :prenom, :nom, :creation_date)");
    }

    public List<Auteur> findALl(){

        List<Auteur> auteurs = this.mapper.map(this.session.execute(this.findAllAuteurStmt.bind())).all();
        return auteurs;
    }

    public void save(Auteur auteur){

        this.session.execute(this.insertAuteurStmt.bind()
                .setString("email", auteur.getEmail())
                .setString("prenom", auteur.getPrenom())
                .setString("nom", auteur.getNom())
                .setTimestamp("creation_date", new Date()));

    }
}
