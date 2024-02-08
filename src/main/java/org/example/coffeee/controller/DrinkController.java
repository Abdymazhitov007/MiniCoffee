package org.example.coffeee.controller;

import lombok.RequiredArgsConstructor;
import org.example.coffeee.model.request.DrinkCreateRequest;
import org.example.coffeee.service.DrinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/drink")
@RequiredArgsConstructor
public class DrinkController {

    private final DrinkService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DrinkCreateRequest request) {
        return ResponseEntity.ok(service.create(request));
    }




}
