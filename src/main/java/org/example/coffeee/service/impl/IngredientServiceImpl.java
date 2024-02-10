package org.example.coffeee.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.coffeee.exception.exceptions.EmptyListException;
import org.example.coffeee.exception.exceptions.NotFoundException;
import org.example.coffeee.mapper.IngredientMapper;
import org.example.coffeee.model.dto.IngredientDTO;
import org.example.coffeee.model.entity.Ingredient;
import org.example.coffeee.repository.IngredientRepository;
import org.example.coffeee.service.IngredientService;
import org.example.coffeee.utils.Language;
import org.example.coffeee.utils.ResourceBundleLanguage;
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
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setIsActive(true);
        return IngredientMapper.INSTANCE.toDto(repository.save(entity));
    }

    @Override
    public IngredientDTO update(int languageOrdinal, IngredientDTO dto) {
        Ingredient entity = IngredientMapper.INSTANCE.toEntity(getById(languageOrdinal, dto.getId()));
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setIsActive(dto.getIsActive());
        entity.setName(dto.getName());
        return IngredientMapper.INSTANCE.toDto(repository.saveAndFlush(entity));
    }

    @Override
    public IngredientDTO getById(int languageOrdinal, Long id) {
        return IngredientMapper.INSTANCE.toDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ResourceBundleLanguage.periodMessage(Language.getLanguage(languageOrdinal), "entityNotFound"))));
    }

    @Override
    public List<IngredientDTO> getAll(int languageOrdinal) {
        List<Ingredient> drinks = repository.findAll();
        if(drinks.isEmpty())
            throw new EmptyListException(ResourceBundleLanguage.periodMessage(Language.getLanguage(languageOrdinal), "emptyList"));
        return IngredientMapper.INSTANCE.toDtos(drinks);
    }

    @Override
    public void deleteById(int languageOrdinal, Long id) {
        IngredientDTO dto = getById(languageOrdinal, id);
        dto.setIsActive(false);
        update(languageOrdinal, dto);
    }

    @Override
    public List<IngredientDTO> getIngredientsByNames(int languageOrdinal, List<String> ingredients) {

        List<Ingredient> ingredientList = new ArrayList<>();
        for(String item: ingredients) {
            ingredientList.add(IngredientMapper.INSTANCE.toEntity(getByName(languageOrdinal, item)));
        }

        return IngredientMapper.INSTANCE.toDtos(ingredientList);
    }

    @Override
    public IngredientDTO create(String name) {
        return save(IngredientDTO.builder()
                .name(name)
                .build());
    }

    @Override
    public List<IngredientDTO> saveAll(int languageOrdinal, List<String> names) {
        for (String i: names) {
            create(i);
        }
        return getAll(languageOrdinal);
    }

    @Override
    public IngredientDTO getByName(int languageOrdinal, String name) {
        return IngredientMapper.INSTANCE.toDto(repository.findByName(name)
                .orElseThrow(() -> new NotFoundException(ResourceBundleLanguage.periodMessage(Language.getLanguage(languageOrdinal), "entityNotFound"))));
    }
}
