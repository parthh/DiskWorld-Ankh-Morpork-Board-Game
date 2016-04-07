/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import ankh.controller.Eventdemo;
import ankh.controller.Card_Event_Action;
import ankh.controller.CurrentPlayer;
import ankh.controller.SaveNameChooser;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import winstrategy.*;
import citystartegy.*;
/**
 *
 * @author Har
 */
public class TurnHandler {

    CurrentPlayer currentplayer;
    Eventhandler event_handler = new Eventhandler();
    MapcreateView mapView = new MapcreateView();
    CityArea_handler city = new CityArea_handler();

    int card_id;
    int card_option;
    Card_Event_Action card_event;
    Win_handler win_cond = new Win_handler();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    boolean continueLoop = false;
    Graphics gdrwa = mapView.getGraphics();
    ArrayList<Integer> eventList = new ArrayList<Integer>();
    ArrayList<Integer> getareacard= new ArrayList<Integer>();
    int randon_card_id = 0;
    int selected_event = 0;
    boolean againturn = false;

    public TurnHandler(boolean abc) {
        this.againturn = abc;
    }

    public TurnHandler() {
        currentplayer = new CurrentPlayer();
        card_event = new Card_Event_Action();
        getareacard.clear();
          
        List<Integer> getareacard_remian  = event_handler.get_area_without_demon(configModel.turn_counter - 1, "building");
            for(int i=0;i<getareacard_remian.size();i++)
            {
                getareacard.add(getareacard_remian.get(i)-1);
            }
        String win = check_wincondition();
        if (win.equalsIgnoreCase("win")) {
            System.out.println("---------------------------WINNER--------------------------------");
            System.out.println("|                                                               |");
            System.out.println("|                                                               |");
            System.out.println("|                " + "Player " + configModel.Player_detail_list.get(configModel.turn_counter-1).getplayer_name() + " is win the game" + "                    |");
            System.out.println("|                " + configModel.Player_detail_list.get(configModel.turn_counter-1).getplayer_name()+" worth : " + configModel.Player_detail_list.get(configModel.turn_counter-1).get_worth()+ " is win the game" + "               |");
            System.out.println("|                                                               |");
            System.out.println("|                                                               |");
            System.out.println("-----------------------------------------------------------------");
        } else {
            check_citycard();
            printMainMenu();
        }
    }

