package com.bharath.location.controllers;

import com.bharath.location.LocationwebApplication;
import com.bharath.location.entities.Location;
import com.bharath.location.repository.LocationRepository;
import com.bharath.location.service.LocationService;
import com.bharath.location.util.EmailUtil;
import com.bharath.location.util.ReportUtil;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class LocationController {

    @Autowired
    LocationService service;

    @Autowired
    LocationRepository repository;

    @Autowired
    EmailUtil emailUtil;

    @Autowired
    ReportUtil reportUtil;

    @Autowired
    ServletContext sc;

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

        /*
         * sending the email
         */
        // emailUtil.sendEmail("springxyz@gmail.com", "Location Saved", "Location saved successfully");

        return "createLocation";
    }

    @RequestMapping("/displayLocations")
    public String displayLocations(ModelMap modelMap) {
        List<Location> locations = service.getAllLocations();
        modelMap.addAttribute("locations", locations);
        return "displayLocations";
    }

    @RequestMapping("deleteLocation")
    public String deleteLocation(@RequestParam("id") int id, ModelMap modelMap) {
        // RequestParam annotation fetches the parameter 'id' from the request url

        Location location = service.getLocationById(id);
        service.deleteLocation(location);
        List<Location> locations = service.getAllLocations();
        modelMap.addAttribute("locations", locations);
        return "displayLocations";
    }

    @RequestMapping("showUpdate")
    public String showUpdate(@RequestParam("id") int id, ModelMap modelMap) {
        Location location = service.getLocationById(id);
        modelMap.addAttribute("location", location);
        return "updateLocation";
    }

    @RequestMapping("/updateLoc")
    public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
        service.updateLocation(location);
        List<Location> locations = service.getAllLocations();
        modelMap.addAttribute("locations", locations);
        return "displayLocations";
    }

    @RequestMapping("/generateReport")
    public String generateReport() {

        // we will store jpeg image at this path (location)
        String path = sc.getRealPath("/");
        List<Object[]> data = repository.findTypeAndTypeCount();
        reportUtil.generatePieChart(path, data);
        return "report";
    }
}
