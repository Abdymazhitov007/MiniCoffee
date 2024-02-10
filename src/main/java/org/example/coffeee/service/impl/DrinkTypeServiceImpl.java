package org.example.coffeee.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.coffeee.exception.exceptions.EmptyListException;
import org.example.coffeee.exception.exceptions.NotFoundException;
import org.example.coffeee.mapper.DrinkTypeMapper;
import org.example.coffeee.model.dto.DrinkTypeDTO;
import org.example.coffeee.model.entity.DrinkType;
import org.example.coffeee.repository.DrinkTypeRepository;
import org.example.coffeee.service.DrinkTypeService;
import org.example.coffeee.utils.Language;
import org.example.coffeee.utils.ResourceBundleLanguage;
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
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setIsActive(true);
        return DrinkTypeMapper.INSTANCE.toDto(repository.save(entity));
    }

    @Override
    public DrinkTypeDTO update(int languageOrdinal, DrinkTypeDTO dto) {
        DrinkType entity = DrinkTypeMapper.INSTANCE.toEntity(getById(languageOrdinal, dto.getId()));
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setName(dto.getName());
        entity.setIsActive(dto.getIsActive());
        return DrinkTypeMapper.INSTANCE.toDto(repository.saveAndFlush(entity));
    }

    @Override
    public DrinkTypeDTO getById(int languageOrdinal, Long id) {
        return DrinkTypeMapper.INSTANCE.toDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ResourceBundleLanguage.periodMessage(Language.getLanguage(languageOrdinal), "entityNotFound"))));
    }

    @Override
    public List<DrinkTypeDTO> getAll(int languageOrdinal) {
        List<DrinkType> drinkTypes = repository.findAll();
        if(drinkTypes.isEmpty()) throw new EmptyListException(ResourceBundleLanguage.periodMessage(Language.getLanguage(languageOrdinal), "emptyList"));
        return DrinkTypeMapper.INSTANCE.toDtos(drinkTypes);
    }



    @Override
    public void deleteById(int languageOrdinal, Long id) {
        DrinkTypeDTO dto = getById(languageOrdinal, id);
        dto.setIsActive(false);
        update(languageOrdinal, dto);
    }

    @Override
    public DrinkTypeDTO getByName(int languageOrdinal, String name) {
        return DrinkTypeMapper.INSTANCE.toDto(repository.findByName(name)
                .orElseThrow(() -> new NotFoundException(ResourceBundleLanguage.periodMessage(Language.getLanguage(languageOrdinal), "entityNotFound"))));
    }

    @Override
    public DrinkTypeDTO create(String name) {
        return save(
                DrinkTypeDTO.builder()
                        .name(name)
                        .build()
        );
    }

    @Override
    public List<DrinkTypeDTO> saveAll(int languageOrdinal, List<String> names) {
        for (String i: names) {
            create(i);
        }
        return getAll(languageOrdinal);
    }
}
