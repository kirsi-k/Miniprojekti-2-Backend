package fi.academy.miniprojekti2backend.repositories;

//Created by: Kirsi Kujala

import fi.academy.miniprojekti2backend.entities.Recipe;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/** Reseptirepositorio eli reseptidatan käsittelyyn käytettävä rajapinta, joka perii CrudRepository-rajapinnan. Rajapinnassa luodaan
 * metodit reseptien hakuun id:n, nimen, ainesosien ja kategorian nimen perusteella
 * qualifier "recipe" erottaa restcontrollerille recipeRepositoryn categoryRepositorysta */
@Qualifier("recipe")
public interface RecipeRepository extends CrudRepository <Recipe,Integer> {

    Iterable<Recipe> findByNameIgnoreCaseContaining(@Param("search") String search);
    List<Recipe> findRecipeById(Integer id);
    Iterable<Recipe> findByIngredientsIgnoreCaseContaining(String search);
    List<Recipe> findAllByCategory_Name(String categoryname);

}
