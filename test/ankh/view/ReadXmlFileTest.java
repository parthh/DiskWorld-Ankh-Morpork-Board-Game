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
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author user
 */
public class ReadXmlFileTest {
    
    ReadXmlFile rd = new ReadXmlFile();
    public static Mapdetail_rectangle[] map = new Mapdetail_rectangle[12];
    public static Document doc;
   
    public ReadXmlFileTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    /**
     * Test of initresdxml method, of class ReadXmlFile.
     */
    @Test
    public void testInitresdxml1() {
        System.out.println("initresdxml");
        String Filename = "MapCreate.xml";
        //ReadXmlFile instance = null;
        Boolean checked = true;
        String filet = "map";
        Boolean check = rd.initresdxml1(Filename, filet, doc);
        assertEquals(checked, check);
        
       
    }
    
    

    
    
    
}
