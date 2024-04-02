package com.MeddicheTruck.mtmain.dtos;

import com.MeddicheTruck.mtcore.models.BaseImageDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PieceImageODto extends BaseImageDto {

    private Long pieceId;

    private String photoPath;

}
