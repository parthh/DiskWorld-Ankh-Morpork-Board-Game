/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import ankh.controller.RectGrid;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author admin
 */
public class SaveGame {
    public static DocumentBuilderFactory docFactory;
    public static DocumentBuilder docBuilder;
    public static Document doc;
    public static Element rootElement;
    public static File f;
    public static int turn;
    public static String file;
    public static  RectGrid[] rg_b ;
    public SaveGame(int player_turn,String file)
    {
        this.file = file;
        turn = player_turn;
        for(int player=0;player<configModel.no_of_players;player++)
        {
            System.out.println("Player Name :"+configModel.Player_detail_list.get(player).getplayer_name());
            System.out.println("Player color :"+configModel.Player_detail_list.get(player).getplayer_color());
            System.out.println("Player minion :"+configModel.Player_detail_list.get(player).getplayer_minion());
        }
       initFile();
       areaInfo();
       playerInfo();
       bankInfo();
       saveFile();
    }
     /**
     * File Initiator
     */
    public static void initFile() {
        docFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SaveGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        doc = docBuilder.newDocument();
        rootElement = doc.createElement("game");
        doc.appendChild(rootElement);

    }

    public static void areaInfo() {
    }

    public static void playerInfo() {
        Element playerinfo = doc.createElement("player");
        rootElement.appendChild(playerinfo);
        
         for(int player=0;player<configModel.no_of_players;player++)
        {
        Element player_item = doc.createElement("player_item");
        playerinfo.appendChild(player_item);
        
        Element id = doc.createElement("id");
        player_item.appendChild(id);
        id.appendChild(doc.createTextNode(String.valueOf(configModel.Player_detail_list.get(player).getid())));
        Element play_turn = doc.createElement("turn");
        player_item.appendChild(play_turn);
        if(player+1 == turn)
        {
        play_turn.appendChild(doc.createTextNode(String.valueOf(1)));
        }
        else
        {
        play_turn.appendChild(doc.createTextNode(String.valueOf(0)));
        }
        
                
        Element name = doc.createElement("name");
        player_item.appendChild(name);
        name.appendChild(doc.createTextNode(configModel.Player_detail_list.get(player).getplayer_name()));
        
        Element color = doc.createElement("color");
        player_item.appendChild(color);
        color.appendChild(doc.createTextNode(configModel.Player_detail_list.get(player).getplayer_color()));
        
        Element sercret_id = doc.createElement("secretcard_name");
        player_item.appendChild(sercret_id);
        sercret_id.appendChild(doc.createTextNode(String.valueOf(configModel.Player_detail_list.get(player).getplayer_secretcard())));
        
        Element minion = doc.createElement("minion");
        player_item.appendChild(minion);
        minion.appendChild(doc.createTextNode(String.valueOf(configModel.Player_detail_list.get(player).getplayer_minion())));
        
        Element building = doc.createElement("building");
        player_item.appendChild(building);
        building.appendChild(doc.createTextNode(String.valueOf(configModel.Player_detail_list.get(player).getplayer_building())));
        
        Element city_area = doc.createElement("city_area");
        player_item.appendChild(city_area);
        int [] city_name = configModel.Player_detail_list.get(player).get_area_card();
        Element city_id;
        if(city_name.length !=0)
        {
        for(int i=0;i<city_name.length;i++)
        {
            city_id = doc.createElement("city_name");
            city_area.appendChild(city_id);
            city_id.appendChild(doc.createTextNode(String.valueOf(city_name[i])));
            
        }
        }
        else
        {
            city_id = doc.createElement("city_name");
            city_id.appendChild(doc.createTextNode(String.valueOf(0)));
        }
        
        Element minion_location = doc.createElement("minion_location");
        player_item.appendChild(minion_location);
       
        for(int area=0;area<12;area++)
        {
           rg_b = configModel.Area_detail_list.get(area).get_rectgrid_obj();
            for(int grid=0;grid<24;grid++)
            {
                if(rg_b[grid].enable && rg_b[grid].type.equalsIgnoreCase("minion") && (rg_b[grid].player_id==player))
                {
                     Element m_loc= doc.createElement("location");
                     minion_location.appendChild(m_loc);
                     m_loc.setAttribute("area", String.valueOf(area+1));
                     System.out.println("inside save file" +rg_b[grid].player_id+"dfsgd");
                     m_loc.appendChild(doc.createTextNode(String.valueOf(grid+1)));
                }
                
            }
        }
        
        
 
        }
    }

    public static void bankInfo() {
    }
    
     /**
     * Save File
     */
    public static void saveFile() {
        // write the content into xml file
        System.out.println("save file");
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult("SaveGames/"+file+".xml");

            try {
                transformer.transform(source, result);
            } catch (TransformerException ex) {
                Logger.getLogger(SaveGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(SaveGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("File saved!");
    }
}
