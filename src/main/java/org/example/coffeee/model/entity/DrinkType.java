package org.example.coffeee.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_drink_type")
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DrinkType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private
    Long id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Boolean isActive;

    private String name;

    public String toString() {
        return "\nDrinkType(id=" + this.getId() + ", createdDate=" + this.getCreatedDate() + ", updatedDate=" + this.getUpdatedDate() + ", isActive=" + this.getIsActive() + ", name=" + this.getName() + ")";
    }
}
