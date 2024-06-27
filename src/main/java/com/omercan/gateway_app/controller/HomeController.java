package com.omercan.gateway_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/authentication")
public class HomeController {

    @GetMapping("index")
    public String index() {


        return "index";
    }
}