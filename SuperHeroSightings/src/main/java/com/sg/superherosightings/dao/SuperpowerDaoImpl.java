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
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.SuperpowerDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Superpower;


public class SuperpowerDaoImpl implements SuperpowerDao {
    
/* Here injecting the jdbcTemplate. It allows us to communicate with the database
using the prepared statements written below it.
*/  
private JdbcTemplate jdbcTemplate; 
public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate; 
}
/* 
Prepared statements are used to create, read, update and delete info on the 
database.
*/
private static final String SQL_INSERT_SUPERPOWER
        = "insert into Superpower "
        + "(SuperpowerName) "
        + "values (?) ";
private static final String SQL_REMOVE_SUPERPOWER
        = "delete from Superpower where SuperpowerID = ? ";
private static final String SQL_SELECT_SUPERPOWER
        = "select * from Superpower where SuperpowerID = ? ";        
private static final String SQL_SELECT_ALL_SUPERPOWERS
        = "select * from Superpower ";
private static final String SQL_UPDATE_SUPERPOWER
        = "update Superpower set "
        + "SuperpowerName = ? "
        + "where SuperpowerID = ? "; 
private static final String SQL_SELECT_SUPERPOWERS_BY_PERSON_ID
        = "select s.SuperpowerID, s.SuperpowerName "
        + "from Superpower s "
        + "Join PersonSuperpower ps on s.SuperpowerID = ps.SuperpowerID "
        + "where ps.PersonID = ? ";

    @Override
    
    /*
    @Transactional annotation is used when a method has two things going on. 
    So in this example, first you get all the attributes that make up superpower
    and then you give it a name(the superpower object) to add to the database. 
    Then make an empty superpowerID variable and set it to whatever the next ID is 
    (ID is autoincremented) to that empty variable and return superpower object 
    with the new attributes you just put in the database. 
    */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Superpower addPower(Superpower superpower) throws PersistenceException {
    jdbcTemplate.update(SQL_INSERT_SUPERPOWER,
    /* User gives the superpower a name.  */
           superpower.getSuperpowerName());
    /*
    takes the last ID created in the database,and then makes the next auto 
    incremented ID.
    queryForObject searches through your superpowers in the database and then 
    assigns a number to the last superpower and then sends it back to the user
    */
    int newID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
            Integer.class);
            
            superpower.setSuperpowerID(newID);
            return superpower;
    }

    @Override
    public Superpower getPower(int powerID) throws PersistenceException {
    try {
       return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPOWER, new SuperpowerMapper(), powerID);
    } catch (EmptyResultDataAccessException ex) {    
        return null;
      }
    }

    @Override
    public List<Superpower> getSuperpowerList() throws PersistenceException {
    return jdbcTemplate.query(SQL_SELECT_ALL_SUPERPOWERS, new SuperpowerMapper()); 
    }

    @Override
    public void updatePower(Superpower superpower) throws PersistenceException {
    jdbcTemplate.update(SQL_UPDATE_SUPERPOWER, superpower.getSuperpowerName(), superpower.getSuperpowerID());
    }

    @Override
    public void removePower(int powerID) throws PersistenceException {
    jdbcTemplate.update(SQL_REMOVE_SUPERPOWER, powerID);
    }

    @Override
    public List<Superpower> getPowersByPersonID(int personID) throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_PERSON_ID, new SuperpowerMapper(), personID);

    }
    
    private List<Superpower> findSuperpowersForPerson(Person person) {
      return jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_PERSON_ID, new SuperpowerMapper(), person.getPersonID());
    }
    
private static final class SuperpowerMapper implements RowMapper<Superpower> {
    /* 
    Mapper also communicates with the database. 
    The mapper converts the data from the database into an object(aka DTO).
    */
        @Override
        public Superpower mapRow(ResultSet rs, int rowNumber) throws SQLException {
            Superpower superpower = new Superpower();
            superpower.setSuperpowerID(rs.getInt("SuperpowerID"));
            superpower.setSuperpowerName(rs.getString("SuperpowerName"));
            return superpower;
        }
    }




}
