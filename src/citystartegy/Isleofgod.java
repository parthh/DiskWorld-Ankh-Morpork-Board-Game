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
public class Isleofgod implements CityInterface{
boolean result = false;
     boolean loop = true;
     boolean status = false;
    @Override
    public boolean cityarea_check(int card_number, int player_id) {
         int money = CurrentPlayer.currentPlayer.getplayer_money();
                int cost = 2;
                int selected_area;
                System.out.println("select are number from below where you want to remove trouble maker =:");
                if (money >= cost) {
             try {
                 configModel.adjacent_list = configModel.cev.get_item_area(9, "trouble");
                 for (Integer trouble : configModel.adjacent_list) {
                     System.out.println(trouble + "=:" + configModel.Area_detail_list.get(trouble - 1).getarea_name());
                 }
                 selected_area = Integer.parseInt(configModel.br.readLine());
                 if (configModel.adjacent_list.contains(selected_area)) {
                     result = configModel.cev.remove_item(9, selected_area - 1, "trouble");
                     if (result) {
                         status = true;
                     }
                 }
             } catch (IOException ex) {
                 Logger.getLogger(Isleofgod.class.getName()).log(Level.SEVERE, null, ex);
             }
                }
        return status;
        
    }
    
    
}
