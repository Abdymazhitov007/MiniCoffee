package org.example.coffeee.service;

import org.example.coffeee.model.dto.IngredientDTO;

import java.util.List;

public interface IngredientService extends BaseService<IngredientDTO>{

    List<IngredientDTO> getIngredientsByNames(int languageOrdinal, List<String> ingredients);
    IngredientDTO create(String name);
    List<IngredientDTO> saveAll(int languageOrdinal, List<String> names);
    IngredientDTO getByName(int languageOrdinal, String name);
}
