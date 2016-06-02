package com.sparksdev.flo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author bengill
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends MongoRepository<T, ID>, QueryDslPredicateExecutor<T>, CrudRepository<T, ID> {
}
