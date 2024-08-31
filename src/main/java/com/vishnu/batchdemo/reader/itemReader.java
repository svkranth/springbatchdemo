package com.vishnu.batchdemo.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class itemReader implements ItemReader<String> {

    String[] itemsList = {"one","two","three","four","five","six","seven","eight","nine","ten"};
    int i = 0;
    

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        
        System.out.println("Inside reader");
        if(i<itemsList.length){
            return itemsList[i++];
        }else{
            return null;
        }
    }

}
