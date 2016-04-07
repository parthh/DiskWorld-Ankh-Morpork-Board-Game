/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winstrategy;

import ankh.controller.CurrentPlayer;
import ankh.view.configModel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import winstrategy.Wincheck_interface;
/**
 *
 * @author admin
 */
public class Chrysoprase_wincheck implements Wincheck_interface{

    @Override
    public boolean winnning_check() {
        
       int build_cost = 0;
                
                List<Integer> building_cost = configModel.cev.get_area_without_demon(configModel.turn_counter-1, "building");
                for(Integer cost:building_cost)
                {
                    build_cost+=configModel.Area_detail_list.get(cost-1).getarea_cost();
                }
                int player_money = CurrentPlayer.currentPlayer.getplayer_money();
                int loan = CurrentPlayer.currentPlayer.get_loan();
                
                int total_money = (build_cost+player_money)-(loan*12);
                if(total_money>=50)
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
                return configModel.status;
    }
    
    
}
