package com.akifev.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "categoryEntity")
    private Set<ProductEntity> productEntity;

    public CategoryEntity(){

    }

    public CategoryEntity(String name) {
        this.name = name;
    }

    public Set<ProductEntity> getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(Set<ProductEntity> productEntity) {
        this.productEntity = productEntity;
    }

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
}
