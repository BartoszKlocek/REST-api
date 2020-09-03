package com.bartek.restapi.service;

import com.bartek.restapi.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAll();

    Optional<Car> findCarByID(long id);

    List<Car> findCarByColor(String color);

    boolean addCar(Car car);

}
