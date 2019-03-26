/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.LocationDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Location;
import sg.dontdiejustkode.superherosightingsgroupwork.services.LocationServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.NotFoundException;


public class LocationServiceLayerImpl implements LocationServiceLayer {

    LocationDao locationDao;
    
    //takes the dao bean and injects it here so that it can use your service layer
    @Inject
    public LocationServiceLayerImpl(LocationDao LocationDao) {
        this.locationDao = LocationDao;
    }
   
    
    @Override
    public Location addLocation(Location location) throws PersistenceException {
        try {
           return locationDao.addLocation(location);
        } catch (PersistenceException ex) {
            Logger.getLogger(LocationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }   
    }

    
    @Override
    public Location getLocation(int personID) throws PersistenceException {
        try {
            return locationDao.getLocation(personID);
        } catch (PersistenceException ex) {
            Logger.getLogger(LocationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }

    
    @Override
    public List<Location> getLocationList() throws PersistenceException {
//    List<Location> locationList= new ArrayList<>();
        try {
            return locationDao.getLocationList();
        } catch (PersistenceException ex) {
            Logger.getLogger(LocationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
       return null;
        }
       
    }

    
    @Override
    public void updateLocation(Location location) throws PersistenceException {
        try {
            locationDao.updateLocation(location);
        } catch (PersistenceException ex) {
            Logger.getLogger(LocationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void removeLocation(int locationID) throws PersistenceException {
        try {
            locationDao.removeLocation(locationID);
        } catch (PersistenceException ex) {
            Logger.getLogger(LocationServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public List<Location> getLocationsByPersonID(int personID) throws PersistenceException {
       List<Location> locationList =  locationDao.getLocationsByPersonID(personID);
       return locationList  ;
    }

    @Override
    public Location getLocationBySightingID(int sightingsID) throws PersistenceException {
        return locationDao.getLocationBySightingID(sightingsID);
    }


      
}
