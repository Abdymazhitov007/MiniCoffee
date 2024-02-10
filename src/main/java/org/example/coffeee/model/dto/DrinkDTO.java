package org.example.coffeee.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DrinkDTO {

    Long id;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;
    Boolean isActive;
    DrinkTypeDTO drinkType;
    String name;
    Double price;
    List<IngredientDTO> ingredients;
    Double volume;

}
