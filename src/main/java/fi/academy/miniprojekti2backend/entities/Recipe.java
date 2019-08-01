package fi.academy.miniprojekti2backend.entities;

//Created by: Tuomas Lahti, edited by: Kirsi Kujala

import javax.persistence.*;
import java.io.Serializable;

//recipe-taulun entity
@Entity
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String ingredients;
    private String instructions;

    /* yhdistetään reseptitauluun kategoriataulu categoryid:n kautta, tämän ohjelman logiikalla reseptillä voi olla vain yksi kategoria,
    siksi many to one, vaikka periaatteessa voisi olla myös many to many */
    @ManyToOne
    @JoinColumn(name = "categoryid", referencedColumnName = "id")
    public Category category;

    public Recipe() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", instructions='" + instructions + '\'' +
                ", category=" + category +
                '}';
    }
}
