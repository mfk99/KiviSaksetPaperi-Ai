/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KSP;

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
public class MainTest {
    
    //testejä ei olla toteutettu
    
    public MainTest() {
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
     * Test of main method, of class Main.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Main.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of vaihdaTila method, of class Main.
     */
    @Test
    public void testVaihdaTila() {
        System.out.println("vaihdaTila");
        Main.vaihdaTila();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of naytaVaihtoehdot method, of class Main.
     */
    @Test
    public void testNaytaVaihtoehdot() {
        System.out.println("naytaVaihtoehdot");
        Main.naytaVaihtoehdot();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pelaajaValitseeAi method, of class Main.
     */
    @Test
    public void testPelaajaValitseeAi() {
        System.out.println("pelaajaValitseeAi");
        Main.pelaajaValitseeAi();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pelaa method, of class Main.
     */
    @Test
    public void testPelaa() {
        System.out.println("pelaa");
        int ai = 0;
        Main.pelaa(ai);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of naytaTilastot method, of class Main.
     */
    @Test
    public void testNaytaTilastot() {
        System.out.println("naytaTilastot");
        Main.naytaTilastot();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of satunnainenVastaus method, of class Main.
     */
    @Test
    public void testSatunnainenVastaus() {
        System.out.println("satunnainenVastaus");
        String rajoitus = "";
        String expResult = "";
        String result = Main.satunnainenVastaus(rajoitus);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
