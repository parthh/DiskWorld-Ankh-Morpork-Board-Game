/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class MapcreateViewTest4 {
    
    Eventhandler instance = new Eventhandler();
    @BeforeClass
    public static void setUpClass() {
       
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    
   /**
     * Test of bank_transaction in area method, of class Card_Event_Action.
     */
    @Test
    public void testbank_transaction()
    {
        System.out.println("Bank Transaction");
        boolean result1 = instance.bank_transaction(0, 2, "withdraw");
        boolean result2 = instance.bank_transaction(0, 2, "deposit");
        boolean result3 = instance.bank_transaction(0, 50, "nskv");
        
        assertTrue(result1);
        assertTrue(result2);
        assertFalse(result3);
    }
    
    /**
     * Test of player_transaction in area method, of class Card_Event_Action.
     */
    @Test
    public void testplayer_transaction()
    {
        System.out.println("player transaction");
        boolean result1 = instance.player_trasanction(0, 1, 2);
        boolean result2 = instance.player_trasanction(1 ,0 ,2);
        assertTrue(result1);
        assertTrue(result2);
    }
    
    @Test
    public void testget_adjacent_area()
    {
        System.out.println("get list adjacent area");
        List<Integer> list = new ArrayList<Integer>(); 
        list.add(1);
        
        List<Integer> list1 = new ArrayList<Integer>();
        list1 = instance.get_adjacent_area(list);
     
        
    }
    
}
