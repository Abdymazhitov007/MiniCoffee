package org.example.coffeee.repository;

import org.example.coffeee.model.entity.Drink;
import org.example.coffeee.model.entity.DrinkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrinkTypeRepository extends JpaRepository<DrinkType, Long> {

    @Override
    @Query(value = "SELECT * from tb_drink_type where is_active = true", nativeQuery = true)
    List<DrinkType> findAll();

    @Override
    @Query(value = "select * from tb_drink_type where is_active = true", nativeQuery = true)
    Optional<DrinkType> findById(Long id);

    DrinkType findByName(String name);

}
