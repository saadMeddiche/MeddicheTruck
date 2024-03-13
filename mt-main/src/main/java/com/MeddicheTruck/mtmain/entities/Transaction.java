package com.MeddicheTruck.mtmain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.MeddicheTruck.mtmain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.TenantId;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @TenantId
    private String tenant;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String Description;

    private LocalDateTime timeTransaction;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToMany(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    @JoinTable(
            name = "transaction_piece",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "piece_id")
    )
    @JsonIgnoreProperties("transactions")
    private List<Piece> pieces;

    @OneToMany(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    @JsonIgnoreProperties("transaction")
    private List<InvolvedPerson> involvedPersons;
}
