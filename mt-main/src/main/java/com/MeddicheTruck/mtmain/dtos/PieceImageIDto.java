package com.MeddicheTruck.mtmain.dtos;

import com.MeddicheTruck.mtcore.annotations.AdaptedDto;
import com.MeddicheTruck.mtcore.annotations.IncludeOnAllRequests;
import com.MeddicheTruck.mtcore.annotations.IncludeOnPostRequest;
import com.MeddicheTruck.mtcore.annotations.IncludeOnPutRequest;
import com.MeddicheTruck.mtcore.models.BaseEntityDto;
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

    @IncludeOnPostRequest
    protected String name;

    @IncludeOnAllRequests
    private Long pieceId;

    @IncludeOnPostRequest
    protected byte[] photoInBase64;

}
