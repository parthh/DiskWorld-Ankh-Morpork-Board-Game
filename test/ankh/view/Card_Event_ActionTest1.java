/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author user
 */
public class Card_Event_ActionTest1 
{
    Eventhandler instance = new Eventhandler();
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    
     /**
     * Test of is_empty_area method, of class Card_Event_Action.
     */
    @Test
    public void testis_empty_area()
    {
        System.out.println("test empty area");
        boolean result = instance.is_empty_area(8);
        boolean result1 = instance.is_empty_area(9);
        boolean result2 = instance.is_empty_area(10);
        boolean result3 = instance.is_empty_area(11);
        boolean result4 = instance.is_empty_area(1);
        boolean result5 = instance.is_empty_area(2);
        boolean result6 = instance.is_empty_area(3);
        boolean result7 = instance.is_empty_area(5);
        boolean result8 = instance.is_empty_area(7);
        boolean result9 = instance.is_empty_area(0);
        boolean result10 = instance.is_empty_area(4);
        boolean result11 = instance.is_empty_area(6);
        assertTrue(result);
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
        assertTrue(result4);
        assertTrue(result5);
        assertTrue(result6);
        assertTrue(result7);
        assertTrue(result8);
        assertFalse(result9);
        assertFalse(result10);
        assertFalse(result11);
        
    }
    
    
    /**
     * Test of checkTroubleMakerPresent method, of class Card_Event_Action.
     */
    @Test
    public void testcheckTroubleMakerPresent()
    {
        System.out.println("check trouble marker present in area");
        boolean result = instance.checkTroubleMakerPresent(8);
        boolean result1 = instance.checkTroubleMakerPresent(9);
        boolean result2 = instance.checkTroubleMakerPresent(10);
        boolean result3 = instance.checkTroubleMakerPresent(11);
        boolean result4 = instance.checkTroubleMakerPresent(1);
        boolean result5 = instance.checkTroubleMakerPresent(2);
        boolean result6 = instance.checkTroubleMakerPresent(3);
        boolean result7 = instance.checkTroubleMakerPresent(5);
        boolean result8 = instance.checkTroubleMakerPresent(7);
        boolean result9 = instance.checkTroubleMakerPresent(0);
        boolean result10 = instance.checkTroubleMakerPresent(4);
        boolean result11 = instance.checkTroubleMakerPresent(6);
        assertFalse(result);
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
        assertFalse(result4);
        assertFalse(result5);
        assertFalse(result6);
        assertFalse(result7);
        assertFalse(result8);
        assertTrue(result9);
        assertTrue(result10);
        assertTrue(result11);
    }
}
