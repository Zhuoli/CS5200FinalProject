package com.bbq.db.project.mongodb;

import bbq.db.project.dao.utils.Constants;
import com.bbq.db.project.model.mongo.UserLog;
import com.mongodb.*;
import org.jboss.resteasy.util.DateUtil;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public List<UserLog> getUserLogs() {

        List<UserLog> userLogs = new ArrayList<UserLog>();

        if(Boolean.valueOf(Constants.MONGO_IS_OPEN)) {
            DBCursor cursor = db.getCollection(Constants.MONGO_LOG_COLLECTION).find();
            while(cursor.hasNext()) {
                UserLog userLog = new UserLog();
                DBObject dbObject = cursor.next();
                userLog.setUserId((Integer)dbObject.get(Constants.MONGO_KEY_USERID));
                userLog.setUserName((String)dbObject.get(Constants.MONGO_KEY_USERNAME));
                userLog.setUserType((Integer)dbObject.get(Constants.MONGO_KEY_USERTYPE));
                userLog.setActionName((String)dbObject.get(Constants.MONGO_KEY_ACTIONNAME));
                userLog.setActionTime((Date)dbObject.get(Constants.MONGO_KEY_PARAMS));
                userLog.setParams((String)dbObject.get(Constants.MONGO_KEY_ACTIONTIME));
                userLogs.add(userLog);
            }
        }

        return userLogs;
    }
}
