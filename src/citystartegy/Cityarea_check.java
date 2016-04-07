/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citystartegy;
import citystartegy.CityInterface;

/**
 *
 * @author admin
 */
public class Cityarea_check {
    public CityInterface CityInterface;
    public int cityid=-1;
 
    public Cityarea_check(CityInterface CityInterface,int city_id)
    {
        this.CityInterface = CityInterface;
        this.cityid = city_id;
    }
    public boolean execute_operation(int card_number , int player_id)
    {
        return CityInterface.cityarea_check(card_number, player_id);
    }
   
}
