/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.OrganizationDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersonDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Organization;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.User;
import sg.dontdiejustkode.superherosightingsgroupwork.services.NotFoundException;
import sg.dontdiejustkode.superherosightingsgroupwork.services.OrganizationServiceLayer;


public class OrganizationServiceLayerImpl implements OrganizationServiceLayer {

    OrganizationDao orgDao;
    PersonDao personDao;
    
    @Inject
    public OrganizationServiceLayerImpl(OrganizationDao organizationDao, PersonDao persDao) {
        this.orgDao = organizationDao;
        this.personDao = persDao;
    }

    @Override
    public Organization addOrganization(Organization organization) throws PersistenceException {
        try {
            return orgDao.addOrganization(organization);
        } catch (PersistenceException ex) {
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
    }


    
//    @Override
//    public Organization getOrganization(int organizationID) throws PersistenceException {
//        try {
//            return orgDao.getOrganization(organizationID);
//        } catch (PersistenceException ex) {
//            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }
    
    
    
        @Override
    public Organization getOrganization(int organizationID) throws PersistenceException {
        try {
            Organization org = orgDao.getOrganization(organizationID);
            List <Person> listOfOrgMembers = personDao.getPeopleByOrganizationID(org.getOrganizationID());
            org.setOrganizationMembers(listOfOrgMembers);
            return org;
        } catch (PersistenceException ex) {
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    
    

    @Override
    public List<Organization> getOrganizationList() throws PersistenceException {
        try {
            return orgDao.getOrganizationList();
        } catch (PersistenceException ex) {
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void updateOrganization(Organization organization) throws PersistenceException {
        try {
            orgDao.updateOrganization(organization);
            
        } catch (PersistenceException ex) {
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void removeOrganization(int OrganizationID) throws PersistenceException {
        try {
            orgDao.removeOrganization(OrganizationID);
        } catch (PersistenceException ex) {
            Logger.getLogger(OrganizationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Organization> getOrganizationsByPersonID(int personID) throws PersistenceException {
       return orgDao.getOrganizationsByPersonID(personID);
    }

    @Override
    public List<User> getAllAdminsForOrganization(int organizationID) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> getAllOrganizationsForAdmin(int userID) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




    
}
