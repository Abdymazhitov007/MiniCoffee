package org.example.coffeee.mapper;

import java.util.List;
import java.util.Set;

public interface BaseMapper<E, D> {
    E toEntity(D d);

    D toDto(E e);

    List<E> toEntities(List<D> dtos);
    Set<E> toEntities(Set<D> dtos);
    Set<D> toDtos(Set<E> entities);

    List<D> toDtos(List<E> entities);
}
