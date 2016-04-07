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
import ankh.controller.GameChooser;

/**
 *
 * @author user
 */
public class MainscreenTest {
    
    public MainscreenTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

   

    /**
     * Test of loadgame method, of class MainscreenView.
     */
    @Test
    public void testLoadgame() {
        System.out.println("loadgame");
        MainscreenView instance = new MainscreenView();
        instance.loadgame();
        boolean result = instance.game_load_test;
        boolean exp_result = true;
        
        
        assertEquals(exp_result, result);
    }

    
    
}
