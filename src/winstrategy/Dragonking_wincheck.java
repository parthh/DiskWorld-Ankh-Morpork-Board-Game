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
public class Dragonking_wincheck implements Wincheck_interface{

    @Override
    public boolean winnning_check() {
        if(configModel.player_trouble_maker<=4)
                {
                    System.out.println("you win the game");
                    configModel.status = true;
                }
        return configModel.status;
    }
}
    

