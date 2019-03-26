/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.UserDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Organization;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.User;

/**
 *
 * @author kaminahar
 */
public class UserDaoImpl implements UserDao {
    
private JdbcTemplate jdbcTemplate; 
public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate; 
}

private static final String SQL_INSERT_USERS
        = "insert into Users "
        + "(Username, UserPassword, Enabled) "
        + "values (?, ?, 1) ";
private static final String SQL_REMOVE_USER
        = "delete from Users where UserID = ?; ";
private static final String SQL_REMOVE_USERORG
        = "delete from UserOrg where UserID = ? ";
private static final String SQL_SELECT_USER 
        = "select * from Users where UserID = ?; ";
private static final String SQL_SELECT_ALL_USERS
        = "select * from Users ";
private static final String SQL_UPDATE_USERS
        = "update Users set "
        + "Username = ?, "
        + "UserPassword = ? "
        + "Enabled = ?; ";
private static final String SQL_INSERT_AUTHORITIES 
        = "insert into Authorities "
        + "( Username, Authority ) "
        + "values (?, ?) ";
private static final String SQL_REMOVE_AUTHORITIES 
        = "delete from Authorities where Username = ?; ";
private static final String SQL_UPDATE_AUTHORITIES 
        = "update Authorities set "
        + "Username = ?, "
        + "Authority = ? "
        + "Where Username = ? ; ";
private static final String SQL_INSERT_USERORG
        = "insert into UserOrg "
        + "(UserID, OrganizationID) "
        + "values (?, ?) ";
private static final String SQL_SELECT_ALL_USERS_BY_ORGANIZATION_ID
        = "select u.UserID, u.Username, u.UserPassword, u.Enabled "
        + "from Users u "
        + " Join UserOrg uo on u.UserID = uo.UserID "
        + "where uo.OrganizationID ";
        

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User addUser(User newUser) {
        jdbcTemplate.update(SQL_INSERT_USERS,
                newUser.getUserName(),
                newUser.getUserPassword());
                
    newUser.setUserId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
    // insert user's roles 
    ArrayList<String> authorities = newUser.getAuthorities();
    for (String authority : authorities) {
        jdbcTemplate.update(SQL_INSERT_AUTHORITIES, 
                newUser.getUserName(),
                authority);
    }
    //insertUserOrgTable(newUser);
    return newUser;

    }
    
    @Override
    public User getUser(String userName) {
        return jdbcTemplate.queryForObject(SQL_SELECT_USER , new UserMapper(), userName);
    }
    
    @Override
    public void editUser(User user) {
        jdbcTemplate.update(SQL_UPDATE_USERS,
            user.getUserName(),
            user.getUserPassword(),
            user.getAuthorities());
        
//        jdbcTemplate.update(SQL_REMOVE_USERORG, user.getUserId());
//        insertUserOrgTable(user);
    }
    
    @Override
    public List<User> getUserList() {
         return jdbcTemplate.query(SQL_SELECT_ALL_USERS, new UserMapper());
    }

    @Override
    public void deleteUser(String userName) {
        //first delete all authorities and then delete all users
        jdbcTemplate.update(SQL_REMOVE_AUTHORITIES, userName);
        
        jdbcTemplate.update(SQL_REMOVE_USER, userName);
    }
    
private void insertUserOrgTable (User user) {
    final int userID = user.getUserId();
    final List<Organization> allUserOrgs = user.getUserOrganizations();
    for (Organization currentOrganization : allUserOrgs) {
        jdbcTemplate.update(SQL_INSERT_USERORG, userID, currentOrganization.getOrganizationID());
    }
}
        
        private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("UserID"));
            user.setUserName(rs.getString("Username"));
            user.setUserPassword(rs.getString("UserPassword"));
            return user;
        }
    }
    
    
    
    
    
    
   
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
  
    
    
    
    
    
    
}
