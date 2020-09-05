package com.bartek.restapi.controller;

import com.bartek.restapi.model.Car;
import com.bartek.restapi.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private CarServiceImpl carService;

    @Autowired
    public CarController(CarServiceImpl carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable long id) {
        if (carService.findCarByID(id).isPresent()) {

            return new ResponseEntity<>(carService.findCarByID(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> getAllByColor(@PathVariable String color) {
        List<Car> allCars = carService.findCarByColor(color);
        return new ResponseEntity<>(allCars, HttpStatus.OK);
    }
}
