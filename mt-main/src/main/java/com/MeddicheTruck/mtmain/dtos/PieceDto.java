package com.MeddicheTruck.mtmain.dtos;

import com.MeddicheTruck.mtcore.models.BaseEntityDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PieceDto  extends BaseEntityDto {

    @NotNull(message = "The name of the piece can not be null")
    @NotBlank(message = "The name of the piece can not be blank")
    private String name;
}