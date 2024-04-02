package com.MeddicheTruck.mtmain.dtos;

import com.MeddicheTruck.mtcore.models.BaseImageDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PieceImageIDto extends BaseImageDto {

    @NotNull(message = "The piece id can not be null")
    private Long pieceId;

    @NotNull(message = "The photoInBase64 can not be null")
    protected byte[] photoInBase64;

}
