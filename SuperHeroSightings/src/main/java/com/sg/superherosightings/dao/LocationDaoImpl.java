/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.LocationDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Location;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Superpower;


public class LocationDaoImpl implements LocationDao {
    
private JdbcTemplate jdbcTemplate; 
public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate; 
}

private static final String SQL_INSERT_LOCATION
        = "insert into Location "
        + "(LocationName, LocationDescription, LocationAddress, LocationLatitude, LocationLongitude) "
        + "values (?,?,?,?,?)";
private static final String SQL_REMOVE_LOCATION
        = "delete from Location where LocationID = ?; ";
private static final String SQL_SELECT_LOCATION
        = "select * from Location where LocationID = ? ";
private static final String SQL_SELECT_LOCATION_BY_PERSON_ID
        = "select l.LocationID, l.LocationName, l.LocationDescription, l.LocationAddress, l.LocationLatitude, l.LocationLongitude  "
        + "from Location l "
        + "Join Sightings s on l.LocationID = s.LocationID "
        + "where s.PersonID = ?; ";
private static final String SQL_SELECT_ALL_LOCATIONS
        = "select * from Location ";
private static final String SQL_UPDATE_LOCATION
        = "update Location set "
        + "LocationName = ?, "
        + "LocationDescription = ?, "
        + "LocationAddress = ?, "
        + "LocationLatitude = ?, "
        + "LocationLongitude = ? "
        + "where LocationID = ?; ";
private static final String SQL_GET_LOCATION_BY_SIGHTING_ID
        = "select l.LocationID, l.LocationName, l.LocationDescription, l.LocationAddress, l.LocationLatitude, l.LocationLongitude  "
        + "from Location l "
        + "Join Sightings s on l.LocationID = s.LocationID "
        + "where s.SightingsID = ?; ";
        
        
        
        
        
         
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Location addLocation(Location location) throws PersistenceException {
    jdbcTemplate.update(SQL_INSERT_LOCATION, 
            location.getLocationName(),
            location.getLocationDescription(),
            location.getLocationAddress(),
            location.getLocationLatitude(),
            location.getLocationLongitude());
    int newID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
    location.setLocationID(newID);
    return location;
    } 

    @Override
    public Location getLocation(int locationID) throws PersistenceException {
 try {
       return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, new LocationMapper(), locationID);
    } catch (EmptyResultDataAccessException ex) {    
        return null;
      }
    }

    @Override
    public List<Location> getLocationsByPersonID(int personID) throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_LOCATION_BY_PERSON_ID, new LocationMapper(), personID);
    }

    @Override
    public List<Location> getLocationList() throws PersistenceException {
    try {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
    } catch (EmptyResultDataAccessException ex) {    
        return null;
      }
    }

    @Override
    public void updateLocation(Location location) throws PersistenceException {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
            location.getLocationName(),
            location.getLocationDescription(),
            location.getLocationAddress(),
            location.getLocationLatitude(),
            location.getLocationLongitude(),
            location.getLocationID());
    }

    @Override
    public void removeLocation(int locationID) throws PersistenceException {
        jdbcTemplate.update(SQL_REMOVE_LOCATION, locationID);
    }

    @Override
    public Location getLocationBySightingID(int sightingsID) throws PersistenceException {
        return jdbcTemplate.queryForObject(SQL_GET_LOCATION_BY_SIGHTING_ID, new LocationMapper(), sightingsID);
    }

private static final class LocationMapper implements RowMapper<Location> {
    /* 
    Mapper also communicates with the database. 
    The mapper converts the data from the database into a DTO.
    */
        @Override
        public Location mapRow(ResultSet rs, int rowNumber) throws SQLException {
            Location location = new Location();
            location.setLocationID(rs.getInt("LocationID"));
            location.setLocationName(rs.getString("LocationName"));
            location.setLocationDescription(rs.getString("LocationDescription"));
            location.setLocationAddress(rs.getString("LocationAddress"));
            location.setLocationLatitude(rs.getBigDecimal("LocationLatitude"));
            location.setLocationLongitude(rs.getBigDecimal("LocationLongitude"));
            return location;
        }
    }  
 
   
    
    
    
    
    
    
    
    



}
