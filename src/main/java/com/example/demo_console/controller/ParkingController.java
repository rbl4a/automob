package com.example.demo_console.controller;

import com.example.demo_console.entity.Parking;
import com.example.demo_console.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {
    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping
    public String helloPage() {
        return "Возможные параметры: " +
                "\n\t/all - вся информация по работе парковки" +
                "\n\t/all-by-government-number/{number} - поиск по номеру авто" +
                "\n\t/all-by-car-model/{modelName} - поиск по модели авто" +
                "\n\t/delete-parking/{id} - удаление записи о парковке по id";
    }

    @GetMapping(path = "/all")
    public ResponseEntity<String> getAllParking() {
        List<Parking> allParking = parkingService.findAllParking();
        return ResponseEntity.ok(allParking.toString());
    }

    @GetMapping(path = "/all-by-government-number/{number}")
    public ResponseEntity<String> getAllParkingByGovNumber(@PathVariable String number) {
        List<Parking> allParking = parkingService.findAllByCarGovNumber(number);
        if (allParking.isEmpty()) {
            return new ResponseEntity<>("Car not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allParking.toString());
    }

    @GetMapping(path = "/all-by-car-model/{modelName}")
    public ResponseEntity<String> getAllParkingByCarModelName(@PathVariable String modelName) {
        List<Parking> allParking = parkingService.findAllByCarModel(modelName);
        if (allParking.isEmpty()) {
            return new ResponseEntity<>("Car not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allParking.toString());
    }

    @DeleteMapping(path = "/delete-parking/{id}")
    public RedirectView deleteParkingById(@PathVariable Long id) {
        parkingService.deleteParkingById(id);
        return new RedirectView("/parking/all");
    }
}
