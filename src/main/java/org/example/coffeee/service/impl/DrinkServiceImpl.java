package org.example.coffeee.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.coffeee.mapper.DrinkMapper;
import org.example.coffeee.mapper.DrinkTypeMapper;
import org.example.coffeee.mapper.IngredientMapper;
import org.example.coffeee.model.dto.DrinkDTO;
import org.example.coffeee.model.dto.DrinkTypeDTO;
import org.example.coffeee.model.entity.Drink;
import org.example.coffeee.model.request.DrinkCreateRequest;
import org.example.coffeee.repository.DrinkRepository;
import org.example.coffeee.service.DrinkService;
import org.example.coffeee.service.DrinkTypeService;
import org.example.coffeee.service.IngredientService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        entity.setPrice(dto.getPrice());
        entity.setSize(dto.getSize());
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
    public DrinkDTO create(DrinkCreateRequest request) {
        return save(DrinkDTO.builder()
                .name(request.getName())
                .price(request.getPrice())
                .size(request.getSize())
                .drinkType(DTService.getByName(request.getDrinkType()))
                .ingredients(IService.getIngredientsByNames(request.getIngredientsName()))
                .build());
    }
}
