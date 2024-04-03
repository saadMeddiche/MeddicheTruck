package com.MeddicheTruck.mtmain.dtos;

import com.MeddicheTruck.mtcore.annotations.AdaptedDto;
import com.MeddicheTruck.mtcore.annotations.IncludeOnPostRequest;
import com.MeddicheTruck.mtcore.models.BaseImageDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AdaptedDto
public class PieceImageIDto extends BaseImageDto {

    @NotNull(message = "The piece id can not be null")
    private Long pieceId;

    @IncludeOnPostRequest
    protected byte[] photoInBase64;

}
