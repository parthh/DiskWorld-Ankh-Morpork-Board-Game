/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import ankh.controller.CurrentPlayer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CityArea_handler {

    Eventhandler cev = new Eventhandler();
    boolean status = false;
    List<Integer> adjacent_list = new ArrayList<Integer>();
    int adjacentarea[];
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    boolean result = false;
    boolean loop = true;

    public boolean cityarea_condition(int areaid, int playerid) throws IOException {

        switch (areaid) {

            case 1: {

                int money = CurrentPlayer.currentPlayer.getplayer_money();
                int cost = 3;
                int selected_area;
                if (money >= cost) {
                    do {
                        System.out.println("Select one of Area to place minion :");
                        adjacentarea = configModel.Area_detail_list.get(0).getadjacent();
                         adjacent_list.add(1);
                        System.out.println("1" + " > " + configModel.Area_detail_list.get(0).getarea_name());
                        for (int i = 0; i < adjacentarea.length; i++) {
                            adjacent_list.add(adjacentarea[i]);
                            System.out.println(adjacentarea[i] + " > " + configModel.Area_detail_list.get(adjacentarea[i] - 1).getarea_name());
                        }
                        selected_area = Integer.parseInt(br.readLine());
                        if (adjacent_list.contains(selected_area)) {
                            result = cev.set_item(playerid, (selected_area - 1), "minion");
                            boolean new_check_trouble = cev.check_item_in_area(selected_area - 1, "trouble");
                            if (!new_check_trouble) {
                                cev.set_item(9, (selected_area - 1), "trouble");
                            }
                            cev.bank_transaction(playerid, cost, "deposit");
                            loop = false;
                            status = true;
                        } else {
                            System.out.println("Please Choose Correct Option");
                            loop = true;
                        }
                        System.out.println("");

                    } while (loop);
                } else {
                    System.out.println("you dont have enought money to play this card");

                }
            }
            break;
            case 2: {
                for (Integer remove_card : CurrentPlayer.currentPlayer.getplayer_green_card()) {
                    if (remove_card != CurrentPlayer.card_id) {
                        CurrentPlayer.currentPlayer.remove_green_card(remove_card);
                        System.out.println("one card remove from your five card is=:" + remove_card);
                        CurrentPlayer.currentPlayer.setplayer_green_card(configModel.greencard_random_number.get(0));
                        System.out.println("new assign card from deck is =:" + configModel.greencard_random_number.get(0));
                        configModel.greencard_random_number.remove(0);
                        status = true;
                        break;
                    }
                }

            }
            break;
            case 3: {
                int cost = 2;
                result = cev.bank_transaction(playerid, cost, "withdraw");
                if (result) {
                    status = true;
                }
            }
            break;
            case 4: {
                System.out.println("you can use your card when random event occured");
                status = true;
            }
            break;
            case 5: {
                int cost = 3;
                System.out.println("you can discard your one card and get $2 from the bank");
                System.out.println("you have below green card =:");
                ArrayList<Integer> remove_list = CurrentPlayer.currentPlayer.getplayer_green_card();
                 for(int i=0;i<remove_list.size();i++)
                {
                cev.card_printer(remove_list.get(i)-1, "green");
                }
               
                System.out.println("enter card number you want to discard and get $2 from the bank");
                int card = Integer.parseInt(br.readLine());

                if (remove_list.contains(card)) {
                    CurrentPlayer.currentPlayer.remove_green_card(card);
                    cev.bank_transaction(playerid, cost, "withdraw");
                    status = true;
                }

            }
            break;
            case 6: {
                int cost = 2;
                result = cev.bank_transaction(playerid, cost, "withdraw");
                if (result) {
                    status = true;
                }
            }
            break;
            case 7: {
                int selected_area;
                System.out.println("Select one of Area to place trouble maker :");
                adjacentarea = configModel.Area_detail_list.get(6).getadjacent();
                adjacent_list.add(7);
                System.out.println("7" + " > " + configModel.Area_detail_list.get(6).getarea_name());
                for (int i = 0; i < adjacentarea.length; i++) {
                    adjacent_list.add(adjacentarea[i]);
                    System.out.println(adjacentarea[i] + " > " + configModel.Area_detail_list.get(adjacentarea[i] - 1).getarea_name());
                    
                }
                selected_area = Integer.parseInt(br.readLine());
                if (adjacent_list.contains(selected_area)) {
                    result = cev.set_item(playerid, (selected_area - 1), "trouble");
                    if (result) {
                        status = true;
                    }
                }

            }
            break;
            case 8: {
                int money = CurrentPlayer.currentPlayer.getplayer_money();
                int cost = 3;
                int selected_area;
                if (money >= cost) {
                    do {
                        System.out.println("Select one of Area to place minion :");
                        adjacentarea = configModel.Area_detail_list.get(7).getadjacent();
                         adjacent_list.add(8);
                          System.out.println("8" + " > " + configModel.Area_detail_list.get(7).getarea_name());
                        for (int i = 0; i < adjacentarea.length; i++) {
                            adjacent_list.add(adjacentarea[i]);
                            System.out.println(adjacentarea[i] + " > " + configModel.Area_detail_list.get(adjacentarea[i] - 1).getarea_name());
                        }
                        selected_area = Integer.parseInt(br.readLine());
                        if (adjacent_list.contains(selected_area)) {
                            result = cev.set_item(playerid, (selected_area - 1), "minion");
                            cev.bank_transaction(playerid, cost, "deposit");
                            loop = false;
                            status = true;
                        } else {
                            System.out.println("Please Choose Correct Option");
                            loop = true;
                        }
                        System.out.println("");

                    } while (loop);
                } else {
                    System.out.println("you dont have enought money to play this card");

                }
            }
            break;
            case 9: {
                int cost = 1;
                result = cev.bank_transaction(playerid, cost, "withdraw");
                if (result) {
                    status = true;
                }
            }
            break;
            case 10: {
                int money = CurrentPlayer.currentPlayer.getplayer_money();
                int cost = 2;
                int selected_area;
                System.out.println("select are number from below where you want to remove trouble maker =:");
                if (money >= cost) {
                    adjacent_list = cev.get_item_area(9, "trouble");
                    for (Integer trouble : adjacent_list) {
                        System.out.println(trouble + "=:" + configModel.Area_detail_list.get(trouble - 1).getarea_name());
                    }
                    selected_area = Integer.parseInt(br.readLine());
                    if (adjacent_list.contains(selected_area)) {
                        result = cev.remove_item(9, selected_area - 1, "trouble");
                        if (result) {
                            status = true;
                        }
                    }
                }

            }
            break;
            case 11: {
                int cost = 3;
                result = cev.bank_transaction(playerid, cost, "withdraw");
                if (result) {
                    status = true;
                }
            }
            break;
            case 12: {
                int cost = 1;
                result = cev.bank_transaction(playerid, cost, "withdraw");
                if (result) {
                    status = true;
                }
            }
            break;
            default: {
                System.out.println("please enter valid area number");
            }
            break;
        }
        return status;
    }

}
