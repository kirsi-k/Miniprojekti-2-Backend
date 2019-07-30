package fi.academy.miniprojekti2backend;

import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository <Recipe,Integer> {
    
}
