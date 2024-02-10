package org.example.coffeee.model.request;

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


    String drinkType;
    String name;
    Double price;
    List<String> ingredientsName;
    Double volume;


    @Override
    public String toString() {
        return "\nDrinkCreateRequest(" +
                "drinkType=" + this.getDrinkType() + ", " +
                "name=" + this.getName() + ", price=" + this.getPrice() + ", " +
                "ingredientsName=" + this.getIngredientsName() + ", " +
                "volume=" + this.getVolume() + ")";
    }
}
