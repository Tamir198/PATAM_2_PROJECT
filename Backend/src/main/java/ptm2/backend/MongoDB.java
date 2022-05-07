package ptm2.backend;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.internal.MongoClientImpl;

public class MongoDB {
    public static MongoClient mongoClient;
    public static MongoDatabase database;
    public static MongoCollection test;
    public static void main(String[] args){
        //mongoClient = new MongoClient(new MongoClientImpl()) {
        //}
    }
}
