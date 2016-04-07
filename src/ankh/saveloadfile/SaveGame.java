/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.saveloadfile;

import ankh.controller.RectGrid;
import ankh.controller.Eventdemo;
import ankh.view.configModel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
        
        Element money = doc.createElement("money");
        player_item.appendChild(money);
        money.appendChild(doc.createTextNode(String.valueOf(configModel.Player_detail_list.get(player).getplayer_money())));
        
        Element loan = doc.createElement("loan");
        player_item.appendChild(loan);
        loan.appendChild(doc.createTextNode(String.valueOf(configModel.Player_detail_list.get(player).get_loan())));
        
        Element city_area = doc.createElement("city_area");
        player_item.appendChild(city_area);
        ArrayList<Integer> city_name= configModel.Player_detail_list.get(player).get_area_card();
        Element city_id;
        if(city_name.size() !=0)
        {
        for(int i=0;i<city_name.size();i++)
        {
            city_id = doc.createElement("city_name");
            city_area.appendChild(city_id);
            city_id.appendChild(doc.createTextNode(String.valueOf(city_name.get(i))));
            
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
        
        Element building_location = doc.createElement("building_location");
        player_item.appendChild(building_location);
       
        for(int area=0;area<12;area++)
        {
           rg_b = configModel.Area_detail_list.get(area).get_rectgrid_obj();
            for(int grid=0;grid<24;grid++)
            {
                if(rg_b[grid].enable && rg_b[grid].type.equalsIgnoreCase("building") && (rg_b[grid].player_id==player))
                {
                     Element m_loc= doc.createElement("buildlocation");
                     building_location.appendChild(m_loc);
                     m_loc.setAttribute("area", String.valueOf(area+1));
                     System.out.println("inside save file" +rg_b[grid].player_id+"dfsgd");
                     m_loc.appendChild(doc.createTextNode(String.valueOf(grid+1)));
                }
                
            }
        }
        
        Element greencard_location = doc.createElement("green_card");
        player_item.appendChild(greencard_location);
        List<Integer> card = configModel.Player_detail_list.get(player).getplayer_green_card();
        for(int i=0;i<card.size();i++)
        {
            Element m_loc= doc.createElement("greencard");
            greencard_location.appendChild(m_loc);
            m_loc.appendChild(doc.createTextNode(String.valueOf(card.get(i))));
        }
       
       
        
        
 
        }
    }

    public static void bankInfo() {
        Element bankinfo = doc.createElement("bank");
        rootElement.appendChild(bankinfo);
        
         Element random_location = doc.createElement("random_card");
        bankinfo.appendChild(random_location);
        int length_rand= configModel.random_event.size();
        
        for(int i=0;i<length_rand;i++)
        {
            Element m_loc= doc.createElement("randomcard");
            random_location.appendChild(m_loc);
            int card_id = configModel.random_event.get(i);
            String card_name=configModel.accessEvent.getEventname(Eventdemo.get(card_id));
            m_loc.appendChild(doc.createTextNode(String.valueOf(card_id)));
        }
        
        Element bank_item = doc.createElement("bank_money");
        bankinfo.appendChild(bank_item);
        bank_item.appendChild(doc.createTextNode(String.valueOf(configModel.bank_money)));
        
        Element trouble_item = doc.createElement("trouble");
        bankinfo.appendChild(trouble_item);
        trouble_item.appendChild(doc.createTextNode(String.valueOf(configModel.player_trouble_maker)));
        
        Element trouble_location = doc.createElement("trouble_location");
        bankinfo.appendChild(trouble_location);
       
        for(int area=0;area<12;area++)
        {
           rg_b = configModel.Area_detail_list.get(area).get_rectgrid_obj();
            for(int grid=0;grid<24;grid++)
            {
                if(rg_b[grid].enable && rg_b[grid].type.equalsIgnoreCase("trouble"))
                {
                     Element m_loc= doc.createElement("troublelocation");
                     trouble_location.appendChild(m_loc);
                     m_loc.setAttribute("area", String.valueOf(area+1));
                     System.out.println("inside save file" +rg_b[grid].player_id+"dfsgd");
                     m_loc.appendChild(doc.createTextNode(String.valueOf(grid+1)));
                }
                
            }
        }
        
        Element demon_item = doc.createElement("demon");
        bankinfo.appendChild(demon_item);
        demon_item.appendChild(doc.createTextNode(String.valueOf(configModel.player_demon)));
        
        Element demon_location = doc.createElement("demon_location");
        bankinfo.appendChild(demon_location);
       
        for(int area=0;area<12;area++)
        {
           rg_b = configModel.Area_detail_list.get(area).get_rectgrid_obj();
            for(int grid=0;grid<24;grid++)
            {
                if(rg_b[grid].enable && rg_b[grid].type.equalsIgnoreCase("demon"))
                {
                     Element m_loc= doc.createElement("demonlocation");
                     demon_location.appendChild(m_loc);
                     m_loc.setAttribute("area", String.valueOf(area+1));
                     System.out.println("inside save file" +rg_b[grid].player_id+"dfsgd");
                     m_loc.appendChild(doc.createTextNode(String.valueOf(grid+1)));
                }
                
            }
        }
        
        Element troll_item = doc.createElement("troll");
        bankinfo.appendChild(troll_item);
        troll_item.appendChild(doc.createTextNode(String.valueOf(configModel.player_trolls)));
        
        Element troll_location = doc.createElement("troll_location");
        bankinfo.appendChild(troll_location);
       
        for(int area=0;area<12;area++)
        {
           rg_b = configModel.Area_detail_list.get(area).get_rectgrid_obj();
            for(int grid=0;grid<24;grid++)
            {
                if(rg_b[grid].enable && rg_b[grid].type.equalsIgnoreCase("troll"))
                {
                     Element m_loc= doc.createElement("trolllocation");
                     troll_location.appendChild(m_loc);
                     m_loc.setAttribute("area", String.valueOf(area+1));
                     System.out.println("inside save file" +rg_b[grid].player_id+"dfsgd");
                     m_loc.appendChild(doc.createTextNode(String.valueOf(grid+1)));
                }
                
            }
        }
        
        Element deck_location = doc.createElement("green_card_deck");
        bankinfo.appendChild(deck_location);
        int length= configModel.greencard_random_number.size();
        
        for(int i=0;i<length;i++)
        {
            Element m_loc= doc.createElement("greendeckcard");
            deck_location.appendChild(m_loc);
            int card_id = configModel.greencard_random_number.get(i);
            
            m_loc.appendChild(doc.createTextNode(String.valueOf(card_id)));
        }
        
        
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
