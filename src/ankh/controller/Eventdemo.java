/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.controller;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author admin
 */
 public enum Eventdemo
{
     
    PLACE_MINION(1),
    PLACE_BUILDING(2),
    ASSASSINATION(3),
    REMOVE_ONE_TROUBLE(4),
    TAKE_MONEY(5),
    SCROLL(6),
    RANDOM_EVENT(7),
    PLAY_ANOTHER_CARD(8),
    INTERRUPT(9),
    dragon(10),
    fog(11),
    explosion(12),
    subsidence(13),
    Earthquake(14);
    
   private static final Map<Integer,Eventdemo> lookup 
      = new HashMap<Integer,Eventdemo>();
    
    static
    {
            for(Eventdemo s : EnumSet.allOf(Eventdemo.class))
     {
           lookup.put(s.getCode(), s);
     }
    }
    public  int code;
     Eventdemo(int code) {
      this.code = code;
 }
    public int getCode() { return code; }

    public static Eventdemo get(int code) { 
         return lookup.get(code); 
    }
}

