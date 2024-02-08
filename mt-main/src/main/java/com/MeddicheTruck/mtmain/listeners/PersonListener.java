package com.MeddicheTruck.mtmain.listeners;

import com.MeddicheTruck.mtmain.entities.Person;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class PersonListener {

    @PrePersist
    public void prePersist(Object object) {
        Person person = (Person) object;

        System.out.println(person.toString());
    }
}
