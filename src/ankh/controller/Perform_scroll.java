/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.controller;


import ankh.view.Eventhandler;
import ankh.view.configModel;
import static ankh.controller.Card_Event_Action.map;
import static ankh.saveloadfile.SaveGame.rg_b;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * All Scroll events of green card handler
 * @version 2.0
 * @author vishal
 */
public class Perform_scroll {

    int player = configModel.turn_counter - 1;
    Eventhandler card_event = new Eventhandler();
    
    public Perform_scroll()
    {
        
    }
    
    /**
     * This is main action to perform scroll event by passing card id.
     * @param card_id card id for which you want to perform scroll event
     * @return boolean
     */
    public boolean perform_green_scroll(String card_id) {
        boolean scroll_action = false;
        switch (card_id) {
            case "1": {
            //Mr Boggies
                // take $2 from every other palyers if possible
                player = configModel.turn_counter - 1;
                for (int from_player = 0; from_player < configModel.no_of_players; from_player++) {
                    if (from_player != player) {
                        card_event.player_trasanction(from_player, player, 2);
                    }

                }
                scroll_action = true;

            }
            break;
            case "2": {
            // Mr. Bent
                // take $10 loan from bank
                // at the end game pay back $12 or loss 15 points
                boolean result = card_event.bank_transaction(player, 10, "withdraw");
                if(result)
                {
                    System.out.println("You take $10 loan from bank");
                    CurrentPlayer.currentPlayer.set_loan(1);
                }
                scroll_action = true;
            }
            break;
            case "3": {
            // Beggres Guide
                // select one of player
                // display option of card 
                // choose two card give it to current player
                 int next_player = card_event.other_player_chooser();
                List<Integer> n_green_card = configModel.Player_detail_list.get(next_player-1).getplayer_green_card();
                boolean loop=true;
                do
                {
                     try {
                         System.out.println("Select 2 card you want from the player :");
                         for(int i=0;i<n_green_card.size();i++)
                         {
                             System.out.println(n_green_card.get(i)+" :="+configModel.green_card_detail.get(n_green_card.get(i)-1).getCard_name());
                             
                         }
                         System.out.println("enter card number 1");
                         int f_card = Integer.parseInt(configModel.br.readLine());
                         System.out.println("enter card number 2");
                         int s_card = Integer.parseInt(configModel.br.readLine());
                         if(n_green_card.contains(f_card) && n_green_card.contains(s_card))
                         {
                             List<Integer> c_green_card = configModel.Player_detail_list.get(player).getplayer_green_card();
                             configModel.Player_detail_list.get(next_player-1).remove_green_card(f_card);
                             configModel.Player_detail_list.get(next_player-1).remove_green_card(s_card);
                             configModel.Player_detail_list.get(player).setplayer_green_card(f_card);
                             configModel.Player_detail_list.get(player).setplayer_green_card(s_card);
                             configModel.Player_detail_list.get(next_player-1).setplayer_green_card(configModel.greencard_random_number.get(0));
                             configModel.discarded_card.add(configModel.greencard_random_number.get(0));
                             configModel.greencard_random_number.remove(0);
                             configModel.Player_detail_list.get(next_player-1).setplayer_green_card(configModel.greencard_random_number.get(0));
                             configModel.discarded_card.add(configModel.greencard_random_number.get(0));
                             configModel.greencard_random_number.remove(0);
                             loop = false;
                             scroll_action = true;
                         }
                         else
                         {
                             System.out.println("please select correct card");
                         }    } catch (IOException ex) {
                         Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }while(loop);
            
                
            }
            break;
            case "4": {
                /**
                 * The Bank of ankh-more pork // same as card 2 
                 * take $10 loan
                 * from bank at the end game pay back $12 or loss 15 points
                 */
                 boolean result = card_event.bank_transaction(player, 10, "withdraw");
                if(result)
                {
                    System.out.println("You take $10 loan from bank");
                    CurrentPlayer.currentPlayer.set_loan(1);
                }
                scroll_action = true;
            }
            break;
            case "5": {
                /**
                 * ankh more pork sunshine dragon sanctuary each player will
                 * give you $1 OR each player give you one card
                 */
                // give $1
                for (int from_player = 0; from_player < configModel.no_of_players; from_player++) {
                    if (from_player != player) {
                        card_event.player_trasanction(from_player, player, 1);
                    }

                }
                scroll_action = true;
            }
            break;
            case "6": {
                /**
                 * segeant anuga No action
                 */
            }
            break;
            case "7": {
                /**
                 * Agony aunts No Action
                 */
            }
            break;
            case "8": {
                // Area 10 isle of gods
                /**
                 * The Dysk Earn $1 for each minion isle of god area id -> 10
                 */
                rg_b = configModel.Area_detail_list.get(10).get_rectgrid_obj();
                int temp_check = 0;
                for (int grid = 0; grid < 24; grid++) {

                    if (rg_b[grid].enable && (rg_b[grid].type.equalsIgnoreCase("minion"))) {
                        temp_check++;

                    }
                }
                card_event.bank_transaction(player, temp_check, "withdraw");
                scroll_action = true;
            }
            break;
            case "9":  {
            try {
                /**
                 * the Duckman move one of minion of another player and move it
                 * to adjacent
                 */
                // select next player
                int next_player = card_event.other_player_chooser();
                List<Integer> minion_area = card_event.get_item_area(next_player-1, "minion");
                System.out.println("Select area from which you want to move minion");
                for(int i=0;i<minion_area.size();i++)
                {
                    System.out.println(minion_area.get(i)+" :="+ configModel.Area_detail_list.get(minion_area.get(i)-1).getarea_name());
                }
                int select_card = Integer.parseInt(configModel.br.readLine());
                
                System.out.println("select an adjacent area in which you want to move minion.");
                int[] adjacent_temp = configModel.Area_detail_list.get(select_card-1).getadjacent();
                  for(int i=0;i<adjacent_temp.length;i++)
                {
                    System.out.println(adjacent_temp[i]+" :="+ configModel.Area_detail_list.get(adjacent_temp[i]-1).getarea_name());
                }
                 int move_area =  Integer.parseInt(configModel.br.readLine());
                card_event.move_item(next_player-1, select_card-1, move_area-1, "minion");
                scroll_action = true;
            } catch (IOException ex) {
                Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            break;
            case "10": {
                /**
                 * DrumKnott Play any other two cards from your hand
                 */
                
            }
            break;
            case "11": {
                /**
                 * CMOT Dibbler roll die if 7 or more -> get $4 if 1 -> pay $2
                 * or remove one minion all other no effect
                 */
                configModel.dice_random_number = map.sufflecards(configModel.dice_random_number);
                int f_rnum = configModel.dice_random_number.get(0);
                if (f_rnum >= 7) {
                    card_event.bank_transaction(player, 4, "withdraw");
                } else if (f_rnum == 1) {
                    boolean result = card_event.bank_transaction(player, 2, "deposit");
                    if (!result) {
                        //remove minion
                        List<Integer> minion_area = new ArrayList<Integer>();
                        minion_area = card_event.get_item_area(player, "minion");
                        if (minion_area.get(0) != null) {
                            card_event.remove_item(player, minion_area.get(0), "minion");
                        } else {
                            System.out.println("You have no any minion in bord");
                        }
                    }
                    scroll_action = true;
                }
            }
            break;
            case "12":
            {  /** Dr Cruces NO ACTION **/ }
            break;
            case "13":
            {  /** Captain Carrot NO ACTION **/ }
            break;
            case "14":
            {
            try {
                /** Mrs Cake
                 * look at all but one of the unused personality cards.
                 */
                boolean flag = true;
               do
               {
                System.out.println("select one of the below unused personality card number");
                for(int i=0;i<configModel.secret_Random_number.size();i++)
                {
                    System.out.println(configModel.secret_card_detail.get(configModel.secret_Random_number.get(i)).id);
                }
                int secret = Integer.parseInt(configModel.br.readLine());
                if(secret == 2 ||secret == 3 ||secret == 7 )
                {
                    System.out.println("Card Name: "+ configModel.secret_card_detail.get(secret-1).get_title());
                    System.out.println("Discription : "+ configModel.secret_card_detail.get(secret-1).get_desc());
                    flag = false;
                    scroll_action = true;
                }
                else
                {
                    System.out.println("Enter valid card numnuber");
                }
               }while(flag);
            } catch (IOException ex) {
                Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
            break;
            case "15":
            {  /** Groat Carrot NO ACTION **/ }
            break;
            case "16":
            {  /** Gimlet's Dwarf Delicataessn NO ACTION **/ }
            break;
            case "17":
            {  /** Gaspode INTRUPPT
             * NO ACTION **/ }
            break;
            case "18":
            {  /** Fresh Strart Club
             * NO ACTION **/ }
            break;
            case "19":
            {  /** Foul Ole Ron
                    same as Duckman
                **/
                // select next player
                 try {
                /**
                 * the Duckman move one of minion of another player and move it
                 * to adjacent
                 */
                // select next player
                int next_player = card_event.other_player_chooser();
                List<Integer> minion_area = card_event.get_item_area(next_player-1, "minion");
                System.out.println("Select area from which you want to move minion");
                for(int i=0;i<minion_area.size();i++)
                {
                    System.out.println(minion_area.get(i)+" :="+ configModel.Area_detail_list.get(minion_area.get(i)-1).getarea_name());
                }
                int select_card = Integer.parseInt(configModel.br.readLine());
                
                System.out.println("select an adjacent area in which you want to move minion.");
                int[] adjacent_temp = configModel.Area_detail_list.get(select_card-1).getadjacent();
                  for(int i=0;i<adjacent_temp.length;i++)
                {
                    System.out.println(adjacent_temp[i]+" :="+ configModel.Area_detail_list.get(adjacent_temp[i]-1).getarea_name());
                }
                 int move_area =  Integer.parseInt(configModel.br.readLine());
                card_event.move_item(next_player-1, select_card-1, move_area-1, "minion");
                scroll_action = true;
            } catch (IOException ex) {
                Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            break;
            case "20":
            {  /** Fool's guide 
                **/
                // select next player
                int next_player = card_event.other_player_chooser();
                List<Integer> minion_area = card_event.get_item_area(player, "minion");
                int[] adjacent_temp = configModel.Area_detail_list.get(minion_area.get(0)).getadjacent();
                card_event.move_item(next_player, minion_area.get(0), adjacent_temp[0], "minion");
                scroll_action = true;
            }
            break;
            case "21":
            {  /** Fire Brigade 
                **/
                // $5 from player
                int next_player = card_event.other_player_chooser();
               
                boolean result = card_event.player_trasanction(next_player-1,player, 5);
                if(!result)
                {
                     System.out.println("player " +configModel.Player_detail_list.get(next_player-1).getplayer_name() + "can not able to give you $5 so you can remove his building");
                    // remove his bulding 
                    List<Integer> building_area = card_event.get_item_area(next_player-1, "building");
                    if(building_area.size()>0)
                    {
                         try {
                             for(int i=0;i<building_area.size();i++)
                             {
                                 System.out.println(building_area.get(i)+" =: " + configModel.Area_detail_list.get(building_area.get(i)).getarea_name());
                             }
                             
                             int area_nu = Integer.parseInt(configModel.br.readLine());
                             boolean res = card_event.remove_item(next_player-1,area_nu,"building");
                         } catch (IOException ex) {
                             Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
                         }
                         scroll_action = true;
                    }
                    else
                    {
                        System.out.println("player "+ configModel.Player_detail_list.get(next_player-1).getplayer_name() + "dont have building");
                        scroll_action = true;
                    }
                }
                else
                {
                    System.out.println("you got $5 from the player  "+configModel.Player_detail_list.get(next_player-1).getplayer_name() );
                    scroll_action = true;
                }
                
            }
            break;
            case "22":
            {  /** 
             * Inigo Skimmer NO ACTION 
             **/ }
            break;
            case "23":
            {   /** History monks
                */
                int size=configModel.discarded_card.size();
                if(size<4)
                {
                    System.out.println("the size of the discarded plie is less than 4 Sorry");
                    scroll_action=true;
                }
                else
                {
                    for(int i=0;i<4;i++)
                    {
                        int card_number=configModel.discarded_card.get(0);
                        configModel.discarded_card.remove(0);
                        configModel.Player_detail_list.get(configModel.turn_counter-1).player_green_card.add(card_number);
                    }
                    scroll_action=true;
                }
                
            }
            break;
            case "24":
            {/** Hex
                *  take three card from draw deck
                */
                for(int i=0;i<3;i++)
                {
                    int gcard = configModel.greencard_random_number.get(0);
                    configModel.green_card_detail.remove(0);
                    configModel.Player_detail_list.get(player).setplayer_green_card(gcard);
                    
                }
                scroll_action=true;
            }
            break;
            case "25":
            {/** Here n Now
                */
                System.out.println("Die number is 1");
                List<Integer> areas = card_event.get_item_area(player,"minion");
                card_event.remove_item(player,areas.get(0), "minion");
                
            }
            break;
            case "26":
            {/** Harry King
             * Discard as many card as you wish and take
             * $2 for each discarded
                */
                System.out.println("How many cards you want to discard ?");
            try {
                String no_of_cards=(new BufferedReader(new InputStreamReader(System.in))).readLine();
                int cards=Integer.parseInt(no_of_cards);
                for(int i=0;i<cards;i++)
                {
                    int card_number=configModel.Player_detail_list.get(configModel.turn_counter-1).player_green_card.get(0);
                    configModel.discarded_card.add(card_number);
                    configModel.Player_detail_list.get(configModel.turn_counter-1).player_green_card.remove(0);
                    card_event.bank_transaction(player, 2,"withdraw");
                }
                scroll_action=true;
            } catch (IOException ex) {
                Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
            }
//                List<Integer> pcards = configModel.Player_detail_list.get(player).getplayer_green_card();
//                configModel.Player_detail_list.get(player).remove_green_card(pcards.get(0));
            }
            break;
            case "27":
            {/** hargs houes NO ACTION
                */
                
            }
            break;
            case "28":
            {/** Mr Gyle NO ACTION
                */
            }
            break;
            case "29":
            {/** The Peeled Nuts NO ACTION
                */
            }
            break;
            case "30":
            {/** The Opera House 
             * Earn $1 for each minion isle of god area id -> 10.
             * same as  8
                */
                 rg_b = configModel.Area_detail_list.get(10).get_rectgrid_obj();
                int temp_check = 0;
                for (int grid = 0; grid < 24; grid++) {

                    if (rg_b[grid].enable && (rg_b[grid].type.equalsIgnoreCase("minion"))) {
                        temp_check++;

                    }
                }
                card_event.bank_transaction(player, temp_check, "withdraw");
                scroll_action = true;
                
            }
            break;
            case "31":
            {/** NoBoddy Nobby 
             * take $3 from player of your choice
                */
                System.out.println("Take $3 from next player");
                int next_player = card_event.other_player_chooser();
                boolean result = card_event.player_trasanction(next_player-1, player, 3);
                if(result)
                {
                    System.out.println("Player "+player+" got $3 from player"+next_player);
                }
                else
                {
                    System.out.println("Player "+next_player +"has not enough money");
                }  
                scroll_action = true;
                    
            }
            break;
            case "32":
            {/** MODO
             *  Discard one card
                */
                boolean repet = true;
                 List<Integer> cards=configModel.Player_detail_list.get(player).getplayer_green_card();
                 do
                 {
                    try {
                        System.out.println("select onr of card to discard it");
                        for(int i=0;i<cards.size();i++)
                        {
                            if(cards.get(i) != CurrentPlayer.card_id)
                            System.out.println(cards.get(i)+" :="+configModel.green_card_detail.get(cards.get(i)-1).getCard_name());
                        }
                        int selec_card = Integer.parseInt(configModel.br.readLine());
                        
                        if(cards.contains(selec_card))
                        {
                            configModel.discarded_card.add(selec_card);
                            configModel.Player_detail_list.get(player).remove_green_card(selec_card);
                            scroll_action=true;
                            repet = false;
                        }
                        else
                        {
                            System.out.println("enter correct number");
                        }  } catch (IOException ex) {
                        Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 }while(repet);
            }
            break;
            case "33":
            {/** The Mended Drum NO Action
                */
                 scroll_action=true;
            }
            break;
            case "34":
            {/** Librarian
             * take four card from deck
                */
                for(int i=0;i<4;i++)
                {
                    int gcard = configModel.greencard_random_number.get(0);
                    configModel.green_card_detail.remove(0);
                    configModel.Player_detail_list.get(player).setplayer_green_card(gcard);
                    
                }
                scroll_action=true;
                
            }
            break;
            case "35":
            {/** Leonard of quirm
             * take four card from deck
                */
                for(int i=0;i<4;i++)
                {
                    int gcard = configModel.greencard_random_number.get(0);
                    configModel.green_card_detail.remove(0);
                    configModel.Player_detail_list.get(player).setplayer_green_card(gcard);
                    
                }
                scroll_action=true;
            }
            break;
           case "36":
            {/** Shonky Shop
             * discard card as many as u wish
             * and take $1 for each discarded 
                */
                System.out.println("How many cards you want to discard ?");
            try {
                String no_of_cards=(new BufferedReader(new InputStreamReader(System.in))).readLine();
                int cards=Integer.parseInt(no_of_cards);
                for(int i=0;i<cards;i++)
                {
                    int card_number=configModel.Player_detail_list.get(configModel.turn_counter-1).player_green_card.get(0);
                    configModel.discarded_card.add(card_number);
                    configModel.Player_detail_list.get(configModel.turn_counter-1).player_green_card.remove(0);
                    card_event.bank_transaction(player, 1,"withdraw");
                }
                scroll_action=true;
            } catch (IOException ex) {
                Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
            break;
           case "37":
            {/** Sacharissa Cripslock
             * Earn $1 for each trouble maker
                */
                List<Integer> temp_trouble = new ArrayList<Integer>();

                    temp_trouble = card_event.get_item_area(9,"trouble");
                    int total_earn = temp_trouble.size();
                    boolean result = card_event.bank_transaction(player, total_earn,"withdraw");
                    if(result)
                    {
                        System.out.println("Player "+player+" Earn $"+total_earn);
                    }
            }
            break;
            case "38":
            {/** Roisen Palm 
             * choose pleyer give one card and take $2
                */
                  int next_player = card_event.other_player_chooser();
                 List<Integer> n_green_card = configModel.Player_detail_list.get(player).getplayer_green_card();
                boolean loop=true;
                do
                {
                     try {
                         System.out.println("Select 1 card you want to give player : " +configModel.Player_detail_list.get(next_player-1).getplayer_name());
                         for(int i=0;i<n_green_card.size();i++)
                         {
                             if(n_green_card.get(i)!= CurrentPlayer.card_id)
                             System.out.println(n_green_card.get(i)+" :="+configModel.green_card_detail.get(n_green_card.get(i)-1).getCard_name());
                             
                         }
                         System.out.println("enter card number 1");
                         int f_card = Integer.parseInt(configModel.br.readLine());
                        
                         if(n_green_card.contains(f_card))
                         {
                             configModel.Player_detail_list.get(next_player-1).setplayer_green_card(f_card);
                             configModel.Player_detail_list.get(player).remove_green_card(f_card);
                             if(card_event.player_trasanction(next_player-1, player, 2))
                             {
                                 System.out.println("you get $2 from the player "+ configModel.Player_detail_list.get(next_player-1).getplayer_name());
                                    loop = false;
                                    scroll_action = true;
                                    break;
                             }
                             
                           
                         }
                         else
                         {
                             System.out.println("please select correct card");
                         }    
                     } catch (IOException ex) {
                         Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }while(loop);
                
            }
            break;   
            case "39":
            {/** Rince wind
             * move your minion from containing trouble 
             * to adjacent area
             **/
             boolean repeat = true;
             List<Integer> minion_area = card_event.get_item_area(player,"minion"); 
             List<Integer> contain = new ArrayList<Integer>();
             int[] temp_area;
             for(int i=0;i<minion_area.size();i++)
             {
                 boolean check = card_event.check_item_in_area(minion_area.get(i),"trouble");
                 if(check)
                 {
                     contain.add(minion_area.get(i));
                 }
             }
             
             do{
                 int u_input = 0;
                 int a_input = 0;
                 System.out.println("Select one of area from you want to move your minion");
                 for(int i=0;i<contain.size();i++)
                 {
                     System.out.println(contain.get(i)+" ="+configModel.Area_detail_list.get(contain.get(i)-1).getarea_name());
                 }
                 try {
                      u_input = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
                 } catch (IOException ex) {
                     Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 if(contain.contains(u_input))
                 {
                     repeat = false;
                 }
                 else
                 {
                    System.out.println("Opps You choose wrong option !");
                 }
                 
                 if(!repeat)
                 {  
                     temp_area = configModel.Area_detail_list.get(u_input-1).getadjacent();
                      System.out.println("Select one of adjcent area to move your minion");
                     for(int i=0;i<temp_area.length;i++)
                     {
                         System.out.println(temp_area[i]+" ="+configModel.Area_detail_list.get(temp_area[i]-1).getarea_name());
                         
                     }
                      try {
                                a_input = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
                           } catch (IOException ex) {
                               Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           if(Arrays.asList(temp_area).contains(a_input))
                           {
                               card_event.move_item(player, u_input,a_input,"minion");
                               repeat = false;
                               scroll_action=true;
                           }
                           else
                           {
                               repeat = true;
                               System.out.println("You choose wrong option");
                           }
                     
                 }
                 
             }while(repeat);
             
             
            }
            break; 
            case "40":
            {/** The Royal Mint NO ACTION
                */
                 scroll_action=true;
            }
            break;         
            case "41":
            {try {
                /** HQueen Molly
                 * select player they will give you two card of their choice
                 */
                
                int next_player = card_event.other_player_chooser();
                List<Integer> n_green_card = configModel.Player_detail_list.get(next_player-1).getplayer_green_card();
                boolean loop=true;
                do
                {
                System.out.println("Select 2 card you want from the player :");
                for(int i=0;i<n_green_card.size();i++)
                {
                    System.out.println(n_green_card.get(i)+" :="+configModel.green_card_detail.get(n_green_card.get(i)-1).getCard_name());
                    
                }
                        System.out.println("enter card number 1");
                int f_card = Integer.parseInt(configModel.br.readLine());
                        System.out.println("enter card number 2");
                int s_card = Integer.parseInt(configModel.br.readLine());
                if(n_green_card.contains(f_card) && n_green_card.contains(s_card))
                {
                 List<Integer> c_green_card = configModel.Player_detail_list.get(player).getplayer_green_card();
                configModel.Player_detail_list.get(next_player-1).remove_green_card(f_card);
                configModel.Player_detail_list.get(next_player-1).remove_green_card(s_card);
                configModel.Player_detail_list.get(player).setplayer_green_card(f_card);
                configModel.Player_detail_list.get(player).setplayer_green_card(s_card);
                configModel.Player_detail_list.get(next_player-1).setplayer_green_card(configModel.greencard_random_number.get(0));
                configModel.discarded_card.add(configModel.greencard_random_number.get(0));
                configModel.greencard_random_number.remove(0);
                configModel.Player_detail_list.get(next_player-1).setplayer_green_card(configModel.greencard_random_number.get(0));
                configModel.discarded_card.add(configModel.greencard_random_number.get(0));
                configModel.greencard_random_number.remove(0);
                loop = false;
                scroll_action = true;
                }
                else
                {
                    System.out.println("please select correct card");
                }
                }while(loop);
            } catch (IOException ex) {
                Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            break;  
            case "42":
            {/** Pink Pussy cat Club NO ACTION
                */
                
            }
            break; 
            case "43":
            {/** Zorgo the Retro phrenologist 
             * Exchange personality card from dack randomly
                */
                 try {
                /** Mrs Cake
                 * look at all but one of the unused personality cards.
                 */
                boolean flag = true;
               do
               {
                System.out.println("select one of the below unused personality card number");
                for(int i=0;i<configModel.secret_Random_number.size();i++)
                {
                    System.out.println(configModel.secret_card_detail.get(configModel.secret_Random_number.get(i)).id);
                }
                int secret = Integer.parseInt(configModel.br.readLine());
                if(secret == 2 ||secret == 3 ||secret == 7 )
                {
                    configModel.Player_detail_list.get(player).setplayer_secretcard(secret-1);
                    System.out.println("Now you have new secret card " + configModel.secret_card_detail.get(secret-1).get_title());
                   
                    System.out.println("Card Name: "+ configModel.secret_card_detail.get(secret-1).get_title());
                    System.out.println("Discription : "+ configModel.secret_card_detail.get(secret-1).get_desc());
                    for(int i=0;i<configModel.secret_Random_number.size();i++)
                    {
                        if(configModel.secret_Random_number.get(i)== secret-1)
                        {
                            configModel.secret_Random_number.remove(i);
                        }
                        
                    }
                    flag = false;
                    scroll_action = true;
                }
                else
                {
                    System.out.println("Enter valid card numnuber");
                }
               }while(flag);
            } catch (IOException ex) {
                Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
              
            }
            break; 
            case "44":
            {/** Dr. Whiteface
             * 
                */
                
            }
            break; 
            case "45":
            {/** Wallace Sonky No Action 
                */
                
            }
            break; 
            case "46":
            {/** The SeamStresses Guild 
             * Choose player give one card will give you $2
                */
                int next_player = card_event.other_player_chooser();
                 List<Integer> n_green_card = configModel.Player_detail_list.get(player).getplayer_green_card();
                boolean loop=true;
                do
                {
                     try {
                         System.out.println("Select 1 card you want to give player : " +configModel.Player_detail_list.get(next_player-1).getplayer_name());
                         for(int i=0;i<n_green_card.size();i++)
                         {
                             if(n_green_card.get(i)!= CurrentPlayer.card_id)
                             System.out.println(n_green_card.get(i)+" :="+configModel.green_card_detail.get(n_green_card.get(i)-1).getCard_name());
                             
                         }
                         System.out.println("enter card number 1");
                         int f_card = Integer.parseInt(configModel.br.readLine());
                        
                         if(n_green_card.contains(f_card))
                         {
                             configModel.Player_detail_list.get(next_player-1).setplayer_green_card(f_card);
                             configModel.Player_detail_list.get(player).remove_green_card(f_card);
                             if(card_event.player_trasanction(next_player-1, player, 2))
                             {
                                 System.out.println("you get $2 from the player "+ configModel.Player_detail_list.get(next_player-1).getplayer_name());
                                    loop = false;
                                    scroll_action = true;
                                    break;
                             }
                             
                           
                         }
                         else
                         {
                             System.out.println("please select correct card");
                         }    
                     } catch (IOException ex) {
                         Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }while(loop);
                
            }
            break; 
            case "47":
            {
                /** NoEVENT
                 * 
                 */
            }
            break; 
            case "48":
            {/** The Theive Guild 
             * take $2 from every player same as Card1
                */
                 player = configModel.turn_counter - 1;
                for (int from_player = 0; from_player < configModel.no_of_players; from_player++) {
                    if (from_player != player) {
                        card_event.player_trasanction(from_player, player, 2);
                    }

                }
                scroll_action = true;
                
            }
            break; 
                        
                            
                                
            default:
            {
                scroll_action = false;
            }
        }
        return scroll_action;
    }
      /**
     * This is main action to perform scroll event by passing card id.
     * @param card_id card id for which you want to perform scroll event
     * @return boolean
     */
    public boolean perform_brown_scroll(String card_id) throws IOException {
        boolean scroll_action = false;
         switch (card_id) {
        case "1":
            {
                /** 
                 * Sergeant cheery little bottom
                 * Take two cards from two deck
                 */
                
                
            }
            break;
        case "2":
            {
                /**
                 *  Otto Chriek
                 * Earn $1 for each trouble maker on bord
                 */
                List<Integer> temp_trouble = new ArrayList<Integer>();

                    temp_trouble = card_event.get_item_area(9,"trouble");
                    int total_earn = temp_trouble.size();
                    boolean result = card_event.bank_transaction(player, total_earn,"withdraw");
                    if(result)
                    {
                        System.out.println("Player "+player+" Earn $"+total_earn);
                        scroll_action = true;
                    }
            }
            break;
        case "3":
            {
                /** The Clacks
                 *  Take two cards from two deck
                 */
            }
            break;
        case "4":
            {
                /** Sargent Colon
                 * NoEVENT
                 **/
            }
            break;
        case "5":
            {
                /** Cosmo Lavish
                 * 
                 */
            }
            break;
        case "6":
            {
                /** The Dean
                 *  remove one minion from unreal Estate(2)
                 */
                boolean repeat = true;
                int u_inp = 0;
                List<Integer> player_list = card_event.get_playerids_item_in_area(2,"minion");
                do{
                    System.out.println("Select Player of whom minion you want to remove?");
                    for(int i=0;i<player_list.size();i++)
                    {
                        System.out.println(player_list.get(i)+ " "+configModel.Player_detail_list.get(player_list.get(i)).player_name);
                    }
                    try {
                        u_inp = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
                    } catch (IOException ex) {
                        Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(player_list.contains(u_inp))
                    {
                        card_event.remove_item(u_inp,2,"minion");
                        scroll_action = true;
                        repeat = false;
                    }
                    else
                    {
                        repeat = true;
                        System.out.println("oops you choose wrong option !");
                    }
                }while(repeat);
            }
            break;
        case "7":
            {
                /** HELLO NO_EVENT
                 */
                
            }
            break;
        case "8":
            {   /** Burleigh & stron..
                 * pay $2 player of of your choice 
                 * than choose minion to assasinate
                 */
                int player_list = configModel.no_of_players;
                int inp_id = 0;
                int m_area = 0;
                List<Integer> areaids = new ArrayList<Integer>();
                List<Integer> pids = new ArrayList<Integer>();
                boolean repeat = true;
                do{
                for (int i = 0; i < configModel.no_of_players; i++) {
                    pids.add(configModel.Player_detail_list.get(i).getid());
                    System.out.println(configModel.Player_detail_list.get(i).getid() + " =:" + configModel.Player_detail_list.get(i).getplayer_name());
                }
                try {
                    inp_id = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
                } catch (IOException ex) {
                    Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(pids.contains(inp_id))
                {
                     List<Integer> p_minion = card_event.get_item_area(inp_id,"minion");
                     for (int i = 0; i < p_minion.size(); i++) {
                         System.out.println(p_minion.get(i) + " =:" + configModel.Area_detail_list.get(p_minion.get(i)));
                     }
                    try {
                        m_area = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
                        boolean res = card_event.remove_item(player,m_area,"minion");
                        if(res)
                        {
                            card_event.player_trasanction(player,inp_id,2);
                            repeat = false;
                            scroll_action = true;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Perform_scroll.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                else
                {
                    System.out.println("Oppes you choose wrong option !");
                }
                }while(repeat);
                
                
            }
            break;
        case "9":
            {
                /** The Bursar
                 * Exchange position any two minion on bord
                 * 
                 */
            }
            break;
        case "10":
            {
                /** Cable street particulars
                 * select one player look his card 
                 * choose one of them to be discarded
                 */
            }
            break;
        case "11":
            {
                /** Canting crew
                 * move one minion belonging to another player 
                 *  from one area to adjacent area
                 */
                int curr_player = configModel.turn_counter;
                List<Integer> mareas = new ArrayList<Integer>();
                List<Integer> plist = new ArrayList<Integer>();
                for(int i=0;i<configModel.no_of_players;i++)
                {
                    if(curr_player != i)
                    {
                       mareas = card_event.get_item_area(player, "minion");
                       if(mareas.size() > 0)
                       {
                           plist.add(i);
                       }
                    }
                    mareas.clear();
                }
                
                int splayer_id = card_event.other_player_chooser();
                List<Integer> s_area = card_event.get_item_area(splayer_id, "minion");
                int[] ad_area = configModel.Area_detail_list.get(s_area.get(0)).adjacent;
                card_event.move_item(splayer_id, s_area.get(0), ad_area[0],"minion");
                scroll_action = true;
            }
            break;
        case "12":
            {
                /** Carcer
                 * Roll twice and remove one minion of your choice from those
                 * area even if there is no trouble there
                 */
                boolean repeat = true;
                boolean f_option = false;
              do{
                configModel.dice_random_number = map.sufflecards(configModel.dice_random_number);
                int f_rnum = configModel.dice_random_number.get(0);
                int s_rnum = configModel.dice_random_number.get(1);
                if(!f_option)
                {
                    System.out.println("Dies number is "+f_rnum);
                    List<Integer> m_list = card_event.get_playerids_item_in_area(f_rnum,"minion");
                    if(m_list.size() == 0)
                    {
                        System.out.println("No minion found in Area"+f_rnum);
                    }
                    else
                    {
                        System.out.println("Which player's minion you want to remove? ");
                        for(int i=0;i<m_list.size();i++)
                        {
                            System.out.println(m_list.get(i)+" "+ configModel.Player_detail_list.get(m_list.get(i)).player_name);
                        }
                        int inp_id = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
                        if(m_list.contains(inp_id))
                        {
                            f_option = true;
                            card_event.remove_item(inp_id,f_rnum,"minion");
                        }
                        else
                        {
                            System.out.println("You choose wrong option");
                        }
                    }
                }
               // SECOND AREA 
            if(f_option)
            {
                        List<Integer> sm_list = card_event.get_playerids_item_in_area(s_rnum,"minion");
                if(sm_list.size() == 0)
                {
                    System.out.println("No minion found in Area"+s_rnum);
                }
                else
                {
                    System.out.println("Which player's minion you want to remove? ");
                    for(int i=0;i<sm_list.size();i++)
                    {
                        System.out.println(sm_list.get(i)+" "+ configModel.Player_detail_list.get(sm_list.get(i)).player_name);
                    }
                    int sinp_id = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
                    if(sm_list.contains(sinp_id))
                    {
                        repeat = false;
                        scroll_action = true;
                        card_event.remove_item(sinp_id,s_rnum,"minion");
                    }
                    else
                    {
                        System.out.println("You choose wrong option");
                    }
                 }
                }
              }while(repeat);
                
                
            }
            break;
            case "13":
            {
                /**
                 * The Chair of indefinite studies
                 * exchange your hand with that of another player
                 */
                
            }
            break;
            case "14":
            {
                /**
                 * Sir Charles Lavatory
                 * Earn $1 for each building
                 */
                int total_build = 0;
                for(int i=0;i<configModel.no_of_players;i++)
                {
                    total_build += configModel.cityarea_card;
                }
                int remain_build = 24-total_build;
                card_event.bank_transaction(player,remain_build,"withdraw");
                scroll_action = true;
            }
            break;
            case "15":
            {
                /**
                 * Dorfl1
                 *  move one of your minion from one area to other area
                 */
                List<Integer> min_area = card_event.get_item_area(player,"minion");
                System.out.println("Choose one of area");
                for(int i=0;i<min_area.size();i++)
                {
                    System.out.println(min_area.get(i)+" "+configModel.Area_detail_list.get(min_area.get(i)).area_name);
                }
                
            }
            break;
        default:{
            scroll_action = true;
        }
        
        
        
    }
         return scroll_action;
    }
}
