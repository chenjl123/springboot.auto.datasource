package com.example.demo.config;

/**
 * 动态数据源持有者，负责利用ThreadLocal存取数据源名称
 */
public class DynamicDataSourceHolder {
    /**
     * 本地线程共享对象
     */
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<String>();

    public static void putDataSource(String name) {
    	System.out.println("切换到{"+name+"}数据源");
        THREAD_LOCAL.set(name);
    }

    public static String getDataSource() {
        return THREAD_LOCAL.get();
    }

    public static void removeDataSource() {
        THREAD_LOCAL.remove();
    }
}
