package com.sparksdev.flo.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.StringUtils;
import com.mongodb.*;
import com.mongodb.gridfs.GridFS;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@EnableScheduling
@Configuration
@EnableAsync
@EnableMongoRepositories(basePackages = "com.sparksdev.flo")
public class SpringMongoConfig extends AbstractMongoConfiguration {

    /** The name of the mongo template bean. */
    public static final String MONGO_TEMPLATE = "mongoTemplate";

    /** The address or comma separated list of addresses to the Mongo Database and it's replicas. */
    @SuppressWarnings("UnusedDeclaration")
    @Value("${mongo.addr:localhost}")
    private String addr;

    /** The name of the database to use. */
    @SuppressWarnings("UnusedDeclaration")
    @Value("${mongo.db:flo_db}")
    private String db;

    // This method NEEDS the static keyword here.  Not sure why though.
    @Order(value = 1)
    @Bean(name = "propertySourcesPlaceholderConfigurer")
    public static PropertySourcesPlaceholderConfigurer myPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public @Bean MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory((MongoClient)mongo(), getDatabaseName());
    }

    @Bean(name = MONGO_TEMPLATE)
    @Override
    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    public MongoTemplate mongoTemplate() throws Exception {
        final MongoTemplate mongoTemplate = super.mongoTemplate();
        return mongoTemplate;
    }

    @Override public MappingMongoConverter mappingMongoConverter() throws Exception {
        MappingMongoConverter mappingMongoConverter =  super.mappingMongoConverter();
        mappingMongoConverter.setMapKeyDotReplacement("\\+");
        return mappingMongoConverter;
    }

    @Override
    protected String getDatabaseName() {
        return db;
    }

    /**
     * Obtains the {@link Mongo} database.
     *
     * @return the Mongo database
     */
    @Bean
    @DependsOn("propertySourcesPlaceholderConfigurer")
    public Mongo mongo() {
        try {
            return connect(addr);
        } catch (/*UnknownHostException |*/ MongoException e) {
            throw new RuntimeException(e);
        }
    }

    private Mongo connect(final String db) {
        Mongo mongo = new MongoClient(db, 27017); //createServerAddresses("db"/*this.addr*/));
        mongo.setWriteConcern(WriteConcern.SAFE);
        return mongo;
    }


    /**
     * Obtains the Mongo Grid File System.
     *
     * @return the GridFS
     */
    @Bean
    public GridFS gridFs() {
        try {
            return new GridFS(mongoTemplate().getDb());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
