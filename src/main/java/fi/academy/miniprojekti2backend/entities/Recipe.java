package fi.academy.miniprojekti2backend.entities;

//Created by: Tuomas Lahti, edited by: Kirsi Kujala

import javax.persistence.*;
import java.io.Serializable;

/** recipe-taulun entity, joka toteuttaa Serializable-rajapinnan */
@Entity
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String ingredients;
    private String instructions;

    /** Yhdistää kategoriataulun reseptitauluun categoryid-kolumnin kautta. Tämän ohjelman logiikalla reseptillä voi olla vain yksi kategoria,
     * siksi yhdistäminen tapahtuu many to one-annotaatiolla, vaikka periaatteessa voisi olla myös many to many (reseptillä useampi kategoria)*/
    @ManyToOne
    @JoinColumn(name = "categoryid", referencedColumnName = "id")
    public Category category;

    /** Luo tyhjän reseptin */
    public Recipe() {

    }
    /** Luo reseptin, jolle annetaan parametrit
     * @param name reseptin nimi
     * @param description reseptin kuvaus
     * @param ingredients reseptin ainesosat
     * @param instructions reseptin valmistusohjeet
     * @param category reseptin kategoria */
    public Recipe(String name, String description, String ingredients, String instructions, Category category) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.category = category;
    }

    /** hakee reseptin id:n */
    public Integer getId() {
        return id;
    }
    /** asettaa reseptin id:n */
    public void setId(Integer id) {
        this.id = id;
    }
    /** hakee reseptin nimen */
    public String getName() {
        return name;
    }
    /** asettaa reseptin nimen */
    public void setName(String name) {
        this.name = name;
    }
    /** hakee reseptin kuvauksen */
    public String getDescription() {
        return description;
    }
    /** asettaa reseptin kuvauksen */
    public void setDescription(String description) {
        this.description = description;
    }
    /** hakee reseptin ainesosat */
    public String getIngredients() {
        return ingredients;
    }
    /** asettaa reseptin ainesosat */
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    /** hakee reseptin valmistusohjeet */
    public String getInstructions() {
        return instructions;
    }
    /** asettaa reseptin valmistusohjeet */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    /** hakee reseptin kategorian */
    public Category getCategory() {
        return category;
    }
    /** asettaa reseptin kategorian */
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
