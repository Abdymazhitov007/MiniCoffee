package org.example.coffeee.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.coffeee.exception.exceptions.EmptyListException;
import org.example.coffeee.exception.exceptions.NotFoundException;
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
import org.example.coffeee.utils.Language;
import org.example.coffeee.utils.ResourceBundleLanguage;
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
    public DrinkDTO update(int languageOrdinal, DrinkDTO dto) {
        Drink entity = DrinkMapper.INSTANCE.toEntity(getById(languageOrdinal, dto.getId()));
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
    public DrinkDTO getById(int languageOrdinal, Long id) {
        return DrinkMapper.INSTANCE.toDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ResourceBundleLanguage.periodMessage(Language.getLanguage(languageOrdinal), "entityNotFound"))));
    }

    @Override
    public List<DrinkDTO> getAll(int languageOrdinal) {
        List<Drink> drinks = repository.findAll();
        if(drinks.isEmpty())
            throw new EmptyListException(ResourceBundleLanguage.periodMessage(Language.getLanguage(languageOrdinal), "emptyList"));
        return DrinkMapper.INSTANCE.toDtos(drinks);
    }

    @Override
    public void deleteById(int languageOrdinal, Long id) {
        DrinkDTO dto = getById(languageOrdinal, id);
        dto.setIsActive(false);
        update(languageOrdinal, dto);
    }

    @Override
    public List<DrinkDTO> saveAll(int languageOrdinal, List<DrinkCreateRequest> dtoList) {

        System.out.println(dtoList);

        for (DrinkCreateRequest i: dtoList) {
            create(languageOrdinal, i);
        }

        return getAll(2);
    }

    @Override
    public DrinkDTO create(int languageOrdinal, DrinkCreateRequest request) {
        List<Ingredient> ingredientList = IngredientMapper.INSTANCE.toEntities(IService.getIngredientsByNames(languageOrdinal, request.getIngredientsName()));
        DrinkType drinkType = DrinkTypeMapper.INSTANCE.toEntity(DTService.getByName(languageOrdinal, request.getDrinkType()));

        Drink entity = new Drink();
        entity.setName(request.getName());
        entity.setIngredients(ingredientList);
        entity.setPrice(request.getPrice());
        entity.setVolume(request.getVolume());
        entity.setDrinkType(drinkType);

        return save(DrinkMapper.INSTANCE.toDto(entity));
    }

    @Override
    public List<DrinkDTO> getLikeName(int languageOrdinal, String name) {
        List<Drink> response = repository.findByNameContaining(name);
        if(response.isEmpty()) throw new EmptyListException(ResourceBundleLanguage.periodMessage(Language.getLanguage(languageOrdinal), "emptyList"));
        return DrinkMapper.INSTANCE.toDtos(response);
    }

    @Override
    public Set<DrinkDTO> getByType(int languageOrdinal, String type) {

        Set<Drink> response = new HashSet<>(DrinkMapper.INSTANCE.toEntities(getLikeName(languageOrdinal, type)));
        List<Drink> list = repository.findByDrinkTypeName(type);
        if (list.isEmpty()) throw new EmptyListException(ResourceBundleLanguage.periodMessage(Language.getLanguage(languageOrdinal), "emptyList"));
        response.addAll(list);

        return DrinkMapper.INSTANCE.toDtos(response);
    }

    @Override
    public List<DrinkDTO> filterTFT(int languageOrdinal, String drinkType, Double priceFrom, Double priceTo) {
        return DrinkMapper.INSTANCE.toDtos(repository.findByDrinkTypeNameAndPriceBetween(drinkType, priceFrom, priceTo));
    }

    @Override
    public List<DrinkDTO> filterTF(int languageOrdinal, String drinkType, Double priceFrom) {
        return DrinkMapper.INSTANCE.toDtos(repository.findByDrinkTypeNameAndPriceGreaterThanEqual(drinkType, priceFrom));
    }

    @Override
    public List<DrinkDTO> filterTT(int languageOrdinal, String drinkType, Double priceTo) {
        return DrinkMapper.INSTANCE.toDtos(repository.findByDrinkTypeNameAndPriceLessThanEqual(drinkType, priceTo));
    }

    @Override
    public List<DrinkDTO> filterFT(int languageOrdinal, Double priceFrom, Double priceTo) {
        return DrinkMapper.INSTANCE.toDtos(repository.findByPriceBetween(priceFrom, priceTo));
    }

    @Override
    public List<DrinkDTO> filterF(int languageOrdinal, Double priceFrom) {
        return DrinkMapper.INSTANCE.toDtos(repository.findByPriceGreaterThanEqual(priceFrom));
    }

    @Override
    public List<DrinkDTO> filterT(int languageOrdinal, Double priceTo) {
        List<DrinkDTO> drinkDTOS = DrinkMapper.INSTANCE.toDtos(repository.findByPriceLessThanEqual(priceTo));
        if(drinkDTOS.isEmpty()) throw new EmptyListException(ResourceBundleLanguage.periodMessage(Language.getLanguage(languageOrdinal), "emptyList"));
        return drinkDTOS;
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
