package com.MeddicheTruck.mtcore.services.implementations;

import com.MeddicheTruck.mtcore.services.Naming;
import org.springframework.stereotype.Component;


@Component
public class TimeNaming implements Naming {
    
    private final String SEPARATOR = "_";

    @Override
    public  String uniquifyWord(String word){

        if(word == null){
            throw new RuntimeException("The word can not be null");
        }

        long currentTimeInMillis = System.currentTimeMillis();

        return new StringBuilder()
                .append(word)
                .append(SEPARATOR)
                .append(currentTimeInMillis)
                .toString();
    }
    
    @Override
    public String extractWord(String uniquifiedWord){
        
      if(uniquifiedWord == null){
          throw new RuntimeException("uniquifiedWord can not be null");
      }

      if(!uniquifiedWord.contains(SEPARATOR)){
          throw new RuntimeException("This uniquifiedWord was not created by TimeNaming class , or at least it do not contain the separator");
      }

      String[] splitWord = uniquifiedWord.split(SEPARATOR);

     if(splitWord[0] != null && !splitWord[0].isBlank() && !splitWord[0].isEmpty()  ){
         return  splitWord[0];
     }else{
         throw new RuntimeException("No word exist only the time exist");
     }
    }
}
