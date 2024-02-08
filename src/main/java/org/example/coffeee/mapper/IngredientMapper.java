package org.example.coffeee.mapper;

import org.example.coffeee.model.dto.IngredientDTO;
import org.example.coffeee.model.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientMapper extends BaseMapper<Ingredient, IngredientDTO>{

    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

}
