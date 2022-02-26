/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KSP;

import java.util.ArrayList;
import java.util.Scanner;
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
public class uiTest {
    
    public Game peli;
    public Statistics stats;
    private static Scanner s;
    
    public uiTest() {
    }
    
    @BeforeClass
    public void setUpClass() {
        peli=new Game();
        s=new Scanner(System.in);
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
     * Test of aloita method, of class ui.
     */
    @Test
    public void testAloita() {
        System.out.println("aloita");
        ui instance = new ui();
        instance.aloita();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of naytaVaihtoehdot method, of class ui.
     */
    @Test
    public void testNaytaVaihtoehdot() {
        System.out.println("naytaVaihtoehdot");
        String expResult = "";
        String result = ui.naytaVaihtoehdot();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valitseAi method, of class ui.
     */
    @Test
    public void testValitseAi() {
        System.out.println("valitseAi");
        ui instance = new ui();
        String expResult = "";
        String result = instance.valitseAi();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pyydaPelaajaltaVastaus method, of class ui.
     */
    @Test
    public void testPyydaPelaajaltaVastaus() {
        System.out.println("pyydaPelaajaltaVastaus");
        String expResult = "";
        String result = ui.pyydaPelaajaltaVastaus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of kysyUusiPeli method, of class ui.
     */
    @Test
    public void testKysyUusiPeli() {
        System.out.println("kysyUusiPeli");
        boolean expResult = false;
        boolean result = ui.kysyUusiPeli();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tulostaTilastot method, of class ui.
     */
    @Test
    public void testTulostaTilastot() {
        System.out.println("tulostaTilastot");
        ArrayList<Integer> tilasto = null;
        ui instance = new ui();
        instance.tulostaTilastot();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of vaihdaTila method, of class ui.
     */
    @Test
    public void testVaihdaTila() {
        System.out.println("vaihdaTila");
        ui instance = new ui();
        instance.vaihdaTila();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of diagnostics method, of class ui.
     */
    @Test
    public void testDiagnostics() {
        System.out.println("diagnostics");
        ui instance = new ui();
        instance.diagnostics();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
