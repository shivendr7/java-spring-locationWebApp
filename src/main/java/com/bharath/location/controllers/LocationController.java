package com.bharath.location.controllers;

import com.bharath.location.entities.Location;
import com.bharath.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {

        /*
         *  By default, Spring will create a model object, set all the field that come
         *  into the request and expose it out as a bean with the following name
         */
        Location locationSaved = service.saveLocation(location);
        String msg = "Location saved with id: " + locationSaved.getId();

        /*
         *  To send the msg back to the UI: spring allows us do that using a concept called
         *  model map
         */
        modelMap.addAttribute("msg", msg);

        return "createLocation";
    }

}
