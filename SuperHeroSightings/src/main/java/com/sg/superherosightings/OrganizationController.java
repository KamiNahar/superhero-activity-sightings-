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
import sg.dontdiejustkode.superherosightingsgroupwork.services.NotFoundException;
import sg.dontdiejustkode.superherosightingsgroupwork.services.OrganizationServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.PersonServiceLayer;

/**
 *
 * @author kaminahar
 */
@Controller
public class OrganizationController {
    
     @Inject 
     OrganizationServiceLayer organizationServiceLayer;
     PersonServiceLayer personServiceLayer; 

    public OrganizationController(OrganizationServiceLayer organizationServiceLayer, PersonServiceLayer personServiceLayer) {
        this.organizationServiceLayer = organizationServiceLayer;
        this.personServiceLayer = personServiceLayer;
    }
    

  
    
    @RequestMapping(value="/organizationIndex", method=RequestMethod.GET)
    public String organizationIndex(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException{
        
        List<Organization> organizationList = organizationServiceLayer.getOrganizationList();
        model.addAttribute("orgList", organizationList);
        
        List<Person> personList = personServiceLayer.getPersonList();
        model.addAttribute("personList", personList);
        
       return "Organization/Organization";
    }
    
    //delete Org
    @RequestMapping(value="/removeOrg" , method=RequestMethod.GET)
    public String removeOrg(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException {
        
        String orgIDString =request.getParameter("organizationID");
        int organizationID = Integer.parseInt(orgIDString);
        organizationServiceLayer.removeOrganization(organizationID);
        
        return "redirect:/organizationIndex";  
       
    }
    
   //get Org by Id to edit, link takes you to the edit page
    @RequestMapping(value="/displayEditOrganizationForm" , method=RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException {
        
//        String orgIDString = request.getParameter("organizationID");
//        int orgID = Integer.parseInt(orgIDString);
//        Organization getOrganizationByIdToEdit = organizationServiceLayer.getOrganization(orgID);
//        model.addAttribute("getOrganizationByIdToEdit", getOrganizationByIdToEdit);
//        
//        List <Person> orgMembers = new ArrayList<>();
//        String [] prsnList = request.getParameterValues("selectOrgMember");
//        for (String currentString : prsnList) {
//            int personID = Integer.parseInt(currentString);
//            Person currentPerson = personServiceLayer.getPerson(personID);
//            orgMembers.add(currentPerson);
//        }
//        model.addAttribute("personList", orgMembers);
//        
         String organization = request.getParameter("organizationID");
         int orgID = Integer.parseInt(organization);
         Organization org = organizationServiceLayer.getOrganization(orgID);
         model.addAttribute("getOrganizationByIdToEdit", org);
         
         List<Person> orgMembers = org.getOrganizationMembers();
         model.addAttribute("personList", orgMembers);
        
        
        
        
        
        return "Organization/EditOrganization";
    }  
    
    
    //update 
    @RequestMapping(value="/editOrganization" , method=RequestMethod.POST)
    public String editOrganization(@ModelAttribute("getOrganizationByIdToEdit") Organization organization, HttpServletRequest request) throws NotFoundException, PersistenceException {
       
        Organization oldOrg = organizationServiceLayer.getOrganization(organization.getOrganizationID());
        
        oldOrg.setOrgName(organization.getOrgName());
        oldOrg.setOrgDescription(organization.getOrgDescription());
        oldOrg.setOrgContactInfo(organization.getOrgContactInfo());
        oldOrg.setIsHeroOrg(organization.getIsHeroOrg());
        
        for (Person currentMembers : oldOrg.getOrganizationMembers()) {
            personServiceLayer.removePersonOrganization(currentMembers.getPersonID(), organization.getOrganizationID());
        }
        
        List <Person> orgMembers = new ArrayList<>();
        String [] prsnList = request.getParameterValues("selectOrgMember");
        for (String currentString : prsnList) {
            int personID = Integer.parseInt(currentString);
            Person currentPerson = personServiceLayer.getPerson(personID);
            orgMembers.add(currentPerson);
        }
        oldOrg.setOrganizationMembers(orgMembers);
        
        organizationServiceLayer.updateOrganization(oldOrg);
        
        return "redirect:/organizationIndex";
    }
   
    @RequestMapping(value = "/addOrganization", method = RequestMethod.POST)
    public String addOrganization(HttpServletRequest request, Model model) throws NotFoundException, PersistenceException {
        
        String orgTypeString = request.getParameter("isHeroOrg");
        Boolean isHeroOrganization = Boolean.parseBoolean(orgTypeString);
        
        List <Person> orgMembers = new ArrayList<>();
        String [] prsnList = request.getParameterValues("selectOrgMember");
        for (String currentString : prsnList) {
            int personID = Integer.parseInt(currentString);
            Person currentPerson = personServiceLayer.getPerson(personID);
            orgMembers.add(currentPerson);
        }
        
        //get values user puts into the form and create a new organization object
        Organization organization = new Organization();
        organization.setOrgName(request.getParameter("orgName"));
        organization.setOrgContactInfo(request.getParameter("orgContactInfo"));
        organization.setOrgDescription(request.getParameter("orgDescription"));
        organization.setIsHeroOrg(isHeroOrganization);
        organization.setOrganizationMembers(orgMembers);
        
        organizationServiceLayer.addOrganization(organization);
 
        return "redirect:/organizationIndex";
    }
    
        @RequestMapping(value = "/goToOrganizationsDetailsPage", method = RequestMethod.GET)
    public String goToLocationDetailsPage(HttpServletRequest request, Model model) throws PersistenceException {
             
         String organization = request.getParameter("organizationID");
         int orgID = Integer.parseInt(organization);
         Organization org = organizationServiceLayer.getOrganization(orgID);
         model.addAttribute("org", org);
         
         List<Person> orgMembers = org.getOrganizationMembers();
         model.addAttribute("members", orgMembers);
         
         
//         List<Person> orgMembers = personServiceLayer.getPeopleByOrganizationID(orgID);
//         model.addAttribute("members", orgMembers);
         
        return "Organization/OrganizationDetails";  
    } 
    
    
    
    
    
    
    
}
