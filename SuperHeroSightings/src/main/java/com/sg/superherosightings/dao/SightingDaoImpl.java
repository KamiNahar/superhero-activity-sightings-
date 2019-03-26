/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.SightingDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Sighting;


public class SightingDaoImpl implements SightingDao {

private JdbcTemplate jdbcTemplate; 
public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate; 
}

private static final String SQL_INSERT_SIGHTINGS
        = "insert into Sightings "
        + "(SightingsDate, PersonID, LocationID) "
        + "values (?, ?, ?); ";
private static final String SQL_REMOVE_SIGHTINGS
        = "delete from Sightings where SightingsID = ?; ";
private static final String SQL_REMOVE_SIGHTINGS_BY_PERSON_ID
        = "select * from Sightings where PersonID = ?; ";
private static final String SQL_SELECT_SIGHTINGS_BY_SIGHTING_ID
        = "select * from Sightings where SightingsID = ?; ";
private static final String SQL_SELECT_SIGHTINGS_BY_LOCATION_ID
        = "select * from Sightings where LocationID = ?; ";
private static final String SQL_SELECT_SIGHTINGS_BY_DATE
        = "select * from Sightings where SightingsDate = ?; ";
private static final String SQL_SELECT_SIGHTINGS_BY_PERSON_ID
        = "select * from Sightings where PersonID = ?; ";
private static final String SQL_SELECT_ALL_SIGHTINGS
        = "select * from Sightings "; 
private static final String SQL_UPDATE_SIGHTINGS 
        = "update Sightings set "
        + "SightingsDate = ?, "
        + "PersonID = ?, "
        + "LocationID = ? "
        + "where SightingsID = ?; ";   
private static final String SQL_SELECT_LAST_TEN_SIGHTINGS
            = "select Person.*, Location.*, Sightings.SightingsID, Sightings.SightingsDate from Location "
            + "inner join Sightings on Location.LocationID = Sightings.LocationID "
            + "inner join Person on Person.PersonID = Sightings.PersonID order by Sightings.SightingsDate desc limit 0,10";
        
        
        
//        
//        
//        = "select * from Sightings "
//        + "order by sightingsDate desc limit 10; ";
//     
        
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting addSighting(Sighting sighting) throws PersistenceException {
    jdbcTemplate.update(SQL_INSERT_SIGHTINGS,
            sighting.getSightingsDate(),
            sighting.getPerson().getPersonID(),
            sighting.getLocation().getLocationID());
    int newID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
    sighting.setSightingsID(newID);
    
    
    
    return sighting;
    }

    @Override
    public Sighting getSightingByID(int sightingID) throws PersistenceException {
    try {
        return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTINGS_BY_SIGHTING_ID, new SightingMapper(), sightingID);
    } catch (EmptyResultDataAccessException ex) {
        return null;
    }
    }

  

  

    @Override
    public List<Sighting> getSightingList() throws PersistenceException {
    return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, new SightingMapper());
    }

    @Override
    public void updateSighting(Sighting sighting) throws PersistenceException {
    jdbcTemplate.update(SQL_UPDATE_SIGHTINGS,
            sighting.getSightingsDate(),
            sighting.getPerson().getPersonID(),
            sighting.getLocation().getLocationID(),
            sighting.getSightingsID());
    }

    @Override
    public void removeSighting(int sightingsID) throws PersistenceException {
    jdbcTemplate.update(SQL_REMOVE_SIGHTINGS, sightingsID);
    }

    
    //actually the last ten sightings- leaving the name same in method due to groupfolder and to avoid errors
    @Override
    public List<Sighting> getFirstTenSightings() throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_LAST_TEN_SIGHTINGS, new SightingMapper());
          
    }

    @Override
    public List<Sighting> getSightingByLocation(int locationID) throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_LOCATION_ID, new SightingMapper(), locationID);
    }

    @Override
    public List<Sighting> getSightingByDate(LocalDate sightingsDate) throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE, new SightingMapper(), sightingsDate);
    }

    @Override
    public List<Sighting> getSightingByPerson(int personID) throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_PERSON_ID, new SightingMapper(), personID);
                
    }


    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingsID(rs.getInt("SightingsID"));
            sighting.setSightingsDate(LocalDate.parse(rs.getDate("SightingsDate").toString()));
            sighting.setPersonID(rs.getInt("PersonID"));
            sighting.setLocationID(rs.getInt("LocationID"));
            return sighting;
        }
        
    }
    
    
    
    
    
    
    
    
    

    
}
