/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import ankh.controller.RectGrid;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author admin
 */
public class ReadXmlFile {

    private static Document doc;
    //public static Map_model mm = new Map_model();
    public static Mapdetail_rectangle[] map = new Mapdetail_rectangle[12];
    public static int[] adjacent_list;
    public static int[] minlocation;
    public static int[][] line = new int[28][4];
    public static int previous_line = 0;
    public String filename;
    public String filetype;
    public boolean check = false;
    public static MapcreateView mView;
    public static Secret_Card[] s_card = new Secret_Card[7];
    public static Random_Card[] r_card = new Random_Card[12];
    public static int[] city_n ;

    public static void main(String args[]) {
        try {
            String str = "MapFiles/MapCreate.xml";
            File file = new File(str);

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();

            doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            if (doc.hasChildNodes()) {
                printNote();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public ReadXmlFile()
    {
        
    }

    public ReadXmlFile(String Filename, String Filetype) {
        filename = Filename;
        filetype = Filetype;
        initresdxml(filename);
    }

    public void initresdxml(String Filename) {
        String str = null;
        try {
            if (filetype.equalsIgnoreCase("save")) {
                str = "SaveGames/" + Filename;
            }
            else if (filetype.equalsIgnoreCase("map")) {
                str = "MapFiles/" + Filename;
            }
            else if(filetype.equalsIgnoreCase("secret"))
            {
                str = "MapFiles/"+ Filename;
            }
            else if(filetype.equalsIgnoreCase("random"))
            {
                str = "MapFiles/"+ Filename;
            }
            
            File file = new File(str);

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();

            doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            if (doc.hasChildNodes()) {
                printNote();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean initresdxml1(String Filename,String filetype,Document doc) {
        String str = null;
        try {
            if (filetype.equalsIgnoreCase("save")) {
                str = "SaveGames/" + Filename;
            }
            else if (filetype.equalsIgnoreCase("map")) {
                str = "MapFiles/" + Filename;
            }
            else if(filetype.equalsIgnoreCase("secret"))
            {
                str = "MapFiles/"+ Filename;
            }
            else if(filetype.equalsIgnoreCase("random"))
            {
                str = "MapFiles/"+ Filename;
            }
            
            File file = new File(str);

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();

            doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            if (doc.hasChildNodes()) {
                //printNote();
                check = true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return check;
    }

    private static void printNote() {

        String rootelement = doc.getDocumentElement().getNodeName();
        if (rootelement.equalsIgnoreCase("MapFile")) {
            NodeList nList = doc.getElementsByTagName("Item");
            System.out.println("Length: " + nList.getLength());
            for (int temp = 0; temp < nList.getLength(); temp++) {

                System.out.println("Temp: " + temp);

                map[temp] = new Mapdetail_rectangle();
                //System.out.println(map.length);
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    map[temp].setareaid(Integer.parseInt(eElement.getAttribute("mapid")));
                    map[temp].setarea_name(eElement.getElementsByTagName("Map_Name").item(0).getTextContent());
                    map[temp].setarea_cost(Integer.parseInt(eElement.getElementsByTagName("Map_Cost").item(0).getTextContent()));
                    map[temp].setarea_detail(eElement.getElementsByTagName("Map_Description").item(0).getTextContent());

                    NodeList adjacent = eElement.getElementsByTagName("adja_item");
                    for (int i = 0; i < adjacent.getLength(); i++) {
                        adjacent_list = new int[adjacent.getLength()];
                        adjacent_list[i] = Integer.parseInt(adjacent.item(i).getTextContent());

                    }

                    map[temp].setadjacent(adjacent_list);
                    NodeList adjacen = eElement.getElementsByTagName("Map_Cordinate");
                    for (int i = 0; i < adjacen.getLength(); i++) {
                        Node xtempNode = adjacen.item(i);
                        if (xtempNode.hasAttributes()) {
                            Element eElement1 = (Element) xtempNode;

                            map[temp].setxcordinate(Integer.parseInt(eElement1.getAttribute("x")));
                            map[temp].setycordinate(Integer.parseInt(eElement1.getAttribute("y")));
                            map[temp].setwidth_rectangle(Integer.parseInt(eElement1.getAttribute("w")));
                            map[temp].setheight_rectangle(Integer.parseInt(eElement1.getAttribute("h")));
                        }
                    }

                    NodeList adjacen2 = eElement.getElementsByTagName("map_line_item");
                    for (int i = 0; i < adjacen2.getLength(); i++) {

                        Node xtempNode1 = adjacen2.item(i);

                        if (xtempNode1.hasAttributes()) {
                            Element eElement2 = (Element) xtempNode1;
                            int x[] = new int[4];
                            x[0] = Integer.parseInt(eElement2.getAttribute("x1"));
                            x[1] = Integer.parseInt(eElement2.getAttribute("y1"));
                            x[2] = Integer.parseInt(eElement2.getAttribute("x2"));
                            x[3] = Integer.parseInt(eElement2.getAttribute("y2"));

                            for (int linej = 0; linej < 4; linej++) {

                                line[previous_line][linej] = x[linej];

                            }
                            previous_line++;

                        }
                    }
                    if (temp == 11) {
                        map[temp].setline_map(line);
                    }

                    configModel.Area_detail_list.add(map[temp]);

                }

            }
        } else if (rootelement.equalsIgnoreCase("game")) {
            System.out.println("inside the game xml reader");
            NodeList nList = doc.getElementsByTagName("player_item");
            configModel.no_of_players = nList.getLength();
            System.out.println("players"+configModel.no_of_players);
            for (int temp1 = 0; temp1 < nList.getLength(); temp1++) {

                configModel.player = new Player_detail[nList.getLength()];
                System.out.println("game check 1");
                configModel.player[temp1] = new Player_detail();
                System.out.println("game check 2");
                Node nNode = nList.item(temp1);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("game check 3");
                    configModel.player[temp1].setid(Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()));
                    configModel.player[temp1].setplayer_name(eElement.getElementsByTagName("name").item(0).getTextContent());
                    configModel.player[temp1].setplayer_color(eElement.getElementsByTagName("color").item(0).getTextContent());
                    configModel.player[temp1].setplayer_secretcard(Integer.parseInt(eElement.getElementsByTagName("secretcard_name").item(0).getTextContent()));
                    configModel.player[temp1].setplayer_minion(Integer.parseInt(eElement.getElementsByTagName("minion").item(0).getTextContent()));
                    configModel.player[temp1].setplayer_building(Integer.parseInt(eElement.getElementsByTagName("building").item(0).getTextContent()));
                    int turn = Integer.parseInt(eElement.getElementsByTagName("turn").item(0).getTextContent());
                    if (turn == 1) {
                        configModel.turn_counter = temp1 + 1;
                    }
                    System.out.println("layer name-->>>" + configModel.player[temp1].getplayer_name());
                    NodeList min_loc = eElement.getElementsByTagName("location");
                    
                    for (int i = 0; i < min_loc.getLength(); i++) {
                        Node nNodew = min_loc.item(i);

                        if (nNodew.hasAttributes()) {
                            Element eElement1 = (Element) nNodew;
                            int areaid = Integer.parseInt(eElement1.getAttribute("area"));
                            int location = Integer.parseInt(min_loc.item(i).getTextContent());
                          
                            RectGrid[] r_g = configModel.Area_detail_list.get(areaid - 1).get_rectgrid_obj();
                            for (int grid = 0; grid < 24; grid++) {
                                if (location == (grid + 1)) {

                                    r_g[grid].enable = true;
                                    r_g[grid].player_id = temp1;
                                    r_g[grid].type = "minion";
                                }
                            }
                        }
                      
//                           
                    }
                    
//                    
                     NodeList city_loc = eElement.getElementsByTagName("city_name");
                     
                        city_n = new int[city_loc.getLength()];
                         for(int i = 0;i<city_loc.getLength();i++)
                         {
                            
                            city_n[i]= Integer.parseInt(city_loc.item(i).getTextContent());
                             
                         }
                         configModel.player[temp1].set_area_card(city_n);
//                     
                     
                        

                }
                configModel.Player_detail_list.add(configModel.player[temp1]);
            }

        }
        else if(rootelement.equalsIgnoreCase("secretcard"))
        {
            System.out.println("inside secret card");
            NodeList nList = doc.getElementsByTagName("item");
            for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);
                s_card[temp] = new Secret_Card();
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
		Element eElement = (Element) nNode;
                s_card[temp].set_data(Integer.valueOf(eElement.getElementsByTagName("card_id").item(0).getTextContent()),
                                eElement.getElementsByTagName("card_name").item(0).getTextContent(),
                                eElement.getElementsByTagName("card_description").item(0).getTextContent());
		}
                configModel.secret_card_detail.add(s_card[temp]);
                        
            }
        }
        else if(rootelement.equalsIgnoreCase("randomcard"))
        {
            System.out.println("inside random card");
            NodeList nList = doc.getElementsByTagName("item");
            for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);
                r_card[temp] = new Random_Card();
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
		Element eElement = (Element) nNode;
                 r_card[temp].set_data(Integer.valueOf(eElement.getElementsByTagName("card_id").item(0).getTextContent()),
                                eElement.getElementsByTagName("card_name").item(0).getTextContent(),
                                eElement.getElementsByTagName("card_description").item(0).getTextContent());
		}
                configModel.random_card_detail.add(r_card[temp]);
            }
        }

    }

}
