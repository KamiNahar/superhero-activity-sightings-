///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sg.superherosightings.dao;
//
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import sg.dontdiejustkode.superherosightingsgroupwork.dao.LocationDao;
//import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
//import sg.dontdiejustkode.superherosightingsgroupwork.dto.Location;
//
///**
// *
// * @author kaminahar
// */
//public class LocationDaoImplTest {
//    
//    LocationDao locationDao;
//    
//    public LocationDaoImplTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() throws PersistenceException {
//    }
//        /* 
//     Inject DAO to test , takes the DAO from the Spring
//    */
//    ApplicationContext ctx
//    = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//    //remove all locations from database.
//    locationDao = ctx.getBean("LocationDaoImpl", LocationDao.class);
//    List<Location> LocationList = locationDao.getLocationList();
//    for (Location currentLocation : LocationList) {
//    locationDao.removeLocation(currentLocation.getLocationID());
//}
//
//        
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of setJdbcTemplate method, of class LocationDaoImpl.
//     */
//    @Test
//    public void testSetJdbcTemplate() {
//    }
//
//    /**
//     * Test of addLocation method, of class LocationDaoImpl.
//     */
//    @Test
//    public void testAddLocation() throws Exception {
//    }
//
//    /**
//     * Test of getLocation method, of class LocationDaoImpl.
//     */
//    @Test
//    public void testGetLocation() throws Exception {
//    }
//
//    /**
//     * Test of getLocationsByPersonID method, of class LocationDaoImpl.
//     */
//    @Test
//    public void testGetLocationsByPersonID() throws Exception {
//    }
//
//    /**
//     * Test of getLocationList method, of class LocationDaoImpl.
//     */
//    @Test
//    public void testGetLocationList() throws Exception {
//    }
//
//    /**
//     * Test of updateLocation method, of class LocationDaoImpl.
//     */
//    @Test
//    public void testUpdateLocation() throws Exception {
//    }
//
//    /**
//     * Test of removeLocation method, of class LocationDaoImpl.
//     */
//    @Test
//    public void testRemoveLocation() throws Exception {
//    }
//    
//}
