package com.space.service;


import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShipService {
    @Autowired
    private ShipRepository shipRepository;


    //Все корабли
    public List<Ship> getAllShips(Pageable pageable) {
        return shipRepository.findAllBy(pageable);
    }
    public Long getAllShipsCount() {
        return shipRepository.count();
    }


    //Отфильтрованные по имени или названию планеты
    public List<Ship> getAllShips(String name, Pageable pageable) {
        return shipRepository.findAllByNameContains(name, pageable);
    }
    public Long getAllShipsCount(String name) {
        return shipRepository.countAllByNameContains(name);
    }

    //Отфильтрованные по названию планеты
    public List<Ship> getAllShipsPlanet(String planet, Pageable pageable) {
        return shipRepository.findAllByPlanetContains(planet, pageable);
    }
    public Long getAllShipsCountPlanet(String planet) {
        return shipRepository.countAllByPlanetContains(planet);
    }

    //Отфильтрованные по типу и дате производства
    public List<Ship> getAllShips(ShipType shipType, Date after, Date before, Pageable pageable) {
        return shipRepository.findAllByShipTypeEqualsAndProdDateBetween(shipType, after, before, pageable);
    }
    public Long getAllShipsCount(ShipType shipType, Date after, Date before) {
        return shipRepository.countAllByShipTypeEqualsAndProdDateBetween(shipType, after, before);
    }

    //Отфильтрованные по типу и скорости
    public List<Ship> getAllShips(ShipType shipType, Double minSpeed, Double maxSpeed, Pageable pageable) {
        return shipRepository.findAllByShipTypeEqualsAndSpeedBetween(shipType, minSpeed, maxSpeed, pageable);
    }
    public Long getAllShipsCount(ShipType shipType, Double minSpeed, Double maxSpeed) {
        return shipRepository.countAllByShipTypeEqualsAndSpeedBetween(shipType, minSpeed, maxSpeed);
    }

    //Отфильтрованные по типу и количеству членов экипажа
    public List<Ship> getAllShips(ShipType shipType, Integer minCrewSize, Integer maxCrewSize, Pageable pageable) {
        return shipRepository.findAllByShipTypeEqualsAndCrewSizeBetween(shipType, minCrewSize, maxCrewSize, pageable);
    }
    public Long getAllShipsCount(ShipType shipType, Integer minCrewSize, Integer maxCrewSize) {
        return shipRepository.countAllByShipTypeEqualsAndCrewSizeBetween(shipType, minCrewSize, maxCrewSize);
    }

    //Отфильтрованные по новизне и рейтингу
    public List<Ship> getAllShips(Boolean isUsed, Double minRating, Double maxRating, Pageable pageable) {
        return shipRepository.findAllByIsUsedAndRatingBetween(isUsed, minRating, maxRating, pageable);
    }
    public Long getAllShipsCount(Boolean isUsed, Double minRating, Double maxRating) {
        return shipRepository.countAllByIsUsedAndRatingBetween(isUsed, minRating, maxRating);
    }

    //Отфильтрованные по дате производства и количеству членов экипажа
    public List<Ship> getAllShips(Date after, Date before, Integer minCrewSize, Integer maxCrewSize, Pageable pageable) {
        return shipRepository.findAllByShipTypeIsNotNullAndIsUsedIsNotNullAndProdDateBetweenAndCrewSizeBetween(after, before, minCrewSize, maxCrewSize, pageable);
    }
    public Long getAllShipsCount(Date after, Date before, Integer minCrewSize, Integer maxCrewSize) {
        return shipRepository.countAllByShipTypeIsNotNullAndIsUsedIsNotNullAndProdDateBetweenAndCrewSizeBetween(after, before, minCrewSize, maxCrewSize);
    }

    //Отфильтрованные по новизне, максимальной скорости и максимальному рейтингу
    public List<Ship> getAllShipsMax(Boolean isUsed, Double maxSpeed, Double maxRating, Pageable pageable) {
        return shipRepository.findByIsUsedAndSpeedBeforeAndRatingBefore(isUsed, maxSpeed, maxRating, pageable);
    }
    public Long getAllShipsCountMax(Boolean isUsed, Double maxSpeed, Double maxRating) {
        return shipRepository.countAllByIsUsedAndSpeedBeforeAndRatingBefore(isUsed, maxSpeed, maxRating);
    }

    //Количество кораблей, отфильтрованных по типу и максимальному количеству членов экипажа
    public Long getAllShipsCount(ShipType shipType, Integer maxCrewSize) {
        return shipRepository.countAllByShipTypeEqualsAndCrewSizeBefore(shipType, maxCrewSize);
    }

    //Количество кораблей, отфильтрованных по минимальным: скорости, количеству членов экипажа, рейтингу
    public Long getAllShipsCount(Double minSpeed, Integer minCrewSize, Double minRating) {
        return shipRepository.countAllBySpeedAfterAndCrewSizeAfterAndRatingAfter(minSpeed, minCrewSize, minRating);
    }

    //Количество кораблей, отфильтрованных по названию, минимальной дате производства, максимальному рейтингу
    public Long getAllShipsCount(String name, Date after, Double maxRating) {
        return shipRepository.countAllByNameContainsAndProdDateAfterAndRatingBefore(name, after, maxRating);
    }

    //Количество кораблей, отфильтрованных по типу и новизне
    public Long getAllShipsCount(ShipType shipType, Boolean isUsed) {
        return shipRepository.countAllByShipTypeEqualsAndIsUsedEquals(shipType, isUsed);
    }

    //Количество кораблей, отфильтрованных по типу, максимальной дате производства и максимальной скорости
    public Long getAllShipsCount(ShipType shipType, Date before, Double maxSpeed) {
        return shipRepository.countAllByShipTypeEqualsAndProdDateBeforeAndSpeedBefore(shipType, before, maxSpeed);
    }

    //Количество кораблей, отфильтрованных по новизне, максимальной и минимальной скорости
    public Long getAllShipsCountIsUsedSpeedBetween(Boolean isUsed, Double minSpeed, Double maxSpeed) {
        return shipRepository.countAllByIsUsedEqualsAndSpeedBetween(isUsed, minSpeed, maxSpeed);
    }


    public Ship getShip(Long id){
        Ship ship = null;
        Optional<Ship> optionalShip = shipRepository.findById(id);
        if (optionalShip.isPresent()) {
            ship = optionalShip.get();
        }
        return ship;
    }



    public void deleteShip (Long id){
        shipRepository.deleteById(id);
    }

    public Ship saveShip (Ship ship){
        return shipRepository.save(ship);
    }

    public Ship saveAndFlushShip (Ship ship){
        return shipRepository.saveAndFlush(ship);
    }


    public Boolean isExist(Long id) {
        return shipRepository.existsById(id);
    }


}

