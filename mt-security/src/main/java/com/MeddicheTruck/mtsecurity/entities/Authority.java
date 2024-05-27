package com.MeddicheTruck.mtsecurity.entities;

import com.MeddicheTruck.mtsecurity.enums.BaseAuthority;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "security_schema")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Authority(BaseAuthority baseAuthority) {
        this.id = baseAuthority.ordinal() + 1;
        this.name = baseAuthority.name();
    }
}
