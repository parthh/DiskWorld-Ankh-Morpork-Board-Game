/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winstrategy;
import winstrategy.Wincheck_interface;
/**
 *
 * @author admin
 */
public class Wincheck {
    public Wincheck_interface wincheck;
 
    public Wincheck(Wincheck_interface wincheck)
    {
        this.wincheck = wincheck;
    }
    public boolean execute_operation()
    {
        return wincheck.winnning_check();
    }
   
}
