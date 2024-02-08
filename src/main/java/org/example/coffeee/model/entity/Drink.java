package org.example.coffeee.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "tb_drink")
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;
    Boolean isActive;

    @ManyToOne
    DrinkType drinkType;
    String name;
    Double price;

    @OneToMany
    List<Ingredient> ingredients;
    Double size;


}
