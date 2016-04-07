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
public class Random_Card {
    
    int id;
    String random_title;
    String random_desc;
    

    
     public void set_data(int id,String title,String desc) {

         this.id = id;
         this.random_title = title;
         this.random_desc = desc;
       

    }

     public String get_title()
     {
         return random_title;
     }

     
     public String get_desc()
     {
         return random_desc;
     }
}


