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
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author matti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({KSP.ai4Test.class, KSP.ai1Test.class, KSP.AITest.class, KSP.uiTest.class, KSP.GameTest.class, KSP.ai2Test.class, KSP.ai3Test.class, KSP.MainTest.class})
public class KSPSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
