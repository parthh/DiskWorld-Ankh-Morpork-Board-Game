/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import ankh.controller.CurrentPlayer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class Win_handler {
    Eventhandler cev = new Eventhandler();
    boolean status = false;

    public boolean win_cond_check(String name) {
        switch (name) {
            case "Lord Vetinari":
            {
                if(configModel.no_of_players==2)
                {
                   List<Integer> minion_area = cev.get_area_without_demon(configModel.turn_counter-1, "minion");
                   
                   if(minion_area.size()>=11)
                   {
                       System.out.println("you win the game");
                       status = true;
                   }
                   
                }
                else if(configModel.no_of_players==3)
                {
                    List<Integer> minion_area = cev.get_item_area(configModel.turn_counter-1, "minion");
                   if(minion_area.size()>=10)
                   {
                       System.out.println("you win the game");
                       status = true;
                   }
                }
                else if(configModel.no_of_players==4)
                {
                    List<Integer> minion_area = cev.get_item_area(configModel.turn_counter-1, "minion");
                   if(minion_area.size()>=9)
                   {
                       System.out.println("you win the game");
                       status = true;
                   }
                }
                else
                {
                    System.out.println("you are not win yet ! Play Smart");
                    status = true;
                }
                
            }
                break;
            case "Lord Selachii":
            {
                building_control();
            }
                break;
            case "Lord Rust":
            {
                building_control();
            }
                break;
            case "Lord de Worde":
            {
                building_control();
            }
                break;

            case "Dragon King of Arms":
            {
                if(configModel.player_trouble_maker<=4)
                {
                    System.out.println("you win the game");
                    status = true;
                }
            }
                break;
            case "Chrysoprase":
            {
                int build_cost = 0;
                
                List<Integer> building_cost = cev.get_area_without_demon(configModel.turn_counter-1, "building");
                for(Integer cost:building_cost)
                {
                    build_cost+=configModel.Area_detail_list.get(cost-1).getarea_cost();
                }
                int player_money = CurrentPlayer.currentPlayer.getplayer_money();
                int loan = CurrentPlayer.currentPlayer.get_loan();
                
                int total_money = (build_cost+player_money)-(loan*12);
                System.out.println("total worth of palyer is"+ total_money);
                System.out.println("my woth is"+configModel.Player_detail_list.get(configModel.turn_counter-1).get_worth());
                if(total_money>=50)
                {
                    System.out.println("you win the game");
                    status = true;
                }
                else
                {
                    System.out.println("you are not win the game yet ! Play Smart !");
                }
                
            }
                break;
            case "Commander Vimes":
            {
                if((configModel.greencard_random_number.size()==0) && (configModel.brown_random_number.size()==0))
                {
                    System.out.println("you win the game");
                    status = true;
                }
                else
                {
                    System.out.println("you are not win the game yet ! Play Hard !");
                }
            }
                break;
            default:
                break;

        }
        return status;
    }
    
    public boolean building_control()
    {
        if(configModel.no_of_players==2)
                {
                   List<Integer> building_area = cev.get_item_area(configModel.turn_counter-1, "building");
                   if(building_area.size()>=7)
                   {
                       System.out.println("you win the game");
                       status = true;
                   }
                   
                }
                else if(configModel.no_of_players==3)
                {
                    List<Integer> building_area = cev.get_item_area(configModel.turn_counter-1, "building");
                   if(building_area.size()>=5)
                   {
                       System.out.println("you win the game");
                       status = true;
                   }
                }
                else if(configModel.no_of_players==4)
                {
                    List<Integer> building_area = cev.get_item_area(configModel.turn_counter-1, "building");
                   if(building_area.size()>=4)
                   {
                       System.out.println("you win the game");
                       status = true;
                   }
                }
                else
                {
                    System.out.println("you are not win yet ! Play Smart");
                    status = true;
                }
        return status;
    }
}
