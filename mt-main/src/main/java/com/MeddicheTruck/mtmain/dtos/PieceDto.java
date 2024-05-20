package com.MeddicheTruck.mtmain.dtos;

import com.MeddicheTruck.mtcore.annotations.AdaptedDto;
import com.MeddicheTruck.mtcore.annotations.IncludeOnAllRequests;
import com.MeddicheTruck.mtcore.annotations.IncludeOnPutRequest;
import com.MeddicheTruck.mtcore.models.BaseEntityDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AdaptedDto
public class PieceDto  extends BaseEntityDto {

    @IncludeOnPutRequest
    public Long id;

    @NotNull(message = "The name of the piece can not be null")
    @NotBlank(message = "The name of the piece can not be blank")
    @IncludeOnAllRequests
    private String name;

    @IncludeOnAllRequests
    @NotNull(message = "The stock of the piece can not be null")
    private Boolean inStock;
}
