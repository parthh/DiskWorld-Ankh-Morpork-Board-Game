/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import ankh.model.Player_detail;
import ankh.saveloadfile.ReadXmlFile;
import ankh.controller.KeyController;
import ankh.controller.RectGrid;
import ankh.model.Mapdetail_rectangle;

import static ankh.view.configModel.area;
import static ankh.view.configModel.building;
import static ankh.view.configModel.demon;
import static ankh.view.configModel.minion;
import static ankh.view.configModel.troll;
import static ankh.view.configModel.trouble;
import static ankh.view.configModel.turn;
import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * This is GUI class for Main Game Map.
 *
 * @author Parth B Gadhiya
 */
public class MapcreateView extends JFrame implements Runnable {

    Thread Gameloop = new Thread(this);
    int[][] LineMap = new int[28][4];
    Mapdetail_rectangle m_r[];
    Player_detail p_d[];

    HashMap<Integer, ArrayList<Integer>> lineinmap;
    ArrayList<Integer>[] line_cordiante;
    JButton jb;
    KeyController keyc;
    TurnHandler live_player;

    /* player1 area variable */
    public int number_player;
    public int parea_ymargin = 10;
    public int cposx, cposy;
    public int p1_x = 750;
    public int p1_y = 50;
    public int p1_w = 280;
    public int p1_h = 150;
    int gamearea_line_x = 650;
    int gamearea_line_y = 580;
    int temp_count = 0;
    String status;
    int turn_h = 25;
    int turn_w = 25;
    int y_margin;
    public int drag = 0;
    public int[][] temp_loc;
    int money = 0;
    String player_name, player_color;
    int player_minion, player_greencard, player_money, player_building;

    public ArrayList<Integer> Random_number_building = new ArrayList<Integer>(12);
    Random rand = new Random();
    public Random rand1 = new Random();

    public ArrayList<Integer> brown;
    ReadXmlFile reader;
    ReadXmlFile secret_card;
    ReadXmlFile random_card;
    ReadXmlFile green_card;
    //SelectPlayers sp = new SelectPlayers();

    int initialmoney = 10;
    Graphics draw;

    /**
     * This method will initialize and map View.
     *
     * @param status this Variable identify game is new game or load game.
     */
    public MapcreateView() {
    }

