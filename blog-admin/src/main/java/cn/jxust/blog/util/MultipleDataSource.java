package cn.jxust.blog.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * 通用数据源设置
 *
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
	public static final String DATA_SOURCE_ADMIN = "adminDataSource";

    public static final Map<String,String> DATA_SOURCE_MAP = new HashMap<String, String>();

	    
    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();

    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }
    
    public static void removeDataSourceKey(){
    	dataSourceKey.remove();
    }
}
