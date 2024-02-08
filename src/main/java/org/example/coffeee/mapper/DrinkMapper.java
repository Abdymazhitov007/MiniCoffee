package org.example.coffeee.mapper;

import org.example.coffeee.model.dto.DrinkDTO;
import org.example.coffeee.model.entity.Drink;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DrinkMapper extends BaseMapper<Drink, DrinkDTO> {

    DrinkMapper INSTANCE = Mappers.getMapper(DrinkMapper.class);

}
