package com.akifev.services;

import com.akifev.entities.CategoryEntity;

import javax.persistence.*;

public class CategoryService {

    private static final String PERSISTENCE_UNIT_NAME = "unit";

    private EntityManager entity = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();

    public CategoryEntity get(int id){
        return entity.find(CategoryEntity.class, id);
    }

    public CategoryEntity add(CategoryEntity category){
        entity.getTransaction().begin();
        CategoryEntity categoryEntity = new CategoryEntity();
        //лучше persist(), а не merge. Это быстрее.
        categoryEntity = entity.merge(category);
        entity.getTransaction().commit();
        //возвращается одна сущность (categoryEntity), а персистится другая (category) - это точно ок?
        return categoryEntity;
    }

    public void delete(CategoryEntity categoryEntity){
        entity.getTransaction().begin();
        //упадет, если categoryEntity - detached entity. (см. джавадок remove)
        //лучше  entity.remove(get(categoryEntity.getId());
        entity.remove(categoryEntity);
        entity.getTransaction().commit();
    }

}
