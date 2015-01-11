package com.akifev.test;

import com.akifev.entities.CategoryEntity;
import com.akifev.entities.ProductEntity;
import com.akifev.services.CategoryService;
import com.akifev.services.ProductService;
import org.junit.Test;

import javax.persistence.EntityManager;

public class UnitTest {
    ProductService ps = new ProductService();
    CategoryService cs = new CategoryService();
    @Test
    public void testAddDelete(){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("UnitTestProduct");
        productEntity.setPrice(111);
        productEntity.setCategoryEntity(new CategoryEntity("UnitTestCategory"));
        EntityManager em = ps.getEntity();
        em.getTransaction().begin();
        productEntity = em.merge(productEntity);
        em.getTransaction().commit();
        em.getTransaction().begin();
        em.remove(productEntity);
        em.remove(productEntity.getCategoryEntity());
        em.getTransaction().commit();
    }

}
