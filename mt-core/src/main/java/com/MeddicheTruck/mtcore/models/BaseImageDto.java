package com.MeddicheTruck.mtcore.models;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseImageDto extends BaseEntityDto {
    @NotNull(message = "The name can not be null")
    @NotBlank(message = "The name can not be blank")
    protected String name;
}
