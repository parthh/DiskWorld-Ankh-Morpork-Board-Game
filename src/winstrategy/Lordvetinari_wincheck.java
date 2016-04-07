/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winstrategy;

import ankh.view.configModel;
import java.util.List;
import winstrategy.Wincheck_interface;
/**
 *
 * @author admin
 */
public class Lordvetinari_wincheck implements Wincheck_interface{

    @Override
    public boolean winnning_check() {
        
                if(configModel.no_of_players==2)
                {
                   List<Integer> minion_area = configModel.cev.get_area_without_demon(configModel.turn_counter-1, "minion");
                   
                   if(minion_area.size()>=11)
                   {
                       System.out.println("you win the game");
                       configModel.status = true;
                   }
                    else
                    {
                        System.out.println("you are not win yet ! Play Smart");

                    }
                   
                }
                else if(configModel.no_of_players==3)
                {
                    List<Integer> minion_area = configModel.cev.get_item_area(configModel.turn_counter-1, "minion");
                   if(minion_area.size()>=10)
                   {
                       System.out.println("you win the game");
                       configModel.status = true;
                   }
                    else
                    {
                        System.out.println("you are not win yet ! Play Smart");

                    }
                }
                else if(configModel.no_of_players==4)
                {
                    List<Integer> minion_area = configModel.cev.get_item_area(configModel.turn_counter-1, "minion");
                   if(minion_area.size()>=9)
                   {
                       System.out.println("you win the game");
                       configModel.status = true;
                   }
                    else
                    {
                    System.out.println("| \t \t \t \t \t\t \t \t \t |");
                    System.out.println("|\t\tyou are not win the game yet ! Play Smart !\t\t |");
                    System.out.println("| \t \t \t \t \t\t \t \t \t |");
                    System.out.println("|-------------------------------------------------------------------------|");

                    }
                }
                else
                {
                    System.out.println("no city area card");
                }
               
                
        return configModel.status;
    }
    
}
