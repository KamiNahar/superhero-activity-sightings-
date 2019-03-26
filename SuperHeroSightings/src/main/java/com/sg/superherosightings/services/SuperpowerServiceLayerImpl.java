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
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.SuperpowerDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Superpower;
import sg.dontdiejustkode.superherosightingsgroupwork.services.NotFoundException;
import sg.dontdiejustkode.superherosightingsgroupwork.services.SuperpowerServiceLayer;


public class SuperpowerServiceLayerImpl implements SuperpowerServiceLayer {

    SuperpowerDao powerDao;
    
    @Inject
    public SuperpowerServiceLayerImpl(SuperpowerDao spowerDao) {
        this.powerDao = spowerDao;   
    }
   
    
    @Override
    public Superpower addPower(Superpower superpower) throws PersistenceException {
        try {
            return powerDao.addPower(superpower);
        } catch (PersistenceException ex) {
            Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
    @Override
    public Superpower getPower(int powerID) throws PersistenceException {
        try {
            return powerDao.getPower(powerID);
        } catch (PersistenceException ex) {
            Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

   
    @Override
    public List<Superpower> getSuperpowerList() throws PersistenceException {
        try {
            return powerDao.getSuperpowerList();
        } catch (PersistenceException ex) {
            Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
    @Override
    public void updatePower(Superpower superpower) throws PersistenceException {
        try {
            powerDao.updatePower(superpower);
        } catch (PersistenceException ex) {
            Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void removePower(int powerID) throws PersistenceException {
        try {
            powerDao.removePower(powerID);
        } catch (PersistenceException ex) {
            Logger.getLogger(SuperpowerServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public List<Superpower> getPowersByPersonID(int personID) throws PersistenceException {
        List<Superpower> superpowerList = powerDao.getPowersByPersonID(personID);
        return superpowerList;
    }



}
