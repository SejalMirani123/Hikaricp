package com.launchdarkly.tutorial.demousing2;

import net.sf.jsqlparser.schema.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {


    @Autowired
    private DataSource dataSource;



    public enum DatabaseSource {
        SQLServer,Oracle;
    }

    @Bean
    @Autowired
    public Database dataSource(CustomDataSourceConfig customDataSourceConfig) {
        Database database = new Database("");
        Map<Object, Object> sources = new HashMap<Object, Object>();
        sources.put(DatabaseSource.SQLServer, customDataSourceConfig.getSQLServerDS());
        sources.put(DatabaseSource.Oracle,customDataSourceConfig.getOracleDS());

        return database;
    }


    @Bean
    @Autowired
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource database) {
        return new NamedParameterJdbcTemplate(database);
    }

}

