/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citystartegy;

import citystartegy.CityInterface;
import ankh.controller.CurrentPlayer;
import ankh.view.configModel;
import java.util.List;

/**
 *
 * @author admin
 */
public class Unrealestate implements CityInterface{

     boolean status = false;
    @Override
    public boolean cityarea_check(int card_number, int playerid) {
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

        return status;
        
    }
    
    
}
