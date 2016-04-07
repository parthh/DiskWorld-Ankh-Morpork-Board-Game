/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankhmorporkgame;

import ankh.view.MainscreenView;


/**
 *
 * @author admin
 */
public class AnkhMorPorkSingle_Object {
     
    private MainscreenView theView;
    
     private static AnkhMorPorkSingle_Object instance;
   /** 
    * Make the constructor private so that this class cannot be instantiated
    */
   private AnkhMorPorkSingle_Object(){
        this.theView = new MainscreenView();
   }
   /**
    * If the instance was not previously created, create it. Then return the instance
    * @return the TowerDefenseGame_SingleObject
    */
   public static AnkhMorPorkSingle_Object getInstance(){
      if (instance == null)
      {
        instance = new AnkhMorPorkSingle_Object();
      }
      return instance;
   }

    /**
     * @return the theView
     */
    public MainscreenView getTheView() {
        return theView;
    }

    /**
     * @return the theModel
     */
    }
    
    

