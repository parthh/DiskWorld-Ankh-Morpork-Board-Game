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
public class Commandervimes_wincheck implements Wincheck_interface{

    @Override
    public boolean winnning_check() {
        if((configModel.greencard_random_number.size()==0))
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