    public void printMainMenu() {

        do {

            eventList.clear();
            boolean isCardInValid = true;
            System.out.println("Hello -" + CurrentPlayer.currentPlayer.getplayer_name() + "-you have Green card :");
            ArrayList<Integer> green_card = CurrentPlayer.currentPlayer.getplayer_green_card();
            for (int i = 0; i < green_card.size(); i++) {
                event_handler.card_printer(green_card.get(i) - 1, "green");
            }
            System.out.println("Enter the card number you want to play :");

            try {

                card_id = Integer.parseInt(br.readLine());
               // isCardInValid=checkCardInValid(card_id);

                if (isCardInValid) {

                    CurrentPlayer.card_id = card_id;
                    int event[] = configModel.green_card_detail.get(card_id - 1).getcard_event();

                    for (int i = 0; i < event.length; i++) {
                        eventList.add(event[i]);
                    }
                //Collections.addAll(eventList, event);

                    do {
                        if (eventList.isEmpty()) {
                            System.out.println("NO events in this your selected card please select diffrent one");
                            continueLoop = true;
                            break;

                        } else {
                            System.out.println("\n Select event you want to perform");
                            showeventmenu(eventList);
                            int selected = Integer.parseInt(br.readLine());
                            if (configModel.accessEvent.getEventname(Eventdemo.get(event[selected - 1])).equals("PLAY_ANOTHER_CARD")) {
                                CurrentPlayer.currentPlayer.remove_green_card(CurrentPlayer.card_id);
                                eventList.clear();
                                continueLoop = false;
                            }
                            else {
                                switch (selected) {

                                    case 1: {
                                        if (configModel.accessEvent.getEventname(Eventdemo.get(event[0])).equals("RANDOM_EVENT")) {
                                            boolean checkflag = true;
                                            int event_id = random_event_id(randon_card_id);
                                            System.out.println("Below is description of your random event card Press '1' to Play");
                                            System.out.println("Card Name =:" + configModel.random_card_detail.get(event_id).get_title());
                                            System.out.println("Card Detail =:" + configModel.random_card_detail.get(event_id).get_desc());
                                            do {
                                                int input = Integer.parseInt(br.readLine());
                                                if (input == 1) {
                                                    CurrentPlayer.random_event_name = configModel.accessEvent.getEventname(Eventdemo.get(randon_card_id));
                                                    continueLoop = card_event.perform_action(configModel.accessEvent.getEventname(Eventdemo.get(event[0])));
                                                    if (continueLoop) {
                                                        checkflag = false;
                                                    }
                                                } else {
                                                    System.out.println("Please press 1 to conitnue");
                                                }
                                            } while (checkflag);

                                        } else {
                                            continueLoop = card_event.perform_action(configModel.accessEvent.getEventname(Eventdemo.get(eventList.get(0))));
                                            System.out.println("return status" + continueLoop);
                                        }

                                        if (continueLoop == true) {
                                            //selected_event = 1;
                                            eventList.remove(0);
                                        }

                                    }
                                    break;
                                    case 2: {
                                        continueLoop = card_event.perform_action(configModel.accessEvent.getEventname(Eventdemo.get(eventList.get(1))));
                                        if (continueLoop == true) {
                                            //selected_event = 2;
                                            eventList.remove(1);
                                            eventList.remove(0);

                                        }

                                        System.out.println("return status" + continueLoop);
                                    }
                                    break;
                                    case 3: {
                                        continueLoop = card_event.perform_action(configModel.accessEvent.getEventname(Eventdemo.get(eventList.get(2))));
                                        System.out.println("return status" + continueLoop);
                                        if (continueLoop == true) {
                                           // selected_event = 3;
                                            eventList.remove(2);
                                            eventList.remove(1);
                                            eventList.remove(0);
                                        }
                                        System.out.println("return status" + continueLoop);
                                    }
                                    break;
                                    case 4: {
                                        continueLoop = card_event.perform_action(configModel.accessEvent.getEventname(Eventdemo.get(eventList.get(3))));
                                        if (continueLoop == true) {
                                         //   selected_event = 4;
                                            eventList.remove(3);
                                            eventList.remove(2);
                                            eventList.remove(1);
                                            eventList.remove(0);
                                        }
                                    }
                                    break;
                                    default: {
                                        System.out.println("you entered something wrong try again");
                                        continueLoop = false;
                                    }
                                    break;

                                }
                                if(continueLoop)
                                {
                                     CurrentPlayer.currentPlayer.remove_green_card(CurrentPlayer.card_id);
                                }
                                check_citycard();
                                if (!eventList.isEmpty()) {
                                    System.out.println("your have remainig event for selected card");
                                    showeventmenu(eventList);
                                    System.out.println("If you Want to proceed Press YES otherwise NO");
                                    String str = br.readLine();
                                    if (str.equalsIgnoreCase("YES")) {
                                        againturn = true;
                                    }

                                }
                                else
                                {
                                    againturn = false;
                                }
                            }

                        }
                    } while (againturn);
                } else {
                    System.out.println("Please enter valid green card number that you have");
                    continueLoop = false;
                }

            } catch (Exception e) {
                e.printStackTrace();
                continueLoop = false;
            }

        } while (!continueLoop);

        if (continueLoop) {
            nextTurn();
        }
    }

    public void nextTurn() {

        //configModel.greencard_random_number = mapView.sufflecards(configModel.greencard_random_number);
        assignnewcard();
        if (configModel.turn_counter < configModel.no_of_players) {
            configModel.turn_counter++;

        } else {
            configModel.turn_counter = 1;
        }

        mapView.PrintGameState();
        mapView.repaint();
        mapView.handler();
    }

    public int random_event_id(int id) {
        int actual_id = 0;
        if (id == 10) {
            actual_id = 0;
        } else if (id == 11) {
            actual_id = 3;
        } else if (id == 12) {
            actual_id = 5;
        } else if (id == 13) {
            actual_id = 8;
        } else if (id == 14) {
            actual_id = 11;
        }
        return actual_id;
    }

    public void assignnewcard() {
        int size = CurrentPlayer.currentPlayer.getplayer_green_card().size();
        for (int i = size; i < 5; i++) {
            int cardid = configModel.greencard_random_number.get(0);
            configModel.Player_detail_list.get(configModel.turn_counter - 1).setplayer_green_card(cardid);
            configModel.greencard_random_number.remove(0);

        }

    }

