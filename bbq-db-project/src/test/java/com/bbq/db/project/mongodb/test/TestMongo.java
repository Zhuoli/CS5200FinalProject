package com.bbq.db.project.mongodb.test;

import bbq.db.project.dao.utils.Constants;
import com.bbq.db.project.model.Address;
import com.bbq.db.project.mongodb.MongoDBManager;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: maohao
 * Date: 14-12-8
 * Time: 下午1:12
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations={
                "/mybatis/mapper/*",
                "/mybatis/mybatis-config.xml",
                "/spring/spring.xml"
        }
)
public class TestMongo {

    @Test
    public void selectAllAddress() throws Exception{

        if(Boolean.valueOf(Constants.MONGO_IS_OPEN)) {
            MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
            DB db = mongoClient.getDB("bbq");
            Set<String> collections = db.getCollectionNames();
            for(String s : collections) {
                System.out.println(s);
            }
            List<String> dbs = mongoClient.getDatabaseNames();
            for(String b : dbs){
                System.out.println(b);
            }

            //MongoDBManager.getDBInstance().add(123, "test", 1, "buy a book", "buy one");
         }
    }
}
