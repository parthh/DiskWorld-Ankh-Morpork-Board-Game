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
public class MapcreateViewTest {
     MapcreateView instance = new MapcreateView();
    public MapcreateViewTest() {
        
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        
       
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of PrintGameState method, of class MapcreateView.
     */
    @Test
    public void testPrintGameState() {
        
        System.out.println("PrintGameState");
        instance.PrintGameState();
        int player_minion = instance.player_minion;
        int player_greencard = instance.player_greencard;
        int player_money = instance.player_money;
        String result = instance.player_name;
        String result1 = instance.player_color;
        assertEquals(9,player_minion);
        assertEquals(10,player_money);
        assertEquals(5,player_greencard);
        assertEquals("Meet", result);
        assertEquals("red",result1);
        
    }
    
    /**
     * Test of bank money present after two player start playing game
     */
    @Test
    public void testbankmoney()
    {
        System.out.println("check bank money");
        int bank_money = configModel.bank_money;
        assertEquals(100, bank_money);
    }
    
    /**
     * Test of green card remaining after two player start playing game
     */
    @Test
    public void testGreenCardRemaining()
    {
        
        System.out.println("remaining green card");
        int expectedResult = 38;
        int result = configModel.greencard_random_number.size();
        assertEquals(expectedResult, result);
    }
    
    /**
     * Test of total trolls remaining after two player start playing game
     */
    @Test
    public void testtrollRemaining()
    {
        System.out.println("total troll rermaining");
        int expectedResult = 3;
        int result = configModel.player_trolls;
        assertEquals(expectedResult, result);
        
    }
    
    /**
     * Test of total demon remaining after two player start playing game
     */
    @Test
    public void testdemonReamining()
    {
        System.out.println("total demon remaining");
        int expectedResult = 4;
        int result = configModel.player_demon;
        assertEquals(expectedResult, result);
    }
    
    /**
     * Test of total minion remaining after two player start playing game
     */
    @Test
    public void testtroubleRemaining()
    {
        System.out.println("total trouble remaining");
        int expectedResult = 9;
        int result = configModel.player_trouble_maker;
        assertEquals(expectedResult, result);
    }
    
    /**
     * test of number of city area, secret and personality card
     */
    @Test
    public void testCards()
    {
       
        System.out.println("test number of cards");
        int expectedResult = 12;
        int expectedResult1 = 12;
        int expectedResult2 = 7;
        int result = configModel.random_card;
        int result1 = configModel.cityarea_card;
        int result2 = configModel.personality_card;
        assertEquals(expectedResult, result);
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);


    }
}

