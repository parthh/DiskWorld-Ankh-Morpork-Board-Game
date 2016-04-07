/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import ankh.controller.Perform_scroll;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class Perform_scrollTest {
    
    public Perform_scrollTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of perform_green_scroll method, of class Perform_scroll.
     */
    @Test
    public void testPerform_green_scroll() {
        System.out.println("perform_green_scroll");
        String card_id = "1";
        Perform_scroll instance = new Perform_scroll();
        boolean expResult = true;
        boolean result = instance.perform_green_scroll(card_id);
        assertEquals(expResult, result);
        System.out.println("check");
        
    }
    
}
