package org.example.coffeee.service;

import java.util.List;

public interface BaseService<D> {

    D save(D dto);
    D update(D dto);
    D getById(Long id);
    List<D> getAll();
    void deleteById(Long id);

}
