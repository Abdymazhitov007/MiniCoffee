package org.example.coffeee.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_ingredient")
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Ingredient {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Long id;
        LocalDateTime createdDate;
        LocalDateTime updatedDate;
        Boolean isActive;

        String name;
}
