/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Location;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Sighting;
import sg.dontdiejustkode.superherosightingsgroupwork.services.LocationServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.NotFoundException;
import sg.dontdiejustkode.superherosightingsgroupwork.services.PersonServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.SightingServiceLayer;

/**
 *
 * @author kaminahar
 */

@Controller
public class LocationController {
 @Inject 
 LocationServiceLayer locationServiceLayer;
 SightingServiceLayer sightingServiceLayer;
 PersonServiceLayer personServiceLayer; 

    public LocationController(LocationServiceLayer locationServiceLayer, SightingServiceLayer sightingServiceLayer, PersonServiceLayer personServiceLayer) {
        this.locationServiceLayer = locationServiceLayer;
        this.sightingServiceLayer = sightingServiceLayer;
        this.personServiceLayer = personServiceLayer;
    }
 
 
   
 
//location home page
    @RequestMapping(value="/locationIndex", method=RequestMethod.GET)
    public String locationIndex(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException{
    
        //displays the locations from the location table from the database 
        List<Location> locationList = locationServiceLayer.getLocationList();
        model.addAttribute("locationList", locationList);
        
       
        
        return "Location/Location";
    }
    
    
    
    //delete location
    @RequestMapping(value="/removeLocation" , method=RequestMethod.GET)
    public String removeLocation(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException {
        
        String locationIDString =request.getParameter("locationID");
        int locationID = Integer.parseInt(locationIDString);
        locationServiceLayer.removeLocation(locationID);
        
        return "redirect:/locationIndex";
    }
    
    
    
    
    //get locationId to edit, click on edit and it links you to the edit page for that specific ID
    @RequestMapping(value="/displayEditLocationForm" , method=RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException {
        
        String locationIDString = request.getParameter("locationID");
        int locationID = Integer.parseInt(locationIDString);
        Location getLocationByIdToEdit = locationServiceLayer.getLocation(locationID);
        model.addAttribute("getLocationByIdToEdit", getLocationByIdToEdit);
        
        return "Location/EditLocation";
    }
    
    
    //update location
    @RequestMapping(value="/editLocation" , method=RequestMethod.POST)
    public String editLocation(@ModelAttribute("getLocationByIdToEdit") Location location, HttpServletRequest request) throws NotFoundException, PersistenceException {
     
        locationServiceLayer.updateLocation(location);
        
        return "redirect:/locationIndex";
    }
   
    @RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    public String addLocation(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException {
        
        
        String latitudeToString = request.getParameter("locationLatitude");
        BigDecimal latitude = new BigDecimal(latitudeToString);
        
        String longitudeToString = request.getParameter("locationLongitude");
        BigDecimal longitude = new BigDecimal(longitudeToString);
        //get values user puts into the form and create a new location object
        Location location = new Location();
        location.setLocationName(request.getParameter("locationName"));
        location.setLocationDescription(request.getParameter("locationDescription"));
        location.setLocationAddress(request.getParameter("locationAddress"));
        location.setLocationLatitude(latitude);
        location.setLocationLongitude(longitude);
        
        //display new location in location table
        locationServiceLayer.addLocation(location);
        return "redirect:/locationIndex";
    }
    
    @RequestMapping(value = "/goToLocationDetailsPage", method = RequestMethod.GET)
    public String goToLocationDetailsPage(HttpServletRequest request, Model model) throws PersistenceException {
             
        String loc = request.getParameter("locationID");
        int locationID = Integer.parseInt(loc);
        Location location = locationServiceLayer.getLocation(locationID);
        model.addAttribute("locationDetails", location);
        
        List<Sighting> sightingDetailsList = sightingServiceLayer.getSightingByLocation(locationID);
        List<Sighting> newSightingList = new ArrayList<>();
        for (Sighting sighting : sightingDetailsList) {
        Person prsnBySightingID = personServiceLayer.getPersonBySightingID(sighting.getSightingsID());
        Location locationBySightingID = locationServiceLayer.getLocationBySightingID(sighting.getSightingsID());
        sighting.setPerson(prsnBySightingID);
        sighting.setLocation(locationBySightingID);
        newSightingList.add(sighting);
        }
        model.addAttribute("sightingDetails", newSightingList);
            
//        List<Person> personDetailsList = personServiceLayer.getPeopleByLocationID(locationID);
//        model.addAttribute("personDetails", personDetailsList);
                          
                
        return "Location/LocationDetails";  
    } 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
