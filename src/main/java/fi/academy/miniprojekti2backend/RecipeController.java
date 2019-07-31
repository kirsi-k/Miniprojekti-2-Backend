package fi.academy.miniprojekti2backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping ("/")
public class RecipeController {

    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("")
    public Iterable<Recipe> haeKaikkiReseptit(){
        return recipeRepository.findAll();
    }
    @GetMapping("/{id}")
    public Iterable<Recipe> haeIdlla(@PathVariable Integer id){
        return recipeRepository.findRecipeById(id);
    }
    @GetMapping("/haeresepti/{name}")
    public Iterable<Recipe> haeReseptinNimella(@PathVariable String name){
        return recipeRepository.findRecipeByNameIgnoreCase(name);
    }
    @PostMapping("")
    public ResponseEntity<Recipe> lisaaUusiResepti(@RequestBody Recipe newRecipe, UriComponentsBuilder builder) {
        List<Recipe> recipes = recipeRepository.findRecipeById(newRecipe.getId());
        if (recipes.size() > 0) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/recipes/{id}").buildAndExpand(newRecipe.getId()).toUri());
            recipeRepository.save(newRecipe);
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> poistaResepti(@PathVariable Integer id) {
        List<Recipe> recipes = recipeRepository.findRecipeById(id);
        if (recipes.size()>0) {
            recipeRepository.delete(recipes.get(0));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
