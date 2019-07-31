package fi.academy.miniprojekti2backend;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends CrudRepository <Recipe,Integer> {

    Iterable<Recipe> findByNameIgnoreCaseContaining(@Param("search") String search);
    List<Recipe> findRecipeById(Integer id);
    Iterable<Recipe> findByIngredientsIgnoreCaseContaining(String search);


}
