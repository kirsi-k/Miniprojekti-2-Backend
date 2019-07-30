package fi.academy.miniprojekti2backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/reseptit")
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
}
