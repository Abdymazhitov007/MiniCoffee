package org.example.coffeee.controller;

import lombok.RequiredArgsConstructor;
import org.example.coffeee.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService service;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam int languageOrdinal) {
        return ResponseEntity.ok(service.getAll(languageOrdinal));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam String name) {
        return ResponseEntity.ok(service.create(name));
    }

    @DeleteMapping("/delete")
    public HttpStatus delete(@RequestParam int languageOrdinal, @RequestParam Long id) {
        service.deleteById(languageOrdinal, id);
        return HttpStatus.OK;
    }

    @PostMapping("/save-all")
    public ResponseEntity<?> saveAll(@RequestParam int languageOrdinal, @RequestBody List<String> names) {
        return ResponseEntity.ok(service.saveAll(languageOrdinal, names));
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<?> getByName(@RequestParam int languageOrdinal, @RequestParam String name) {
        return ResponseEntity.ok(service.getByName(languageOrdinal, name));
    }
}
