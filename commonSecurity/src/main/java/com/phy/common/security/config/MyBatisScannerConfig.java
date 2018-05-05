package com.phy.common.security.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @desc: 初始化MapperScannerConfigurer类, 并自定义MyBatis的Mapper接口扫描路径和配置sessionFactory
 * @author: tivy
 * @createTime: 2018-05-03 10:31:38
 * @history:
 * @version: v1.0
 */
@Configuration
public class MyBatisScannerConfig {
    @Bean
    public MapperScannerConfigurer MapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.phy.common.security.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }
}
