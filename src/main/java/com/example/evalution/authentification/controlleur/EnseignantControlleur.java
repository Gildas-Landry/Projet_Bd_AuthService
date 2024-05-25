package com.example.evalution.authentification.controlleur;

import com.example.evalution.authentification.model.Enseignant;
import com.example.evalution.authentification.service.EnseignantService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*") 
@Controller
@RequestMapping(path="enseignant")
public class EnseignantControlleur {

    private EnseignantService enseignantService;
    public EnseignantControlleur(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

    @ResponseStatus(value= HttpStatus.CREATED)
    @PostMapping(path = "inscription")
    public void creer (@RequestBody Enseignant enseignant){
        this.enseignantService.creer(enseignant);
    }
    @ResponseStatus(value= HttpStatus.CREATED)
    @PostMapping(path = "connection")

    public ResponseEntity<?> connection(@RequestBody Enseignant enseignant) {
        try {
            Enseignant enseignantFromDb = enseignantService.connection(enseignant);
            return new ResponseEntity<>(enseignantFromDb.getId(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public @ResponseBody  List<Enseignant> rechercher (){
        return this.enseignantService.rechercher();
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(path ="{id}")
    public void supprimer (@PathVariable int id){
        this.enseignantService.supprimer(id);
    }
    @GetMapping(path ="{id}",produces = APPLICATION_JSON_VALUE)
    public @ResponseBody Enseignant lire (@PathVariable int id ){
        return this.enseignantService.lire(id);
    }
    @PutMapping(path ="{id}",consumes = APPLICATION_JSON_VALUE )
    public @ResponseBody void modifier (@PathVariable int id,@RequestBody Enseignant enseignant){
        this.enseignantService.modifier(id,enseignant);
    }

}
