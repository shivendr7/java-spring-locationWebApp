package com.bharath.location.controllers;

import com.bharath.location.entities.Location;
import com.bharath.location.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationRESTController {

    @Autowired
    LocationRepository locationRepository;

    @GetMapping // binds the http get methods to getLocations() in our restful endpoints
    public List<Location> getLocations() {
        // Spring automatically serializes the list response into json before returning
        return locationRepository.findAll();
    }
}
