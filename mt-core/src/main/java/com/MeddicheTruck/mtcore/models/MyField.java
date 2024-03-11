package com.MeddicheTruck.mtcore.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MyField {

    private String name;

    private String type;

    private boolean required;

    private String defaultValue;

    private String id;

    @Override
    public String toString() {
        return "MyField{" +
                "\n  name='" + name + '\'' +
                ", \n  type='" + type + '\'' +
                ", \n  required=" + required +
                ", \n  defaultValue='" + defaultValue + '\'' +
                ", \n  id='" + id + '\'' +
                '}';
    }
}
