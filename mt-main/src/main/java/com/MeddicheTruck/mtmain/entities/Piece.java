package com.MeddicheTruck.mtmain.entities;

import com.MeddicheTruck.mtmain.listeners.PieceListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.TenantId;
import org.springframework.data.rest.core.annotation.RestResource;

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

    @TenantId
    @JsonIgnore
    private String tenant;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The name of the piece can not be null")
    private String name;

    @OneToMany(mappedBy =  "piece" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnoreProperties("piece")
    @RestResource(exported = false)
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
