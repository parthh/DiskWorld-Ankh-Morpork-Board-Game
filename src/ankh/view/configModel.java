/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;


import ankh.controller.Eventdemo;
import ankh.controller.Event;
import ankh.model.Mapdetail_rectangle;
import ankh.model.Secret_Card;
import ankh.model.Player_detail;
import ankh.model.Browncard_detail;
import ankh.model.Greencard_detail;
import ankh.model.Random_Card;
import ankh.controller.RectGrid;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author admin
 */
public class configModel {
    //player start from 0;
    public static Eventhandler cev = new Eventhandler();
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static List<Integer> adjacent_list = new ArrayList<Integer>();
    public static int adjacentarea[];
   
    public static boolean status = false;
    public static Event accessEvent = new Event();
    public static Eventdemo evcode;
    public static int turn_counter = 1;
    public static int minion_h =25;
    public static int minion_w=25;
    public static String load_gamefile;

    public static Mapdetail_rectangle[] map = new Mapdetail_rectangle[12];
    public static  RectGrid[] rg_b ;
    
    
    public static Player_detail player[];
    public static Image[] area = new Image[12];
    public static int no_of_players;
    public static ArrayList<Player_detail> Player_detail_list= new ArrayList<>();
    public static ArrayList<Mapdetail_rectangle> Area_detail_list= new ArrayList<>();
    public static ArrayList<Secret_Card> secret_card_detail = new ArrayList<>();
    public static ArrayList<Random_Card> random_card_detail = new ArrayList<>();
    public static ArrayList<Greencard_detail> green_card_detail = new ArrayList<>();
    public static ArrayList<Browncard_detail> brown_card_detail = new ArrayList<>();
    public static int[][] player_area_cordi = new int[4][2];
    
    public static Image[] minion = new Image[4];
    public static Image[] building = new Image[4];
    public static Image[] money = new Image[1];
    public static Image[] demon = new Image[1];
    public static Image[] troll = new Image[1];
    public static Image[] trouble = new Image[1];
    public static Image[] turn  = new Image[1];
    public static int player_demon = 4;
    public static int player_trouble_maker = 12;
    public static int player_trolls = 3;
    public static int player_minion = 12;
    public static int player_building =6;
    public static int total_money = 120;
    public static int bank_money;
    public static int random_card = 12;
    public static int cityarea_card = 12;
    public static int personality_card = 7;
    
    public static ArrayList<Integer> secret_Random_number = new ArrayList<Integer>(7);
    public static ArrayList<Integer> greencard_random_number = new ArrayList<Integer>(48);
    public static ArrayList<Integer> brown_random_number = new ArrayList<Integer>(53);
    public static ArrayList<Integer> discarded_card = new ArrayList<Integer>();
    public static ArrayList<Integer> dice_random_number  = new ArrayList<Integer>(12);

     public static ArrayList<Integer> random_event = new ArrayList<Integer>(5);
 
   
    
}