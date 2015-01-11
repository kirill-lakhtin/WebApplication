package com.akifev.services;

import com.akifev.entities.*;
import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.*;


public class ProductService {
    public EntityManager entity = Persistence.createEntityManagerFactory("unit").createEntityManager();
    public ProductEntity get(int id){
        return entity.find(ProductEntity.class, id);
    }

    public ProductEntity add(ProductEntity product){
        entity.getTransaction().begin();
        TypedQuery<CategoryEntity> typedQuery = searchCategoryByName(product);
        List<CategoryEntity> result = typedQuery.getResultList();
        if(result.size()!=0){
            product.setCategoryEntity(result.get(0));
        }
        ProductEntity merged = entity.merge(product);
        entity.getTransaction().commit();
        return merged;
    }

    private TypedQuery<CategoryEntity> searchCategoryByName(ProductEntity product) {
        CriteriaBuilder cb = entity.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> criteriaQuery = cb.createQuery(CategoryEntity.class);
        Root<CategoryEntity> root = criteriaQuery.from(CategoryEntity.class);
        Predicate predicate = cb.equal(root.get("name"), product.getCategoryEntity().getName());
        criteriaQuery.where(predicate);
        return entity.createQuery(criteriaQuery);
    }

    public List<ProductEntity> search(Map<String, String> criterions){
        CriteriaBuilder cb = entity.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> criteriaQuery = cb.createQuery(ProductEntity.class);
        Root<ProductEntity> root = criteriaQuery.from(ProductEntity.class);
        List<Predicate> predicates = new ArrayList();
        if(!criterions.get("name").equals(""))
            predicates.add(cb.like(cb.upper(root.get(ProductEntity_.name)), criterions.get("name").toUpperCase() + "%"));
        if(!criterions.get("price_from").equals(""))
            predicates.add(cb.gt(root.get(ProductEntity_.price), Integer.parseInt(criterions.get("price_from"))));
        if(!criterions.get("price_to").equals(""))
            predicates.add(cb.lt(root.get(ProductEntity_.price), Integer.parseInt(criterions.get("price_to"))));
        if(!criterions.get("category_name").equals("")) {
            Join<ProductEntity, CategoryEntity> join = root.join(ProductEntity_.categoryEntity);
            predicates.add(cb.like(cb.upper(join.get(CategoryEntity_.name)), criterions.get("category_name").toUpperCase() + "%"));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<ProductEntity> typedQuery = entity.createQuery(criteriaQuery);
        return typedQuery.getResultList();

    }
    public void delete(int id){
        entity.getTransaction().begin();
        entity.remove(get(id));
        entity.getTransaction().commit();
    }

    public void delete(ProductEntity product){
        entity.getTransaction().begin();
        entity.remove(product);
        entity.getTransaction().commit();
    }


    public List<ProductEntity> getAll(){
        TypedQuery<ProductEntity> products = entity.createNamedQuery("ProductEntity.getAll", ProductEntity.class);
        return products.getResultList();
    }

    public EntityManager getEntity() {
        return entity;
    }
}

