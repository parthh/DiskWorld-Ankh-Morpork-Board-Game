/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is Player_detail class is used to store the information of players.
 * @author Parth B Gadhiya
 */
public class Player_detail {
    
    int player_id;
    String player_name;
    String player_color;
    int player_minion;
    int building;
    int player_money;
    int player_secretcard;
    int player_city[];
    int player_green_card[];
    int player_brown_card[];
    int player_area_card[];
    int[] minion_location;
    int[] building_location;
    int[] stored_min_location;
    int[] stored_bui_location;
    int[] minion_position = new int[2];
    int[][] temp_loc;
    ArrayList<Integer> a_list = new ArrayList<>();
    
   
    
    public Player_detail()
    {
        
    }
    /**
     * This Player_detail will initialize player details.
     * @param id this Variable is unique identity of player.
     * @param name this is name of player.
     * @param color this is color selected by the player.
     * @param minion this is initial number of minion assign to player.
     * @param building this is initial number of building assign to player.
     */
    public Player_detail(int id,String name, String color, int minion,int building)
    {
        this.player_id = id;
        this.player_name = name;
        this.player_color = color;
        this.player_minion = minion;
        this.building = building;
    }
     /**
     * @param setid the setid to set
     */
    public void setid(int player_id)
    {
        this.player_id = player_id;
    }
    public int getid()
    {
        return player_id;
    }
     /**
     * @param setplayer_name the setplayer_name to set
     */
    public void setplayer_name(String player_name)
    {
        this.player_name = player_name;
    }
    public String getplayer_name()
    {
        return player_name;
    }
     /**
     * @param setplayer_color the setplayer_color to set
     */
    public void setplayer_color(String player_color)
    {
        this.player_color = player_color;
    }
    public String getplayer_color()
    {
        return player_color;
    }
     /**
     * @param setplayer_minion the setplayer_minion to set
     */
    public void setplayer_minion(int player_minion)
    {
        this.player_minion = player_minion;
    }
    public int getplayer_minion()
    {
        return player_minion;
    }
     /**
     * @param setplayer_building the setplayer_building to set
     */
    public void setplayer_building(int building)
    {
        this.building = building;
    }
    public int getplayer_building()
    {
        return building;
    }
     /**
     * @param setplayer_money the setplayer_money to set
     */
    public void setplayer_money(int player_money)
    {
        this.player_money = player_money;
    }
    public int getplayer_money()
    {
        return player_money;
    }
     /**
     * @param setplayer_secretcard the setplayer_secretcard to set
     */
    public void setplayer_secretcard(int player_secretcard)
    {
        this.player_secretcard = player_secretcard;
    }
    public int getplayer_secretcard()
    {
        return player_secretcard;
    }
     /**
     * @param setplayer_city the setplayer_city to set
     */
    public void setplayer_city(int player_city)
    {
        int i = this.player_city.length;
        this.player_city[i] = player_city;
    }
    public int[] getplayer_city()
    {
        return player_city;
    }
    public int getsingle_city(int i)
    {
        return player_city[i];
    }
     /**
     * @param setplayer_green_card the setplayer_green_card to set
     */
    public void setplayer_green_card(int[] player_green_card)
    {
        
        this.player_green_card = player_green_card;
    }
    public int[] getplayer_green_card()
    {
        return player_green_card;
    }
     /**
     * @param setplayer_brown_card the setplayer_brown_card to set
     */
    public void setplayer_brown_card(int player_brown_card)
    {
        int i = this.player_brown_card.length;
        this.player_brown_card[i] = player_brown_card;
    }
    public int[] getplayer_brown_card()
    {
        return player_brown_card;
    }
     /**
     * @param set_minion_position the set_minion_position to set
     */
     public void set_minion_position(int x,int y)
    {
        
       minion_position[0] = x;
       minion_position[1] = y;

       
    }
    public int[] get_minion_position()
    {
       return minion_position;
    }
 /**
     * @param stored_min_location the stored_min_location to set
     */
    public void set_stored_minion(int[] minion)
    {
        this.stored_min_location = minion;
        
    }
    public int[] get_stored_minion()
    {
        return stored_min_location;
    }
   
     /**
     * @param set_area_card the set_area_card to set
     */
    public void set_area_card(int[] area_card)
    {
        player_area_card = new int[area_card.length];
        
        player_area_card = area_card;
        
    }
    public int[] get_area_card()
    {
        return player_area_card;
    }
}
