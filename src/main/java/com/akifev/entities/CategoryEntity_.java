package com.akifev.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CategoryEntity.class)
public class CategoryEntity_ {
    public static volatile SingularAttribute<CategoryEntity, Integer> id;
    public static volatile SingularAttribute<CategoryEntity, String> name;
}
