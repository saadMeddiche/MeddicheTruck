package com.MeddicheTruck.mtmain.dtos;

import com.MeddicheTruck.mtcore.models.BaseEntityDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PieceImageODto extends BaseEntityDto {

    private Long pieceId;

    private String name;

    private String photoPath;

}
