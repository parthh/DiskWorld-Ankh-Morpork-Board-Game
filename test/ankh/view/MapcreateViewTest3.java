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
public class MapcreateViewTest3 {
    Eventhandler instance = new Eventhandler();
    @BeforeClass
    public static void setUpClass() {
       
        
    }
    

    @AfterClass
    public static void tearDownClass() {
    }
    
     /**
     * Test of remove_item in area method, of class Card_Event_Action.
     */
    @Test
    public void testremove_item()
    {
        System.out.println("remove item in particular area");
        boolean remove_item1 = instance.remove_item(0, 8, "minion");
        boolean remove_item2 = instance.remove_item(1, 9, "minion");
        assertTrue(remove_item1);
        assertTrue(remove_item2);

        
    }
}
