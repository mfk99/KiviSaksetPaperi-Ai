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
public class AITest {
    
    //testejä ei olla toteutettu
    
    public AITest() {
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
     * Test of paivitaAi method, of class AI.
     */
    @Test
    public void testPaivitaAi() {
        System.out.println("paivitaAi");
        int i = 0;
        AI instance = null;
        instance.paivitaAi(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of syotaVastaus method, of class AI.
     */
    @Test
    public void testSyotaVastaus() {
        System.out.println("syotaVastaus");
        String pelaajaVastaus = "";
        int indeksi = 0;
        AI instance = null;
        instance.syotaVastaus(pelaajaVastaus, indeksi);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of laskeTulokset method, of class AI.
     */
    @Test
    public void testLaskeTulokset() {
        System.out.println("laskeTulokset");
        String pelaajaVastaus = "";
        int indeksi = 0;
        AI instance = null;
        instance.laskeTulokset(pelaajaVastaus, indeksi);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of laskeParasAi method, of class AI.
     */
    @Test
    public void testLaskeParasAi() {
        System.out.println("laskeParasAi");
        AI instance = null;
        int expResult = 0;
        int result = instance.laskeParasAi();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of haeVastaus method, of class AI.
     */
    @Test
    public void testHaeVastaus() {
        System.out.println("haeVastaus");
        int i = 0;
        AI instance = null;
        String expResult = "";
        String result = instance.haeVastaus(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ai1Vastaus method, of class AI.
     */
    @Test
    public void testAi1Vastaus() {
        System.out.println("ai1Vastaus");
        AI instance = null;
        instance.ai1Vastaus();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ai2Vastaus method, of class AI.
     */
    @Test
    public void testAi2Vastaus() {
        System.out.println("ai2Vastaus");
        AI instance = null;
        instance.ai2Vastaus();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ai3Vastaus method, of class AI.
     */
    @Test
    public void testAi3Vastaus() {
        System.out.println("ai3Vastaus");
        int indeksi = 0;
        AI instance = null;
        instance.ai3Vastaus(indeksi);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ai4Vastaus method, of class AI.
     */
    @Test
    public void testAi4Vastaus() {
        System.out.println("ai4Vastaus");
        int indeksi = 0;
        AI instance = null;
        instance.ai4Vastaus(indeksi);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
