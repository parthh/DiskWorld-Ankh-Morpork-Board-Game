/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.controller;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLEngineResult.Status;

/**
 *
 * @author admin
 */
public class  Event
{
   static Eventdemo ev;
   public  Event()
    {
     
    }
    
   public String getEventname(Eventdemo ev)
   {
       this.ev = ev;
       return ev.toString();
       
   }
  
}


