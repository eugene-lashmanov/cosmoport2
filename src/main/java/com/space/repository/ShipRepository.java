package com.space.repository;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface ShipRepository extends JpaRepository<Ship, Long> {

    List<Ship> findAllBy(Pageable pageable);

    List<Ship> findAllByNameContains(String name, Pageable pageable);
    Long countAllByNameContains(String name);

    List<Ship> findAllByPlanetContains(String planet, Pageable pageable);
    Long countAllByPlanetContains(String planet);

    List<Ship> findAllByShipTypeEqualsAndProdDateBetween(ShipType shipType, Date after, Date before, Pageable pageable);
    Long countAllByShipTypeEqualsAndProdDateBetween(ShipType shipType, Date after, Date before);

    List<Ship> findAllByShipTypeEqualsAndSpeedBetween(ShipType shipType, Double minSpeed, Double maxSpeed, Pageable pageable);
    Long countAllByShipTypeEqualsAndSpeedBetween(ShipType shipType, Double minSpeed, Double maxSpeed);

    List<Ship> findAllByShipTypeEqualsAndCrewSizeBetween(ShipType shipType, Integer minCrewSize, Integer maxCrewSize, Pageable pageable);
    Long countAllByShipTypeEqualsAndCrewSizeBetween(ShipType shipType, Integer minCrewSize, Integer maxCrewSize);

    List<Ship> findAllByIsUsedAndRatingBetween(Boolean isUsed, Double minRating, Double maxRating, Pageable pageable);
    Long countAllByIsUsedAndRatingBetween(Boolean isUsed, Double minRating, Double maxRating);

    List<Ship> findAllByShipTypeIsNotNullAndIsUsedIsNotNullAndProdDateBetweenAndCrewSizeBetween(Date after, Date before, Integer crewSizeAfter, Integer crewSizeBefore, Pageable pageable);

    Long countAllByShipTypeIsNotNullAndIsUsedIsNotNullAndProdDateBetweenAndCrewSizeBetween(Date after, Date before, Integer crewSizeAfter, Integer crewSizeBefore);

    List<Ship> findByIsUsedAndSpeedBeforeAndRatingBefore(Boolean isUsed, Double maxSpeed, Double maxRating, Pageable pageable);
    Long countAllByIsUsedAndSpeedBeforeAndRatingBefore(Boolean isUsed, Double maxSpeed, Double maxRating);

    Long countAllByShipTypeEqualsAndCrewSizeBefore(ShipType shipType, Integer maxCrewSize);

    Long countAllBySpeedAfterAndCrewSizeAfterAndRatingAfter(Double minSpeed, Integer minCrewSize, Double minRating);

    Long countAllByNameContainsAndProdDateAfterAndRatingBefore(String name, Date after, Double maxRating);

    Long countAllByShipTypeEqualsAndIsUsedEquals(ShipType shipType, Boolean isUsed);

    Long countAllByShipTypeEqualsAndProdDateBeforeAndSpeedBefore(ShipType shipType, Date before, Double maxSpeed);

    Long countAllByIsUsedEqualsAndSpeedBetween(Boolean isUsed, Double minSpeed, Double maxSpeed);

    Optional<Ship> findById(Long id);


}
