package bbq.db.project.dao.utils;

import com.sun.org.apache.xerces.internal.impl.PropertyManager;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: maohao
 * Date: 14-12-4
 * Time: 下午11:11
 * To change this template use File | Settings | File Templates.
 */
public class Constants {

    private static final String CONFIG = "config";
    private static final ResourceBundle RESOURCE_BUNDLE =ResourceBundle.getBundle(CONFIG);

    public static final String CODE_SUCCESS = "A00000";
    public static final String INNER_ERROR = "E00001";
    public static final String INVALID_PARAMS = "E00002";
    public static final String NO_DATA = "E00003";
    public static final String USER_EXISTS = "E00004";
    public static final String CAN_NOT_ACCESS = "E00005";
    public static final String NO_ENOUGH_AMOUNT = "E00006";
    public static final String NO_ENOUGH_BOOK = "E00007";

    public static final Integer NORMAL_USER = 1;
    public static final Integer ADMIN = 2;

    public static final String MONGODB_URL = Constants.getString("mongodb.url");
    public static final Integer MONGODB_PORT = Integer.valueOf(Constants.getString("mongodb.port"));
    public static final String MONGODB_DB = Constants.getString("mongodb.database");
    public static final String MONGO_IS_OPEN = Constants.getString("mongodb.open");
    public static final String MONGO_LOG_COLLECTION = "bbq_log";

    public static final String MONGO_KEY_USERID = "USER_ID";
    public static final String MONGO_KEY_USERNAME = "USER_NAME";
    public static final String MONGO_KEY_USERTYPE = "USER_TYPE";
    public static final String MONGO_KEY_ACTIONNAME = "ACTION_NAME";
    public static final String MONGO_KEY_ACTIONTIME = "ACTION_TIME";
    public static final String MONGO_KEY_PARAMS = "PARAMS";

    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            throw new RuntimeException( "! config : "+ key + '!');
        }
    }
}
