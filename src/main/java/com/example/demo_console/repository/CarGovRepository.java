package com.example.demo_console.repository;

import com.example.demo_console.entity.CarGovNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarGovRepository extends JpaRepository<CarGovNumber, Long> {
}
