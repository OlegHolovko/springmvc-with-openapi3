package com.holovko.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class HomeController {
    @RequestMapping(value = "")
    public ModelAndView index() {
        return new ModelAndView("redirect:swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config");
    }
}
