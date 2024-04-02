package com.MeddicheTruck.mtmain.dtos;

import com.MeddicheTruck.mtcore.models.BaseImageDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PieceImageDto extends BaseImageDto {

    @NotNull(message = "The piece id can not be null")
    private Long pieceId;
}
