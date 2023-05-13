package com.bharath.location.service;

import com.bharath.location.entities.Location;
import com.bharath.location.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // allows Spring to create its object on runtime and inject it as dependency
public class LocationServiceImpl implements  LocationService{

    @Autowired
    private LocationRepository repository;
    @Override
    public Location saveLocation(Location location) {
        return repository.save(location);
    }

    @Override
    public Location updateLocation(Location location) {
        return repository.save(location);
    }

    @Override
    public void deleteLocation(Location location) {
        repository.delete(location);
    }

    @Override
    public Location getLocationById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Location> getAllLocations() {
        return repository.findAll();
    }

}
