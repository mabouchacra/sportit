package com.sportit.service;

import com.sportit.model.Auteur;
import com.sportit.repository.AuteurRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Service to manage Auteur entity
 */
@Service
public class AuteurService {

    @Inject
    private AuteurRepository auteurRepository;

    public List<Auteur> findAll(){

        return this.auteurRepository.findALl();
    }

    public Optional<Auteur> findByEmail(String email){

        if(email == null){
            throw new IllegalArgumentException("L'email est obligatoire.");
        }

        return this.auteurRepository.findByEmail(email);
    }
}
