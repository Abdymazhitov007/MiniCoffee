package org.example.coffeee.service;

import org.example.coffeee.model.dto.DrinkTypeDTO;

public interface DrinkTypeService extends BaseService<DrinkTypeDTO>{

    DrinkTypeDTO getByName(String name);

}
