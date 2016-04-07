/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.controller;

import ankh.model.Player_detail;
import ankh.view.configModel;

/**
 *
 * @author Har
 */
public class CurrentPlayer 
{
   public static Player_detail currentPlayer;
        public static int card_id;
        public static int option_id;
        public static String random_event_name;
    
    public CurrentPlayer(int card_id,int card_option)
    {
        currentPlayer=configModel.Player_detail_list.get(configModel.turn_counter);
        this.card_id=card_id;
        this.option_id=card_option;
                
        
    }
    public CurrentPlayer()
    {
       currentPlayer=configModel.Player_detail_list.get(configModel.turn_counter-1);
       card_id=0;
       option_id=0;
               
       
    }
    
}
