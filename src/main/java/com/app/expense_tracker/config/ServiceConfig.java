package com.app.expense_tracker.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@MapperScan("com.app.expense_tracker.db_mappers")
@PropertySource("properties/app-${spring.profiles.active}.properties")
@PropertySource("properties/secret.properties")
public class ServiceConfig {
}
