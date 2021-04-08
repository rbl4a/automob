package com.example.demo_console.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String getMainPage() {
        return "Список страниц: " +
                "\n /init - Инициализация базы данных, редирект в корень." +
                "\n /parking - Страница с данными о парковке";
    }
}
