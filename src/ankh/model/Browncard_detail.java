/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.model;

/**
 *
 * @author admin
 */
public class Browncard_detail {
    int card_id;
    String card_name;
    int[] card_event=null;
    String card_detail;
    int money=0;
    
    public Browncard_detail()
    {
        
    }
     public void Browncard_setdetail(int card_id,String card_name, int[] card_event, String card_detail)
    {
        this.card_id = card_id;
        this.card_name = card_name;
        this.card_event = card_event;
        this.card_detail = card_detail;
    }
    
    public int getCard_id()
    {
        return card_id;
    }
    public String getCard_name()
    {
        return card_name;
    }
    public int[] getcard_event()
    {
        return card_event;
    }
    public String getCard_detail()
    {
        return card_detail;
    }
     public int getselected_card(int id)
    {
        return card_event[id];
    }
     public void setmoney(int money)
     {
         this.money = money;
         
     }
     public int getmoney()
     {
         return money;
     }
             
}
