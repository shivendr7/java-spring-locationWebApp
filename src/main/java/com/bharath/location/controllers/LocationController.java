package com.bharath.location.controllers;

import com.bharath.location.entities.Location;
import com.bharath.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LocationController {

    @Autowired
    LocationService service;

    @RequestMapping("/showCreate")
    public String showCreate() {
        return "createLocation"; // returning the correct jsp page
    }

    @RequestMapping("/")
    public String hello() {
        return "home";
    }

    @RequestMapping("/saveLoc")
    public String saveLocation(@ModelAttribute("location") Location location) {
        /*
         *  By default Spring will create a model object, set all the field that come
         *  into the request and expose it out as a bean with the following name
         */
        service.saveLocation(location);
        return "createLocation";
    }

}
