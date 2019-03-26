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
//import org.springframework.jdbc.core.JdbcTemplate;
//import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
//import sg.dontdiejustkode.superherosightingsgroupwork.dao.SuperpowerDao;
//import sg.dontdiejustkode.superherosightingsgroupwork.dto.Superpower;
//
///**
// *
// * @author kaminahar
// */
//public class SuperpowerDaoImplTest {
//    
//    SuperpowerDao superpowerDao;
//    
//    public SuperpowerDaoImplTest() {
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
//    /* 
//     Inject DAO to test , takes the DAO from the Spring
//    */
//   ApplicationContext ctx
//  = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//
//   /*
//   Remove all superpowers from the database. 
//   */
//   superpowerDao = ctx.getBean("SuperpowerDaoImpl", SuperpowerDao.class);
//   List<Superpower> ListOfSuperPowers = superpowerDao.getSuperpowerList();
//   for (Superpower currentSuperpower : ListOfSuperPowers) {
//       superpowerDao.removePower(currentSuperpower.getSuperpowerID());
//   }
//    }
//     
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of setJdbcTemplate method, of class SuperpowerDaoImpl.
//     */
//    @Test
//    public void testSetJdbcTemplate() {
//    
//    }
//
//    /**
//     * Test of addPower method, of class SuperpowerDaoImpl.
//     */
//    @Test
//    public void testAddGetRemovePower() throws Exception {
//    //instantiate DTO
//    Superpower superpower = new Superpower(); 
//    superpower.setSuperpowerName("Fly");
//    superpowerDao.addPower(superpower);
//    //dao is getting the power from database
//    Superpower fromDao = superpowerDao.getPower(superpower.getSuperpowerID());
//    assertEquals(fromDao, superpower);
//    superpowerDao.removePower(superpower.getSuperpowerID());
//    assertNull(superpowerDao.getPower(superpower.getSuperpowerID()));
//    \
//    
//
//    /**
//     * Test of getPower method, of class SuperpowerDaoImpl.
//     */
//    @Test
//    public void testGetPower() throws Exception {
//
//    }
//
//    /**
//     * Test of getSuperpowerList method, of class SuperpowerDaoImpl.
//     */
//    @Test
//    public void testGetSuperpowerList() throws Exception {
//
//    }
//
//    /**
//     * Test of updatePower method, of class SuperpowerDaoImpl.
//     */
////    @Test
////    public void testUpdatePower() throws Exception {
////    Superpower sp = new Superpower();
////    sp.setSuperpowerName("Speed");
////    superpowerDao.addPower(sp);
////    sp.setSuperpowerName("super speed");
////    superpowerDao.updatePower(sp);
////    Superpower fromDb = superpowerDao.getPower(sp.getSuperpowerID());
////    assertEquals(fromDb, sp);
////
////    }
//
//    /**
//     * Test of removePower method, of class SuperpowerDaoImpl.
//     */
//    @Test
//    public void testRemovePower() throws Exception {
//   
//    }
//    
//}
