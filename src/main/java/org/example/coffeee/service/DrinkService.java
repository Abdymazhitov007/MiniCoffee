package org.example.coffeee.service;

import org.example.coffeee.model.dto.DrinkDTO;
import org.example.coffeee.model.entity.Drink;
import org.example.coffeee.model.request.DrinkCreateRequest;
import org.example.coffeee.model.response.MainResponse;

import java.util.List;
import java.util.Set;

public interface DrinkService extends BaseService<DrinkDTO> {

    DrinkDTO create(int languageOrdinal, DrinkCreateRequest request);

    List<DrinkDTO> saveAll(int languageOrdinal, List<DrinkCreateRequest> dtoList);

    List<DrinkDTO> getLikeName(int languageOrdinal, String name);

    Set<DrinkDTO> getByType(int languageOrdinal, String type);

    List<DrinkDTO> filterTFT(int languageOrdinal, String drinkType, Double priceFrom, Double priceTo);
    List<DrinkDTO> filterTF(int languageOrdinal, String drinkType, Double priceFrom);
    List<DrinkDTO> filterTT(int languageOrdinal, String drinkType, Double priceTo);
    List<DrinkDTO> filterFT(int languageOrdinal, Double priceFrom, Double priceTo);
    List<DrinkDTO> filterT(int languageOrdinal, Double priceTo);
    List<DrinkDTO> filterF(int languageOrdinal, Double priceFrom);

    List<MainResponse> toResponse(Iterable<DrinkDTO> drinks);
    MainResponse toResponse(DrinkDTO drink);
}
