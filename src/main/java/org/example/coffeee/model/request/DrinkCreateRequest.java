package org.example.coffeee.model.request;

import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DrinkCreateRequest {

    @Pattern(regexp = "^(fd)$")
    String drinkType;
    String name;
    Double price;
    List<String> ingredientsName;
    Double size;

}
