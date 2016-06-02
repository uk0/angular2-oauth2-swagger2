package com.sparksdev.flo.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

import java.util.Set;

/**
 * @author bengill
 */
public class MongoUtils {

    /**
     * Removes the contents of the specified database.
     *
     * @param db
     *            the database to clear
     */
    public static void cleanDatabase(final DB db) {

        Set<String> collectionNames = db.getCollectionNames();
        for (String collectionName : collectionNames) {
            if (!"system.indexes".equals(collectionName)) {
                DBCollection coll = db.getCollection(collectionName);
                coll.dropIndexes();
                coll.drop();
            }
        }

        // Wait for all updates to complete
        db.getMongo().fsync(false);
    }


    public static void dropFloDb() {
        Mongo mongo = new Mongo();
        mongo.dropDatabase("flo_db");
    }

    public static void dropFloTestDb() {
        Mongo mongo = new Mongo();
        mongo.dropDatabase("flo_test_db");
    }
}
