package com.MeddicheTruck.mtcore.models;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseImageDto extends BaseEntityDto {
    protected String name;
    protected String photoPath;
}
