package org.example.coffeee.controller;

import lombok.RequiredArgsConstructor;
import org.example.coffeee.service.DrinkTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/drink-type")
@RequiredArgsConstructor
public class DrinkTypeController {

    private final DrinkTypeService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam String name) {
        return ResponseEntity.ok(service.create(name));
    }

    @DeleteMapping("/delete")
    public HttpStatus delete(@RequestParam Long id) {
        service.deleteById(id);
        return HttpStatus.OK;
    }

    @PostMapping("/save-all")
    public ResponseEntity<?> saveAll(@RequestBody List<String> names) {
        return ResponseEntity.ok(service.saveAll(names));

    }
}
