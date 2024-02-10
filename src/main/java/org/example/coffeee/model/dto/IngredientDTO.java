package org.example.coffeee.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class IngredientDTO {

    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Boolean isActive;

    private String name;

    public String toString() {
        return "\nIngredientDTO(id=" + this.getId() + ", createdDate=" + this.getCreatedDate() + ", updatedDate=" + this.getUpdatedDate() + ", isActive=" + this.getIsActive() + ", name=" + this.getName() + ")";
    }
}
