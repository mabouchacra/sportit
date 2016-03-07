package com.sportit.web.rest;

import com.sportit.model.Auteur;
import com.sportit.service.AuteurService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Rest controller to manage Auteur resource
 */
@RestController
@RequestMapping("/auteur")
public class AuteurResource {

    @Inject
    private AuteurService auteurService;

    /**
     * GET /auteur -> get all user
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllAuteur(){
        List<Auteur> auteurs = this.auteurService.findAll();
        return new ResponseEntity(auteurs, HttpStatus.OK);
    }

    /**
     * POST /auteur -> save a new Auteur.
     * If Email already exist, throw bad request
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveAuteur(@RequestBody @Valid Auteur auteur){

        if(auteur == null){
            return ResponseEntity.badRequest().header("X-auteur-management-error", "Auteur is mandatory").body(null);
        }

        if(this.auteurService.findByEmail(auteur.getEmail()).isPresent()){
            return ResponseEntity.badRequest().header("X-auteur-management-error", "Auteur already exist").body(null);
        }

        Auteur newAuteur = this.auteurService.createAuteur(auteur);
        return ResponseEntity.created(URI.create("/auteur/email?"+auteur.getEmail())).body(newAuteur);
    }

    /**
     * GET /auteur/Search?email=some_email -> Return user by email
     */
    @RequestMapping(value="/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getByEmail(@RequestParam("email") String email){
        return this.auteurService.findByEmail(email).map(auteur -> new ResponseEntity(auteur, HttpStatus.OK)).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
}
