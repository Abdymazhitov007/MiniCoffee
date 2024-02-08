package org.example.coffeee.repository;

import org.example.coffeee.model.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Override
    @Query(value = "SELECT * from tb_ingredient where is_active = true", nativeQuery = true)
    List<Ingredient> findAll();

    @Override
    @Query(value = "select * from tb_ingredient where is_active = true", nativeQuery = true)
    Optional<Ingredient> findById(Long id);

    Ingredient findByName(String name);

}
