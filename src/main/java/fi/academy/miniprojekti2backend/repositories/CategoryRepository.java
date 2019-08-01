package fi.academy.miniprojekti2backend.repositories;

//Created by: Kirsi Kujala

import fi.academy.miniprojekti2backend.entities.Category;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;

@Qualifier("category")
public interface CategoryRepository extends CrudRepository <Category, Integer> {


}
