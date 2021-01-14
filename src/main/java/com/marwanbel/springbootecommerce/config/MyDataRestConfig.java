package com.marwanbel.springbootecommerce.config;

import com.marwanbel.springbootecommerce.Entity.Product;
import com.marwanbel.springbootecommerce.Entity.ProductCategory;
import javax.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.config.RepositoryConfiguration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager){
        entityManager= theEntityManager;
    }


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] unsupportedActions={HttpMethod.POST,HttpMethod.PUT,HttpMethod.DELETE};
        //Disable HTTP methods for POST, DELETE and PUT
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions));

        //Disable HTTP methods for POST, DELETE and PUT
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActions));

        //call internal helper method
        exposeIds(config);
        // alternative
        // config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(e -> e.getJavaType()).collect(Collectors.toList()).toArray(new Class[0]));
    }

    private void exposeIds(RepositoryRestConfiguration config) {

        //expose entity ids

        //get a list of all entity classes from the entityManager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        //Create an ArrayList of entity types
        List<Class> entityClasses = new ArrayList<>();

        //Get the Entity types for entities
        for(EntityType tempEntityTypes : entities ){
            entityClasses.add(tempEntityTypes.getJavaType());

        }
        //- expose the entity ids for the array of entity/domain type

        Class[]  domainTypes= entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);

    }
}
