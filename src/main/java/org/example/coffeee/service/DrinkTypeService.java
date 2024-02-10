package org.example.coffeee.service;

import org.example.coffeee.model.dto.DrinkTypeDTO;

import java.util.List;

public interface DrinkTypeService extends BaseService<DrinkTypeDTO>{

    DrinkTypeDTO getByName(int languageOrdinal, String name);

    DrinkTypeDTO create(String name);

    List<DrinkTypeDTO> saveAll(int languageOrdinal, List<String> names);
}
