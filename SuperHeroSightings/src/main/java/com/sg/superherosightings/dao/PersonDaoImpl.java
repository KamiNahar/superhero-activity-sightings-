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
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersonDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Organization;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Superpower;


public class PersonDaoImpl implements PersonDao {
    
private JdbcTemplate jdbcTemplate; 
/*
set up a setter injection for jdbcTemplate- jdbcTemplate communicates with the 
database
*/
public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate; 
}
/*
Prepared statements also communicates queries with the database. 
Use prepared statements to create, read, update and delete info in the database.
*/
private static final String SQL_INSERT_PERSON
        = "insert into Person "
        + "(PersonName, PersonDescription, IsHero) "
        + "values (?, ?, ?); ";
private static final String SQL_INSERT_PERSONSUPERPOWER
        = "insert into PersonSuperpower "
        + "(PersonID, SuperpowerID) "
        + "values (?, ?) ";
private static final String SQL_INSERT_PERSONORGANIZATION
        = "insert into PersonOrganization "
        + "(PersonID, OrganizationID) "
        + "values (?, ?) ";
private static final String SQL_REMOVE_PERSONSUPERPOWER
        = "delete from PersonSuperpower where PersonID = ? ";
private static final String SQL_REMOVE_PERSONORGANIZATION
        = "delete from PersonOrganization where PersonID = ? ";
private static final String SQL_REMOVE_PERSON
        = "delete from Person where PersonID = ? ";
private static final String SQL_SELECT_PERSON
        = "select * from Person where PersonID = ? ";
private static final String SQL_SELECT_ALL_PERSONS
        = "select * from Person "; 
private static final String SQL_UPDATE_PERSON 
        = "update Person set "
        + "PersonName = ?, "
        + "PersonDescription = ?, "
        + "IsHero = ? "
        + "where PersonID = ?; ";   
private static final String SQL_SELECT_SUPERPOWERS_BY_PERSON_ID
        = "select s.SuperpowerID, s.SuperpowerName "
        + "from Superpower s "
        + "Join PersonSuperpower ps on s.SuperpowerID = ps.SuperpowerID "
        + "where ps.PersonID = ?; ";
private static final String SQL_SELECT_ORGANIZATIONS_BY_PERSON_ID
        = "select o.OrganizationID, o.OrgName, o.OrgContactInfo, o.OrgDescription, o.IsHeroOrg  "
        + "from Organization o "
        + "Join PersonOrganization po on o.OrganizationID = po.OrganizationID "
        + "where po.PersonID = ?; ";
private static final String SQL_SELECT_PEOPLE_BY_LOCATION_ID
        = "select p.PersonID, p.PersonName, p.PersonDescription, p.IsHero "
        + "from Person p "
        + "Join Sightings s on p.PersonID = s.PersonID "
        + "where s.LocationID = ?; ";
private static final String SQL_SELECT_PEOPLE_BY_ORGANIZATION_ID 
        = "select p.PersonID, p.PersonName, p.PersonDescription, p.IsHero "
        + "from Person p "
        + "Join PersonOrganization po on p.PersonID = po.PersonID "
        + "where po.OrganizationID = ?; ";  
private static final String SQL_SELECT_PEOPLE_BY_SUPERPOWER_ID
        = "select p.PersonID, p.PersonName, p.PersonDescription, p.IsHero "
        + "from Person p "
        + "Join PersonSuperpower ps on p.PersonID = ps.PersonID "
        + "where ps.SuperpowerID = ?; ";
