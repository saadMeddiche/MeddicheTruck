package com.MeddicheTruck.mtcore.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class MyEntity {

    private String name;

    private List<MyField> myFields;

    @Override
    public String toString() {
        return "MyEntity{" +
                "\n  name='" + name + '\'' +
                ", \n  myFields=" + myFields +
                '}';
    }

}
