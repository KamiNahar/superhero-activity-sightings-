/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.services;


import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.LocationDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersonDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.SightingDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Location;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Sighting;
import sg.dontdiejustkode.superherosightingsgroupwork.services.NotFoundException;
import sg.dontdiejustkode.superherosightingsgroupwork.services.SightingServiceLayer;


public class SightingServiceLayerImpl implements SightingServiceLayer {

    SightingDao sightingDao;
    PersonDao personDao;
    LocationDao locationDao;
    
    @Inject
     public SightingServiceLayerImpl(SightingDao sightingDao, PersonDao personDao, LocationDao locationDao) {
        this.sightingDao = sightingDao;
        this.personDao = personDao;
        this.locationDao = locationDao;
    }

            
    
    @Override
    public Sighting addSighting(Sighting sighting) throws PersistenceException {
        try {
            return sightingDao.addSighting(sighting);
        } catch (PersistenceException ex) {
            Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


    @Override
    public Sighting getSightingByID(int sightingsID) throws PersistenceException {
        try {
           Sighting sight = sightingDao.getSightingByID(sightingsID);
           Person prsn = personDao.getPersonBySightingID(sightingsID);
           Location loc = locationDao.getLocationBySightingID(sightingsID);
           sight.setPerson(prsn);
           sight.setLocation(loc);
           return sight;
        } catch (PersistenceException ex) {
            Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Sighting> getSightingList() throws PersistenceException {
        try {
            return sightingDao.getSightingList();
        } catch (PersistenceException ex) {
            Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void updateSighting(Sighting sighting) throws PersistenceException {
        try {
            sightingDao.updateSighting(sighting);
        } catch (PersistenceException ex) {
            Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeSighting(int sightingID) throws PersistenceException {
        try {
            sightingDao.removeSighting(sightingID);
        } catch (PersistenceException ex) {
            Logger.getLogger(SightingServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Sighting> getSightingByDate(LocalDate sightingsDate) throws PersistenceException {
        return sightingDao.getSightingByDate(sightingsDate);
    }

    //last ten sightings here not the first ten. 
    @Override
    public List<Sighting> getFirstTenSightings() throws PersistenceException {
        return sightingDao.getFirstTenSightings();

    }

    @Override
    public List<Sighting> getSightingByLocation(int locationID) throws PersistenceException {
        return sightingDao.getSightingByLocation(locationID);
    }

    @Override
    public List<Sighting> getSightingByPerson(int personID) throws PersistenceException {
        return sightingDao.getSightingByPerson(personID);
    }


    
}
