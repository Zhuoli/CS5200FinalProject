package com.bbq.db.project.mongodb;

import bbq.db.project.dao.utils.Constants;
import com.mongodb.*;
import org.jboss.resteasy.util.DateUtil;

import java.net.UnknownHostException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: maohao
 * Date: 14-12-8
 * Time: 1:39 pm
 * To change this template use File | Settings | File Templates.
 */
public class MongoDBManager {

    private static MongoDBManager mongoDBManager = null;
    private static DB db;

    private MongoDBManager(){}

    public static MongoDBManager getDBInstance() {

        try {
            if(Boolean.valueOf(Constants.MONGO_IS_OPEN) &&
                    mongoDBManager == null) {
                mongoDBManager = new MongoDBManager();
                MongoClient mongo = new MongoClient(Constants.MONGODB_URL, Constants.MONGODB_PORT);
                db = mongo.getDB(Constants.MONGODB_DB);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }

        return mongoDBManager;
    }

    public void add(Integer userId, String userName, Integer userType, String actionName, String params) {

        if(Boolean.valueOf(Constants.MONGO_IS_OPEN)) {
            DBObject dbObject = new BasicDBObject();
            dbObject.put(Constants.MONGO_KEY_USERID, userId);
            dbObject.put(Constants.MONGO_KEY_USERNAME, userName);
            dbObject.put(Constants.MONGO_KEY_USERTYPE, userType);
            dbObject.put(Constants.MONGO_KEY_ACTIONNAME, actionName);
            dbObject.put(Constants.MONGO_KEY_PARAMS, params);
            dbObject.put(Constants.MONGO_KEY_ACTIONTIME, DateUtil.formatDate(new Date(), "yyyyMMdd HH:mm:ss"));
            db.getCollection(Constants.MONGO_LOG_COLLECTION).insert(dbObject);
        }
    }
}
