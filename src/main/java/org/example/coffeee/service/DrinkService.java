package org.example.coffeee.service;

import org.example.coffeee.model.dto.DrinkDTO;
import org.example.coffeee.model.entity.Drink;
import org.example.coffeee.model.request.DrinkCreateRequest;
import org.example.coffeee.model.response.MainResponse;

import java.util.List;
import java.util.Set;

public interface DrinkService extends BaseService<DrinkDTO> {

    DrinkDTO create(DrinkCreateRequest request);

    List<DrinkDTO> saveAll(List<DrinkCreateRequest> dtoList);

    List<DrinkDTO> getLikeName(String name);

    Set<DrinkDTO> getByType(String type);

    List<DrinkDTO> filterTFT(String drinkType, Double priceFrom, Double priceTo);
    List<DrinkDTO> filterTF(String drinkType, Double priceFrom);
    List<DrinkDTO> filterTT(String drinkType, Double priceTo);
    List<DrinkDTO> filterFT(Double priceFrom, Double priceTo);
    List<DrinkDTO> filterT(Double priceTo);
    List<DrinkDTO> filterF(Double priceFrom);

    List<MainResponse> toResponse(Iterable<DrinkDTO> drinks);
    MainResponse toResponse(DrinkDTO drink);
}
