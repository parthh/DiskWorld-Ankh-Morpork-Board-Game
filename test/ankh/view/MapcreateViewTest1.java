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
public class MapcreateViewTest1 {
    
    @BeforeClass
    public static void setUpClass() {
        
       
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    
    /**
     * Test of set_item in area method, of class Card_Event_Action.
     */
    @Test
    public void testset_item()
    {
        System.out.println("set item in particular area");
        Eventhandler instance = new Eventhandler();
        boolean set_item1 = instance.set_item(0, 8, "minion");
        boolean set_item2 = instance.set_item(1, 9, "minion");
        assertTrue(set_item1);
        assertTrue(set_item2);

    }
}
