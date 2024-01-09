package com.pib102.pibApp.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.LocalCacheScope;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SqlSessionFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.pib102.pibApp.**.mapper"})
public class MybatisConfig {

    @Bean
    public SqlSessionFactoryBeanCustomizer sqlSessionFactoryBeanCustomizer(DataSource dataSource) {
        return factoryBean -> {

            String[] packages = {
                    "com.pib102.pibApp.**.dto",
            };

            org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
            config.setMapUnderscoreToCamelCase(true);
            config.setLocalCacheScope(LocalCacheScope.STATEMENT);
            config.setDefaultExecutorType(ExecutorType.SIMPLE);

            factoryBean.setDataSource(dataSource);
            factoryBean.setTypeAliasesPackage(String.join(",", packages));
            factoryBean.setConfiguration(config);
        };
    }
}
