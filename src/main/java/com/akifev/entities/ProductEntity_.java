package com.akifev.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductEntity.class)
public class ProductEntity_{
    public static volatile SingularAttribute<ProductEntity, String> name;
    public static volatile SingularAttribute<ProductEntity, Integer> price;
    public static volatile SingularAttribute<ProductEntity, CategoryEntity> categoryEntity;
}