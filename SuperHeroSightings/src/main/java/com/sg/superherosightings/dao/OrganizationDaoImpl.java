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
import sg.dontdiejustkode.superherosightingsgroupwork.dao.OrganizationDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Organization;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.User;


public class OrganizationDaoImpl implements OrganizationDao  {
    
private JdbcTemplate jdbcTemplate; 
public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate; 
}

private static final String SQL_INSERT_ORGANIZATION
        = "insert into Org "
        + "(OrgName, OrgContactInfo, OrgDescription, IsHeroOrg) "
        + "values (?,?,?,?) ";
private static final String SQL_INSERT_PERSONORGANIZATION
        = "insert into PersonOrganization "
        + "(PersonID, OrganizationID) "
        + "values (?, ?) ";
private static final String SQL_REMOVE_PERSONORGANIZATION
        = "delete from PersonOrganization where OrganizationID = ? ";
private static final String SQL_REMOVE_ORGANIZATION
        = "delete from Org where OrganizationID = ?; ";
private static final String SQL_SELECT_ORGANIZATION
        = "select * from Org where OrganizationID = ?; ";
private static final String SQL_SELECT_ORGANIZATIONS_BY_PERSON_ID
        = "select o.OrganizationID, o.OrgName, o.OrgContactInfo, o.OrgDescription, o.IsHeroOrg  "
        + "from Org o "
        + "Join PersonOrganization po on o.OrganizationID = po.OrganizationID "
        + "where po.PersonID = ?; ";
private static final String SQL_SELECT_ALL_ORGANIZATIONS
        = "select * from Org ";
private static final String SQL_UPDATE_ORGANIZATIONS
        = "update Org set "
        + "OrgName = ?, "
        + "OrgContactInfo = ?, "
        + "OrgDescription = ?, "
        + "IsHeroOrg = ? "
        + "where OrganizationID = ?; ";

   
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization addOrganization(Organization organization) throws PersistenceException {
    jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
            organization.getOrgName(),
            organization.getOrgContactInfo(),
            organization.getOrgDescription(),
            organization.getIsHeroOrg());
    int newID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
    organization.setOrganizationID(newID);
    insertPersonOrganizationTable(organization);
    
    return organization;
    }

    @Override
    public Organization getOrganization(int organizationID) throws PersistenceException {
    try {
        return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION, new OrganizationMapper(), organizationID);
    } catch (EmptyResultDataAccessException ex) {
        return null;
    }
    }

    @Override
    public List<Organization> getOrganizationList() throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
    
    }

    @Override
    public void updateOrganization(Organization organization) throws PersistenceException {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATIONS,
                organization.getOrgName(),
                organization.getOrgContactInfo(),
                organization.getOrgDescription(),
                organization.getIsHeroOrg(),
                organization.getOrganizationID());
        jdbcTemplate.update(SQL_REMOVE_PERSONORGANIZATION, organization.getOrganizationID());
        insertPersonOrganizationTable(organization);
    }

    @Override
    public void removeOrganization(int organizationID) throws PersistenceException {
        jdbcTemplate.update(SQL_REMOVE_ORGANIZATION, organizationID);

    }

    @Override
    public List<Organization> getOrganizationsByPersonID(int personID) throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_PERSON_ID, new OrganizationMapper(), personID);
    }

 
  private void insertPersonOrganizationTable(Organization organization) throws PersistenceException {

  final int organizationID = organization.getOrganizationID();
  final List<Person> allPersonsInAnOrganization = organization.getOrganizationMembers();
  for (Person currentPerson : allPersonsInAnOrganization) {
      //order matters here too, jdbcTemplate order has to be same as prepared statement order (order matters in most places)
      jdbcTemplate.update(SQL_INSERT_PERSONORGANIZATION,currentPerson.getPersonID(), organizationID);
     }
   
  }

    @Override
    public List<User> getAllAdminsForOrganization(int organizationID) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> getAllOrganizationsForAdmin(int userID) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
            Organization organization = new Organization(); 
            organization.setOrganizationID(rs.getInt("OrganizationID"));
            organization.setOrgName(rs.getString("OrgName"));
            organization.setOrgContactInfo(rs.getString("OrgContactInfo"));
            organization.setOrgDescription(rs.getString("OrgDescription"));
            organization.setIsHeroOrg(rs.getBoolean("IsHeroOrg"));
            return organization;
        } 
        
    }
    
    
  
    
}
