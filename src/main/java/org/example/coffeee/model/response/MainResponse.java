package org.example.coffeee.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class MainResponse {

    private String drinkType;
    private String name;
    private Double price;
    private List<String> ingredientsName;
    private Double volume;

}
