package org.example.coffeee.model.dto;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.coffeee.model.entity.DrinkType;
import org.example.coffeee.model.entity.Ingredient;

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
    Double size;

}
