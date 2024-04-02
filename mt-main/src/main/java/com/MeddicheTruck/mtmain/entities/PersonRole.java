package com.MeddicheTruck.mtmain.entities;

import com.MeddicheTruck.mtcore.models.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

/*
* Each Transaction have involved persons in it , and each person has his own role in that transaction . For Example, he can be a buyer or a seller or a manager.
* So this class is used to represent that
* */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonRole extends BaseEntity {

    private String name;

    @Override
    public String toString() {
        return "PersonRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
