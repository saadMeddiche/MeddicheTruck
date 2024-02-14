package com.MeddicheTruck.mtmain.entities;

import com.MeddicheTruck.mtmain.listeners.PieceListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(PieceListener.class)
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The name of the piece can not be null")
    private String name;

    @OneToMany(mappedBy =  "piece" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnoreProperties("piece")
    private List<PieceImage> images ;

    @ManyToMany(mappedBy = "pieces")
    @JsonIgnoreProperties({"pieces" , "involvedPersons"})
    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public String toString() {
        return "Piece{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", images=" + images +
                '}';
    }

}
