package com.MeddicheTruck.mtcore.services.implementations;

import com.MeddicheTruck.mtcore.services.Naming;


public class TimeNaming implements Naming {
    
    private final String SEPARATOR = "#¤#";

    @Override
    public  String uniquifyWord(String word){

        long currentTimeInMillis = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();

        sb.append(word);
        sb.append(SEPARATOR);
        sb.append(currentTimeInMillis);

        return sb.toString();
    }
    
    @Override
    public String extractWord(String uniquifiedWord){
        
        String[] splitWord = uniquifiedWord.split(SEPARATOR);
        
        return splitWord[0];
    }
}
