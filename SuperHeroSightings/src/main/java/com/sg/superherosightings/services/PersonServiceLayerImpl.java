/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.jdbc.core.RowMapper;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.OrganizationDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersonDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.SuperpowerDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Organization;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Superpower;
import sg.dontdiejustkode.superherosightingsgroupwork.services.NotFoundException;
import sg.dontdiejustkode.superherosightingsgroupwork.services.PersonServiceLayer;


public class PersonServiceLayerImpl implements PersonServiceLayer {

    PersonDao personDao;
    SuperpowerDao superpowerDao;
    OrganizationDao organizationDao;
    
    @Inject
    public PersonServiceLayerImpl(PersonDao personDao, SuperpowerDao superpowerDao, OrganizationDao organizationDao) {
        this.personDao = personDao;
        this.superpowerDao = superpowerDao;
        this.organizationDao = organizationDao;
    }
    
    @Override
    public Person addPerson(Person person) throws PersistenceException {
        try {
            return personDao.addPerson(person);
        } catch (PersistenceException ex) {
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


    @Override
    public Person getPerson(int personID) throws PersistenceException {
        try {
            Person prsn =  personDao.getPerson(personID);
            List<Superpower> superpowersForPerson = superpowerDao.getPowersByPersonID(prsn.getPersonID());
            List<Organization> orgsForPerson = organizationDao.getOrganizationsByPersonID(prsn.getPersonID());
            prsn.setSuperpowers(superpowersForPerson);
            prsn.setListofOrganizations(orgsForPerson);
            return prsn;
        } catch (PersistenceException ex) {
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    } 

    @Override
    public List<Person> getPersonList() throws PersistenceException {
        try {
            return personDao.getPersonList();
        } catch (PersistenceException ex) {
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void updatePerson(Person person) throws PersistenceException {
        try {
            personDao.updatePerson(person);
        } catch (PersistenceException ex) {
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removePerson(int personID) throws PersistenceException {
        try {
            personDao.removePerson(personID);
        } catch (PersistenceException ex) {
            Logger.getLogger(PersonServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addPersonOrganization(int personID, int organizationID) throws PersistenceException {
        personDao.addPersonOrganization(personID, organizationID);
    }

    @Override
    public void addPersonSuperpower(int personID, int powerID) throws PersistenceException {
        personDao.addPersonSuperpower(personID, powerID);
    }

    @Override
    public List<Person> getPeopleByLocationID(int locationID) throws PersistenceException {
        List<Person> personList = personDao.getPeopleByLocationID(locationID);
        return personList;
    }

    @Override
    public List<Person> getPeopleByOrganizationID(int organizationID) {
        List<Person> personList = personDao.getPeopleByOrganizationID(organizationID);
        return personList; 
    }

    @Override
    public void removePersonOrganization(int personID, int organizationID) throws PersistenceException {
        personDao.removePersonOrganization(personID, organizationID);
    }

    @Override
    public void removePersonSuperpower(int personID, int powerID) throws PersistenceException {
        personDao.removePersonSuperpower(personID, powerID);
    }

    @Override
    public List<Person> getPeopleBySuperpowerID(int superpowerID) {
        List<Person> getPersonListByPowerID = personDao.getPeopleBySuperpowerID(superpowerID);
        return getPersonListByPowerID;
    }

    @Override
    public Person getPersonBySightingID(int sightingsID) throws PersistenceException {
        return personDao.getPersonBySightingID(sightingsID);
    }

    
    
} 


