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
public class Theshades implements CityInterface{
boolean result = false;
     boolean loop = true;
     boolean status = false;
    @Override
    public boolean cityarea_check(int card_number, int player_id) {
        try {
            int selected_area;
            System.out.println("Select one of Area to place trouble maker :");
            configModel.adjacentarea = configModel.Area_detail_list.get(6).getadjacent();
            configModel.adjacent_list.add(7);
            System.out.println("7" + " > " + configModel.Area_detail_list.get(6).getarea_name());
            for (int i = 0; i < configModel.adjacentarea.length; i++) {
                configModel.adjacent_list.add(configModel.adjacentarea[i]);
                System.out.println(configModel.adjacentarea[i] + " > " + configModel.Area_detail_list.get(configModel.adjacentarea[i] - 1).getarea_name());
                
            }
            selected_area = Integer.parseInt(configModel.br.readLine());
            if (configModel.adjacent_list.contains(selected_area)) {
                result = configModel.cev.set_item(player_id, (selected_area - 1), "trouble");
                if (result) {
                    status = true;
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Theshades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    
}
