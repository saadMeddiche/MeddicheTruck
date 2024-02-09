package com.MeddicheTruck.mtmain.listeners;

import com.MeddicheTruck.mtmain.entities.Person;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class PersonListener {

    @PrePersist
    public void prePersist(Person person) {
        person.getPhoneNumbers().forEach(phoneNumber -> phoneNumber.setPerson(person));
    }
}
