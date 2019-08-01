package fi.academy.miniprojekti2backend.repositories;

//Created by: Kirsi Kujala

import fi.academy.miniprojekti2backend.entities.Category;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;

/** CrudRepository-rajapinnan perivä categoryRepository-rajapinta. Pakollinen apuluokka, ei omia metodeja
 * qualifier "category" erottaa recipeRepositorystä ja käsittelee useiden autowire-kandidaattien ongelman */
@Qualifier("category")
public interface CategoryRepository extends CrudRepository <Category, Integer> {


}
