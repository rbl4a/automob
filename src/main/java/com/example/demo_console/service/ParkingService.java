package com.example.demo_console.service;

import com.example.demo_console.entity.Parking;

import java.util.List;

public interface ParkingService {
    List<Parking> findAllParking();
    void saveAllParking(List<Parking> parking);
}
