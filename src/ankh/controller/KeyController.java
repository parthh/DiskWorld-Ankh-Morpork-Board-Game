/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.controller;

import ankh.view.MapcreateView;
import ankh.view.configModel;
import static ankh.view.configModel.player;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Collections;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * This is KeyController class to handle all mouse listener events.
 * @author Parth B Gadhiya
 */
public class KeyController implements MouseMotionListener, MouseListener {

    public MapcreateView mapView;
    public int[] minion, building;
    public int minclick;
    public boolean rclick;
    public int rcx, rcy, rpointer;
    public boolean mouse_click = false;
    public int curren_turn;
    public RectGrid[] rgb_relese;
    public int checkout_player;
/**
 * @param mapView this is parameter of MapCreateView Screen to handle their mouse events
 */
    public KeyController(MapcreateView mapView) {
        this.mapView = mapView;
        minion = new int[configModel.no_of_players];
        building = new int[configModel.no_of_players];

        Date d = new Date(2010, 04,17);
        d.toString();


    }
    /**
     * This Method will listen mouse drag movement and store x,y coordinates.
     * @param e the MouseEvent
     */
    @Override
    public void mouseDragged(MouseEvent e) {

        mapView.cposx = e.getX();
        mapView.cposy = e.getY();
        mapView.drag = 1;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    /**
     * This Method will listen mouse click event on for next turn and save game.
     * @param e the MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("inside the mouse click");
        /* Next Button click*/
        Rectangle s_game = new Rectangle(50, 650, 100, 30);
        if (s_game.contains(e.getX(), e.getY())) {
            System.out.println("inside next click");
            if (configModel.turn_counter < mapView.number_player) {
                configModel.turn_counter++;

            } else {
                configModel.turn_counter = 1;
            }
        }
        /*Save Button Click*/

        Rectangle next_turn = new Rectangle(50, 600, 100, 30);
        if (next_turn.contains(e.getX(), e.getY())) {

            new SaveNameChooser(mapView, configModel.turn_counter);

        }
        
         Rectangle random_turn = new Rectangle(50, 700, 100, 30);
       
    }

    /**
     * This method create Message show dialog box.
     * @param str message string.
     */
    public void displayMessage(String str) {
        JOptionPane.showMessageDialog(mapView, str);
    }
    /**
     * This Method will listen mouse pressed event to handle drag from player to game area.
     * @param e the MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        curren_turn = configModel.turn_counter - 1;
        int minionxy[] = configModel.Player_detail_list.get(curren_turn).get_minion_position();
        Rectangle playerarea = new Rectangle(mapView.p1_x, mapView.p1_y + (curren_turn * 170), mapView.p1_w, mapView.p1_h);
        if (playerarea.contains(e.getX(), e.getY())) {

            Rectangle minionarea = new Rectangle(minionxy[0], minionxy[1], configModel.minion_w, configModel.minion_h);
            if (minionarea.contains(e.getX(), e.getY())) {
                minion[curren_turn] = 1;
                mouse_click = true;
            } else {
                displayMessage("Select from your area!!");
            }
        }

        int area = getCurrentare(e.getX(), e.getY());
        rgb_relese = configModel.Area_detail_list.get(area).get_rectgrid_obj();
        for (int grid = 0; grid < 24; grid++) {

            int[] cg = rgb_relese[grid].get_rectgrid();
            int cx = cg[0];
            int cy = cg[1];

            Rectangle removeminion = new Rectangle(cx, cy, configModel.minion_w, configModel.minion_h);
            if (removeminion.contains(e.getX(), e.getY())) {
                if (rgb_relese[grid].enable) {
                    rcx = cx;
                    rcy = cy;
                    checkout_player = rgb_relese[grid].player_id;
                    rclick = rgb_relese[grid].enable;
                    rpointer = grid;
                    System.out.println("You Click for remove");
                }
            }

        }

    }
    /**
     * This Method will listen mouse Release event to store minion on game area.
     * @param e the MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {

        mapView.drag = 0;
        if (mouse_click) {

            int area = getCurrentare(e.getX(), e.getY());
            RectGrid[] rgb = configModel.Area_detail_list.get(area).get_rectgrid_obj();
            for (int grid = 0; grid < 24; grid++) {
                int[] cg = rgb[grid].get_rectgrid();
                int cx = cg[0];
                int cy = cg[1];
                if (minion[curren_turn] == 1) {
                    Rectangle minionarea = new Rectangle(cx, cy, configModel.minion_w, configModel.minion_h);
                    if (minionarea.contains(e.getX(), e.getY())) {
                        //int item_status = rgb[grid].get_item_status();
                        if (!rgb[grid].enable) {

                            //  rgb[grid].set_item_status(1);
                            minion[curren_turn] = 0;
                            int p_minion = configModel.Player_detail_list.get(curren_turn).getplayer_minion();
                            int c_minion = p_minion - 1;
                            configModel.Player_detail_list.get(curren_turn).setplayer_minion(c_minion);

                            rgb[grid].enable = true;
                            rgb[grid].player_id = curren_turn;
                            rgb[grid].type = "minion";

                        } else {
                            System.out.println("Already Item Present");
                        }
                    }

                }
            }

            mouse_click = false;
        }
        /**
         * remove Minion from area *
         */
        if (rclick) {
            Rectangle item_remove_area;

            if (rgb_relese[rpointer].type.equalsIgnoreCase("minion")) {

                int minionxy[] = configModel.Player_detail_list.get(curren_turn).get_minion_position();
                Rectangle minionarea = new Rectangle(minionxy[0], minionxy[1], configModel.minion_w, configModel.minion_h);
                if (minionarea.contains(e.getX(), e.getY())) {
                    if (curren_turn == checkout_player) {
                        int p_minion = configModel.Player_detail_list.get(curren_turn).getplayer_minion();
                        int c_minion = p_minion + 1;
                        configModel.Player_detail_list.get(curren_turn).setplayer_minion(c_minion);

                        rgb_relese[rpointer].enable = false;
                        rgb_relese[rpointer].player_id = 0;
                        rgb_relese[rpointer].type = "";
                        System.out.println("Remove");

                    }
                    rclick = false;
                }
            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    /**
     * This Method will get x and y cordinate of mouse and return area number.
     * @param x x Cordinate of mouse.
     * @param y y cordinate of mouse.
     * @return area_number.
     */
    public int getCurrentare(int x, int y) {
        int area_number = 0;
        for (int i = 0; i < 12; i++) {
            int xcord = configModel.Area_detail_list.get(i).getxcordinate();
            int ycord = configModel.Area_detail_list.get(i).getycordinate();
            int height = configModel.Area_detail_list.get(i).getheight_rectangle();
            Rectangle area_rect = new Rectangle(xcord, ycord, height, height);
            if (area_rect.contains(x, y)) {
                area_number = i;
                break;
            }
        }
        return area_number;
    }
}