private static final String SQL_SELECT_PERSON_BY_SIGHTING_ID
          = "select p.PersonID, p.PersonName, p.PersonDescription, p.IsHero "
        + "from Person p "
        + "Join Sightings s on p.PersonID = s.PersonID "
        + "where s.SightingsID = ?; ";
        
        
        
        
        
        
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Person addPerson(Person person) throws PersistenceException {
      jdbcTemplate.update(SQL_INSERT_PERSON,
            person.getPersonName(),
            person.getPersonDescription(),
            person.getIsHero());
    int newID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
    person.setPersonID(newID);
    insertPersonSuperpowerTable(person);
    
    return person;
    }

    @Override
    public Person getPerson(int personID) throws PersistenceException {
    try {
        return jdbcTemplate.queryForObject(SQL_SELECT_PERSON, new PersonMapper(), personID);
    } catch (EmptyResultDataAccessException ex) {
        return null;
    }
    }

    @Override
    public List<Person> getPersonList() throws PersistenceException {
      return jdbcTemplate.query(SQL_SELECT_ALL_PERSONS, new PersonMapper());
    }

    @Override
    public void updatePerson(Person persons) throws PersistenceException {
      jdbcTemplate.update(SQL_UPDATE_PERSON ,
            persons.getPersonName(),
            persons.getPersonDescription(),
            persons.getIsHero(),
            persons.getPersonID());
      
      jdbcTemplate.update(SQL_REMOVE_PERSONSUPERPOWER, persons.getPersonID());
      insertPersonSuperpowerTable(persons);
    }

    @Override
    public void removePerson(int personID) throws PersistenceException {
        
        jdbcTemplate.update(SQL_REMOVE_PERSONSUPERPOWER, personID);

        jdbcTemplate.update(SQL_REMOVE_PERSON, personID);
        
    }

    @Override
    public void addPersonOrganization(int personID, int organizationID) throws PersistenceException {
        jdbcTemplate.update(SQL_INSERT_PERSONORGANIZATION, personID, organizationID);
    }
    
   
    private void addPersonOrganization(int personID) throws PersistenceException {
        jdbcTemplate.update(SQL_INSERT_PERSONORGANIZATION, personID);
    }


     @Override
    public void removePersonOrganization(int personID, int organizationID) throws PersistenceException {
        jdbcTemplate.update(SQL_REMOVE_PERSONORGANIZATION, personID);
    }
    
    private void removePersonOrganization(int personID) throws PersistenceException {
        jdbcTemplate.update(SQL_REMOVE_PERSONORGANIZATION, personID);
    }
    
    
    @Override
    public void addPersonSuperpower(int personID, int powerID) throws PersistenceException {
        jdbcTemplate.update(SQL_INSERT_PERSONSUPERPOWER, personID, powerID);
    }
    
   
    private void addPersonSuperpower(int personID) throws PersistenceException {
        jdbcTemplate.update(SQL_INSERT_PERSONSUPERPOWER, personID);
    }

    
    @Override
    public void removePersonSuperpower(int personID, int powerID) throws PersistenceException {
        jdbcTemplate.update(SQL_REMOVE_PERSONSUPERPOWER, personID, powerID);
    } 
    
   
    private void removePersonSuperpower(int personID) throws PersistenceException {
        jdbcTemplate.update(SQL_REMOVE_PERSONSUPERPOWER, personID);
    }
    
    
    @Override
    public List<Person> getPeopleByLocationID(int locationID) throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_PEOPLE_BY_LOCATION_ID, new PersonMapper(), locationID);
    }

    @Override
    public List<Person> getPeopleByOrganizationID(int organizationID) {
        return jdbcTemplate.query(SQL_SELECT_PEOPLE_BY_ORGANIZATION_ID, new PersonMapper(), organizationID);
    }   
    
    
    @Override
    public List<Person> getPeopleBySuperpowerID(int superpowerID) {
        return jdbcTemplate.query(SQL_SELECT_PEOPLE_BY_SUPERPOWER_ID, new PersonMapper(), superpowerID);
    }
    
    
    
    private void insertPersonSuperpowerTable(Person person) {
    /*
    creating final variables to get person Id and list of superpowers
    Use these variables to insert values into the bridge table
    -final keyword means the value would remain the same and can't be changed
    */
    final int personID = person.getPersonID();
    final List<Superpower> allSuperpowers = person.getSuperpowers();
    /*
    Take one superpower from the list of allSuperpowers 
    and assign it to currentSuperpower and then perform the codes in
    in the bracket to that currentsuperpower. once you finish move on to next
    superpower until you go through the full list.
    */
    for (Superpower currentSuperpower : allSuperpowers) {
        jdbcTemplate.update(SQL_INSERT_PERSONSUPERPOWER, personID,currentSuperpower.getSuperpowerID());
       }
  
    } 

    @Override
    public Person getPersonBySightingID(int sightingsID) throws PersistenceException {
        return jdbcTemplate.queryForObject(SQL_SELECT_PERSON_BY_SIGHTING_ID, new PersonMapper(), sightingsID);
    }

   

    
    private static final class PersonMapper implements RowMapper<Person> {
   
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setPersonID(rs.getInt("PersonID"));
            person.setPersonName(rs.getString("PersonName"));
            person.setPersonDescription(rs.getString("PersonDescription"));
            person.setIsHero(rs.getBoolean("IsHero"));
            return person;
        }
    }
    


    
    
    
    
    
    
    
    
    
    
    
}
