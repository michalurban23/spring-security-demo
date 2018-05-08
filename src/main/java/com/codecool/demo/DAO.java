package com.codecool.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ComponentScan
@Component
@Configuration
public class DAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void init(){
        jdbcTemplate.execute("drop table rules if exists");
        jdbcTemplate.execute("create table rules(id SERIAL, role VARCHAR(255), path VARCHAR(255))");

        List<Object[]> splitUpNames = Arrays.asList("/admin ADMIN", "/user USER").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
//        splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO rules(path, role) VALUES (?,?)", splitUpNames);

//        log.info("Querying for customer records where first_name = 'Josh':");



    }

    public List<Rule> getRules() {
        ArrayList<Rule> result = new ArrayList<Rule>();

        jdbcTemplate.query(
                "SELECT id, role, path FROM rules",
                (rs, rowNum) -> new Rule(rs.getLong("id"), rs.getString("role"), rs.getString("path"))
        ).forEach(rule -> result.add(rule));

        return result;

    }


}
