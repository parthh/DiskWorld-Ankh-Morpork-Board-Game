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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Thescours implements CityInterface{

     boolean status = false;
    @Override
    public boolean cityarea_check(int card_number, int player_id) {
        try {
            int cost = 3;
            System.out.println("you can discard your one card and get $2 from the bank");
            System.out.println("you have below green card =:");
            ArrayList<Integer> remove_list = CurrentPlayer.currentPlayer.getplayer_green_card();
            for(int i=0;i<remove_list.size();i++)
            {
                configModel.cev.card_printer(remove_list.get(i)-1, "green");
            }
            
            System.out.println("enter card number you want to discard and get $2 from the bank");
            int card = Integer.parseInt(configModel.br.readLine());
            
            if (remove_list.contains(card)) {
                CurrentPlayer.currentPlayer.remove_green_card(card);
                configModel.cev.bank_transaction(player_id, cost, "withdraw");
                status = true;
            }
         
        } catch (IOException ex) {
            Logger.getLogger(Thescours.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return status;
    }
    
    
}
