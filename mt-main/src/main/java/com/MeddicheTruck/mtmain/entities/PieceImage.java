package com.MeddicheTruck.mtmain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PieceImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Transient
    private byte[] photoInBase64Format;

//    @Lob
//    @Column(name = "photo", columnDefinition="BLOB")
    private String photoPath;

    @ManyToOne
    @JsonIgnoreProperties("pieceImages")
    private Piece piece;

    @Override
    public String toString() {
        return "PieceImage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photo=" + Arrays.toString(photoInBase64Format) +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }


}
