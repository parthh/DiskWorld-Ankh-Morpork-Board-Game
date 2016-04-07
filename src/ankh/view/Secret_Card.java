/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

/**
 *
 * @author admin
 */
public class Secret_Card {
    
    int id;
    String secret_title;
    String secret_desc;
    
    
     public void set_data(int id,String title,String desc) {

         this.id = id;
         this.secret_title = title;
         this.secret_desc = desc;
       

    }
     public String get_title()
     {
         return secret_title;
     }
     
     public String get_desc()
     {
         return secret_desc;
     }
}
