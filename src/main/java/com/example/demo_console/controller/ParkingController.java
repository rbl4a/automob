package com.example.demo_console.controller;

import com.example.demo_console.entity.Parking;
import com.example.demo_console.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
                "\n /all - все даты парковки и цены";
    }

    @GetMapping(value = "/all")
    public ResponseEntity<String> getAllParking(Model model) {
        List<Parking> allParking = parkingService.findAllParking();
        System.out.println(allParking);
        return ResponseEntity.ok(allParking.toString());
    }


}
