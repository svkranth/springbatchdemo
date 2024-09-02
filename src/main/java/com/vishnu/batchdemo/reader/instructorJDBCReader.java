package com.vishnu.batchdemo.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.vishnu.batchdemo.model.instructorCSV;

@Configuration
public class instructorJDBCReader {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<instructorCSV> inJdbcCursorItemReader(){
        JdbcCursorItemReader<instructorCSV> reader = new JdbcCursorItemReader<instructorCSV>();

        reader.setDataSource(dataSource);
        reader.setSql("select id, youtube_channel as Channel, hobby from instructor_detail");
        reader.setRowMapper(new BeanPropertyRowMapper<instructorCSV>(){
            {
                setMappedClass(instructorCSV.class);
            }
        });
        return reader;
    }

}
