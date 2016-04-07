/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import ankh.controller.Eventdemo;
import ankh.controller.Eventdemo;
import ankh.controller.Perform_scroll;
import ankh.controller.Perform_scroll;
import static ankh.saveloadfile.SaveGame.rg_b;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import ankh.model.Player_detail;
import ankh.view.configModel;

/**
 *
 * @author admin
 */
public class Eventhandler {
    public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public int[] removeDuplicates(int[] arr) {
        Set<Integer> alreadyPresent = new HashSet<>();
        int[] whitelist = new int[arr.length];
        int i = 0;

        for (int element : arr) {
            if (alreadyPresent.add(element)) {
                whitelist[i++] = element;
            }
        }

        return Arrays.copyOf(whitelist, i);
    }

    public boolean set_item(int player_id, int area_id, String item_type) {
        boolean set_complete = false;
        rg_b = configModel.Area_detail_list.get(area_id).get_rectgrid_obj();
        if(item_type == "trouble" && check_item_in_area(area_id, item_type))
        {
            return true;
        }
      
        for (int grid = 0; grid < 24; grid++) {

            if (!rg_b[grid].enable) {
                rg_b[grid].enable = true;
                rg_b[grid].type = item_type;
                rg_b[grid].player_id = player_id;

                if (player_id != 9) {
                    rg_b[grid].grid_color = configModel.Player_detail_list.get(player_id).getplayer_color();
                    System.out.println(item_type + " placed into area" + (area_id + 1) + "of player " + (player_id + 1));

                } else {
                    System.out.println(item_type + " placed into area" + (area_id + 1));
                }
                set_complete = true;
                break;
            }
        }
        if (set_complete) {
            switch (item_type) {
                case "minion": {
                    boolean new_check_trouble = check_item_in_area(area_id, "trouble");
                    if (!new_check_trouble) {
                        set_item(9, area_id, "trouble");
                    }
                    configModel.Player_detail_list.get(player_id).setplayer_minion(configModel.Player_detail_list.get(player_id).getplayer_minion() - 1);

                }
                break;
                case "building": {
                    configModel.Player_detail_list.get(player_id).set_area_card(area_id + 1);
                    configModel.Player_detail_list.get(player_id).setplayer_building(configModel.Player_detail_list.get(player_id).getplayer_building() - 1);
                }
                break;
                case "trouble": {
                    configModel.player_trouble_maker--;
                }
                break;
                case "demon": {
                    boolean new_check_trouble = check_item_in_area(area_id, "trouble");
                    if (!new_check_trouble) {
                        set_item(9, area_id, "trouble");
                    }
                    configModel.player_demon--;
                }
                break;
                case "troll": {
                    boolean new_check_trouble = check_item_in_area(area_id, "trouble");
                    if (!new_check_trouble) {
                        set_item(9, area_id, "trouble");
                    }
                    configModel.player_trolls--;
                }
                break;
                default:
                    set_complete = false;
            }
        }
        return set_complete;

    }

    public boolean move_item(int player_id, int old_area, int new_area, String item_type) {
        // remove item from old area
        boolean oldstatus = false;
        boolean newstatus = false;
        oldstatus = remove_item(player_id, old_area, item_type);
        if (oldstatus) {
            //put item to new area
            set_item(player_id, new_area, item_type);
            newstatus = true;
        }
        return newstatus;
    }

    public boolean remove_all(int player_id, int area_id, String item_type) {
        rg_b = configModel.Area_detail_list.get(area_id).get_rectgrid_obj();
        for (int grid = 0; grid < 24; grid++) {
            remove_item(player_id, area_id, item_type);
        }
        return true;
    }

