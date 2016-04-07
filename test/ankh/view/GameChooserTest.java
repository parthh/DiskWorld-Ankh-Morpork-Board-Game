/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import ankh.controller.GameChooser;
import java.awt.event.ActionEvent;
import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class GameChooserTest {
    
    
    public GameChooserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of listFilesForFolder method, of class GameChooser.
     */
    @Test
    public void testListFilesForFolder() {
        System.out.println("listFilesForFolder");
        File folder = null;
        GameChooser instance = new GameChooser();
        instance.listFilesForFolder(new File("SaveGames"));
        boolean exp_result = true;
        boolean result = instance.check_game;
        assertEquals(exp_result, result);
    }
    
}
