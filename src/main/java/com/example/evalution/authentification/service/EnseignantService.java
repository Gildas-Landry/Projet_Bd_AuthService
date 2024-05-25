package com.example.evalution.authentification.service;

import com.example.evalution.authentification.model.Enseignant;
import com.example.evalution.authentification.repository.EnseignantRepository;
import com.example.evalution.authentification.securite.EmailValidator;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnseignantService  {

    private EnseignantRepository enseignantRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public EnseignantService(EnseignantRepository enseignantRepository) {
        this.enseignantRepository = enseignantRepository;
    }
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptPasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    public void creer (Enseignant enseignant){

        if (!EmailValidator.isValidEmail(enseignant.getEmail())) {
            throw new RuntimeException("Votre mail invalide");
        }

          //String passwordCrypt = this.passwordEncoder.encode(enseignant.getPassword());
          //enseignant.setPassword(passwordCrypt);
        Enseignant enseignantBd = this.enseignantRepository.findByEmail(enseignant.getEmail());

        if (enseignantBd == null) {
            this.enseignantRepository.save(enseignant);
        }

    }
    public List<Enseignant> rechercher() {
        return this.enseignantRepository.findAll();
           }

    public void supprimer(int id) {
        this.enseignantRepository.deleteById(id);
    }

    public Enseignant lire(int id) {
        Optional<Enseignant> optionalEnseignant = this.enseignantRepository.findById(id);
        return optionalEnseignant.orElse(null);
    }
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifier(int id,Enseignant enseignant) {

        Enseignant enseignantDlbd =this.lire(id);
        if (enseignantDlbd.getId() != null){
            enseignantDlbd.setNom(enseignant.getNom());
            enseignantDlbd.setEmail(enseignant.getEmail());
            enseignantDlbd.setPassword(enseignant.getPassword());
            this.enseignantRepository.save(enseignantDlbd);
        }
    }
    public Enseignant connection (Enseignant enseignant){

        Enseignant enseignantdlbd=this.enseignantRepository.findByEmailAndPassword(enseignant.getEmail(), enseignant.getPassword());

        if (enseignantdlbd == null) {
            throw new EntityNotFoundException("enseignant introuvable");
        }

        return enseignantdlbd;
    }
        
    
}
