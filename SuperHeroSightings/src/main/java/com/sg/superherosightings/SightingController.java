        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Location;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Organization;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Sighting;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Superpower;
import sg.dontdiejustkode.superherosightingsgroupwork.services.LocationServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.NotFoundException;
import sg.dontdiejustkode.superherosightingsgroupwork.services.OrganizationServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.PersonServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.SightingServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.SuperpowerServiceLayer;

/**
 *
 * @author kaminahar
 */
@Controller
public class SightingController {
    @Inject
    SightingServiceLayer sightingServiceLayer;
    PersonServiceLayer personServiceLayer;
    LocationServiceLayer locationServiceLayer;
    SuperpowerServiceLayer superpowerServiceLayer;
    OrganizationServiceLayer organizationServiceLayer;

    public SightingController(SightingServiceLayer sightingServiceLayer, PersonServiceLayer personServiceLayer, LocationServiceLayer locationServiceLayer, SuperpowerServiceLayer superpowerServiceLayer, OrganizationServiceLayer organizationServiceLayer) {
        this.sightingServiceLayer = sightingServiceLayer;
        this.personServiceLayer = personServiceLayer;
        this.locationServiceLayer = locationServiceLayer;
        this.superpowerServiceLayer = superpowerServiceLayer;
        this.organizationServiceLayer = organizationServiceLayer;
    }

    
    
   
    
    @RequestMapping(value="/sightingIndex", method=RequestMethod.GET)
    public String sightingIndex(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException{
       
        List<Sighting> sightingsList = sightingServiceLayer.getSightingList();
        List<Sighting> improvedSightingList = new ArrayList<>();
        for (Sighting currentSighting : sightingsList) {
        Sighting sighting = currentSighting;    
        int prsnID = currentSighting.getPersonID();
        int locID = currentSighting.getLocationID();
        Person prsn = personServiceLayer.getPerson(prsnID);
        Location loc = locationServiceLayer.getLocation(locID);
        sighting.setPerson(prsn);
        sighting.setLocation(loc);
        improvedSightingList.add(sighting);
        } 
        model.addAttribute("improvedSightingList", improvedSightingList);
        
        List<Person> personList = personServiceLayer.getPersonList();
        model.addAttribute("personList", personList);
        
        List<Location> locationList = locationServiceLayer.getLocationList();
        model.addAttribute("locationList", locationList);
                
       return "Sighting/Sighting";
    } 
    
 
    
    @RequestMapping(value={"/bob", "/"}, method=RequestMethod.GET)
    public String displayLastTenSightings(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException{
       
        //the method "getFirstTenSighting" in your service layer passes in the method from your dao, 
        //which gets the last ten sightings using your prepared statements and gets those sightings 
        //inside this sightingsList here. 
      
        
        List<Sighting> sightingsList = sightingServiceLayer.getFirstTenSightings();
        
        List<Sighting> lastTenSightingList = new ArrayList<>();
        for (Sighting currentSighting : sightingsList) {
            
        Sighting sighting = currentSighting;    
        int prsnID = currentSighting.getPersonID();
        int locID = currentSighting.getLocationID();
        Person prsn = personServiceLayer.getPerson(prsnID);
        Location loc = locationServiceLayer.getLocation(locID);
        
        sighting.setPerson(prsn);
        sighting.setLocation(loc);
        lastTenSightingList.add(sighting);
        } 
        
        model.addAttribute("lastTenSightingList", lastTenSightingList);
        
     
       
       return "bob";
    } 
    
    
    
    
    
    
    
    
    
    //delete Sighting
    @RequestMapping(value="/removeSighting" , method=RequestMethod.GET)
    public String removeSighting(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException {
        
        String sightingsIDString =request.getParameter("sightingsID");
        int sightingsID = Integer.parseInt(sightingsIDString);
        sightingServiceLayer.removeSighting(sightingsID);
        
        return "redirect:/SightingIndex";
    }
    
    //get Sighting by Id to edit, link takes you to the edit sighting page
    @RequestMapping(value="/displayEditSightingForm" , method=RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException {
        
        String sightingsIDString = request.getParameter("sightingsID");
        int sightingsID = Integer.parseInt(sightingsIDString);
        Sighting getSightingByIdToEdit = sightingServiceLayer.getSightingByID(sightingsID);
        model.addAttribute("getSightingByIdToEdit", getSightingByIdToEdit);
        
        
        
        
        List<Person> prsnList = personServiceLayer.getPersonList();
        model.addAttribute("prsnList", prsnList);
        
        List<Location> locList = locationServiceLayer.getLocationList();
        model.addAttribute("locList", locList);
        
 
        return "Sighting/EditSighting";
          
        
         
    }
    
    //update sighting
    @RequestMapping(value="/editSighting" , method=RequestMethod.POST)
    public String editSighting (@ModelAttribute("getSightingByIdToEdit") Sighting sighting, HttpServletRequest request) throws NotFoundException, PersistenceException {
        
        String locationIDString = request.getParameter("selectLocation");
        int sightingLocation = Integer.parseInt(locationIDString);
        Location currentLocation = locationServiceLayer.getLocation(sightingLocation);
        
        String personIDString = request.getParameter("selectPerson");
        int sightingPerson = Integer.parseInt(personIDString);
        Person currentPerson = personServiceLayer.getPerson(sightingPerson);

        String sightingsDate = request.getParameter("sightingsDate");
        LocalDate date = LocalDate.parse(sightingsDate);
      
        
        sighting.setSightingsDate(date);
        sighting.setLocation(currentLocation);
        sighting.setPerson(currentPerson);
        
        sightingServiceLayer.updateSighting(sighting);
        
        return "redirect:/sightingIndex";
    }
   
    //Create
    @RequestMapping(value = "/addSighting", method = RequestMethod.POST)
    public String addSighting(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException {
        
        String locationIDString = request.getParameter("selectLocation");
        int sightingLocation = Integer.parseInt(locationIDString);
        Location currentLocation = locationServiceLayer.getLocation(sightingLocation);
        
        String personIDString = request.getParameter("selectPerson");
        int sightingPerson = Integer.parseInt(personIDString);
        Person currentPerson = personServiceLayer.getPerson(sightingPerson);

        String sightingsDate = request.getParameter("sightingsDate");
        LocalDate date = LocalDate.parse(sightingsDate);
   
        
        //get values user puts into the form and create a new sighting object
        Sighting sighting = new Sighting();
        sighting.setSightingsDate(date);  
        sighting.setLocation(currentLocation);
        sighting.setPerson(currentPerson);
 
        sightingServiceLayer.addSighting(sighting);
 
        return "redirect:/sightingIndex";
         

    } 
    
    @RequestMapping(value = "/goToSightingsDetailsPage", method = RequestMethod.GET)
    public String goToSightingsDetailsPage(HttpServletRequest request, Model model) throws PersistenceException {
           
         String sighting = request.getParameter("sightingsID");
           int sightingsID = Integer.parseInt(sighting);
           Sighting sight = sightingServiceLayer.getSightingByID(sightingsID);
           model.addAttribute("sightDetails", sight);
    
  
        return "Sighting/SightingDetails";  
    }  
    

    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
