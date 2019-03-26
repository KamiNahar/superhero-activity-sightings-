///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sg.superherosightings.services;
//
//import java.util.List;
//import javax.inject.Inject;
//import sg.dontdiejustkode.superherosightingsgroupwork.dao.UserDao;
//import sg.dontdiejustkode.superherosightingsgroupwork.dto.User;
//import sg.dontdiejustkode.superherosightingsgroupwork.services.UserServiceLayer;
//
///**
// *
// * @author kaminahar
// */
//public class UserServiceLayerImpl implements UserServiceLayer {
//
//    UserDao userDao; 
//    
//    @Inject
//    public UserServiceLayerImpl(UserDao userDao) {
//        this.userDao = userDao;
//    }
//    
//    
//    @Override
//    public User addUser(User user) {
//        return userDao.addUser(user);
//    }
//
//
//    @Override
//    public List<User> getUserList() {
//        return userDao.getUserList();
//    }
//
//    @Override
//    public void deleteUser(String userName) {
//        userDao.deleteUser(userName);
//    }
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//        
//    @Override
//    public User getUser(String userName) {
//        return userDao.getUser(userName);
//    }
//    
//    @Override
//    public void editUser(User user) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    
//    
//    
//    
//}
