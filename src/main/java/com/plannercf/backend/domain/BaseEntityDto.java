package com.plannercf.backend.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntityDto implements Serializable {
    private Long id;

    public BaseEntityDto(@Nullable Long id) {
        this.id = id;
    }

    public BaseEntityDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntityDto that = (BaseEntityDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
