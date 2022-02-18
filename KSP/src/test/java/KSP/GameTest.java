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
public class GameTest {
    
    public GameTest() {
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
     * Test of pelaa method, of class Game.
     */
    @Test
    public void testPelaa() {
        System.out.println("pelaa");
        int ai = 0;
        boolean admin = false;
        Game instance = new Game();
        instance.pelaa(ai, admin);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of satunnainenVastaus method, of class Game.
     */
    @Test
    public void testSatunnainenVastaus() {
        System.out.println("satunnainenVastaus");
        String rajoitus = "";
        String expResult = "";
        String result = Game.satunnainenVastaus(rajoitus);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
