package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 数据源配置
 */
@Configuration
@EnableScheduling
@MapperScan(basePackages = "com.example.demo.dao", sqlSessionFactoryRef   = "sqlSessionFactory")
public class DataSourceConfig {

	 //数据源1
    @Bean(name = "orderDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.order") // application.properteis中对应属性的前缀
    public DataSource orderDataSource() {
        return DataSourceBuilder.create().build();
    }
 
    //数据源2
    @Bean(name = "msgDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.msg") // application.properteis中对应属性的前缀
    public DataSource msgDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    //@Primary
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(orderDataSource());
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<Object, Object>();
        dsMap.put("orderDataSource", orderDataSource());
        dsMap.put("msgDataSource", msgDataSource());
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }
    
    
    /**
     * @param dynamicDataSource
     * @return
     * @throws Exception
     */
    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        //扫描mapper配置
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        //设置数据库实体
        sqlSessionFactoryBean.setTypeAliasesPackage("com.example.demo.entity");
        return sqlSessionFactoryBean.getObject();
    }
    
    /**
     * 配置@Transactional注解事物
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}