/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.model;


import ankh.controller.CurrentPlayer;
import ankh.view.Eventhandler;
import ankh.view.configModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import citystartegy.*;
import winstrategy.Annonym_wincheck;
import winstrategy.Chrysoprase_wincheck;
import winstrategy.Commandervimes_wincheck;
import winstrategy.Dragonking_wincheck;
import winstrategy.Lorddeworde_wincheck;
import winstrategy.Lordrust_wincheck;
import winstrategy.Lordselachii_wincheck;
import winstrategy.Lordvetinari_wincheck;
import winstrategy.Wincheck;


/**
 * This is Player_detail class is used to store the information of players.
 * @author Parth B Gadhiya
 */
public class Player_detail {
    
    public int player_id;
    public String player_name;
    public String player_color;

    public int player_minion;
    public int building;
    public int player_money;
    public int player_secretcard;
    public int player_city[];
    public ArrayList<Integer> player_green_card = new ArrayList<Integer>();
    public int player_brown_card[];
    public ArrayList<Integer> player_area_card = new ArrayList<Integer>();
    public int[] minion_location;
     public int[] building_location;
    public int loan=0;
    public int[] minion_position = new int[2];
    public int[][] temp_loc;
    public ArrayList<Integer> a_list = new ArrayList<>();
    public Eventhandler event_handle = new Eventhandler();
    public Wincheck win_cond;
    public ArrayList<Cityarea_check> city_cond_list = new ArrayList<Cityarea_check>();
    public Cityarea_check city_cond = new Cityarea_check(new Annonym_city(),-1);

    public Player_detail()
    {
         win_cond = new Wincheck(new Annonym_wincheck());
        
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
        System.out.println("player id in the secret card" + player_secretcard);
        this.player_secretcard = player_secretcard;
        assign_identity(player_secretcard);
        
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
    public void setplayer_green_card(int player_green)
    {
        
        player_green_card.add(player_green);
    }
    public ArrayList<Integer> getplayer_green_card()
    {
        return player_green_card;
    }

    public int getplayer_green_card1(int id)
    {
        return player_green_card.get(id);
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
     * @param set_area_card the set_area_card to set
     */
    public void set_area_card(int area_card)
    {
        
        player_area_card.add(area_card);
        assign_cityarea(area_card);
        
        
    }
    public ArrayList<Integer> get_area_card()
    {
        return player_area_card;
    }
    
    public void remove_area_card(int id)
    {
        for(int i=0;i<player_area_card.size();i++)
        {
            if(player_area_card.get(i)==id)
            {
                player_area_card.remove(i);
            }
         
           
        }
    }
    public void remove_green_card(int card)
    {
        for(int i=0;i<player_green_card.size();i++)
        {
            if(player_green_card.get(i)==card)
            {
                player_green_card.remove(i);
            }
         
           
        }
        
        
       
    }
    public void set_loan(int i)
    {
        loan += i;
    }
    public int get_loan()
    {
        return loan;
    }
    
    public int get_worth()
    {
        int total_money = 0;
        int build_cost = 0;
        int total_minion = event_handle.get_area_without_demon(player_id-1, "minion").size();
        List<Integer> building_cost = event_handle.get_area_without_demon(player_id-1, "building");
        for(Integer cost:building_cost)
        {
            build_cost+=configModel.Area_detail_list.get(cost-1).getarea_cost();
        }
        int player_money = configModel.Player_detail_list.get(player_id-1).getplayer_money();
        int loan = CurrentPlayer.currentPlayer.get_loan();
        total_money = (total_minion*5)+(build_cost+player_money)-(loan*15);
        return total_money;
    }
    
    public void assign_identity(int id)
    {
             
        switch(id)
        {
            case 0:
            {
                win_cond = new Wincheck(new Lordvetinari_wincheck());
            }
                break;
            case 1:
            {
                win_cond = new Wincheck(new Lordselachii_wincheck());
            }
                break;
            case 2:
            {
                win_cond = new Wincheck(new Lordrust_wincheck());
            }
                break;
            case 3:
            {
                win_cond = new Wincheck(new Lorddeworde_wincheck());
            }
                break;
            case 4:
            {
                win_cond = new Wincheck(new Dragonking_wincheck());
            }
                break;
            case 5:
            {
                win_cond = new Wincheck(new Chrysoprase_wincheck());
            }
                break;
            case 6:
            {
                win_cond = new Wincheck(new Commandervimes_wincheck());
            }
                break;
            default:
                break;
                
                
                
        }
    }
     public void assign_cityarea(int id)
    {
             
        switch(id)
        {
            case 0:
                city_cond = new Cityarea_check(new Dollysister(),0);
                break;
            case 1:
                city_cond = new Cityarea_check(new Unrealestate(),1);
                break;
            case 2:
               city_cond = new Cityarea_check(new Dragonlanding(),2);
                break;
            case 3:
                city_cond = new Cityarea_check(new Smallgods(),3);
                break;
            case 4:
                city_cond = new Cityarea_check(new Thescours(),4);
                break;
            case 5:
                city_cond = new Cityarea_check(new Thehippo(),5);
                break;
            case 6:
                city_cond = new Cityarea_check(new Theshades(),6);
                break;
            case 7:
                city_cond = new Cityarea_check(new Dimwell(),7);
                break;
            case 8:
                city_cond = new Cityarea_check(new Longwall(),8);
                break;
            case 9:
                city_cond = new Cityarea_check(new Isleofgod(),9);
                break;
            case 10:
                city_cond = new Cityarea_check(new Sevensleepers(),10);
                break;
            case 11:
                city_cond = new Cityarea_check(new Naphill(),11);
                break;
                
            default:
                break;
                
                
                
        }
        city_cond_list.add(city_cond);
    }
}
