package com.example.nursery_test1.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "subclass")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Subclass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;

    private String name;

    private String description;

    private String img;

    @ManyToOne
    @JoinColumn(name="cid")
//    @JsonBackReference
    /*不会重复遍历自己*/
//    @JsonIgnore
    @JsonIgnoreProperties(value = "subclassList")
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    //    @Override
//    public String toString() {
//        return "Subclass{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", category=" + category +
//                '}';
//    }
}
