/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Superpower;
import sg.dontdiejustkode.superherosightingsgroupwork.services.NotFoundException;
import sg.dontdiejustkode.superherosightingsgroupwork.services.PersonServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.SuperpowerServiceLayer;

/**
 *
 * @author kaminahar
 */
@Controller
public class SuperpowerController {
    @Inject
    SuperpowerServiceLayer powerServiceLayer;
    PersonServiceLayer personServiceLayer;

    public SuperpowerController(SuperpowerServiceLayer powerServiceLayer, PersonServiceLayer personServiceLayer) {
        this.powerServiceLayer = powerServiceLayer;
        this.personServiceLayer = personServiceLayer;
    }

    
    
    //displays superpower list
    @RequestMapping(value="/superpowerIndex", method=RequestMethod.GET)
    public String superpowerIndex(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException{
        
       List<Superpower> powerList = powerServiceLayer.getSuperpowerList();
       model.addAttribute("superpowerList", powerList);
       
       return "Superpower/Superpower";
    }  
    
    //deletes superpower 
    @RequestMapping (value="/removeSuperpower" , method=RequestMethod.GET)
    public String removeSuperpower(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException {
       
        String superpowerIDString = request.getParameter("superpowerID");
        int superpowerID = Integer.parseInt(superpowerIDString);
        powerServiceLayer.removePower(superpowerID);
        
        //return to the page  you want it to display the action on
        return "redirect:/superpowerIndex";
    }    

    //get superpower by Id to edit 
    @RequestMapping(value="/editSuperpower" , method=RequestMethod.GET)
    public String displayEditSuperpowerForm(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException {
        
        String superpowerIDString = request.getParameter("superpowerID");
        int powerID = Integer.parseInt(superpowerIDString);
        Superpower getSuperpowerToEdit = powerServiceLayer.getPower(powerID);        
        model.addAttribute("getSuperpowerToEdit", getSuperpowerToEdit);
        
        //the redirect is used when you are redirecting to an URL
        //when there is no redirect: in front of the path, that's going to your JSP page 
        return "Superpower/EditSuperpower";
    }
    
    //edit superpower
    @RequestMapping(value="/editSuperpower" , method=RequestMethod.POST)
    public String editSuperpower(@ModelAttribute("getSuperpowerToEdit") Superpower superpower) throws NotFoundException, PersistenceException {
         
        
        powerServiceLayer.updatePower(superpower);
        
        return "redirect:/superpowerIndex";
    }
   
    //add
    @RequestMapping(value = "/addSuperpower", method = RequestMethod.POST)
    public String addSuperpower(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException {
        
        
        //get values user puts into the form and create a new superpower object
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName(request.getParameter("superpowerName"));

       //display new superpower in superpower table
        powerServiceLayer.addPower(superpower);
        return "redirect:/superpowerIndex";
    }
        
 @RequestMapping(value = "/goToSuperpowerDetailsPage", method = RequestMethod.GET)
 public String goToSuperpowerDetailsPage(HttpServletRequest request, Model model) throws PersistenceException {
             
           String power = request.getParameter("superpowerID");
            int superpowerID = Integer.parseInt(power);
            Superpower sPower = powerServiceLayer.getPower(superpowerID);
            model.addAttribute("sPower", sPower);
            
//            List<Superpower> superpowersForPerson = powerServiceLayer.getPowersByPersonID(superpowerID);
//            model.addAttribute("personList", superpowersForPerson);
//            
            List<Person> personList = personServiceLayer.getPeopleBySuperpowerID(superpowerID);
            model.addAttribute("personList", personList);
//            
     
   return "Superpower/SuperpowerDetails";  
 }
   




















 
    
}
