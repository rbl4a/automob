package com.example.demo_console.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер корня
 */
@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String getMainPage() {
        return "Список страниц: " +
                "\n\t /init - Инициализация базы данных, редирект в корень." +
                "\n\t /parking - Данные о парковке" +
                "\n\t /car - Данные о зарегестрированных машинах";
    }
}
