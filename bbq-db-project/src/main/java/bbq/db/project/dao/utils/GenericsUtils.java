package bbq.db.project.dao.utils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GenericsUtils {
	private static final Log log = LogFactory.getLog(GenericsUtils.class);

	private GenericsUtils() {
	}

	@SuppressWarnings({ "rawtypes" })
	public static Class getSuperClassGenricType(Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class clazz, int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			log.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			log.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			log.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return Object.class;
		}
		return (Class) params[index];
	}
	
	@SuppressWarnings("rawtypes")
	public static List<Class<?>> getGenericClassWithReturnClassOfMethod(Method m){
		List<Class<?>> result=new ArrayList<Class<?>>();
		ParameterizedType pt=(ParameterizedType)m.getGenericReturnType();
		
		for (Type t:pt.getActualTypeArguments()){
			result.add((Class)t);
		}
		return result;
	}

	public static Class<?> UniqueGenericClassWithReturnClassOfMethod(Method m){
		ParameterizedType pt=(ParameterizedType)m.getGenericReturnType();
		if(pt.getActualTypeArguments().length>1)
			throw new RuntimeException("GenericClassWithReturnClass not alone!");
		return pt.getActualTypeArguments()[0].getClass();
	}
	
}
