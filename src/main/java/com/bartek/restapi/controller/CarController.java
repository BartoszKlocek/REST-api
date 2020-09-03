package com.bartek.restapi.controller;

import com.bartek.restapi.model.Car;
import com.bartek.restapi.model.Color;
import com.bartek.restapi.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        return new ResponseEntity<>(carService.findCarByColor(color), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car) {
        if (carService.addCar(car)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity editCar(@RequestBody Car car, @PathVariable long id) {
        if (carService.editCar(car, id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/{id}")
    public ResponseEntity editFieldOfCar(@RequestParam(name = "model", required = false) String model,
                                         @RequestParam(name = "mark", required = false) String mark,
                                         @RequestParam(name = "color", required = false) String color,
                                         @PathVariable long id) {
        Optional<Car> found = carService.findCarByID(id);
        if (found.isPresent()) {
            if (!Objects.equals(mark, null)) {
                found.get().setMark(mark);
            }
            if (!Objects.equals(model, null)) {
                found.get().setModel(model);
            }
            if (!Objects.equals(color, null)) {
                found.get().setColor(Color.valueOf(color.toUpperCase()));
            }
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@PathVariable long id) {
        if (carService.deleteCar(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
