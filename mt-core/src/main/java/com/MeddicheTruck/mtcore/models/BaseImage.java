package com.MeddicheTruck.mtcore.models;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseImage extends BaseEntity {
    protected String name;
    protected String photoPath;
}
