/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citystartegy;

import citystartegy.CityInterface;
import ankh.controller.CurrentPlayer;
import ankh.view.configModel;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Dollysister implements CityInterface{

    boolean result = false;
     boolean loop = true;
     boolean status = false;
    @Override
    public boolean cityarea_check(int card_number, int player_id) {
          int money = CurrentPlayer.currentPlayer.getplayer_money();
                int cost = 3;
                int selected_area;
                if (money >= cost) {
                    do {
                        try {
                            System.out.println("Select one of Area to place minion :");
                            configModel.adjacentarea = configModel.Area_detail_list.get(0).getadjacent();
                            configModel.adjacent_list.add(1);
                            System.out.println("1" + " > " + configModel.Area_detail_list.get(0).getarea_name());
                            for (int i = 0; i < configModel.adjacentarea.length; i++) {
                                configModel.adjacent_list.add(configModel.adjacentarea[i]);
                                System.out.println(configModel.adjacentarea[i] + " > " + configModel.Area_detail_list.get(configModel.adjacentarea[i] - 1).getarea_name());
                            }
                            selected_area = Integer.parseInt(configModel.br.readLine());
                            if (configModel.adjacent_list.contains(selected_area)) {
                                result = configModel.cev.set_item(player_id, (selected_area - 1), "minion");
                                boolean new_check_trouble = configModel.cev.check_item_in_area(selected_area - 1, "trouble");
                                if (!new_check_trouble) {
                                    configModel.cev.set_item(9, (selected_area - 1), "trouble");
                                }
                                configModel.cev.bank_transaction(player_id, cost, "deposit");
                                loop = false;
                                status = true;
                            } else {
                                System.out.println("Please Choose Correct Option");
                                loop = true;
                            }
                            System.out.println("");
                        } catch (IOException ex) {
                            Logger.getLogger(Dollysister.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } while (loop);
                } else {
                    System.out.println("you dont have enought money to play this card");

                }
                return status;
    }
    
    
}
