package com.MeddicheTruck.mtmain.entities;

import com.MeddicheTruck.mtcore.models.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Piece extends BaseEntity {

    private String name;

    private Boolean inStock = true;

    @OneToMany(mappedBy =  "piece" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonIgnoreProperties("piece")
    private List<PieceImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "piece" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonIgnoreProperties("piece")
    private List<PieceTransaction> transactions = new ArrayList<>();

    @Override
    public String toString() {
        return "Piece{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
