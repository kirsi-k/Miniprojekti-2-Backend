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

/** RecipeController on RestController-luokka, joka luo REST api-palvelun ja CRUD-metodit datan käsittelyä varten.
 * RestControlleriin lisätty CrossOrigin annotaatio CORS-ongelman välttämiseksi*/
@CrossOrigin (origins = {"http://localhost:3000"})
@RestController
@RequestMapping ("/reseptit")
public class RecipeController {

    private RecipeRepository recipeRepository;

    /** Autowired-annotaatiolla ruiskutetaan reseptirepositorion riippuvuus restkontrollerille,
     * repositiolle määritellään qualifier, jotta ei tule unsatisfied beans erroria*/
    @Autowired
    public RecipeController(@Qualifier("recipe") RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }
    /** haeKaikkiReseptit-metodi palauttaa kaikki recipe-taulukon reseptit (eli CRUDin READ)*/
    @GetMapping("")
    public Iterable<Recipe> haeKaikkiReseptit(){
        return recipeRepository.findAll();
    }
    /** haeIdlla-metodi palauttaa yhden reseptin id:n perusteella */
    @GetMapping("/{id}")
    public Iterable<Recipe> haeIdlla(@PathVariable Integer id){
        return recipeRepository.findRecipeById(id);
    }
    /** haeReseptinNimella-metodi hakee kaikki reseptit, joiden nimessä on parametrina annettu kirjainyhdistelmä
     * kirjainten koolla ei ole väliä */
    @GetMapping("/haenimella/{name}")
    public Iterable<Recipe> haeReseptinNimella(@PathVariable String name){
        return recipeRepository.findByNameIgnoreCaseContaining(name);
    }
    /** haeReseptinAinesosalla-metodi hakee kaikki reseptit, joiden ainesosat sisältävät parametrina annetun kirjainyhdistelmän
     * kirjainten koolla ei ole väliä */
    @GetMapping("/haeainesosalla/{ingredients}")
    public Iterable<Recipe> haeReseptinAinesosalla(@PathVariable String ingredients){
        return recipeRepository.findByIngredientsIgnoreCaseContaining(ingredients);
    }
    /** haeReseptinKategorialla-metodi hakee kaikki reseptit, jotka kuuluvat parametrina annettuun category-taulun kategoriaan
     *  (haku kategorian nimellä) */
    @GetMapping("/haekategorialla/{category_name}")
    public List<Recipe> haeReseptinKategorialla(@PathVariable String category_name){
        return recipeRepository.findAllByCategory_Name(category_name);
    }
    /** haeRandomResepti-metodi arpoo recipe-taulun resepteistä id:n perusteella sattumanvaraisen reseptin */
    @GetMapping("/haerandom")
    public Iterable<Recipe> haeRandomResepti(){
        long maara = recipeRepository.count();
        int i = Math.toIntExact(maara);
        int id = (int)(Math.random() * i + 1);
        return recipeRepository.findRecipeById(id);
    }
    /** lisaaUusiResepti-metodi mahdollistaa uuden reseptin lisäämisen tietokantaan (eli CRUDin CREATE)
     * Ohjelma huolehtii uuden reseptin id:n luomisesta, mutta metodi varmistaa, että tietokannasta ei jo löydy reseptiä sillä id:llä, jolla koittaa uuden luoda.
     * Metodi palauttaa Http Statuksen 201 "Created", jos uuden reseptin luonti onnistuu tai statuksen 409 "Conflict", jos id:llä löytyy jo resepti, eikä uutta voitu luoda*/
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
    /** poistaResepti-metodi poistaa valitun reseptin id:n perusteella (eli CRUDin DELETE)
     * Mikäli poisto onnistuu, palauttaa HTTP Statuksen 204 "No content" eli serveri on onnistuneesti käsitellyt pyynnön, eikä id:llä enää löydy sisältöä
     * Jos id:llä ei löydy reseptiä, palauttaa statuksen 409 "Conflict" */
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
    /** paivitaResepti-metodi mahdollistaa reseptin muokkaamisen (eli CRUDin UPDATE) id:n perusteella
     * palauttaa Http Statuksen 200 "Ok", jos serveri on käsitellyt päivityksen onnistuneesti
     * palauttaa Http Statuksen 404 "Not found", jos id:llä ei löydy reseptiä päivitettäväksi */
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> paivitaResepti(@PathVariable Integer id,@RequestBody Recipe recipe) {
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
