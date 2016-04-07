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
public class Smallgods implements CityInterface{

     boolean status = false;
    @Override
    public boolean cityarea_check(int card_number, int player_id) {
        System.out.println("you can use your card when random event occured");
               status = true;
        return status;
        
    }
    
    
}
