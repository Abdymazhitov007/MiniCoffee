package org.example.coffeee.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.coffeee.mapper.DrinkMapper;
import org.example.coffeee.mapper.DrinkTypeMapper;
import org.example.coffeee.mapper.IngredientMapper;
import org.example.coffeee.model.dto.DrinkDTO;
import org.example.coffeee.model.dto.IngredientDTO;
import org.example.coffeee.model.entity.Drink;
import org.example.coffeee.model.entity.Ingredient;
import org.example.coffeee.repository.IngredientRepository;
import org.example.coffeee.service.IngredientService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository repository;

    @Override
    public IngredientDTO save(IngredientDTO dto) {
        Ingredient entity = IngredientMapper.INSTANCE.toEntity(dto);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setIsActive(true);
        return IngredientMapper.INSTANCE.toDto(repository.save(entity));
    }

    @Override
    public IngredientDTO update(IngredientDTO dto) {
        Ingredient entity = repository.findById(dto.getId()).orElseThrow();
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setName(dto.getName());
        return IngredientMapper.INSTANCE.toDto(repository.saveAndFlush(entity));
    }

    @Override
    public IngredientDTO getById(Long id) {
        return IngredientMapper.INSTANCE.toDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<IngredientDTO> getAll() {
        List<Ingredient> drinks = repository.findAll();
//        if(drinks.isEmpty()) throw new Exception(" ");
        return IngredientMapper.INSTANCE.toDtos(drinks);
    }

    @Override
    public void deleteById(Long id) {
        IngredientDTO dto = getById(id);
        dto.setIsActive(false);
        update(dto);
    }

    @Override
    public List<IngredientDTO> getIngredientsByNames(List<String> ingredients) {

        List<Ingredient> ingredientList = new ArrayList<>();
        for(String item: ingredients) {
            ingredientList.add(repository.findByName(item));
        }

        return IngredientMapper.INSTANCE.toDtos(ingredientList);
    }
}
