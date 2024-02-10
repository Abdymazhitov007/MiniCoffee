package org.example.coffeee.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.coffeee.mapper.DrinkMapper;
import org.example.coffeee.mapper.DrinkTypeMapper;
import org.example.coffeee.mapper.IngredientMapper;
import org.example.coffeee.model.dto.DrinkDTO;
import org.example.coffeee.model.dto.IngredientDTO;
import org.example.coffeee.model.entity.Drink;
import org.example.coffeee.model.entity.DrinkType;
import org.example.coffeee.model.entity.Ingredient;
import org.example.coffeee.model.request.DrinkCreateRequest;
import org.example.coffeee.model.response.MainResponse;
import org.example.coffeee.repository.DrinkRepository;
import org.example.coffeee.service.DrinkService;
import org.example.coffeee.service.DrinkTypeService;
import org.example.coffeee.service.IngredientService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository repository;
    private final DrinkTypeService DTService;
    private final IngredientService IService;

    @Override
    public DrinkDTO save(DrinkDTO dto) {
        Drink entity = DrinkMapper.INSTANCE.toEntity(dto);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setIsActive(true);
        return DrinkMapper.INSTANCE.toDto(repository.save(entity));
    }

    @Override
    public DrinkDTO update(DrinkDTO dto) {
        Drink entity = repository.findById(dto.getId()).orElseThrow();
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setDrinkType(DrinkTypeMapper.INSTANCE.toEntity(dto.getDrinkType()));
        entity.setIngredients(IngredientMapper.INSTANCE.toEntities(dto.getIngredients()));
        entity.setName(dto.getName());
        entity.setIsActive(dto.getIsActive());
        entity.setPrice(dto.getPrice());
        entity.setVolume(dto.getVolume());
        return DrinkMapper.INSTANCE.toDto(repository.saveAndFlush(entity));
    }

    @Override
    public DrinkDTO getById(Long id) {
        return DrinkMapper.INSTANCE.toDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<DrinkDTO> getAll() {
        List<Drink> drinks = repository.findAll();
//        if(drinks.isEmpty()) throw new Exception(" ");
        return DrinkMapper.INSTANCE.toDtos(drinks);
    }

    @Override
    public void deleteById(Long id) {
        DrinkDTO dto = getById(id);
        dto.setIsActive(false);
        update(dto);
    }

    @Override
    public List<DrinkDTO> saveAll(List<DrinkCreateRequest> dtoList) {

        System.out.println(dtoList);

        for (DrinkCreateRequest i: dtoList) {
            create(i);
        }

        return getAll();
    }

    @Override
    public DrinkDTO create(DrinkCreateRequest request) {
        List<Ingredient> ingredientList = IngredientMapper.INSTANCE.toEntities(IService.getIngredientsByNames(request.getIngredientsName()));
        DrinkType drinkType = DrinkTypeMapper.INSTANCE.toEntity(DTService.getByName(request.getDrinkType()));

        Drink entity = new Drink();
        entity.setName(request.getName());
        entity.setIngredients(ingredientList);
        entity.setPrice(request.getPrice());
        entity.setVolume(request.getVolume());
        entity.setDrinkType(drinkType);

        return save(DrinkMapper.INSTANCE.toDto(entity));
    }

    @Override
    public List<DrinkDTO> getLikeName(String name) {
        return DrinkMapper.INSTANCE.toDtos(repository.findByNameContaining(name));
    }

    @Override
    public Set<DrinkDTO> getByType(String type) {

        Set<Drink> response = new HashSet<>();
        response.addAll(DrinkMapper.INSTANCE.toEntities(getLikeName(type)));
        response.addAll(repository.findByDrinkTypeName(type));

        return DrinkMapper.INSTANCE.toDtos(response);
    }

    @Override
    public List<DrinkDTO> filterTFT(String drinkType, Double priceFrom, Double priceTo) {
        return DrinkMapper.INSTANCE.toDtos(repository.findByDrinkTypeNameAndPriceBetween(drinkType, priceFrom, priceTo));
    }

    @Override
    public List<DrinkDTO> filterTF(String drinkType, Double priceFrom) {
        return DrinkMapper.INSTANCE.toDtos(repository.findByDrinkTypeNameAndPriceGreaterThanEqual(drinkType, priceFrom));
    }

    @Override
    public List<DrinkDTO> filterTT(String drinkType, Double priceTo) {
        return DrinkMapper.INSTANCE.toDtos(repository.findByDrinkTypeNameAndPriceLessThanEqual(drinkType, priceTo));
    }

    @Override
    public List<DrinkDTO> filterFT(Double priceFrom, Double priceTo) {
        return DrinkMapper.INSTANCE.toDtos(repository.findByPriceBetween(priceFrom, priceTo));
    }

    @Override
    public List<DrinkDTO> filterF(Double priceFrom) {
        return DrinkMapper.INSTANCE.toDtos(repository.findByPriceGreaterThanEqual(priceFrom));
    }

    @Override
    public List<DrinkDTO> filterT(Double priceTo) {
        return DrinkMapper.INSTANCE.toDtos(repository.findByPriceLessThanEqual(priceTo));
    }

    @Override
    public List<MainResponse> toResponse(Iterable<DrinkDTO> drinks) {
        List<MainResponse> response = new ArrayList<>();
        for (DrinkDTO i: drinks) {
            response.add(toResponse(i));
        }
        return response;
    }

    @Override
    public MainResponse toResponse(DrinkDTO drink) {
        return MainResponse.builder()
                .name(drink.getName())
                .drinkType(drink.getDrinkType().getName())
                .ingredientsName(drink.getIngredients().stream().map(IngredientDTO::getName).toList())
                .price(drink.getPrice())
                .volume(drink.getVolume())
                .build();
    }



}
