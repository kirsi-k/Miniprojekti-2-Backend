package fi.academy.miniprojekti2backend.entities;

//Created by: Kirsi Kujala

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**category-taulun entity, joka toteuttaa Serializable-rajapinnan */
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    /** Yhdistää category-taulun ja recipe-taulun: kategorialla voi olla monta reseptiä, siksi tässä category on one to many */
    @Transient
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    public List<Recipe> reseptit;

    /** luo tyhjän kategorian */
    public Category() {
        this.reseptit = new ArrayList<>();
    }
    /** Luo kategorian, jolla on tietty nimi ja tietty lista reseptejä
     * @param name kategorian nimi
     * @param reseptit kategorian reseptit */
    public Category(String name, List<Recipe> reseptit) {
        this.name = name;
        this.reseptit = reseptit;
    }

    /** hakee kategorian id:n */
    public Integer getId() {
        return id;
    }
    /** asettaa kategorian id:n */
    public void setId(Integer id) {
        this.id = id;
    }
    /** hakee kategorian nimen */
    public String getName() {
        return name;
    }
    /** asettaa kategorian nimen */
    public void setName(String name) {
        this.name = name;
    }
    /** hakee kategorian reseptit */
    public List<Recipe> getReseptit() {
        return reseptit;
    }
    /** asettaa kategorian reseptit */
    public void setReseptit(List<Recipe> reseptit) {
        this.reseptit = reseptit;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }
}
