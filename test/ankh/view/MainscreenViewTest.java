/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import ankh.view.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author user
 */
public class MainscreenViewTest {
    MainscreenView mv = new MainscreenView();
    
    public MainscreenViewTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    /**
     * Test of initnewgame method, of class MainscreenView.
     */
    @Test
    public void testInitnewgame() {
        System.out.println("initnewgame");
        MainscreenView instance = new MainscreenView();
        instance.initnewgame();
       boolean cehck_screen= instance.chec_screen_view;
       boolean ecpect_res = true;
        assertEquals(ecpect_res, cehck_screen);
   }
    
   
    
}
