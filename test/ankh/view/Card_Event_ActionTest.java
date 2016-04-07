package ankh.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class Card_Event_ActionTest {
    
    public Card_Event_ActionTest() {
    }
    Eventhandler instance = new Eventhandler();
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    
    /**
     * Test of check_item_in_area method, of class Card_Event_Action.
     */
    @Test
    public void testCheck_item_in_area() {
        System.out.println("check_item_in_area");
       
        boolean trouble_1 = instance.check_item_in_area(0, "trouble");
        boolean trouble_4 = instance.check_item_in_area(4, "trouble");
        boolean trouble_6 = instance.check_item_in_area(6, "trouble");
        boolean minion_1 = instance.check_item_in_area(0, "minion");
        boolean minion_4 = instance.check_item_in_area(4, "minion");
        boolean minion_6 = instance.check_item_in_area(6, "minion");
        boolean trouble_2 = instance.check_item_in_area(3,"minion");
        boolean minion_2 = instance.check_item_in_area(3,"trouble");
        assertTrue(trouble_1);
        assertTrue(trouble_4);
        assertTrue(trouble_6);
        assertFalse(trouble_2);
        assertTrue(minion_1);
        assertTrue(minion_4);
        assertTrue(minion_6);
        assertFalse(minion_2);
    }
    
    /**
     * Test of get_player_item_in_area method, of class Card_Event_Action.
     */
    @Test
    public void testget_player_item_in_area()
    {
        System.out.println("Get player item from particular area");
        int[] result = new int[2];
        int[] expextedResult = {1,0};
        result = instance.get_player_item_in_area(0, 0);
        for(int i=0;i<result.length;i++)
        {
            assertEquals(expextedResult[i],result[i]);
            
        }
    }
    
    

        
 

}
