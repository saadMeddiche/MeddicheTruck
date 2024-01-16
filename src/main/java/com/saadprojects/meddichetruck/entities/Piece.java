package com.saadprojects.meddichetruck.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy =  "piece" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnoreProperties("piece")
    private List<PieceImage> images;

    @ManyToMany(mappedBy = "pieces")
    @JsonIgnoreProperties({"pieces" , "involvedPersons"})
    private List<Transaction> transactions;
}
