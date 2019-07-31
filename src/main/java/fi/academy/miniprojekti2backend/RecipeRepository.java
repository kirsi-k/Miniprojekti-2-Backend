package fi.academy.miniprojekti2backend;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends CrudRepository <Recipe,Integer> {

    Iterable<Recipe> findRecipeByNameIgnoreCase(@Param("search") String search);
    List<Recipe> findRecipeById(Integer id);

}
