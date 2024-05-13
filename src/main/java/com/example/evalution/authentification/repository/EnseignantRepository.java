package com.example.evalution.authentification.repository;

import com.example.evalution.authentification.model.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant,Integer> {
    Enseignant findByEmail (String email);
    Enseignant findByNom(String nom);
   
}
