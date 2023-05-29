package com.bharath.location.controllers;

import com.bharath.location.entities.Location;
import com.bharath.location.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping
    public Location createLocation(@RequestBody Location location) { // request will come as json...spring will deserialize this into location object
        return locationRepository.save(location);
    }

    @PutMapping
    public Location updateLocation(@RequestBody Location location) {
        return locationRepository.save(location); // if the location already exists, i.e database got a similar id..it will automatically do an update
    }

    @DeleteMapping("/{id}") // the url will have the id in request
    public void deleteLocation(@PathVariable("id") int id) { // maps parameter's value to id in request url
        locationRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Optional<Location> getLocation(@PathVariable("id") int id) {
        return locationRepository.findById(id);
    }

}
