package com.vishnu.batchdemo.proessor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class itemProcessor implements ItemProcessor<String,String>{

    @Override
    public String process(String item) throws Exception {
        System.out.println("Inside processor");
        return new StringBuilder(item).reverse().toString();
    }

}
