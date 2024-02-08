package org.example.coffeee.service;

import org.example.coffeee.model.dto.IngredientDTO;

import java.util.List;

public interface IngredientService extends BaseService<IngredientDTO>{

    List<IngredientDTO> getIngredientsByNames(List<String> ingredients);

}
