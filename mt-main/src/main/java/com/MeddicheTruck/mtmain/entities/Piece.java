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

    @NotNull(message = "The name of the piece can not be null")
    private String name;

    @OneToMany(mappedBy =  "piece" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnoreProperties("piece")
    @RestResource(exported = false)
    private List<PieceImage> images ;

    @ManyToMany(mappedBy = "pieces")
    @JsonIgnoreProperties({"pieces" , "involvedPersons"})
    private List<Transaction> transactions = new ArrayList<>();

}
