/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ankh.view.MapcreateView;
import ankh.model.Player_detail;
import ankh.view.configModel;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author user
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ankh.view.MapcreateViewTest.class, ankh.view.Card_Event_ActionTest.class, ankh.view.Perform_scrollTest.class,
    ankh.view.TurnHandlerTest.class,ankh.view.CityArea_handlerTest.class, ankh.view.Card_Event_ActionTest1.class,
    ankh.view.MapcreateViewTest1.class,ankh.view.MapcreateViewTest2.class,ankh.view.MapcreateViewTest3.class,
    ankh.view.MapcreateViewTest4.class})
public class TestSuiteBuild2 {
    
    @BeforeClass
    public static void setUpClass() {
        
        configModel.no_of_players = 2;
        Player_detail testPlayer_detail = new Player_detail(0,"Meet","red",configModel.player_minion,configModel.player_building);
        Player_detail testplayer_detail1 = new Player_detail(1,"Parth","green",configModel.player_minion,configModel.player_building); 
        configModel.Player_detail_list.add(testPlayer_detail);
        configModel.Player_detail_list.add(testplayer_detail1);
        MapcreateView instance = new MapcreateView("start");
        }
     
    
}
