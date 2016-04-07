/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.controller;

/**
 *
 * @author p_gadhi
 */
public class RectGrid {

    int[] dia = new int[4];
    public int item_status = 0;
    public boolean enable = false;
    public boolean trouble_status = false;
    public int player_id;
    public String type;
    public String grid_color;
    public int demon_count=0;
    public int troll_count=0;

    
    /**
     * @param set_rectgrid the set_rectgrid to set
     */
    public void set_rectgrid(int x, int y, int h, int w) {

        dia[0] = x;
        dia[1] = y;
        dia[2] = w;
        dia[3] = h;

    }
    /**
     * @return the Rect Grid
     */
    public int[] get_rectgrid() {
        return dia;
    }
    /**
     * @param set_item_status the set_item_status to set
     */
    public void set_item_status(int item) {
        this.item_status = item;
    }
    /**
     * @return the item_status
     */
    public int get_item_status() {
        return this.item_status;
    }
  
    public void set_demon(int demon_count)
    {
        this.demon_count = demon_count;
    }
    public int get_demon()
    {
        return demon_count;
    }
    public void set_troll(int troll_count)
    {
        this.troll_count = troll_count;
    }
    public int get_troll()
    {
        return troll_count;
    }

}
