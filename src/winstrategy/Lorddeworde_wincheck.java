/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winstrategy;

import ankh.model.Player_detail;
import ankh.view.configModel;
import java.util.List;
import winstrategy.Wincheck_interface;

/**
 *
 * @author admin
 */
public class Lorddeworde_wincheck implements Wincheck_interface{
   
    

    @Override
    public boolean winnning_check() 
    {
        if(configModel.no_of_players==2)
                {
                   List<Integer> building_area = configModel.cev.get_item_area(configModel.turn_counter-1, "building");
                   if(building_area.size()>=7)
                   {
                       System.out.println("you win the game");
                       configModel.status = true;
                   }
                   
                }
                else if(configModel.no_of_players==3)
                {
                    List<Integer> building_area = configModel.cev.get_item_area(configModel.turn_counter-1, "building");
                   if(building_area.size()>=5)
                   {
                       System.out.println("you win the game");
                       configModel.status = true;
                   }
                }
                else if(configModel.no_of_players==4)
                {
                    List<Integer> building_area = configModel.cev.get_item_area(configModel.turn_counter-1, "building");
                   if(building_area.size()>=4)
                   {
                       System.out.println("you win the game");
                       configModel.status = true;
                   }
                }
                else
                {
                     System.out.println("| \t \t \t \t \t\t \t \t \t |");
                    System.out.println("|\t\tyou are not win the game yet ! Play Smart !\t\t |");
                    System.out.println("| \t \t \t \t \t\t \t \t \t |");
                    System.out.println("|-------------------------------------------------------------------------|");
                    
                }
        return configModel.status;
        
    }
    
}
