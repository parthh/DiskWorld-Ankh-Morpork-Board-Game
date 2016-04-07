/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winstrategy;
import ankh.view.configModel;
import winstrategy.Wincheck_interface;

/**
 *
 * @author admin
 */
public class Default_wining implements Wincheck_interface{

    @Override
    public boolean winnning_check() {
        System.out.println("inside the annoynoums class");
        int player[] = new int[configModel.no_of_players];
        for(int i=0;i<configModel.no_of_players;i++)
        {
           player[i] =configModel.Player_detail_list.get(i).get_worth();
        }
        int max = player[0];
        int j=0;
        
        for(;j<configModel.no_of_players;j++)
        {
            if(player[j+1]>max)
            {
               max = player[j];
               break;
            }
        }
        if(j==4)
        {
            
               System.out.println("player "+ configModel.Player_detail_list.get(0).getplayer_name()+"win the game and his worth is"+configModel.Player_detail_list.get(0).get_worth());
               configModel.status = true;
        }
        else
        {
            System.out.println("player "+ configModel.Player_detail_list.get(j).getplayer_name()+"win the game and his worth is"+configModel.Player_detail_list.get(j).get_worth());
            configModel.status = true;
        }
        
        return  configModel.status;
    }
    
}
