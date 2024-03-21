package com.launchdarkly.tutorial.demousing2;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Configuration
public class CustomDataSourceConfig {






    @Bean
    @Primary
    public DataSource getSQLServerDS() {
        return populateDataSource1();
    }

    @Bean(name = "oracleDataSource")
    public DataSource getOracleDS() {
        return populateDataSource1();
    }


//    private DataSource populateDataSource() {
//
//        Map<String, String> datahikarimap = new HashMap<>();
//        Properties properties = new Properties();
//        properties.setProperty("jdbcUrl", "jdbc:mysql://localhost:3306/database");
//        properties.setProperty("username", "root");
//        properties.setProperty("password", "root");
//        properties.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
//        try {
//            properties.putAll(datahikarimap);
//            System.out.println("Properties:" + properties);
//            HikariConfig hikariConfig = new HikariConfig(properties);
//            return new HikariDataSource(hikariConfig);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error creating HikariDataSource", e);
//        }
//    }


    private DataSource populateDataSource1() {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
                Map<String, String> datahikarimap = new HashMap<>();
                for (String propertyName : properties.stringPropertyNames()) {
                    System.out.println("Property name  " + propertyName + "  "+ "Values " + properties.getProperty(propertyName));
                    datahikarimap.put(propertyName, properties.getProperty(propertyName));
                }
                HikariConfig hikariConfig = new HikariConfig(properties);
                return new HikariDataSource(hikariConfig);
            } else {
                throw new IOException("Unable to load application.properties. InputStream is null.");
            }
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }


    @Autowired
    private Environment env;

    private DataSource populateDataSource3() {
        Properties properties = new Properties();
        properties.setProperty("jdbcUrl", env.getProperty("spring.datasource.url"));
        properties.setProperty("username", env.getProperty("spring.datasource.username"));
        properties.setProperty("password", env.getProperty("spring.datasource.password"));
        properties.setProperty("driverClassName", env.getProperty("spring.datasource.driver-class-name"));
        try {
            HikariConfig hikariConfig = new HikariConfig(properties);
            return new HikariDataSource(hikariConfig);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating HikariDataSource", e);
        }
    }

}