    public boolean remove_item(int player_id, int area_id, String item_type) {
        rg_b = configModel.Area_detail_list.get(area_id).get_rectgrid_obj();
        int i = 0;
        boolean remove_flag = false;

        if (player_id == 9) {
            for (int grid = 0; grid < 24; grid++) {
                if (rg_b[grid].enable && rg_b[grid].type.equalsIgnoreCase(item_type)) {
                    rg_b[grid].enable = false;
                    rg_b[grid].type = "";
                    rg_b[grid].player_id = 9;
                    rg_b[grid].grid_color = "";
                    System.out.println(item_type + " removed from area" + (area_id + 1));
                    remove_flag = true;

                    break;
                }
            }
        } else {
            for (int grid = 0; grid < 24; grid++) {
                if (rg_b[grid].enable && rg_b[grid].type.equalsIgnoreCase(item_type) && (rg_b[grid].player_id == player_id)) {
                    rg_b[grid].enable = false;
                    rg_b[grid].type = "";
                    rg_b[grid].player_id = 9;
                    rg_b[grid].grid_color = "";
                    System.out.println(item_type + " removed from area" + (area_id + 1) + " of player " + (player_id + 1));
                    remove_flag = true;
                    break;
                }
            }
        }
        if (!remove_flag) {
            // System.out.println("No item Removed!");
        }

        if (remove_flag) {

            switch (item_type) {
                case "minion": {
                    boolean new_check_trouble = check_item_in_area(area_id, "trouble");
                    if (new_check_trouble) {
                        remove_item(9, area_id, "trouble");
                    }
                    configModel.Player_detail_list.get(player_id).setplayer_minion(configModel.Player_detail_list.get(player_id).getplayer_minion() + 1);
                }
                break;
                case "building": {
                    configModel.Player_detail_list.get(player_id).remove_area_card(area_id + 1);
                    configModel.Player_detail_list.get(player_id).setplayer_building(configModel.Player_detail_list.get(player_id).getplayer_building() + 1);
                }
                break;
                case "trouble": {
                    configModel.player_trouble_maker++;
                    remove_flag = true;
                }
                break;
                case "demon": {
                    boolean new_check_trouble = check_item_in_area(area_id, "trouble");
                    if (new_check_trouble) {
                        remove_item(9, area_id, "trouble");
                    }
                    configModel.player_demon++;
                }
                break;
                case "troll": {
                    boolean new_check_trouble = check_item_in_area(area_id, "trouble");
                    if (new_check_trouble) {
                        remove_item(9, area_id, "trouble");
                    }
                    configModel.player_trolls++;
                }
                break;
                default: {
                    remove_flag = false;
                }
                break;
            }
        }
        return remove_flag;
    }

