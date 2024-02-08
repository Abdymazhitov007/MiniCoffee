package org.example.coffeee.mapper;

import org.example.coffeee.model.dto.DrinkTypeDTO;
import org.example.coffeee.model.entity.DrinkType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DrinkTypeMapper extends BaseMapper<DrinkType, DrinkTypeDTO>{

    DrinkTypeMapper INSTANCE = Mappers.getMapper(DrinkTypeMapper.class);

}
