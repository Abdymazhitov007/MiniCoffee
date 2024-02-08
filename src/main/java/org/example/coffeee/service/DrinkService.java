package org.example.coffeee.service;

import org.example.coffeee.model.dto.DrinkDTO;
import org.example.coffeee.model.request.DrinkCreateRequest;

public interface DrinkService extends BaseService<DrinkDTO> {

    DrinkDTO create(DrinkCreateRequest request);

}
