package org.example.coffeee.service;

import java.util.List;

public interface BaseService<D> {

    D save(D dto);
    D update(int languageOrdinal, D dto);
    D getById(int languageOrdinal, Long id);
    List<D> getAll(int languageOrdinal);
    void deleteById(int languageOrdinal, Long id);

}
