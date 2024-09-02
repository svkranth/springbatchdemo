package com.vishnu.batchdemo.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.vishnu.batchdemo.model.instructorCSV;

@Component
public class instructorItemWriter implements ItemWriter<instructorCSV> {

    @Override
    public void write(Chunk< ? extends instructorCSV> instructorDetails) throws Exception {
        for (instructorCSV instructor : instructorDetails) {
            System.out.println(instructor.toString());
        }
    }

}
