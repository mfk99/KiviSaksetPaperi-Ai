/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KSP;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matti
 */
public class StatisticsTest {
    
    public StatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setAiVastaukset method, of class Statistics.
     */
    @Test
    public void testSetAiVastaukset() {
        System.out.println("setAiVastaukset");
        int i = 0;
        String s = "";
        Statistics instance = null;
        instance.setAiVastaukset(i, s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAiVastaukset method, of class Statistics.
     */
    @Test
    public void testGetAiVastaukset() {
        System.out.println("getAiVastaukset");
        Statistics instance = null;
        String[] expResult = null;
        String[] result = instance.getAiVastaukset();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPelaajaVastaukset method, of class Statistics.
     */
    @Test
    public void testSetPelaajaVastaukset() {
        System.out.println("setPelaajaVastaukset");
        int i = 0;
        String s = "";
        Statistics instance = null;
        instance.setPelaajaVastaukset(i, s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPelaajaVastaukset method, of class Statistics.
     */
    @Test
    public void testGetPelaajaVastaukset() {
        System.out.println("getPelaajaVastaukset");
        Statistics instance = null;
        String[] expResult = null;
        String[] result = instance.getPelaajaVastaukset();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAiTulokset method, of class Statistics.
     */
    @Test
    public void testSetAiTulokset() {
        System.out.println("setAiTulokset");
        int i = 0;
        int j = 0;
        int x = 0;
        Statistics instance = null;
        instance.setAiTulokset(i, j, x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAiTulokset method, of class Statistics.
     */
    @Test
    public void testGetAiTulokset() {
        System.out.println("getAiTulokset");
        Statistics instance = null;
        int[][] expResult = null;
        int[][] result = instance.getAiTulokset();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTulokset method, of class Statistics.
     */
    @Test
    public void testAddTulokset() {
        System.out.println("addTulokset");
        int x = 0;
        Statistics instance = null;
        instance.addTulokset(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTulokset method, of class Statistics.
     */
    @Test
    public void testGetTulokset() {
        System.out.println("getTulokset");
        Statistics instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getTulokset();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
