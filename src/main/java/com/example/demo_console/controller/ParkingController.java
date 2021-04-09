package com.example.demo_console.controller;

import com.example.demo_console.entity.Parking;
import com.example.demo_console.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                "\n\t/all-by-government-number/{number} - поиск по номеру авто";
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


}
