package com.launchdarkly.tutorial.demousing2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class YourDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void insertData(String columnName, String anotherColumn, int numericColumn, boolean booleanColumn) {

        String sql = "INSERT INTO your_table (column_name, another_column, " +
                "numeric_column, boolean_column) " +
                "VALUES (:columnName, :anotherColumn, :numericColumn, :booleanColumn)";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("columnName", columnName);
        parameters.put("anotherColumn", anotherColumn);
        parameters.put("numericColumn", numericColumn);
        parameters.put("booleanColumn", booleanColumn);


        namedParameterJdbcTemplate.update(sql, parameters);
    }

    public void insertData(YourDataDTO yourData) {
        insertData(
                yourData.getColumnName(),
                yourData.getAnotherColumn(),
                yourData.getNumericColumn(),
                yourData.isBooleanColumn()
        );
    }



}