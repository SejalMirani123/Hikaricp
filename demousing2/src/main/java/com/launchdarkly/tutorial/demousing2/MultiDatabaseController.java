package com.launchdarkly.tutorial.demousing2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MultiDatabaseController {


    @Autowired
    private YourDao yourDao;

    @PostMapping("/insert")
    public String insertData(@RequestBody YourDataDTO yourData) throws SQLException {
        yourDao.insertData(yourData);
        return "Data inserted successfully!";
    }
}