    public boolean showeventmenu(ArrayList<Integer> menu) {
        boolean menu_show = false;
        for (int i = 0; i < menu.size(); i++) {

            switch (configModel.accessEvent.getEventname(Eventdemo.get(menu.get(i)))) {
                case "SCROLL": {
                    System.out.print((i + 1) + " - " + "SCROLL = ");
                    System.out.println(configModel.green_card_detail.get(card_id - 1).getCard_detail());
                    menu_show = true;
                }
                break;
                case "TAKE_MONEY":
                    System.out.println((i + 1) + " - " + "TAKE_MONEY =: " + configModel.green_card_detail.get(card_id - 1).getmoney());
                    menu_show = true;
                    break;
                case "RANDOM_EVENT": {
                    configModel.random_event = mapView.sufflecards(configModel.random_event);
                    randon_card_id = configModel.random_event.get(0);
                    System.out.println((i + 1) + " - " + "RNDOM EVENT =: you must have to play this event");
                    configModel.random_event.remove(0);
                    menu_show = true;

                }
                break;
                case "INTERRUPT":
                {
                    System.out.println("- "+ configModel.accessEvent.getEventname(Eventdemo.get(menu.get(i))) +" you can not use it");
                }
                break;
                default: {
                    System.out.println((i + 1) + " - " + configModel.accessEvent.getEventname(Eventdemo.get(menu.get(i))));
                    menu_show = true;
                }
                break;
            }

        }
        return menu_show;
    }

    public void check_citycard() {
        boolean city_handle = false;
        
        int total_card = 0;
        try {
            boolean repeat = true;

            
            if (getareacard.size() > 0) {
                do {
                    System.out.println(CurrentPlayer.currentPlayer.getplayer_name() +"  you have below area cards :");
                    System.out.println("");
                    for (Integer card : getareacard) {
                        String area_detail = configModel.Area_detail_list.get(card).getarea_detail();
                        
                        System.out.println((card + 1) + " > " + configModel.Area_detail_list.get(card).getarea_name() + " [" + area_detail + "]");

                    }
                    System.out.println("0 > NO PLAY");
                    total_card = Integer.parseInt(br.readLine());
                    if (total_card == 0) {
                        repeat = false;
                    } else {
                        if (getareacard.contains((total_card - 1))) {
                           // city_handle = city.cityarea_condition(total_card, configModel.turn_counter - 1);
                            int area_size = configModel.Player_detail_list.get(configModel.turn_counter-1).city_cond_list.size();
                            for(int i=0;i<area_size;i++)
                            {
                                if(CurrentPlayer.currentPlayer.city_cond_list.get(i).cityid==(total_card-1))
                                {
                                     city_handle = CurrentPlayer.currentPlayer.city_cond_list.get(i).execute_operation(total_card, configModel.turn_counter-1);
                                     break;
                                }
                            }
                           
                            if (city_handle) {
                                 
                                for(int i=0;i<getareacard.size();i++)
                                {
                                    if(getareacard.get(i)==total_card-1)
                                    {
                                        getareacard.remove(i);
                                    }
                                }
                                //getareacard.remove(getareacard.indexOf(total_card - 1));
                            }

                        } else {
                            System.out.println("Please enter valid number !!");
                        }
                    }
                } while (repeat && getareacard.size() > 0);
            } else {
                System.out.println("oops you didn't owned any city yet !!");
            }

        } catch (IOException ex) {
            Logger.getLogger(TurnHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String check_wincondition() {
        boolean result;
        if(configModel.greencard_random_number.size()==0)
        {
            Wincheck win_cond = new Wincheck(new Default_wining());
            result = win_cond.execute_operation();
        }
        else
        {
            result = configModel.Player_detail_list.get(configModel.turn_counter-1).win_cond.execute_operation();
        }
        if (result) {

            return "win";
        } else {
            return "no_win";
        }
        //return stat;
    }

    public boolean checkCardInValid(int card_id) {
        for (int card_number : currentplayer.currentPlayer.getplayer_green_card()) {
            if (card_number == card_id) {
                return true;

            }
        }
        return false;

    }

}
