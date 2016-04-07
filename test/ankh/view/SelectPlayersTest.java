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
public class SelectPlayersTest {
    
    public SelectPlayersTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of startgame method, of class SelectPlayers.
     */
    @Test
    public void testStartgame() {
        System.out.println("startgame");
        SelectPlayers instance = new SelectPlayers();
        instance.startgame();
        boolean result = instance.condition;
        boolean exp_result = true;
        assertEquals(exp_result, result);
    }
    
}
