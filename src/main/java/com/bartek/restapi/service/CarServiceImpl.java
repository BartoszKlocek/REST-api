package com.bartek.restapi.service;

import com.bartek.restapi.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private List<Car> carList;

    public CarServiceImpl() {
        carList = new ArrayList<>();
        new Car(1,"Kia","Sportage","White");
        new Car(2,"Hyundai","Elantra","Green");
        new Car(3,"Mazda","CX-5","Yellow");
    }

    @Override
    public List<Car> findAll() {
        return carList;
    }

    @Override
    public Optional<Car> findCarByID(long id) {
        return carList.stream().filter(a->a.getId()==id).findFirst();
    }
}
