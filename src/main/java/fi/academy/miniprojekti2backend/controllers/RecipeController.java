package fi.academy.miniprojekti2backend.controllers;

//Created by: Kirsi Kujala

import fi.academy.miniprojekti2backend.entities.Recipe;
import fi.academy.miniprojekti2backend.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

//CrossOrigin annotaatio CORS-ongelman välttämiseksi
@CrossOrigin (origins = {"http://localhost:3000"})
@RestController
@RequestMapping ("/reseptit")
public class RecipeController {

    private RecipeRepository recipeRepository;

    //ruiskutetaan reseptirepon riippuvuus kontrollerille, määritellään qualifier, jotta ei tule unsatisfied beans erroria
    @Autowired
    public RecipeController(@Qualifier("recipe") RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }
    //metodi palauttaa kaikki recipe-taulukon reseptit (READ)
    @GetMapping("")
    public Iterable<Recipe> haeKaikkiReseptit(){
        return recipeRepository.findAll();
    }
    //metodi palauttaa yhden reseptin id:n perusteella
    @GetMapping("/{id}")
    public Iterable<Recipe> haeIdlla(@PathVariable Integer id){
        return recipeRepository.findRecipeById(id);
    }
    //metodi hakee kaikki reseptit, joiden nimessä on parametrina annettu kirjainyhdistelmä
    @GetMapping("/haenimella/{name}")
    public Iterable<Recipe> haeReseptinNimella(@PathVariable String name){
        return recipeRepository.findByNameIgnoreCaseContaining(name);
    }
    //metodi hakee kaikki reseptit, joiden ainesosat sisältävät parametrina annetun kirjainyhdistelmän
    @GetMapping("/haeainesosalla/{ingredients}")
    public Iterable<Recipe> haeReseptinAinesosalla(@PathVariable String ingredients){
        return recipeRepository.findByIngredientsIgnoreCaseContaining(ingredients);
    }
    //metodi hakee kaikki reseptit, jotka kuuluvat parametrina annettuun category-taulun kategoriaan (haku kategorian nimellä)
    @GetMapping("/haekategorialla/{category_name}")
    public List<Recipe> haeReseptinKategorialla(@PathVariable String category_name){
        return recipeRepository.findAllByCategory_Name(category_name);
    }
    //metodi arpoo recipe-taulun resepteistä id:n perusteella sattumanvaraisen reseptin
    @GetMapping("/haerandom")
    public Iterable<Recipe> haeRandomResepti(){
        long maara = recipeRepository.count();
        int i = Math.toIntExact(maara);
        int id = (int)(Math.random() * i + 1);
        return recipeRepository.findRecipeById(id);
    }
    //metodi uuden reseptin lisäämiseksi (CREATE)
    @PostMapping("")
    public ResponseEntity<Recipe> lisaaUusiResepti(@RequestBody Recipe newRecipe, UriComponentsBuilder builder) {
        List<Recipe> recipes = recipeRepository.findRecipeById(newRecipe.getId());
        if (recipes.size() > 0) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/{id}").buildAndExpand(newRecipe.getId()).toUri());
            recipeRepository.save(newRecipe);
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
    }
    //metodi reseptin poistamiseksi id:n perusteella (DELETE)    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> poistaResepti(@PathVariable Integer id) {
        List<Recipe> recipes = recipeRepository.findRecipeById(id);
        if (recipes.size() > 0) {
            recipeRepository.delete(recipes.get(0));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    //metodi reseptin muokkaamista varten (UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> paivita(@PathVariable Integer id,@RequestBody Recipe recipe) {
        List<Recipe> recipes = recipeRepository.findRecipeById(id);
        if (recipes.size() > 0) {
            recipe.setId(id);
            recipeRepository.save(recipe);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
