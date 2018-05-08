package com.codecool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@SpringBootApplication
public class App 
{

    @Bean
    public DataSource dataSource(){
        return
                new EmbeddedDatabaseBuilder()
                        .setType(EmbeddedDatabaseType.H2)
                        .setName("testDB;MODE=MySQL")
//                        .addScript("classpath:db/H2.schema.sql")
//                        .addScript("classpath:db/H2.data.sql")
                        .build();
    }


    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }
}
