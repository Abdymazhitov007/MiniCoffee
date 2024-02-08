package org.example.coffeee.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.coffeee.exception.exceptions.EmptyListException;
import org.example.coffeee.mapper.DrinkMapper;
import org.example.coffeee.mapper.DrinkTypeMapper;
import org.example.coffeee.mapper.IngredientMapper;
import org.example.coffeee.model.dto.DrinkDTO;
import org.example.coffeee.model.dto.DrinkTypeDTO;
import org.example.coffeee.model.entity.Drink;
import org.example.coffeee.model.entity.DrinkType;
import org.example.coffeee.repository.DrinkTypeRepository;
import org.example.coffeee.service.DrinkTypeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DrinkTypeServiceImpl implements DrinkTypeService {

    private final DrinkTypeRepository repository;

    @Override
    public DrinkTypeDTO save(DrinkTypeDTO dto) {
        DrinkType entity = DrinkTypeMapper.INSTANCE.toEntity(dto);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setIsActive(true);
        return DrinkTypeMapper.INSTANCE.toDto(repository.save(entity));
    }

    @Override
    public DrinkTypeDTO update(DrinkTypeDTO dto) {
        DrinkType entity = repository.findById(dto.getId()).orElseThrow();
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setName(dto.getName());
        return DrinkTypeMapper.INSTANCE.toDto(repository.saveAndFlush(entity));
    }

    @Override
    public DrinkTypeDTO getById(Long id) {
        return DrinkTypeMapper.INSTANCE.toDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<DrinkTypeDTO> getAll() {
        List<DrinkType> drinkTypes = repository.findAll();
        if(drinks.isEmpty()) throw new EmptyListException(" ");
        return DrinkTypeMapper.INSTANCE.toDtos(drinkTypes);
    }



    @Override
    public void deleteById(Long id) {
        DrinkTypeDTO dto = getById(id);
        dto.setIsActive(false);
        update(dto);
    }

    @Override
    public DrinkTypeDTO getByName(String name) {
        return DrinkTypeMapper.INSTANCE.toDto(repository.findByName(name));
    }
}
