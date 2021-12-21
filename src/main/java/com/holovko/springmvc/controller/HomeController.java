package com.holovko.springmvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping(value = "/")
public class HomeController {
    @GetMapping(value = "/")
    public ModelAndView index() {
        return new ModelAndView("redirect:swagger-ui/index.html?configUrl=/api-docs/swagger-config");
    }
}