    public MapcreateView(String status) {

        minion[0] = new ImageIcon("resources/minion.png").getImage();
        minion[1] = new ImageIcon("resources/blue_minion.png").getImage();
        minion[2] = new ImageIcon("resources/green_minion.png").getImage();
        minion[3] = new ImageIcon("resources/yellow_minion.png").getImage();
        building[0] = new ImageIcon("resources/red_build.png").getImage();
        building[1] = new ImageIcon("resources/blue_build.png").getImage();
        building[2] = new ImageIcon("resources/green_build.png").getImage();
        building[3] = new ImageIcon("resources/yello_build.png").getImage();
        trouble[0] = new ImageIcon("resources/trouble.png").getImage();
        turn[0] = new ImageIcon("resources/turn.png").getImage();
        demon[0] = new ImageIcon("resources/demon.png").getImage();
        troll[0] = new ImageIcon("resources/troll.png").getImage();

        this.status = status;
        secret_card = new ReadXmlFile("SecretIdentityCards.xml", "secret");
        random_card = new ReadXmlFile("RandomCardXML.xml", "random");
        green_card = new ReadXmlFile("GreenPlayerCard.xml", "greencard");

        for (int i = 1; i <= 12; i++) {
            configModel.dice_random_number.add(i);

        }
        for (int j = 1; j <= 53; j++) {
            configModel.brown_random_number.add(j);
        }
        /* executes at the time of Start Game*/
        if (this.status.equalsIgnoreCase("start")) {
            reader = new ReadXmlFile("MapCreate.xml", "map");

            configModel.map = reader.map;

            m_r = configModel.map;

            for (int i = 1; i <= 7; i++) {
                configModel.secret_Random_number.add(i);

            }

            for (int j = 1; j <= 48; j++) {
                configModel.greencard_random_number.add(j);
            }

            for (int j = 1; j <= 5; j++) {
                configModel.random_event.add(j + 9);
            }

            configModel.secret_Random_number = sufflecards(configModel.secret_Random_number);
            configModel.greencard_random_number = sufflecards(configModel.greencard_random_number);
            for (int cp = 0; cp < configModel.no_of_players; cp++) {

                /* Random number to assign for secret card*/
                // int random_number = rand.nextInt(Random_number.size());
                int random_number;
                boolean check_player_card = false;
                if (configModel.no_of_players == 2) {
                    do {
                        random_number = configModel.secret_Random_number.get(0);
                        if (random_number == 6) {
                            configModel.secret_Random_number.remove(0);
                            check_player_card = true;
                        } else {
                            configModel.Player_detail_list.get(cp).setplayer_secretcard(random_number - 1);
                            configModel.secret_Random_number.remove(0);
                            check_player_card = false;
                        }
                    } while (check_player_card);
                } else {
                    random_number = configModel.secret_Random_number.get(0);
                    configModel.Player_detail_list.get(cp).setplayer_secretcard(random_number - 1);
                    configModel.secret_Random_number.remove(0);
                }
//                }

                for (int l = 0; l < 5; l++) {

                    configModel.Player_detail_list.get(cp).setplayer_green_card(configModel.greencard_random_number.get(0));
                    configModel.greencard_random_number.remove(0);
                }

            }

            this.status = status;
            this.number_player = configModel.no_of_players;
            grid();
            for (int j = 0; j < 3; j++) {

                if (j == 0) {
                    configModel.rg_b = configModel.Area_detail_list.get(0).get_rectgrid_obj();
                } else if (j == 1) {
                    configModel.rg_b = configModel.Area_detail_list.get(4).get_rectgrid_obj();
                } else {
                    configModel.rg_b = configModel.Area_detail_list.get(6).get_rectgrid_obj();
                }

                for (int i = 0; i < configModel.no_of_players; i++) {

                    configModel.rg_b[i].enable = true;
                    configModel.rg_b[i].player_id = i;
                    configModel.rg_b[i].type = "minion";
                    int p_minion = configModel.Player_detail_list.get(i).getplayer_minion();
                    int c_minion = p_minion - 1;
                    configModel.Player_detail_list.get(i).setplayer_minion(c_minion);
                    configModel.rg_b[i].grid_color = configModel.Player_detail_list.get(i).getplayer_color();
                    configModel.Player_detail_list.get(i).setplayer_money(initialmoney);
                    configModel.Player_detail_list.get(i).setplayer_building(configModel.player_building);

                }
                configModel.bank_money = configModel.total_money - (initialmoney * configModel.no_of_players);
                configModel.rg_b[configModel.no_of_players].enable = true;
                configModel.rg_b[configModel.no_of_players].type = "trouble";
                configModel.player_trouble_maker--;
            }

        }
        /* Execute at the time of load game*/
        if (this.status.equalsIgnoreCase("load")) {
            reader = new ReadXmlFile("MapCreate.xml", "map");
            configModel.map = reader.map;
            m_r = configModel.map;
            grid();

            ReadXmlFile read_savefile = new ReadXmlFile(configModel.load_gamefile, "save");

            configModel.secret_Random_number.add(1);
            configModel.secret_Random_number.add(2);
            configModel.secret_Random_number.add(6);
            configModel.secret_Random_number = sufflecards(configModel.secret_Random_number);
            configModel.greencard_random_number = sufflecards(configModel.greencard_random_number);

          
                
                
            
            this.status = status;
            number_player = configModel.no_of_players;

        }

        for (int i = 0; i < 12; i++) {
            area[i] = new ImageIcon("resources/area_" + (i + 1) + ".jpg").getImage();
        }

        keyc = new KeyController(this);

        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1300, 800);
        this.setVisible(true);
        this.setResizable(false);
        this.addMouseListener(keyc);
        this.addMouseMotionListener(keyc);

