package org.example.coffeee.controller;

import lombok.RequiredArgsConstructor;
import org.example.coffeee.model.request.DrinkCreateRequest;
import org.example.coffeee.service.DrinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/drink")
@RequiredArgsConstructor
public class DrinkController {

    private final DrinkService service;

//    GET api/v1/drink
//    параметры(drinkType, priceFrom,, priceTo)
//  -> апи возвращает список напитков в зависимости от выбранных фильтров.
//    1. Тип напитка, если тип не выбран то по умолчанию выбераются все типы напитков
//    2. priceFrom минимальная цена, если не выбрано то занчит нет минимальной цены
//    3. priceTo максимальная цена, если не выбрано то значит не максимальной цены
//    Пример: Если указали dateFrom но не указали dateTo значит возвращаем списко напитков
//    цена которых не ниже priceFrom

    @GetMapping("/")
    public ResponseEntity<?> filter(@RequestParam(required = false) String drinkType,
                                    @RequestParam(required = false) int languageOrdinal,
                                    @RequestParam(required = false) Double priceFrom,
                                    @RequestParam(required = false) Double priceTo) {

        if (drinkType != null && priceFrom != null && priceTo != null) {
            return ResponseEntity.ok(service.toResponse(service.filterTFT(languageOrdinal, drinkType, priceFrom, priceTo)));
        } else if (drinkType != null && priceFrom != null) {
            return ResponseEntity.ok(service.toResponse(service.filterTF(languageOrdinal, drinkType, priceFrom)));
        } else if (drinkType != null && priceTo != null) {
            return ResponseEntity.ok(service.toResponse(service.filterTT(languageOrdinal, drinkType, priceTo)));
        } else if (drinkType != null) {
            return ResponseEntity.ok(service.toResponse(service.getByType(languageOrdinal, drinkType)));
        } else if (priceFrom != null && priceTo != null) {
            return ResponseEntity.ok(service.toResponse(service.filterFT(languageOrdinal, priceFrom, priceTo)));
        } else if (priceFrom != null) {
            return ResponseEntity.ok(service.toResponse(service.filterF(languageOrdinal, priceFrom)));
        } else if (priceTo != null) {
            return ResponseEntity.ok(service.toResponse(service.filterT(languageOrdinal, priceTo)));
        } else {
            return ResponseEntity.ok(service.toResponse(service.getAll(languageOrdinal)));
        }
    }

//    GET api/v1/drink/name
//    параметры(name)
//  -> апи возвращает список напитков где содержится в имени занчение из переменной name
    @GetMapping("/name")
    public ResponseEntity<?> getByName(@RequestParam int languageOrdinal, @RequestParam String name) {
        return ResponseEntity.ok(service.toResponse(service.getLikeName(languageOrdinal, name)));
    }

//    GET api/v1/drink/type
//    параметры()
//  -> апи возвращает список обьектов который содержит название типа напитка и спиок напитков
//  относящихся к этому типу
    @GetMapping("/type")
    public ResponseEntity<?> getByType(@RequestParam int languageOrdinal, @RequestParam String type) {
        return ResponseEntity.ok(service.toResponse(service.getByType(languageOrdinal, type)));
    }


    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(@RequestParam int languageOrdinal) {
        return ResponseEntity.ok(service.toResponse(service.getAll(languageOrdinal)));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam int languageOrdinal,  @RequestBody DrinkCreateRequest request) {
        return ResponseEntity.ok(service.toResponse(service.create(languageOrdinal, request)));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int languageOrdinal, @RequestParam Long id) {
        service.deleteById(languageOrdinal, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/save-all")
    public ResponseEntity<?> saveAll(@RequestParam int languageOrdinal, @RequestBody List<DrinkCreateRequest> requests) {

        return ResponseEntity.ok(service.toResponse(service.saveAll(languageOrdinal, requests)));
    }

    @GetMapping("/id")
    public ResponseEntity<?> getById(@RequestParam int languageOrdinal, @RequestParam Long id) {
        return ResponseEntity.ok(service.toResponse(service.getById(languageOrdinal, id)));
    }


}
