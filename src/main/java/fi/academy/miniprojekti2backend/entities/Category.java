package fi.academy.miniprojekti2backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Transient
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    public List<Recipe> reseptit;

    public Category() {
        this.reseptit = new ArrayList<>();
    }

    public Category(String name, List<Recipe> reseptit) {
        this.name = name;
        this.reseptit = reseptit;
    }

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

    public List<Recipe> getReseptit() {
        return reseptit;
    }

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
