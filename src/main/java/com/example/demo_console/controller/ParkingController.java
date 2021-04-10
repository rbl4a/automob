package com.example.demo_console.controller;

import com.example.demo_console.entity.Car;
import com.example.demo_console.entity.Parking;
import com.example.demo_console.service.CarService;
import com.example.demo_console.service.ParkingService;
import com.fasterxml.jackson.annotation.JsonView;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Контроллер управления парковкой
 */
@RestController
@JsonView
@RequestMapping("/parking")
public class ParkingController {
    private final ParkingService parkingService;
    private final CarService carService;

    public ParkingController(ParkingService parkingService, CarService carService) {
        this.parkingService = parkingService;
        this.carService = carService;
    }

    /**
     * Начальная страница каталога parking
     * @return спискок подкаталогов
     */
    @GetMapping
    public String helloPage() {
        return "Возможные параметры: " +
                "\n\t/all - вся информация по работе парковки" +
                "\n\t/all-by-government-number/{number} - поиск по номеру авто" +
                "\n\t/all-by-car-model/{modelName} - поиск по модели авто" +
                "\n\t/delete-parking/{id} - удаление записи о парковке по id" +
                "\n\t/update-end-date-time/{idParking}?endDate&endTime - обновление полей даты и времени парковки" +
                "\n\t/add-parking?startDate&startTime&carId - добавление авто на парковку";
    }

    /**
     * Список всех записей о парковках автомобилей в БД
     * @return HTTP 200 OK со списком всех записей о парковках в БД
     */
    @GetMapping(path = "/all")
    public ResponseEntity<String> getAllParking() {
        List<Parking> allParking = parkingService.findAllParking();
        return ResponseEntity.ok(allParking.toString());
    }

    /**
     * Поиск информации о стоянках конкретного авто по его гос номеру
     * @param number - гос номер
     * @return HTTP 200 OK со списком записей о стоянках конкретного авто
     */
    @GetMapping(path = "/all-by-government-number/{number}")
    public ResponseEntity<String> getAllParkingByGovNumber(@PathVariable String number) {
        List<Parking> allParking = parkingService.findAllByCarGovNumber(number);
        if (allParking.isEmpty()) {
            return new ResponseEntity<>("Car not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allParking.toString());
    }

    /**
     * Поиск информации об автомобиле по названию модели
     * @param modelName - название модели
     * @return HTTP 200 OK со списком записей о стоянках авто с заданной моделью
     */
    @GetMapping(path = "/all-by-car-model/{modelName}")
    public ResponseEntity<String> getAllParkingByCarModelName(@PathVariable String modelName) {
        List<Parking> allParking = parkingService.findAllByCarModel(modelName);
        if (allParking.isEmpty()) {
            return new ResponseEntity<>("Car not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allParking.toString());
    }

    /**
     * Удаление автомобиля из БД по id
     * @param id - id автомобиля
     * @return redirect на {@link ParkingController#getAllParking()}
     */
    @DeleteMapping(path = "/delete-parking/{id}")
    public RedirectView deleteParkingById(@PathVariable Long id) {
        parkingService.deleteParkingById(id);
        return new RedirectView("/parking/all");
    }

    /**
     * Добавление даты и времени окончания парковки
     * @param id парковки
     * @param endDate дата окончания парковки
     * @param endTime время окончания парковки
     * @return redirect на {@link ParkingController#getAllParking()}
     */
    @PostMapping("/update-end-date-time/{idParking}")
    public RedirectView updateEndDateTime(@PathVariable("idParking") Long id,
                                          @RequestParam("endDate") LocalDate endDate,
                                          @RequestParam("endTime") LocalTime endTime) {
        Parking parking = parkingService.getById(id);
        parking.setEndDate(endDate);
        parking.setEndTime(endTime);
        parking.updatePrice();
        parkingService.saveParking(parking);
        return new RedirectView("/parking/all");
    }

    /**
     * Постановка авто из БД на стоянку
     * @param startDate дата начала парковки в формате yyyy-MM-dd
     * @param startTime дата окончания парковки HH:mm:ss
     * @param id автомобиля
     * @throws NotFoundException если автомобиль не найден в БД
     */
    @PostMapping("/add-parking")
    public void addParking(@RequestParam("startDate") LocalDate startDate,
                           @RequestParam("startTime") LocalTime startTime,
                           @RequestParam("carId") Long id) throws NotFoundException {
        Optional<Car> optionalCar = Optional.ofNullable(carService.findCarById(id));
        Parking parking = new Parking(optionalCar
                .orElseThrow(() -> new NotFoundException(String.format("Car with id=%d not found", id))), startDate, null, startTime, null);
        parkingService.saveParking(parking);
    }
}