        Gameloop.start();
        PrintGameState();
        handler();

    }

    /**
     * This grid method is used create grid inside the 12 area.
     *
     */
    public void grid() {
        for (int arealoop = 0; arealoop < 12; arealoop++) {
            int temp_count = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 6; j++) {
                    //System.out.println("--"+temp_count+"\n");

                    int ax = configModel.Area_detail_list.get(arealoop).getxcordinate() + configModel.minion_w * j;
                    int ay = configModel.Area_detail_list.get(arealoop).getycordinate() + configModel.minion_w * i;
                    configModel.Area_detail_list.get(arealoop).set_rectgrid_obj(temp_count, ax, ay, configModel.minion_h, configModel.minion_w);
                    //int[] cordinate = configModel.Area_detail_list.get(arealoop).get_rectgrid();
                    //System.out.println("AREA Get x and get y"+cordinate[0]+"and ay"+cordinate[1]);
                    temp_count++;
                }
            }
        }

    }

    /**
     * This paint method is used to draw whole map.
     *
     * @param g this is graphics variable.
     */
    public void PrintGameState() {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("--------------------------------Game State-------------------------------");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("---------------There are =: " + configModel.no_of_players + "Players :---------------");
        for (int i = 0; i < configModel.no_of_players; i++) {
            int sec_id_player = configModel.Player_detail_list.get(i).getplayer_secretcard();
            System.out.println("Player  " + (i + 1) + "("
                    + configModel.Player_detail_list.get(i).getplayer_color() + ")  " + "is Playing as   "
                    + configModel.secret_card_detail.get(sec_id_player).get_title());

            //player_info.add(configModel.Player_detail_list.get(i).getplayer_money());
        }

        System.out.println("----------------Current state of the game board :------------------------");
        for (int j = 0; j < 12; j++) {
            System.out.println("******  City Area: "+configModel.Area_detail_list.get(j).getarea_name()+" ******" );
            configModel.rg_b = configModel.Area_detail_list.get(j).get_rectgrid_obj();
            boolean troubl_flag = false;
            int demoncount = 0;
            int trollcount = 0;
            int buildingcount = 0;
            for (int l = 0; l < 24; l++) {
                if (configModel.rg_b[l].enable) {

                    if (configModel.rg_b[l].type.equals("minion")) {
                        
                        System.out.println("Minion in area =:" + configModel.rg_b[l].grid_color);
                    }
                    if (configModel.rg_b[l].type.equals("trouble")) {
                        troubl_flag = true;

                    }
                    if (configModel.rg_b[l].type.equals("building")) {
                        buildingcount++;
                        System.out.println("Building In Area =:" + configModel.rg_b[l].grid_color);
                    }
                    if (configModel.rg_b[l].type.equals("demon")) {
                        demoncount++;

                    }
                    if (configModel.rg_b[l].type.equals("troll")) {

                        trollcount++;

                    }
                }

            }
            if(buildingcount==0)
            {
                 System.out.println("Building In Area =: NO");
            }
            if (troubl_flag) {
                System.out.println("Tourble Maker : YES");
            } else {
                System.out.println("Tourble Maker : NO");
            }
            System.out.println("Demon in area =:" + demoncount);
            System.out.println("Troll in area =:" + trollcount);

            System.out.println("\n");

        }

        player_minion = configModel.Player_detail_list.get(0).getplayer_minion();
        player_building = configModel.Player_detail_list.get(0).getplayer_building();
        player_money = configModel.Player_detail_list.get(0).getplayer_money();
        player_name = configModel.Player_detail_list.get(0).getplayer_name();
        player_greencard = configModel.Player_detail_list.get(0).getplayer_green_card().size();
        player_color = configModel.Player_detail_list.get(0).getplayer_color();

        for (int i = 0; i < configModel.no_of_players; i++) {

            System.out.println("---------------Player "+(i+1)+"\t"+ configModel.Player_detail_list.get(i).getplayer_name() + "  Current Inventory:---------------------");
            ArrayList<Integer> area_num = configModel.Player_detail_list.get(i).get_area_card();
            System.out.println("Minion is : " + configModel.Player_detail_list.get(i).getplayer_minion());
            System.out.println("Building is : " + configModel.Player_detail_list.get(i).getplayer_building());
            System.out.println("Money is : " + configModel.Player_detail_list.get(i).getplayer_money());
            System.out.print("City Area Card :");
            try {
                if (area_num.size() > 0) {
                    for (int j = 0; j < area_num.size(); j++) {

                        System.out.println(configModel.Area_detail_list.get(area_num.get(j)).getarea_name());
                    }

                }
            } catch (NullPointerException e) {
                System.out.println("");
            }

            System.out.println("Green card : ");
            int green = configModel.Player_detail_list.get(i).getplayer_green_card().size();
            for (int j = 0; j < green; j++) {
                System.out.println(configModel.Player_detail_list.get(i).getplayer_green_card1(j) + " : "+ configModel.green_card_detail.get((configModel.Player_detail_list.get(i).getplayer_green_card1(j))-1).getCard_name());
            }

            System.out.println("\n");

        }
        System.out.println("The Bank has =:" + configModel.bank_money + " Ankh-Morpork dollars.");
        System.out.println("Total Trouble  Remain =:" + configModel.player_trouble_maker);
        System.out.println("Total Demon Remain =:" + configModel.player_demon);
        System.out.println("Total Troll Remain =:" + configModel.player_trolls);
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("---------------------------Game State Finish---------------------------------");

    }

    public void handler() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                live_player = new TurnHandler();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }).start();
    }

    @Override
    public void paint(Graphics g) {

        draw = g;
        /*player turn*/
        int color1 = Integer.parseInt("ededed", 16);
        g.setColor(new Color(color1));
        g.fillRect(0, 0, getWidth(), getHeight());

        if (configModel.turn_counter == 1) {
            g.drawImage(turn[0], p1_x + 240, p1_y, turn_w, turn_h, null);
            ArrayList<Integer> cards = configModel.Player_detail_list.get(configModel.turn_counter - 1).getplayer_green_card();
            g.setColor(Color.BLACK);

            for (int i = 0; i < cards.size(); i++) {

                g.drawString(cards.get(i) + " : " + configModel.green_card_detail.get(cards.get(i) - 1).getCard_name(), p1_x + 300, p1_y + 20 + (i * 15));
            }

        } else if (configModel.turn_counter == 2) {
            g.drawImage(turn[0], p1_x + 240, p1_h * (configModel.turn_counter - 1) + p1_y + (20 * (configModel.turn_counter - 1)), 25, 25, null);
            g.setColor(Color.BLACK);
            ArrayList<Integer> cards = configModel.Player_detail_list.get(configModel.turn_counter - 1).getplayer_green_card();
            for (int i = 0; i < cards.size(); i++) {
                g.drawString(cards.get(i) + " : " + configModel.green_card_detail.get(cards.get(i) - 1).getCard_name(), p1_x + 300, p1_h * (configModel.turn_counter - 1) + p1_y + 20 + (i * 15) + (20 * (configModel.turn_counter - 1)));
            }
        } else if (configModel.turn_counter == 3) {
            g.drawImage(turn[0], p1_x + 240, p1_h * (configModel.turn_counter - 1) + p1_y + (20 * (configModel.turn_counter - 1)), 25, 25, null);

            g.setColor(Color.BLACK);
            ArrayList<Integer> cards = configModel.Player_detail_list.get(configModel.turn_counter - 1).getplayer_green_card();
            for (int i = 0; i < cards.size(); i++) {
                g.drawString(cards.get(i) + " : " + configModel.green_card_detail.get(cards.get(i) - 1).getCard_name(), p1_x + 300, p1_h * (configModel.turn_counter - 1) + p1_y + 20 + (i * 15) + (20 * (configModel.turn_counter - 1)));
            }
        } else if (configModel.turn_counter == 4) {
            g.drawImage(turn[0], p1_x + 240, p1_h * (configModel.turn_counter - 1) + p1_y + (20 * (configModel.turn_counter - 1)), 25, 25, null);

            g.setColor(Color.BLACK);
            ArrayList<Integer> cards = configModel.Player_detail_list.get(configModel.turn_counter - 1).getplayer_green_card();
            for (int i = 0; i < cards.size(); i++) {
                g.drawString(cards.get(i) + " : " + configModel.green_card_detail.get(cards.get(i) - 1).getCard_name(), p1_x + 300, p1_h * (configModel.turn_counter - 1) + p1_y + 20 + (i * 15) + (20 * (configModel.turn_counter - 1)));
            }
        }

        /*game play area*/
        int color = Integer.parseInt("cebe9a", 16);
        g.setColor(new Color(color));
        g.fillRect(0, 0, 730, 580);

        /* bank & common item area*/
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 582, 730, 200);
        g.setColor(new Color(color));
        g.fillRoundRect(50, 600, 100, 30, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString("Save Game", 65, 620);

        g.setColor(new Color(color));
        g.fillRoundRect(50, 650, 100, 30, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString("Next", 70, 670);

        g.setColor(new Color(color));
        g.fillRoundRect(50, 700, 100, 30, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString("Random", 75, 720);

        g.drawString("Green-remain: " + configModel.greencard_random_number.size(), 250, 620);
        g.drawString("Brown-remain: " + configModel.brown_random_number.size(), 250, 650);
        g.drawString("Trouble-remain: " + configModel.player_trouble_maker, 250, 680);
        g.drawString("Demon-remain: " + configModel.player_demon, 250, 710);
        g.drawString("Troll-remain: " + configModel.player_trolls, 250, 740);
        /* Players area */
        for (int player = 1; player <= number_player; player++) {

            if (player > 1) {
                y_margin = p1_h * (player - 1) + p1_y + (20 * (player - 1));

            } else {
                y_margin = p1_y * (player);
            }
            g.drawString("Name: " + configModel.Player_detail_list.get(player - 1).getplayer_name(), p1_x + 20, y_margin + 20);
            g.drawString("Color: " + configModel.Player_detail_list.get(player - 1).getplayer_color(), p1_x + 150, y_margin + 20);
            String color_minion = configModel.Player_detail_list.get(player - 1).getplayer_color();
            miniondragdrop(color_minion, p1_x + 20, y_margin + 40, configModel.minion_w, configModel.minion_h, "minion");

            int sec_id = configModel.Player_detail_list.get(player - 1).getplayer_secretcard();
            //initialmoney= 10;
            g.drawString("Money:" + configModel.Player_detail_list.get(player - 1).getplayer_money(), p1_x + 20, y_margin + 100);

            g.drawString("Sec_Card: " + configModel.secret_card_detail.get(sec_id).get_title(), p1_x + 95, y_margin + 55);
            g.drawString("Building:", p1_x + 95, y_margin + 70);
            g.drawString("Green: ", p1_x + 5, y_margin + 120);
            int green = configModel.Player_detail_list.get(player - 1).getplayer_green_card().size();
            for (int c = 0; c < green; c++) {

                g.drawString("" + configModel.Player_detail_list.get(player - 1).getplayer_green_card1(c), p1_x + 50 + (25 * c), y_margin + 120);
            }
        
            g.drawString("Worth : "+configModel.Player_detail_list.get(player-1).get_worth(), p1_x + 5, y_margin + 140);
            
            g.drawString("City_Card:", p1_x + 95, y_margin + 90);

            try {
                ArrayList<Integer> area_num = configModel.Player_detail_list.get(player - 1).get_area_card();

                if (area_num.size() > 0) {
                    for (int j = 0; j < area_num.size(); j++) {

                        g.drawString("" + configModel.Area_detail_list.get(area_num.get(j)).getarea_name(), p1_x + 180, y_margin + 95 + (initialmoney * j));
                    }
                } else {

                }
                g.drawString("" + (configModel.Player_detail_list.get(player - 1).getplayer_building()), p1_x + 150, y_margin + 70);
            } catch (NullPointerException e) {
                //e.printStackTrace();

                g.drawString("" + (configModel.Player_detail_list.get(player - 1).getplayer_building()), p1_x + 150, y_margin + 70);
            }

            configModel.Player_detail_list.get(player - 1).set_minion_position(p1_x + 20, y_margin + 40);
            g.drawString("" + configModel.Player_detail_list.get(player - 1).getplayer_minion(), p1_x + 50, y_margin + 55);
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect(p1_x + 20, y_margin + 40, configModel.minion_w, configModel.minion_h);
            g.setColor(Color.BLACK);

            g.drawRect(p1_x, y_margin, p1_w, p1_h);

        }
        /* Bank Area*/
        //int bank = configModel.total_money - (15 * number_player);
        g.drawString("BankMoney: " + configModel.bank_money, 390, 680);

        /*area rectangle*/
        if (m_r.length > 0) {
            for (int rectlist = 0; rectlist < m_r.length; rectlist++) {
                //System.out.println("I ID:" + rectlist);

                m_r[rectlist].getareaid();
                int x = m_r[rectlist].getxcordinate();
                int y = m_r[rectlist].getycordinate();
                int width = m_r[rectlist].getwidth_rectangle();
                int height = m_r[rectlist].getheight_rectangle();
                int cornerx = m_r[rectlist].corner_x;
                int cornery = m_r[rectlist].corner_y;
                g.drawRect(x, y, width, height);
                g.drawImage(area[rectlist], x, y, width, height, null);
                /* line in area*/
                if (rectlist == 11) {
                    LineMap = m_r[rectlist].getline_map();
                    for (int line = 0; line < 28; line++) {
                        g.setColor(Color.BLACK);
                        g.drawLine(LineMap[line][0], LineMap[line][1], LineMap[line][2], LineMap[line][3]);
                    }
                }

            }

        }

        /* drag and drop*/
        for (int i = 0; i < 12; i++) {
            configModel.rg_b = configModel.Area_detail_list.get(i).get_rectgrid_obj();
            for (int l = 0; l < 24; l++) {

                int[] cg = configModel.rg_b[l].get_rectgrid();
                int cx = cg[0];
                int cy = cg[1];

                if (configModel.rg_b[l].enable && configModel.rg_b[l].type.equals("minion")) {
                    int player_id = configModel.rg_b[l].player_id;
                    String player_color = configModel.Player_detail_list.get(player_id).getplayer_color();
                    miniondragdrop(player_color, cx, cy, configModel.minion_w, configModel.minion_h, "minion");

                    //g.drawImage(minion[0], cx, cy, configModel.minion_w, configModel.minion_h, null);
                    //g.drawRect(cx, cy, configModel.minion_w, configModel.minion_h);
                }
                if (configModel.rg_b[l].enable && configModel.rg_b[l].type.equalsIgnoreCase("trouble")) {
                    g.drawImage(trouble[0], cx, cy, configModel.minion_w, configModel.minion_h, null);
                }
                if (configModel.rg_b[l].enable && configModel.rg_b[l].type.equalsIgnoreCase("demon")) {
                    g.drawImage(demon[0], cx, cy, configModel.minion_w, configModel.minion_h, null);
                }
                if (configModel.rg_b[l].enable && configModel.rg_b[l].type.equalsIgnoreCase("troll")) {
                    g.drawImage(troll[0], cx, cy, configModel.minion_w, configModel.minion_h, null);
                }
                if (configModel.rg_b[l].enable && configModel.rg_b[l].type.equalsIgnoreCase("building")) {
                    //System.out.println("in the buildingseeting paint event");
                    int player_id = configModel.rg_b[l].player_id;
                    String player_color = configModel.Player_detail_list.get(player_id).getplayer_color();
                    // System.out.println("player color to build building"+ player_color);
                    miniondragdrop(player_color, cx, cy, configModel.minion_w, configModel.minion_h, "building");
                    //g.drawImage(trouble[0], cx, cy, configModel.minion_w, configModel.minion_h, null);
                }
            }
        }

        if (drag == 1) {
            g.setColor(Color.white);
            String player_color = configModel.Player_detail_list.get(configModel.turn_counter - 1).getplayer_color();
            miniondragdrop(player_color, cposx, cposy, configModel.minion_w, configModel.minion_h, "");
            //g.drawImage(minion[0], cposx, cposy, configModel.minion_w, configModel.minion_h, null);
            // g.drawRect(cposx, cposy, configModel.minion_w, configModel.minion_h);

        }
        //handler();

    }

    public void miniondragdrop(String color, int minx, int miny, int minwidth, int minheight, String type) {
        if (type.equals("minion")) {
            switch (color) {
                case "red": {
                    draw.drawImage(minion[0], minx, miny, minwidth, minheight, null);
                }
                break;
                case "blue": {
                    draw.drawImage(minion[1], minx, miny, minwidth, minheight, null);
                }
                break;
                case "green": {
                    draw.drawImage(minion[2], minx, miny, minwidth, minheight, null);
                }
                break;
                case "yellow": {
                    draw.drawImage(minion[3], minx, miny, minwidth, minheight, null);
                }
                break;
                default:

            }
        }
        if (type.equals("building")) {
            //ystem.out.println("in the building swithc loop");
            switch (color) {
                case "red": {
                    draw.drawImage(building[0], minx, miny, minwidth, minheight, null);
                }
                break;
                case "blue": {
                    draw.drawImage(building[1], minx, miny, minwidth, minheight, null);
                }
                break;
                case "green": {
                    draw.drawImage(building[2], minx, miny, minwidth, minheight, null);
                }
                break;
                case "yellow": {
                    draw.drawImage(building[3], minx, miny, minwidth, minheight, null);

                    break;

                }
                default:
            }
        }

    }

    public ArrayList<Integer> sufflecards(ArrayList<Integer> cards) {
        Collections.shuffle(cards);
        return cards;
    }

    /**
     * this run method is used to run thread continously to paint the component.
     */
    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MapcreateView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
