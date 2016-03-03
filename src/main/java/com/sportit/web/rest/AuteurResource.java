package com.sportit.web.rest;

import com.sportit.model.Auteur;
import com.sportit.service.AuteurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
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
}
