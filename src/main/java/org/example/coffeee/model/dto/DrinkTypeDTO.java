package org.example.coffeee.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DrinkTypeDTO {

    Long id;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;
    Boolean isActive;

    String name;

}
