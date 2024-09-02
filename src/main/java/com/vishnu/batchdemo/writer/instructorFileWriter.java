package com.vishnu.batchdemo.writer;

import java.io.IOException;
import java.io.Writer;

import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.vishnu.batchdemo.model.instructorCSV;

@Configuration
public class instructorFileWriter {

    @Bean
    public FlatFileItemWriter<instructorCSV> instructorCsvWriter(){
        FlatFileItemWriter<instructorCSV> empWriter = new FlatFileItemWriter<instructorCSV>();

        empWriter.setResource(new FileSystemResource("C:\\\\Users\\\\Vishnu\\\\Desktop\\\\story\\instructor_extract.csv"));
        //The below line is to write header to extract file
        empWriter.setHeaderCallback(new FlatFileHeaderCallback(){

            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("id,Channel,hobby");
            }
            
        });

        //The below line will write data into extract file by mapping from model class
        empWriter.setLineAggregator(new DelimitedLineAggregator<instructorCSV>(){
            {
                setFieldExtractor(new BeanWrapperFieldExtractor<instructorCSV>(){
                    {
                        setNames(new String[] {"id","Channel","hobby"});
                    }
                });
                //Below line is optional if extract file is comma delimited as comma is default delimiter.
                setDelimiter(",");
            }
        });

        return empWriter;
    }
}