    public boolean check_item_in_area(int area_id, String item_type) {
        rg_b = configModel.Area_detail_list.get(area_id).get_rectgrid_obj();
        int temp_check = 0;
        for (int grid = 0; grid < 24; grid++) {
            if (rg_b[grid].enable && (rg_b[grid].type.equalsIgnoreCase(item_type))) {
                temp_check++;

            }

        }
        if (temp_check == 0) {
            return false;
        } else {
            return true;
        }
    }
    public int other_player_chooser()
    {
        List<Integer> player_ids = new ArrayList<Integer>();
        int curr_player = configModel.turn_counter;
               
        int u_input = 0;
        boolean repeat = true;
        do{
            System.out.println("Select One player from the below list");
             for(int i=1;i<=configModel.no_of_players;i++)
                {
                    if(curr_player != i)
                    {
                        player_ids.add(i);
                        System.out.println(i+" ="+configModel.Player_detail_list.get(i-1).player_name);
                    }
                    
                }
            try {
                      u_input = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
                 } catch (IOException ex) {
                     Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 if(player_ids.contains(u_input))
                 {
                     repeat = false;
                 }
                 else
                 {
                    System.out.println("Opps You choose wrong option !");
                 }
        }while(repeat);
        return u_input;
    }
    public List<Integer> get_playerids_item_in_area(int area_id,String item_type)
    {
        int no_player = configModel.no_of_players;
        List<Integer> player_list = new ArrayList<Integer>();
        for(int i=0;i<no_player;i++)
        {
            int[] item_present = get_player_item_in_area(i,area_id);
            if(item_type == "minion")
            {
                int temp = item_present[0];
                if(temp > 0)
                {
                    player_list.add(i);
                }
            }
            if(item_type == "building")
            {
               int temp = item_present[1];
                if(temp > 0)
                {
                    player_list.add(i);
                } 
            }
        }
        return player_list;
    }
    public int[] get_player_item_in_area(int player_id, int area_id) {
        rg_b = configModel.Area_detail_list.get(area_id).get_rectgrid_obj();
        int bulding_count = 0;
        int minion_count = 0;
        int[] area_item = new int[2];
        for (int grid = 0; grid < 24; grid++) {
            if (rg_b[grid].enable && (rg_b[grid].player_id == player_id)) {
                if (rg_b[grid].type.equalsIgnoreCase("building")) {
                    bulding_count++;
                }
                if (rg_b[grid].type.equalsIgnoreCase("minion")) {
                    minion_count++;
                }
            }
        }
        area_item[0] = minion_count;
        area_item[1] = bulding_count;
        return area_item;
    }

    public boolean is_empty_area(int area_id) {
        rg_b = configModel.Area_detail_list.get(area_id).get_rectgrid_obj();
        int temp_check = 0;
        for (int grid = 0; grid < 24; grid++) {
            if (rg_b[grid].enable) {
                temp_check++;
            }
        }
        if (temp_check == 0) {
            System.out.println("Area" + area_id + " is Empty !");
            return true;
        } else {
            return false;
        }
    }

    public List<Integer> get_item_area(int player_id, String item_type) {
        List<Integer> area_list = new ArrayList<Integer>();
        for (int area = 0; area < 12; area++) {
            rg_b = configModel.Area_detail_list.get(area).get_rectgrid_obj();
            int i = 0;
            for (int grid = 0; grid < 24; grid++) {
                if (player_id == 9) {
                    if (rg_b[grid].enable && rg_b[grid].type.equalsIgnoreCase(item_type)) {
                        area_list.add(area + 1);
                    }
                } else if (rg_b[grid].enable && rg_b[grid].type.equalsIgnoreCase(item_type) && (rg_b[grid].player_id == player_id)) {
                    area_list.add(area + 1);
                }
            }

        }
        // Remove Duplicate area
        HashSet hs = new HashSet();
        hs.addAll(area_list);
        area_list.clear();
        area_list.addAll(hs);
        return area_list;
    }
     public List<Integer> get_area_without_demon(int player_id, String item_type) {
        List<Integer> area_list = new ArrayList<Integer>();
        for (int area = 0; area < 12; area++) {
            rg_b = configModel.Area_detail_list.get(area).get_rectgrid_obj();
            int i = 0;
            
            for (int grid = 0; grid < 24; grid++) {
                if (player_id == 9) {
                    if (rg_b[grid].enable && rg_b[grid].type.equalsIgnoreCase(item_type)) {
                        boolean demon_check = check_item_in_area(area, "demon");
                            if(!demon_check)
                            {
                            area_list.add(area + 1);
                            }
                        
                    }
                } else if (rg_b[grid].enable && rg_b[grid].type.equalsIgnoreCase(item_type) && (rg_b[grid].player_id == player_id)) {
                    boolean demon_check = check_item_in_area(area, "demon");
                            if(!demon_check)
                            {
                            area_list.add(area + 1);
                            }
                    
                }
            }

        }
        // Remove Duplicate area
        HashSet hs = new HashSet();
        hs.addAll(area_list);
        area_list.clear();
        area_list.addAll(hs);
        return area_list;
    }

    public List<Integer> get_adjacent_area(List<Integer> item_area) {

        List<Integer> adjacent_list = new ArrayList<Integer>();
        int[] adjacent_temp = null;
        for (int i = 0; i < item_area.size(); i++) {
            //get adjucent
            adjacent_temp = configModel.Area_detail_list.get(item_area.get(i)-1).getadjacent();
            adjacent_list.add(item_area.get(i));
            for (int j = 0; j < adjacent_temp.length; j++) {
                adjacent_list.add(adjacent_temp[j]);
            }
        }
        // Remove Duplicate area
        HashSet hs = new HashSet();
        hs.addAll(adjacent_list);
        adjacent_list.clear();
        adjacent_list.addAll(hs);

        return adjacent_list;
    }

    public boolean bank_transaction(int player, int amount, String tra_type) {

        //check money in players account
        int player_money = configModel.Player_detail_list.get(player).getplayer_money();
        if (tra_type == "deposit") {
            if (player_money > amount) {
                // credit to bank account from user's account
                configModel.bank_money = configModel.bank_money + amount;
                configModel.Player_detail_list.get(player).setplayer_money(player_money - amount);
                return true;
            } else {
                System.out.println("You have not enough money!");
                return false;
            }
        } else if (tra_type == "withdraw") {
            configModel.bank_money = configModel.bank_money - amount;
            configModel.Player_detail_list.get(player).setplayer_money(player_money + amount);
            return true;
        }
        else
        {
            return false;
        }
        
    }

    public boolean player_trasanction(int from_id, int to_id, int amount) {
        int from_player_money = configModel.Player_detail_list.get(from_id).getplayer_money();
        int to_player_money = configModel.Player_detail_list.get(to_id).getplayer_money();
        if (from_player_money > amount) {
            configModel.Player_detail_list.get(from_id).setplayer_money(from_player_money - amount);
            configModel.Player_detail_list.get(to_id).setplayer_money(to_player_money + amount);
            return true;
        } else {
            System.out.println("Player" + from_id + " have not enough money to pay");
            return false;
        }

    }

    public boolean checkTroubleMakerPresent(int area_id) {

        rg_b = configModel.Area_detail_list.get(area_id).get_rectgrid_obj();

        int i = 0;
        for (int grid = 0; grid < 24; grid++) {
            if (rg_b[grid].enable && rg_b[grid].type.equalsIgnoreCase("trouble")) {
                return true;
            }

        }
        return false;
    }

    public boolean checkForInterrupt(int player_id, int area_id) {
        int interrupCardNumber[];
        String str="";
       
                for (Integer i : configModel.Player_detail_list.get(player_id).getplayer_green_card()) {
                    if ((i == 45) || (i == 17) || (i == 18)) {
                        System.out.println("player "+ (player_id+1)+ "you have interrupt card "+ configModel.green_card_detail.get(i-1).getCard_name());
                        System.out.println("do you want to play interrupt card Enter YES or NO");
                        try {
                            str = br.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(Eventhandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(str.equalsIgnoreCase("yes"))
                        {
                            handleInterrupt(i, player_id, area_id);
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                     
                    }
                    else
                    {
                        return false;
                    }
                }
        return false;
    }

   
    public void handleInterrupt(int card_id, int player_id, int area_id) {
        String player_name = "";
        String area_name = "";
        player_name = configModel.Player_detail_list.get(player_id).getplayer_name();
        area_name = configModel.Area_detail_list.get(area_id).getarea_name();
        switch (card_id) {
            case 45: {
                System.out.println("Sorry! The Minion of the Player " + player_name + " cannot be Removed as He has Interrupted the "
                        + "Assassination by using the " + configModel.green_card_detail.get(card_id).getCard_name());
                configModel.discarded_card.add(card_id);
                configModel.Player_detail_list.get(player_id).remove_green_card(card_id);

                break;
            }
            case 17: {

                System.out.println("Sorry! The Minion of the Player " + player_name + " cannot be Removed as He has Interrupted the "
                        + "Assassination by using the Gaspode Card  ");
                configModel.discarded_card.add(card_id);
                configModel.Player_detail_list.get(player_id).remove_green_card(card_id);

                break;
            }
            case 18: {
                System.out.println("The Minion of the " + player_name + "has been Removed from" + area_name);
                boolean loop_again =true;
                do
                {
                System.out.println("please select from the following area where you want to move the minion");
               // int area[] = configModel.Area_detail_list.get(area_id).getadjacent();
                for(int i=0;i<12;i++)
                {
                    if(i!=area_id)
                    {
                    System.out.println((i+1)+":= "+ configModel.Area_detail_list.get(i).getarea_name());
                    }
                }
                int move_area_no=0;
                    try {
                        move_area_no = Integer.parseInt(br.readLine());
                       
                    } catch (IOException ex) {
                        Logger.getLogger(Eventhandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                boolean chek_present = false;
                        if((move_area_no-1)!=area_id)
                        {
                            chek_present = true;
                        }
                        else
                        {
                            System.out.println("choose different are not same past one");
                            break;
                        }
                if(chek_present)
                {
                    boolean check =move_item(player_id, area_id, move_area_no-1, "minion");
                    if(check)
                    {
                         configModel.discarded_card.add(card_id);
                        configModel.Player_detail_list.get(player_id).remove_green_card(card_id);
                        loop_again = false;
                    }
                    
                            
                }
                else
                {
                    System.out.println("Choose correct Option");
                }
                }while(loop_again);
                
                break;
            }
            default: {
                System.out.println("Something wrong with the logic");
            }
        }
    }

    //check city area card4 for small GOD
    public int check_small_god() {
        
        int temp = 0;
        boolean player_status = false;
        for (int p = 0; p < configModel.no_of_players; p++) {

            List<Integer> area_cards = configModel.Player_detail_list.get(p).get_area_card();

            for (int a = 0; a < area_cards.size(); a++) {
                if (area_cards.get(a) == 4) {
                    temp = p;
                    player_status = true;
                    System.out.println("player has small god card");
                    break;
                }
            }
        }
        if (player_status) {
            return temp;
        } else {
            System.out.println("player has small god card  dsgdggdfgdf");
            return 9;
        }

    }
    
    public boolean small_god_buildings(int area_id) throws IOException
    {
        int f_rnum = area_id;
        int playerHASgod = check_small_god();
                
                for (int i = 0; i < configModel.no_of_players; i++) {
                    if (i == playerHASgod) {
                        if (!is_empty_area(f_rnum)) {
                            int[] area_item = get_player_item_in_area(playerHASgod, f_rnum);
                            int player_money = configModel.Player_detail_list.get(playerHASgod).getplayer_money();
                            if (player_money >= 3 && area_item[1] > 0) {
                                System.out.println("Player" + i + " has Small God card. You can protact you building");
                                System.out.println("Do you want to protact than Enter 1");
                                System.out.println("1 Building");
                                int input = Integer.parseInt(br.readLine());
                                if (input!=1) {
                                    remove_item(playerHASgod, f_rnum, "building");
                                }else
                                {
                                    System.out.println("You save your building in area"+f_rnum);
                                }

                            }
                        }
                    } else {
                        System.out.println("removing building >>>>>>>>>>");
                        boolean result = remove_item(i, f_rnum, "building");
                     
                        
                    }
                }
                return true;
    }
     public int get_next_player()
    {
        int player=0;
         if (configModel.turn_counter < configModel.no_of_players) {
                player = configModel.turn_counter+1;
                //configModel.turn_counter++;
                //System.out.println("player number"+configModel.turn_counter);

            } else {
                player = 1;
            }
         return player;
    }
     public void card_printer(int card_id,String card_type)
     {
         switch(card_type)
         {
             case "green":
             {
                 String printer="";
                 String scroll="";
                 int event[] = configModel.green_card_detail.get(card_id).getcard_event();
                 if(event.length>0)
                 {
                  for(int i=0;i<event.length;i++)
                {
                    
                    switch (configModel.accessEvent.getEventname(Eventdemo.get(event[i]))) {
                        case "SCROLL":
                        {
                            
                            scroll="SCROLL[" +configModel.green_card_detail.get(card_id).getCard_detail()+"] ";
                        }
                            break;
                        case "TAKE_MONEY":
                            printer += "TAKE $"+ configModel.green_card_detail.get(card_id).getmoney()+", ";
                            break;
                        case "RANDOM_EVENT":
                        {
                            printer += "RANDOM EVENT, ";
                            
                            
                        }
                        break;                    
                        default:
                        {
                            printer += " "+configModel.accessEvent.getEventname(Eventdemo.get(event[i]))+", ";
                        }
                            break; 
                    }
                    
                }
                  
                
             }
             
             else
             {
                     printer = " NO EVENT ";
             }
                  System.out.println("card:"+configModel.green_card_detail.get(card_id).getCard_id()
                         +" > " +
                         configModel.green_card_detail.get(card_id).getCard_name() 
                         +" ("+
                         printer 
                         +""+ 
                         scroll
                         +")");
             }
             
                 break;
             default:
             
         }
     }

}