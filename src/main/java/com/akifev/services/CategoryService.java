package com.akifev.services;

import com.akifev.entities.CategoryEntity;

import javax.persistence.*;

public class CategoryService {
    public EntityManager entity = Persistence.createEntityManagerFactory("unit").createEntityManager();

    public CategoryEntity get(int id){
        return entity.find(CategoryEntity.class, id);
    }

    public CategoryEntity add(CategoryEntity category){
        entity.getTransaction().begin();
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity = entity.merge(category);
        entity.getTransaction().commit();
        return categoryEntity;
    }

    public void delete(CategoryEntity categoryEntity){
        entity.getTransaction().begin();
        entity.remove(categoryEntity);
        entity.getTransaction().commit();
    }

}
