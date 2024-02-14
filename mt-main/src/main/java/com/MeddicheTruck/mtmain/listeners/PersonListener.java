package com.MeddicheTruck.mtmain.listeners;

import com.MeddicheTruck.mtmain.entities.Person;
import jakarta.persistence.PrePersist;


public class PersonListener {

    @PrePersist
    public void prePersist(Person person) {
        person.getPhoneNumbers().forEach(phoneNumber -> phoneNumber.setPerson(person));
    }
}
