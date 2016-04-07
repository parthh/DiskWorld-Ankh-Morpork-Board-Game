/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.controller;

import ankh.view.Eventhandler;
import ankh.view.MapcreateView;
import ankh.view.configModel;
import static ankh.saveloadfile.SaveGame.doc;
import static ankh.saveloadfile.SaveGame.rg_b;
import ankh.view.configModel.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.lang.Math.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
  * All Card event action handler implements an application 
 * that handle all Green border cards and Brown border cards
 * event actions.
 * @author vishal
 */
public class Card_Event_Action {

    boolean isTroubleMarkerThere = false;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    boolean action_complete = false;
    boolean random_complete = false;
    int selected_area_id;
    public static MapcreateView map = new MapcreateView();
    Eventhandler eh = new Eventhandler();
    Perform_scroll perform = new Perform_scroll();

    public boolean perform_action(String action) throws IOException {
        int player = configModel.turn_counter - 1;
        int selected_area;
        boolean loop = true;
        switch (action) {
            case "PLACE_MINION": {
                List<Integer> minion_area;
                List<Integer> adjacent_list;
                int total_minion = configModel.Player_detail_list.get(player).getplayer_minion();
                //get all area containing minion   
                minion_area = eh.get_item_area(player, "minion");
                // get all adjcent area
                adjacent_list = eh.get_adjacent_area(minion_area);
                
                int select_ownMinion;
                boolean result = false;
                // give option to user to select area
                do {
                    if (total_minion == 0) {

                        System.out.println("Select Area From which you want to take minion");
                        for (int i = 0; i < minion_area.size(); i++) {
                            System.out.println(minion_area.get(i) + ". " + configModel.Area_detail_list.get(minion_area.get(i) - 1).getarea_name());
                        }
                        select_ownMinion = Integer.parseInt(br.readLine());

                        System.out.println("Select one of Area to place minion :");
                        for (int i = 0; i < adjacent_list.size(); i++) {
                            System.out.println(adjacent_list.get(i) + " > " + configModel.Area_detail_list.get(adjacent_list.get(i) - 1).getarea_name());
                        }
                        selected_area = Integer.parseInt(br.readLine());
                        if (adjacent_list.contains(selected_area) && minion_area.contains(select_ownMinion)) {
                            // boolean result = set_item(player, (selected_area - 1), "minion");
                            result = eh.move_item(player, (select_ownMinion - 1), (selected_area - 1), "minion");
                            if (result) {
                                loop = false;
                            }
                        } else {
                            System.out.println("Please Choose Correct Option");
                            loop = true;
                        }

                    } else {

                        System.out.println("Select one of Area to place minion :");
                        for (int i = 0; i < adjacent_list.size(); i++) {
                            System.out.println(adjacent_list.get(i) + " > " + configModel.Area_detail_list.get((adjacent_list.get(i))-1).getarea_name());
                        }
                        selected_area = Integer.parseInt(br.readLine());
                        if (adjacent_list.contains(selected_area)) {
                            result = eh.set_item(player, (selected_area - 1), "minion");
                            if (result) {
                                loop = false;
                            }

                        } else {
                            System.out.println("Please Choose Correct Option");
                            loop = true;
                        }
                    }

                } while (loop);
                // set minion into seleted area and update minion counter

                //check for trouble maker
                /**
                 * Rules: As soon as a minion is moved or removed from an area,
                 * any trouble marker in the area is also removed (even if there
                 * are two or more minions remaining).
                 *
                 */
                if (result) {
                    //check winning condition here
                    action_complete = true;
                }

            }
            break;
            case "PLACE_BUILDING": {
                // check if all six bulding on bord

                ArrayList<Integer> total_area_card = configModel.Player_detail_list.get(player).get_area_card();
                // get area containing minion
                List<Integer> minion_area = new ArrayList<Integer>();
                minion_area = eh.get_item_area(player, "minion");
                int area_cost;
                List<Integer> area_list = new ArrayList<Integer>();
                List<Integer> temp_list = new ArrayList<Integer>();

                if (total_area_card.size() < 6) {

                    // check area not conatain bulding and trouble maker
                    for (int i = 0; i < minion_area.size(); i++) {
                        rg_b = configModel.Area_detail_list.get(minion_area.get(i) - 1).get_rectgrid_obj();
                        int temp_check = 0;
                        for (int grid = 0; grid < 24; grid++) {
                            // add trouble maker here condition <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                            if (rg_b[grid].enable && ((rg_b[grid].type.equalsIgnoreCase("building")) || (rg_b[grid].type.equalsIgnoreCase("trouble")))){
                                temp_check++;

                            }

                        }
                        if (temp_check == 0) {
                            temp_list.add(minion_area.get(i));
                        }
                     
                    }
                    if(temp_list.size()>0)
                    {
                    do {

                        System.out.println("Select area in which you want to put building");
                        for (int i = 0; i < temp_list.size(); i++) {
                            System.out.println(temp_list.get(i) + ". " + configModel.Area_detail_list.get(temp_list.get(i) - 1).getarea_name()
                                    + " Cost $" + configModel.Area_detail_list.get(temp_list.get(i) - 1).getarea_cost()+" card detail is :"+ configModel.Area_detail_list.get(temp_list.get(i) - 1).getarea_detail());
                        }
                        selected_area = Integer.parseInt(br.readLine());
                        if (temp_list.contains(selected_area)) {
                            // get cost of selected area
                            area_cost = configModel.Area_detail_list.get(selected_area - 1).getarea_cost();

                            //check money in players account
                            int player_money = configModel.Player_detail_list.get(player).getplayer_money();
                            if (player_money > area_cost) {
                                // credit to bank account from user's account
                                eh.bank_transaction(player, area_cost, "deposit");
                                //configModel.bank_money = configModel.bank_money + area_cost;
                                //configModel.Player_detail_list.get(player).setplayer_money(player_money - area_cost);
                                // get city area card into account

                                player_money = configModel.Player_detail_list.get(player).getplayer_money();
                                System.out.println("Bank Balance $" + configModel.bank_money);
                                System.out.println("Your have $" + (player_money));

                                boolean result = eh.set_item(player, (selected_area - 1), "building");

                                if (result) {
                                    List<Integer> altemp = eh.get_item_area(player, "building");
                                    for (int i = 0; i < altemp.size(); i++) {
                                        System.out.println("Player" + (player + 1) + " have area " + altemp.get(i));
                                    }
                                    action_complete = true;
                                    loop = false;
                                }

                            } else {
                                System.out.println("Opps you have not enough money to buy this area !");
                                loop = true;
                            }
                        } else {
                            System.out.println("Please Choose Correct Option");
                            loop = true;
                        }

                    } while (loop);
                    }
                    else
                    {
                        System.out.println("You can not put building right now! because of all your minion are contain trouble marker or building");
                        action_complete = true;
                    }
                } else {
                    // move building to another area
                    List<Integer> own_building = new ArrayList<Integer>();
                    own_building = configModel.Player_detail_list.get(player).get_area_card();
                    int select_ownBuilding;

                    // show option that not contian own building troublen ane contain menion
                    for (int i = 0; i < minion_area.size(); i++) {
                        if (!(own_building.contains(minion_area.get(i)))) {
                            rg_b = configModel.Area_detail_list.get(minion_area.get(i) - 1).get_rectgrid_obj();
                            int temp_check = 0;
                            for (int grid = 0; grid < 24; grid++) {

                                if (rg_b[grid].enable && (rg_b[grid].type.equalsIgnoreCase("building") || rg_b[grid].type.equalsIgnoreCase("trouble"))) {
                                    temp_check++;
                                }

                            }
                            if (temp_check == 0) {
                                temp_list.add(minion_area.get(i));
                            }

                        }
                    }

                    do {
                        System.out.println("Select one of your bulding move to another Area");
                        for (int i = 0; i < own_building.size(); i++) {
                            System.out.println(own_building.get(i) + ". " + configModel.Area_detail_list.get(own_building.get(i) - 1).getarea_name());
                        }
                        select_ownBuilding = Integer.parseInt(br.readLine());

                        System.out.println("Select one of area where you want to move: ");
                        for (int i = 0; i < temp_list.size(); i++) {
                            System.out.println(temp_list.get(i) + ". " + configModel.Area_detail_list.get(temp_list.get(i) - 1).getarea_name()
                                    + " Cost $" + configModel.Area_detail_list.get(temp_list.get(i) - 1).getarea_cost() +" card detail is :"+ configModel.Area_detail_list.get(temp_list.get(i) - 1).getarea_detail() );
                        }
                        selected_area = Integer.parseInt(br.readLine());

                        if (temp_list.contains(selected_area)) {
                            // get cost of selected area
                            area_cost = configModel.Area_detail_list.get(selected_area).getarea_cost();
                            //check money in players account
                            int player_money = configModel.Player_detail_list.get(player).getplayer_money();
                            if (player_money > area_cost) {
                                // credit to bank account from user's account
                                eh.bank_transaction(player, area_cost, "deposit");
                                configModel.Player_detail_list.get(player).get_area_card().remove(select_ownBuilding);
                                boolean result = eh.set_item(player, (selected_area - 1), "building");

                                if (result) {
                                    List<Integer> altemp = eh.get_item_area(player, "building");
                                    for (int i = 0; i < altemp.size(); i++) {
                                        System.out.println("Player" + (player + 1) + " have area " + altemp.get(i));
                                    }
                                    action_complete = true;
                                    loop = false;
                                }
                            } else {
                                System.out.println("Opps you have not enough money to buy this area !");
                                loop = true;
                            }
                        } else {
                            System.out.println("Please Choose Correct Option");
                            loop = true;
                        }

                    } while (loop);

                }
            }
            break;

            case "ASSASSINATION": {
                boolean continueLoop = true;
                boolean cheid = false;
                while (continueLoop) {
                    try {
                        ArrayList<Integer> assasinte_player = new ArrayList<Integer>();
                        int player_id = 0;

                        System.out.println("what you want to remove");
                        System.out.println("1 for Minion");
                        System.out.println("2 for Demon");
                        System.out.println("3 for Troll");
                        int choice = Integer.parseInt(br.readLine());
                        if (choice == 1) {
                            do {
                                cheid = false;
                                System.out.println("Please enter the player number whose minion you want to assassinate");
                                for (int i = 0; i < configModel.no_of_players; i++) {
                                    if (configModel.Player_detail_list.get(i).getid() != configModel.turn_counter) {
                                        assasinte_player.add(configModel.Player_detail_list.get(i).getid());
                                        System.out.println(configModel.Player_detail_list.get(i).getid() + " =:" + configModel.Player_detail_list.get(i).getplayer_name());
                                    }

                                }
                                player_id = Integer.parseInt(br.readLine());

                                if (!(assasinte_player.contains(player_id))) {
                                    System.out.println("oopss  enter number from the below list  not your own!!");
                                    cheid = true;
                                }

                            } while (cheid);
                            
                            player_id = player_id -1;
                            int ta_id = 0;
                            List<Integer> area_id;
                            ArrayList<Integer> target_area = new ArrayList<Integer>();
                            do {
                                cheid = false;
                                System.out.println("Select the area from the following from which you want to remove the minion");
                                area_id = eh.get_item_area(player_id, "minion");

                                for (Integer i : area_id) {
                                    if(eh.checkTroubleMakerPresent(i-1))
                                    {
                                    target_area.add(configModel.Area_detail_list.get(i - 1).getareaid());
                                    System.out.println(i+ " =: " + configModel.Area_detail_list.get(i - 1).getarea_name());
                                    }
                                }
                                ta_id = Integer.parseInt(br.readLine());

                                if (!(target_area.contains(ta_id))) {
                                    System.out.println("oops enter the area id from the below list");
                                    cheid = true;
                                }
                                else
                                {
                                     boolean isInteruptable = eh.checkForInterrupt(player_id, ta_id - 1);
                                     if(!isInteruptable)
                                     {
                                        isTroubleMarkerThere =  eh.remove_item(player_id, ta_id-1, "minion");
                                        continueLoop = false;
                                        action_complete = true;
                                     }
                                     else
                                     {
                                        continueLoop = false;
                                        action_complete = true;
                                     }
                                
                                }

                            } while (cheid);

                        } else if (choice == 2) {
                            int ta_id = 0;
                            List<Integer> area_id;
                            ArrayList<Integer> target_area = new ArrayList<Integer>();
                            do {
                                cheid = false;
                                System.out.println("Select the area from the following from which you want to remove the Demon");
                                area_id = eh.get_item_area(9, "demon");

                                for (Integer i : area_id) {
                                    if(eh.checkTroubleMakerPresent(i-1))
                                    {
                                    target_area.add(configModel.Area_detail_list.get(i - 1).getareaid());
                                    System.out.println(configModel.Area_detail_list.get(i - 1).getareaid() + " =: " + configModel.Area_detail_list.get(i - 1).getarea_name());
                                    }
                                }
                                ta_id = Integer.parseInt(br.readLine());

                                if (!(target_area.contains(ta_id))) {
                                    System.out.println("oops enter the area id from the below list");
                                    cheid = true;
                                }
                                else
                                {
                                    isTroubleMarkerThere =  eh.remove_item(9, ta_id-1, "demon");
                                    continueLoop = false;
                                    action_complete = true;
                                }

                            } while (cheid);

                        }
                        else if (choice == 3) {
                             int ta_id = 0;
                             int trollcount=0;
                            List<Integer> area_id;
                            ArrayList<Integer> target_area = new ArrayList<Integer>();
                            do {
                                cheid = false;
                                System.out.println("Select the area from the following from which you want to remove the troll");
                                area_id = eh.get_item_area(9, "troll");

                                for (Integer i : area_id) {
                                     if(eh.checkTroubleMakerPresent(i-1))
                                    {
                                        trollcount++;
                                    target_area.add(configModel.Area_detail_list.get(i - 1).getareaid());
                                    System.out.println(configModel.Area_detail_list.get(i - 1).getareaid() + " =: " + configModel.Area_detail_list.get(i - 1).getarea_name());
                                    }
                                }
                                if(trollcount==0)
                                {
                                    System.out.println("you can not assasinate troll because that are not contain trouble maker");
                                    
                                }
                                else
                                {
                                ta_id = Integer.parseInt(br.readLine());

                                if (!(target_area.contains(ta_id))) {
                                    System.out.println("oops enter the area id from the below list");
                                    cheid = true;
                                }
                                else
                                {
                                    isTroubleMarkerThere =  eh.remove_item(9, ta_id-1, "troll");
                                    continueLoop = false;
                                    action_complete = true;
                                }
                                }
                            } while (cheid);
                        }
                    } catch (Exception e) {
                        System.out.println("kindly enter the Correct name of area as Represented in the List number");
                        continueLoop = true;
                    }
                }
            }
            break;
            case "REMOVE_ONE_TROUBLE": {

                do {
                    System.out.println("Select one of area from where you want to remove trouble maker");
                    List<Integer> trouble_area = eh.get_item_area(9, "trouble");

                    for (int i = 0; i < trouble_area.size(); i++) {
                        System.out.println(trouble_area.get(i) + " . " + configModel.Area_detail_list.get(trouble_area.get(i) - 1).getarea_name());
                    }
                    selected_area = Integer.parseInt(br.readLine());
                    if (trouble_area.contains(selected_area)) {
                        boolean result = eh.remove_item(9, selected_area - 1, "trouble");
                        if (result) {
                            action_complete = true;
                            loop = false;

                        }
                    } else {
                        System.out.println("Please Choose Correct Option");
                        loop = true;
                    }

                } while (loop);

            }
            break;
            case "TAKE_MONEY": {
                int card_id = CurrentPlayer.card_id;
                int card_money = configModel.green_card_detail.get(card_id - 1).getmoney();
                System.out.println((player + 1) + " can take $" + card_money + " using Card" + card_id + " from bank!");
                int player_money = configModel.Player_detail_list.get(player).getplayer_money();
                boolean result = eh.bank_transaction(player, card_money, "withdraw");
                if (result) {
                    action_complete = true;
                }

                System.out.println("You got $" + card_money + " From bank");
                System.out.println("Your have $" + (card_money + player_money));

            }
            break;
            case "SCROLL":
                boolean result = perform.perform_green_scroll(String.valueOf(CurrentPlayer.card_id));
                if (result) {
                    action_complete = true;
                }
                break;
            case "RANDOM_EVENT": {
                if (perform_random_event(CurrentPlayer.random_event_name)) {
                    action_complete = true;
                }

            }

            break;
            case "PLAY_ANOTHER_CARD":
                System.out.println("Better try again");
                break;
            case "INTERRUPT":
                System.out.println("You can only use Interrupt card when random event occured");
                action_complete = true;
                break;
            default:
                System.out.println("Invalid grade");
        }
        return action_complete;

    }

      /**
    * This is random event action which make use of perform card random event given in card.
    * @param event pass event to perform
    * @return boolean
    * @throws IOException handle user input
    */
    public boolean perform_random_event(String event) throws IOException {
        switch (event) {
            case "dragon": {

                configModel.dice_random_number = map.sufflecards(configModel.dice_random_number);
                int f_rnum = configModel.dice_random_number.get(0) - 1;
                System.out.println("Dice number is:" + configModel.dice_random_number.get(0));

                int playerHASgod = eh.check_small_god();
                boolean loop = true;
                for (int i = 0; i < configModel.no_of_players; i++) {
                    if (i == playerHASgod) {
                        if (!eh.is_empty_area(f_rnum)) {
                            int[] area_item = eh.get_player_item_in_area(playerHASgod, f_rnum);
                            int player_money = configModel.Player_detail_list.get(playerHASgod).getplayer_money();
                            if (player_money >= 3 && (area_item[0] > 0 || area_item[1] > 0)) {
                                System.out.println("Player" + i + " has Small God card. You can protact one of your piece");
                                System.out.println("Do you want to protact your one of your following piece");

                                do {
                                    if (area_item[0] > 0) {
                                        System.out.println("1. Minion");
                                    }
                                    if (area_item[1] > 0) {
                                        System.out.println("2. Building");
                                    }
                                    int input = Integer.parseInt(br.readLine());
                                    if (input == 1) {

                                        if (area_item[0] > 1) {
                                            eh.remove_all(playerHASgod, f_rnum, "minion");
                                            eh.set_item(playerHASgod, f_rnum, "minion");
                                        }
                                        loop = false;
                                    } else if (input == 2) {
                                        if (area_item[1] > 1) {
                                            eh.remove_all(playerHASgod, f_rnum, "building");
                                            eh.set_item(playerHASgod, f_rnum, "building");
                                        }
                                        loop = false;
                                    } else {
                                        System.out.println("Opps enter valid number");
                                    }
                                } while (loop);

                            } else {
                                System.out.println("You have not enough money or ypou dont have building or minion in area");
                            }

                        }

                    } else {
                        eh.remove_all(i, f_rnum, "minion");
                        eh.remove_all(i, f_rnum, "building");
                    }

                    eh.remove_all(9, f_rnum, "trouble");
                    eh.remove_all(9, f_rnum, "demon");
                    eh.remove_all(9, f_rnum, "troll");
                    random_complete = true;

                }

            }
            break;
            case "flood": {
                

            }
            break;
            case "fire": {

            }
            case "fog": {
                /**
                 * raw and discard the top five cards from the draw pile. Make
                 * sure you and the rest of the players can see which cards have
                 * been discarded.
                 *
                 */
                System.out.println("Following cards removed from pile !");
                for (int i = 0; i < 5; i++) {
                    //configModel.greencard_random_number.get(i);
                    System.out.println(configModel.greencard_random_number.get(i) + ". " + configModel.green_card_detail.get(configModel.greencard_random_number.get(i)-1).getCard_name());
                    configModel.discarded_card.add(i);
                    configModel.greencard_random_number.remove(i);
                }

                random_complete = true;
            }
            break;
            case "riots": {

            }
            case "explosion": {
                //Roll the die and remove any building in the area of the same number.   
                configModel.dice_random_number = map.sufflecards(configModel.dice_random_number);
                int f_rnum = configModel.dice_random_number.get(0);
                System.out.println("Dia number is " + f_rnum);
                random_complete = eh.small_god_buildings(f_rnum - 1);
//                int playerHASgod = check_small_god();
//                boolean loop = true;
//                for (int i = 0; i < configModel.no_of_players; i++) {
//                    if (i == playerHASgod) {
//                        if (!is_empty_area(f_rnum)) {
//                            int[] area_item = get_player_item_in_area(playerHASgod, f_rnum);
//                            int player_money = configModel.Player_detail_list.get(playerHASgod).getplayer_money();
//                            if (player_money >= 3 && area_item[1] > 0) {
//                                System.out.println("Player" + i + " has Small God card. You can protact you building");
//                                System.out.println("Do you want to protact than Enter 1");
//                                System.out.println("1 Building");
//                                int input = Integer.parseInt(br.readLine());
//                                if (area_item[1] > 1) {
//                                    remove_all(playerHASgod, f_rnum, "building");
//                                    set_item(playerHASgod, f_rnum, "building");
//                                }
//
//                            }
//                        }
//                    } else {
//                        remove_item(i, f_rnum, "building");
//                        System.out.println("Building removed from Area" + f_rnum);
//                    }
//                }
//
//                random_complete = true;

            }
            break;
            case "mysterious_murders": {

            }
            case "demons_from_the_dungeon_dimensions": {

            }
            case "subsidence": {
                /**
                 * All players must pay $2 for each building they have on the
                 * board. If they cannot pay for a building then it is removed
                 * from the board.
                 *
                 */
                for (int player = 0; player < configModel.no_of_players; player++) {
                    List<Integer> altemp = eh.get_item_area(player, "building");
                    int total_building = altemp.size();
                    int pay_cost = 2;
                    int total_pay = total_building * pay_cost;
                    //check player money
                    int player_money = configModel.Player_detail_list.get(player).getplayer_money();
                    if (player_money > total_pay) {
                        eh.bank_transaction(player, total_pay, "deposit");
                        System.out.println("Player " + (player + 1) + " pay $" + total_pay);
                        random_complete = true;
                    } else {
                        //  6 = 10 - 4
                        int selected_area;
                        int total_remain = total_pay - player_money;
                        int can_pay_for = (int) Math.floor(player_money / pay_cost);
                        int cant_pay = total_building - can_pay_for;
                        System.out.println("You have only $" + player_money + " Total cost is " + total_pay);
                        System.out.println("You have to remove your " + cant_pay + " buildings from area ");
                        System.out.println("Choose " + cant_pay + " buildings from following options");
                        for (int i = 0; i < altemp.size(); i++) {
                            System.out.println(altemp.get(i) + ". " + configModel.Area_detail_list.get(altemp.get(i) - 1).getarea_name());
                        }
                        for (int build = 0; build < cant_pay; build++) {
                            System.out.println("building " + (build + 1) + " >>>>> ");

                            try {
                                selected_area = Integer.parseInt(br.readLine());
                                eh.remove_item(player, (selected_area - 1), "building");
                            } catch (IOException ex) {
                                Logger.getLogger(Card_Event_Action.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        eh.bank_transaction(player, (can_pay_for * pay_cost), "deposit");
                        random_complete = true;
                    }

                }
            }
            break;
            case "bloody_stupid_johnson": {

            }
            case "trolls": {

            }
            case "Earthquake": {
                //Roll the die twice and remove any buildings from the areas of the same number, if any.
                configModel.dice_random_number = map.sufflecards(configModel.dice_random_number);
                System.out.println("random numbe");
                int f_rnum = configModel.dice_random_number.get(0);
                System.out.println("random numbe" + f_rnum);

                eh.small_god_buildings(f_rnum - 1);
                int s_rnum = configModel.dice_random_number.get(1);
                System.out.println("random numbe" + s_rnum);
                eh.small_god_buildings(s_rnum - 1);

                random_complete = true;
            }
            break;
        }
        return random_complete;

    }

}
