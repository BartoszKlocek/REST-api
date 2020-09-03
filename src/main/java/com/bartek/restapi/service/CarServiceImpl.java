package com.bartek.restapi.service;

import com.bartek.restapi.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private List<Car> carList;

    public CarServiceImpl() {
        carList = new ArrayList<>();
        carList.add(new Car(1, "Kia", "Sportage", "White"));
        carList.add(new Car(2, "Hyundai", "Elantra", "Green"));
        carList.add(new Car(3, "Mazda", "CX-5", "Yellow"));
    }

    @Override
    public List<Car> findAll() {
        return carList;
    }

    @Override
    public Optional<Car> findCarByID(long id) {
        return carList.stream().filter(a -> a.getId() == id).findFirst();
    }

    @Override
    public List<Car> findCarByColor(String color) {
        return carList
                .stream()
                .filter(a -> a.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());

    }

    @Override
    public boolean addCar(Car car) {
        car.setId((carList.get(carList.size() - 1).getId()) + 1);
        return carList.add(car);
    }

    @Override
    public boolean editCar(Car car, long id) {
        Optional<Car> found = carList.stream().filter(a -> a.getId() == id).findFirst();
        if (found.isPresent()) {
            if (!Objects.equals(car.getMark(), null)) {
                found.get().setMark(car.getMark());
            }
            if (!Objects.equals(car.getModel(), null)) {
                found.get().setModel(car.getModel());
            }
            if (!Objects.equals(car.getColor(), null)) {
                found.get().setColor(car.getColor());
            }
            return true;
        }
        return false;
    }
}
