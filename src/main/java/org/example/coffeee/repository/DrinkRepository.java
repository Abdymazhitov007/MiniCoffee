package org.example.coffeee.repository;

import org.example.coffeee.model.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {

    @Override
    @Query(value = "SELECT * from tb_drink where is_active = true", nativeQuery = true)
    List<Drink> findAll();

    @Override
    @Query(value = "select * from tb_drink where is_active = true and id = :id", nativeQuery = true)
    Optional<Drink> findById(Long id);

    List<Drink> findByNameContaining(String name);

    List<Drink> findByDrinkTypeName(String name);

    List<Drink> findByDrinkTypeNameAndPriceBetween(String drinkType, double priceFrom, double priceTo);
    List<Drink> findByDrinkTypeNameAndPriceGreaterThanEqual(String drinkType,Double priceFrom);
    List<Drink> findByDrinkTypeNameAndPriceLessThanEqual(String drinkType,Double priceFrom);
    List<Drink> findByPriceBetween(Double priceFrom, Double priceTo);
    List<Drink> findByPriceGreaterThanEqual(Double priceFrom);
    List<Drink> findByPriceLessThanEqual(Double priceTo);


}
