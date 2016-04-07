/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author user
 */
public class MapcreateViewTest2 {
    
    Eventhandler instance = new Eventhandler();
    @BeforeClass
    public static void setUpClass() {
       
        
    }
    

    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Test of move_item in area method, of class Card_Event_Action.
     */
    @Test
    public void testmove_item()
    {
        System.out.println("move item in particular area");
        
        boolean move_item1 = instance.move_item(0, 4, 5, "minion");
        boolean move_item2 = instance.move_item(0, 5, 4, "minion");
        assertTrue(move_item1);
        assertTrue(move_item2);

    }
    
}
