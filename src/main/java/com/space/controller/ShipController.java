package com.space.controller;


import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping(value = "/rest")
public class ShipController {

    @Autowired
    private ShipService shipService;

    //Get all ships
    @GetMapping("/ships")
    public List<Ship> getAllShips(@RequestParam(name = "name", required = false) String name,
                                  @RequestParam(name = "planet", required = false) String planet,
                                  @RequestParam(name = "shipType", required = false) ShipType shipType,
                                  @RequestParam(name = "after", required = false) Long after,
                                  @RequestParam(name = "before", required = false) Long before,
                                  @RequestParam(name = "isUsed", required = false) Boolean isUsed,
                                  @RequestParam(name = "minCrewSize", required = false) Integer minCrewSize,
                                  @RequestParam(name = "maxCrewSize", required = false) Integer maxCrewSize,
                                  @RequestParam(name = "minSpeed", required = false) Double minSpeed,
                                  @RequestParam(name = "maxSpeed", required = false) Double maxSpeed,
                                  @RequestParam(name = "minRating", required = false) Double minRating,
                                  @RequestParam(name = "maxRating", required = false) Double maxRating,
                                  @RequestParam(name = "order", required = false, defaultValue = "ID") ShipOrder order,
                                  @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "3") Integer pageSize) {
        if (isAllParamsNull(name, planet, shipType, after, before, isUsed, minCrewSize, maxCrewSize, minSpeed, maxSpeed, minRating, maxRating)) {
            return shipService.getAllShips(getPageable(pageNumber, pageSize, order));
        } else if (planet == null && shipType == null && after == null && before == null && isUsed == null &&
                minCrewSize == null && maxCrewSize == null && minSpeed == null && maxSpeed == null && minRating == null &&
                maxRating == null) {
            return shipService.getAllShips(name, getPageable(pageNumber, pageSize, order));
        } else if (name == null && shipType == null && after == null && before == null && isUsed == null &&
                minCrewSize == null && maxCrewSize == null && minSpeed == null && maxSpeed == null && minRating == null &&
                maxRating == null) {
            return shipService.getAllShipsPlanet(planet, getPageable(pageNumber, pageSize, order));
        } else if (name == null && planet == null && isUsed == null &&
                minCrewSize == null && maxCrewSize == null && minSpeed == null && maxSpeed == null && minRating == null &&
                maxRating == null) {
            return shipService.getAllShips(shipType, new Date(after), new Date(before), getPageable(pageNumber, pageSize, order));
        } else if (name == null && planet == null && after == null && before == null && isUsed == null &&
                minCrewSize == null && maxCrewSize == null && minRating == null &&
                maxRating == null) {
            return shipService.getAllShips(shipType, minSpeed, maxSpeed, getPageable(pageNumber, pageSize, order));
        } else if (name == null && planet == null && after == null && before == null && isUsed == null
                && minSpeed == null && maxSpeed == null && minRating == null &&
                maxRating == null) {
            return shipService.getAllShips(shipType, minCrewSize, maxCrewSize, getPageable(pageNumber, pageSize, order));
        } else if (name == null && planet == null && shipType == null && after == null && before == null &&
                minCrewSize == null && maxCrewSize == null && minSpeed == null && maxSpeed == null) {
            return shipService.getAllShips(isUsed, minRating, maxRating, getPageable(pageNumber, pageSize, order));
        } else if (name == null && planet == null && shipType == null && after == null && before == null &&
                minCrewSize == null && maxCrewSize == null && minSpeed == null && minRating == null) {
            return shipService.getAllShipsMax(isUsed, maxSpeed, maxRating, getPageable(pageNumber, pageSize, order));
        } else if (shipType == null && isUsed == null && name == null && planet == null && minSpeed == null && maxSpeed == null &&
                minRating == null && maxRating == null) {
            return shipService.getAllShips(new Date(after), new Date(before), minCrewSize, maxCrewSize,
                    getPageable(pageNumber, pageSize, order));
        }
        return null;
    }

    private boolean isAllParamsNull(String name, String planet, ShipType shipType, Long after, Long before,
                                    Boolean isUsed, Integer minCrewSize, Integer maxCrewSize, Double minSpeed,
                                    Double maxSpeed, Double minRating, Double maxRating) {
        return name == null && planet == null && shipType == null && after == null && before == null && isUsed == null &&
                minCrewSize == null && maxCrewSize == null && minSpeed == null && maxSpeed == null && minRating == null &&
                maxRating == null;
    }

    private Pageable getPageable(Integer pageNumber, Integer pageSize, ShipOrder order) {
        return PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName()));
    }

    private Calendar getCalendarAndSetBeginningOfYear(Long date) {
        Calendar calendar = new GregorianCalendar(new Date(date).getYear() + 1900, Calendar.JANUARY, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    //Get ships count
    @GetMapping("/ships/count")
    public Long getShipsCount(@RequestParam(name = "name", required = false) String name,
                              @RequestParam(name = "planet", required = false) String planet,
                              @RequestParam(name = "shipType", required = false) ShipType shipType,
                              @RequestParam(name = "after", required = false) Long after,
                              @RequestParam(name = "before", required = false) Long before,
                              @RequestParam(name = "isUsed", required = false) Boolean isUsed,
                              @RequestParam(name = "minCrewSize", required = false) Integer minCrewSize,
                              @RequestParam(name = "maxCrewSize", required = false) Integer maxCrewSize,
                              @RequestParam(name = "minSpeed", required = false) Double minSpeed,
                              @RequestParam(name = "maxSpeed", required = false) Double maxSpeed,
                              @RequestParam(name = "minRating", required = false) Double minRating,
                              @RequestParam(name = "maxRating", required = false) Double maxRating) {
        if (isAllParamsNull(name, planet, shipType, after, before, isUsed, minCrewSize, maxCrewSize, minSpeed, maxSpeed, minRating, maxRating)) {
            return shipService.getAllShipsCount();
        } else if (planet == null && shipType == null && after == null && before == null && isUsed == null &&
                minCrewSize == null && maxCrewSize == null && minSpeed == null && maxSpeed == null && minRating == null &&
                maxRating == null) {
            return shipService.getAllShipsCount(name);
        } else if (name == null && shipType == null && after == null && before == null && isUsed == null &&
                minCrewSize == null && maxCrewSize == null && minSpeed == null && maxSpeed == null && minRating == null &&
                maxRating == null) {
            return shipService.getAllShipsCountPlanet(planet);
        } else if (name == null && planet == null && isUsed == null &&
                minCrewSize == null && maxCrewSize == null && minSpeed == null && maxSpeed == null && minRating == null &&
                maxRating == null) {
            return shipService.getAllShipsCount(shipType, new Date(after), new Date(before));
        } else if (name == null && planet == null && after == null && before == null && isUsed == null &&
                minCrewSize == null && maxCrewSize == null && minRating == null &&
                maxRating == null) {
            return shipService.getAllShipsCount(shipType, minSpeed, maxSpeed);
        } else if (name == null && planet == null && after == null && before == null && isUsed == null
                && minSpeed == null && maxSpeed == null && minRating == null &&
                maxRating == null) {
            if (minCrewSize == null) {
                return shipService.getAllShipsCount(shipType, maxCrewSize);
            } else {
                return shipService.getAllShipsCount(shipType, minCrewSize, maxCrewSize);
            }
        } else if (name == null && planet == null && shipType == null && after == null && before == null &&
                minCrewSize == null && maxCrewSize == null && minSpeed == null && maxSpeed == null) {
            return shipService.getAllShipsCount(isUsed, minRating, maxRating);
        } else if (name == null && planet == null && shipType == null && after == null && before == null &&
                minCrewSize == null && maxCrewSize == null && minSpeed == null && minRating == null) {
            return shipService.getAllShipsCountMax(isUsed, maxSpeed, maxRating);
        } else if (shipType == null && isUsed == null && name == null && planet == null && minSpeed == null && maxSpeed == null &&
                minRating == null && maxRating == null) {
            return shipService.getAllShipsCount(new Date(after), new Date(before), minCrewSize, maxCrewSize);
        } else if (name == null && planet == null && shipType == null && after == null && before == null && isUsed == null &&
                maxCrewSize == null && maxSpeed == null &&
                maxRating == null) {
            return shipService.getAllShipsCount(minSpeed, minCrewSize, minRating);
        } else if (planet == null && shipType == null && before == null && isUsed == null &&
                minCrewSize == null && maxCrewSize == null && minSpeed == null && maxSpeed == null && minRating == null) {
            return shipService.getAllShipsCount(name, new Date(after), maxRating);
        } else if (name == null && planet == null && after == null && before == null &&
                minCrewSize == null && maxCrewSize == null && minSpeed == null && maxSpeed == null && minRating == null &&
                maxRating == null) {
            return shipService.getAllShipsCount(shipType, isUsed);
        } else if (name == null && planet == null && after == null && isUsed == null &&
                minCrewSize == null && maxCrewSize == null && minSpeed == null && minRating == null &&
                maxRating == null) {
            return shipService.getAllShipsCount(shipType, new Date(before), maxSpeed);
        } else if (name == null && planet == null && shipType == null && after == null && before == null &&
                minCrewSize == null && maxCrewSize == null && minRating == null &&
                maxRating == null) {
            return shipService.getAllShipsCountIsUsedSpeedBetween(isUsed, minSpeed, maxSpeed);
        }
        return null;
    }

    //Edit ship
    @GetMapping("/ships/{id}")
    public Ship getShip(@PathVariable String id) {
        if (checkValid(id)) {
            Long idLong = Long.parseLong(id);
            if (shipService.isExist(idLong)) {
                return shipService.getShip(idLong);
            } else {
                throw new ShipNotFoundException();
            }
        } else {
            throw new BadRequestException();
        }
    }

    private boolean checkValid(String id) {
        boolean isValid = false;
        try {
            Long idLong = Long.parseLong(id);
            if (idLong > 0) {
                isValid = true;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    //Update ship
    @PostMapping("/ships/{id}")
    public Ship updateShip(@PathVariable String id, @RequestBody Ship ship) {
        if (checkValid(id)) {
            Long idLong = Long.parseLong(id);
            if (shipService.isExist(idLong)) {
                ship.setId(idLong);
                if (ship.getName() == null && ship.getPlanet() == null && ship.getShipType() == null && ship.getProdDate() == null &&
                        ship.getSpeed() == null && ship.getCrewSize() == null) {
                    return shipService.getShip(idLong);
                } else {
                    if (ship.getName() == null) {
                        ship.setName(shipService.getShip(idLong).getName());
                    } else {
                        ship.setName(ship.getName());
                    }
                    if (ship.getName().isEmpty()) {
                        throw new BadRequestException();
                    }
                    if (ship.getPlanet() == null) {
                        ship.setPlanet(shipService.getShip(idLong).getPlanet());
                    } else {
                        ship.setPlanet(ship.getPlanet());
                    }
                    if (ship.getShipType() == null) {
                        ship.setShipType(shipService.getShip(idLong).getShipType());
                    } else {
                        ship.setShipType(ship.getShipType());
                    }
                    if (ship.getProdDate() == null) {
                        ship.setProdDate(shipService.getShip(idLong).getProdDate());
                    } else {
                        ship.setProdDate(ship.getProdDate());
                    }
                    if ((ship.getProdDate().getYear() + 1900) < 2800 ||
                            (ship.getProdDate().getYear() + 1900) > 3019) {
                        throw new BadRequestException();
                    }
                    if (ship.getUsed() == null) {
                        ship.setUsed(shipService.getShip(idLong).getUsed());
                    } else {
                        ship.setUsed(ship.getUsed());
                    }
                    if (ship.getSpeed() == null) {
                        ship.setSpeed(shipService.getShip(idLong).getSpeed());
                    } else {
                        ship.setSpeed(ship.getSpeed());
                    }
                    if (ship.getCrewSize() == null) {
                        ship.setCrewSize(shipService.getShip(idLong).getCrewSize());
                    } else {
                        ship.setCrewSize(ship.getCrewSize());
                    }
                    if (ship.getCrewSize() < 1 || ship.getCrewSize() > 9999) {
                        throw new BadRequestException();
                    }
                    Double rating = Math.rint(100.0 * ((80 * ship.getSpeed() * (ship.getUsed() ? 0.5 : 1)) / (3019 - (ship.getProdDate().getYear() + 1900) + 1))) / 100.0;
                    ship.setRating(rating);
                    return shipService.saveAndFlushShip(ship);
                }
            } else {
                throw new ShipNotFoundException();
            }
        } else {
            throw new BadRequestException();
        }
    }

    //Delete ship
    @DeleteMapping("/ships/{id}")
    public void deleteShip(@PathVariable String id) {
        if (checkValid(id)) {
            Long idLong = Long.parseLong(id);
            if (shipService.isExist(idLong)) {
                shipService.deleteShip(idLong);
            } else {
                throw new ShipNotFoundException();
            }
        } else {
            throw new BadRequestException();
        }
    }

    //Create ship
    @PostMapping("/ships")
    public Ship createShip(@RequestBody Ship ship) {
        if (ship.getName() == null || ship.getPlanet() == null || ship.getShipType() == null || ship.getProdDate() == null ||
                ship.getSpeed() == null || ship.getCrewSize() == null) {
            throw new BadRequestException();
        }
        if (ship.getName().length() > 50 || ship.getPlanet().length() > 50) {
            throw new BadRequestException();
        }
        if (ship.getName().isEmpty() || ship.getPlanet().isEmpty()) {
            throw new BadRequestException();
        }
        Double roundingSpeed = Math.rint(100.0 * ship.getSpeed()) / 100.0;
        if (ship.getSpeed() < 0.01 || ship.getSpeed() > 0.99) {
            throw new BadRequestException();
        }
        if (ship.getCrewSize() < 1 || ship.getCrewSize() > 9999) {
            throw new BadRequestException();
        }
        if (ship.getProdDate().getTime() < 0) {
            throw new BadRequestException();
        }
        if ((ship.getProdDate().getYear() + 1900) < 2800 || (ship.getProdDate().getYear() + 1900) > 3019) {
            throw new BadRequestException();
        }
        ship.setSpeed(roundingSpeed);
        if (ship.getUsed() == null) {
            ship.setUsed(false);
        } else {
            ship.setUsed(ship.getUsed());
        }
        Double rating = Math.rint(100.0 * ((80 * ship.getSpeed() * (ship.getUsed() ? 0.5 : 1)) / (3019 - (ship.getProdDate().getYear() + 1900) + 1))) / 100.0;
        ship.setRating(rating);
        return shipService.saveShip(ship);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    static class BadRequestException extends RuntimeException {

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    static class ShipNotFoundException extends RuntimeException {

    }


}


