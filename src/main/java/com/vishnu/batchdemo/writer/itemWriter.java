package com.vishnu.batchdemo.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class itemWriter implements ItemWriter<String>{

    @Override
    public void write(Chunk< ? extends String> items) throws Exception {
        System.out.println("Inside writer");
        for (String item : items) {
           System.out.println(item); 
        }
    }

}
