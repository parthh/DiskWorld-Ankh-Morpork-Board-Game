/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class CityArea_handlerTest {
    
    public CityArea_handlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of cityarea_condition method, of class CityArea_handler.
     */
    @Test
    public void testCityarea_condition() throws Exception {
        System.out.println("cityarea_condition");
        MapcreateView map = new MapcreateView();
        int player_money = map.player_money;
        int areaid = 3;
        int playerid = 0;
        CityArea_handler instance = new CityArea_handler();
        boolean expResult = true;
        boolean result = instance.cityarea_condition(areaid, playerid);
        assertEquals(expResult, result);
        assertEquals(player_money , map.player_money);
    }
    

}
