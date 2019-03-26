 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings;

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
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Organization;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Superpower;
import sg.dontdiejustkode.superherosightingsgroupwork.services.NotFoundException;
import sg.dontdiejustkode.superherosightingsgroupwork.services.OrganizationServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.PersonServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.SuperpowerServiceLayer;

/**
 *
 * @author kaminahar
 */
@Controller
public class PersonController {
    
    @Inject
    PersonServiceLayer personServiceLayer;
    SuperpowerServiceLayer superpowerServiceLayer;
    OrganizationServiceLayer organizationServiceLayer; 

    public PersonController(PersonServiceLayer personServiceLayer, SuperpowerServiceLayer superpowerServiceLayer, OrganizationServiceLayer organizationServiceLayer) {
        this.personServiceLayer = personServiceLayer;
        this.superpowerServiceLayer = superpowerServiceLayer;
        this.organizationServiceLayer = organizationServiceLayer;
    }

    

    @RequestMapping(value="/personIndex", method=RequestMethod.GET)
    public String personIndex(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException{
        
        List<Person> personList = personServiceLayer.getPersonList();
        model.addAttribute("personList", personList);
        
        List<Superpower> superpowerList = superpowerServiceLayer.getSuperpowerList();
        model.addAttribute("superpowerList", superpowerList);
        
        List<Organization> organizationList = organizationServiceLayer.getOrganizationList();
        model.addAttribute("orgList", organizationList);
       //this goes back to the JSP 
       return "Person/Person";
    }
    
    
    @RequestMapping (value="/removePerson" , method=RequestMethod.GET)
    public String removePerson(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException {
       
        String personIDString = request.getParameter("personID");
        int personID = Integer.parseInt(personIDString);
        personServiceLayer.removePerson(personID);
        
        //redirect takes you to another controller method due to the controller endpoints (these are URLs)
        //in this case goes to the method personIndex because the request mapping value is /personIndex. and then since 
        //that method returns the JSP page, after the person is removed it will display the List again 
        //this time w/o that person that was removed.
        return "redirect:/personIndex";
    }    
    
    
      //get person by Id to edit, link takes you to the edit page
    @RequestMapping(value="/displayEditPersonForm" , method=RequestMethod.GET)
    public String displayEditPersonForm(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException {
        
        String personIDString = request.getParameter("personID");
        int personID = Integer.parseInt(personIDString);
        Person getPersonByIdToEdit = personServiceLayer.getPerson(personID);
        model.addAttribute("getPersonByIdToEdit", getPersonByIdToEdit);
        
        List<Superpower> spList = superpowerServiceLayer.getSuperpowerList();
        model.addAttribute("superpowerList", spList);
        
        List<Organization> orgList = organizationServiceLayer.getOrganizationList();
        model.addAttribute("orgList", orgList);
            
        
        return "Person/EditPerson";
    }  
    
    
    //update 
    @RequestMapping(value="/editPerson" , method=RequestMethod.POST)
    public String editPerson(@ModelAttribute("getPersonByIdToEdit") Person person, HttpServletRequest request) throws NotFoundException, PersistenceException {
     
        List <Superpower> superpowersForPerson = new ArrayList<>(); 
        //takes multiple superpower ID's and puts it inside the string array using request.parameter values.
        String [] spList =  request.getParameterValues("selectSuperpower");
        // goes through the list of superpowers, takes each and converts it into an integer that you can use to get the power
        //then add the power to the list. 
        for (String currentString : spList) {
            int superpowerID = Integer.parseInt(currentString);
            Superpower currentSuperpower = superpowerServiceLayer.getPower(superpowerID);
            superpowersForPerson.add(currentSuperpower);
        } 
        
        List <Organization> organizationsForPerson = new ArrayList<>();
        String [] orgList = request.getParameterValues("selectOrganization");
        for (String currentOrg : orgList) {
            int orgID = Integer.parseInt(currentOrg);
            Organization currentOrganization = organizationServiceLayer.getOrganization(orgID);
            organizationsForPerson.add(currentOrganization);
        }
        
        person.setSuperpowers(superpowersForPerson);
        person.setListofOrganizations(organizationsForPerson);
        personServiceLayer.updatePerson(person);
      
        return "redirect:/personIndex";
    }
   
    
    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String addPerson(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException {
        
        String isHeroString = request.getParameter("isHero");
        Boolean isHero = Boolean.parseBoolean(isHeroString);
        
        List <Superpower> superpowersForPerson = new ArrayList<>(); 
        //takes multiple superpower ID's and puts it inside the string array using request.parameter values.
        String [] spList =  request.getParameterValues("selectSuperpower");
        // goes through the list of superpowers, takes each and converts it into an integer that you can use to get the power
        //then add the power to the list. 
        for (String currentString : spList) {
            int superpowerID = Integer.parseInt(currentString);
            Superpower currentSuperpower = superpowerServiceLayer.getPower(superpowerID);
            superpowersForPerson.add(currentSuperpower);
        } 
        
        List <Organization> organizationsForPerson = new ArrayList<>();
        String [] orgList = request.getParameterValues("selectOrganization");
        for (String currentOrg : orgList) {
            int orgID = Integer.parseInt(currentOrg);
            Organization currentOrganization = organizationServiceLayer.getOrganization(orgID);
            organizationsForPerson.add(currentOrganization);
        }
        
        //get values user puts into the form and create a new person object
        Person person = new Person();
        person.setPersonName(request.getParameter("personName"));
        person.setPersonDescription(request.getParameter("personDescription"));
        person.setIsHero(isHero);
        person.setSuperpowers(superpowersForPerson);
        person.setListofOrganizations(organizationsForPerson);
        personServiceLayer.addPerson(person);
 
        return "redirect:/personIndex";
    }
    
    
        
    @RequestMapping(value = "/goToPersonDetailsPage", method = RequestMethod.GET)
    public String goToPersonDetailsPage(HttpServletRequest request, Model model) throws PersistenceException {
             
           String person = request.getParameter("personID");
            int personID = Integer.parseInt(person);
            Person prsn = personServiceLayer.getPerson(personID);
            model.addAttribute("prsn", prsn);
    
        List<Superpower> superpowerList = prsn.getSuperpowers();
        model.addAttribute("superpowerList", superpowerList);
        
        List<Organization> organizationList = prsn.getListofOrganizations();
        model.addAttribute("orgList", organizationList);
            
     
        return "Person/PersonDetails";  
    }
    
    
    
    
    
    
    
    
    
    
    
}
