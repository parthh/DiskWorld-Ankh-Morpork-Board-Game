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
public class Dragonlanding implements CityInterface{
boolean result = false;
     boolean loop = true;
     boolean status = false;
    @Override
    public boolean cityarea_check(int card_number, int player_id) {
        int cost = 2;
                result = configModel.cev.bank_transaction(player_id, cost, "withdraw");
                if (result) {
                    status = true;
                }
                
                return status;
    }
    
    
}
