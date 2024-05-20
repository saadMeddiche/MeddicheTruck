package com.MeddicheTruck.mtmain.dtos;

import com.MeddicheTruck.mtcore.annotations.AdaptedDto;
import com.MeddicheTruck.mtcore.annotations.IncludeOnAllRequests;
import com.MeddicheTruck.mtcore.annotations.IncludeOnPostRequest;
import com.MeddicheTruck.mtcore.annotations.IncludeOnPutRequest;
import com.MeddicheTruck.mtcore.models.BaseEntityDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AdaptedDto
public class PieceImageIDto extends BaseEntityDto {

    @IncludeOnPutRequest
    protected Long id;

    @IncludeOnAllRequests
    @NotNull(message = "The name of the piece image can not be null")
    protected String name;

    @IncludeOnPostRequest
    @NotNull(message = "The photo path of the piece can not be null")
    private Long pieceId;

    @IncludeOnPostRequest
    @NotNull(message = "The photo of the piece can not be null")
    protected byte[] photoInBase64;

}
