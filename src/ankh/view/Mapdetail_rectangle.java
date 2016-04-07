/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import ankh.controller.RectGrid;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is Mapdetail_rectangle is used to store the detail of every area rectangle.
 * @author Parth B Gadhiya
 */
public class Mapdetail_rectangle extends Rectangle{
    
    int area_id;
    char minion[]=null;
    boolean trouble;
    boolean building;
    int demon;
    int trolls;
    int xcordinate;
    int ycordinate;
    int width_rectangle;
    int height_rectangle;
    int corner_x=10;
    int corner_y=10;
    String area_name;
    String area_detail;
    int adjacent[]=null;
    int area_cost;
    int[][] line_map = new int[29][4];
    RectGrid[] r_g ;
    
    
    
    
    
    public Mapdetail_rectangle()
    {
        r_g = new RectGrid[24];
    }
    
    /**
     * @param line_map the line_map to set
     */
    public void setline_map(int[][] line_map)
    {
        this.line_map = line_map;
    }
    /**
     * @return the line_map
     */
    public int[][] getline_map()
    {
        return line_map;
    }
    /**
     * @param area_id the area_id to set
     */
    public void setareaid(int area_id)
    {
        this.area_id = area_id;
    }
    /**
     * @return the area_id
     */
    public int getareaid()
    {
        return area_id;
    }
    /**
     * @param minion the minion to set
     */
    public void setminion(char minion[])
    {
        this.minion = minion;
    }
    /**
     * @return the minion
     */
    public char[] getminion()
    {
        return minion;
    }
    /**
     * @param trouble the trouble to set
     */
    public void settrouble(boolean trouble)
    {
        this.trouble = trouble;
    }
    /**
     * @return the trouble
     */
    public boolean gettrouble()
    {
        return trouble;
        
    }
    /**
     * @param building the building to set
     */
    public void setbuilding(boolean building)
    {
        this.building = building;
    }
    /**
     * @return the building
     */
    public boolean getbuilding()
    {
        return building;
        
    }
    /**
     * @param demon the demon to set
     */
    public void setdemon(int demon)
    {
        this.demon = demon;
    }
    /**
     * @return the demon
     */
    public int getdemon()
    {
        return demon;
    }
    /**
     * @param trolls the trolls to set
     */
     public void settroll(int trolls)
    {
        this.trolls = trolls;
    }
    /**
     * @return the trolls
     */
    public int gettrolls()
    {
        return trolls;
    }
    /**
     * @param xcordinate the xcordinate to set
     */
      public void setxcordinate(int xcordinate)
    {
        this.xcordinate = xcordinate;
    }
    /**
     * @return the xcordinate
     */
    public int getxcordinate()
    {
        return xcordinate;
    }
    /**
     * @param ycordinate the ycordinate to set
     */
      public void setycordinate(int ycordinate)
    {
        this.ycordinate = ycordinate;
    }
    /**
     * @return the ycordinate
     */
    public int getycordinate()
    {
        return ycordinate;
    }
    /**
     * @param width_rectangle the width_rectangle to set
     */
      public void setwidth_rectangle(int width_rectangle)
    {
        this.width_rectangle = width_rectangle;
    }
    /**
     * @return the width_rectangle
     */
    public int getwidth_rectangle()
    {
        return width_rectangle;
    }
    /**
     * @param height_rectangle the height_rectangle to set
     */
      public void setheight_rectangle(int height_rectangle)
    {
        this.height_rectangle = height_rectangle;
    }
    /**
     * @return the height_rectangle
     */
    public int getheight_rectangle()
    {
        return height_rectangle;
    }
    /**
     * @param area_name the area_name to set
     */
      public void setarea_name(String area_name)
    {
        this.area_name = area_name;
    }
    /**
     * @return the area_name
     */
    public String getarea_name()
    {
        return area_name;
    }
    /**
     * @param area_detail the area_detail to set
     */
      public void setarea_detail(String area_detail)
    {
        this.area_detail = area_detail;
    }
    /**
     * @return the area_detail
     */
    public String getarea_detail()
    {
        return area_detail;
    }
    /**
     * @param area_cost the area_cost to set
     */
      public void setarea_cost(int area_cost)
    {
        this.area_cost = area_cost;
    }
    /**
     * @return the area_cost
     */
    public int getarea_cost()
    {
        return area_cost;
    }
    /**
     * @param adjacent the adjacent to set
     */
      public void setadjacent(int[] adjacent)
    {
        this.adjacent = adjacent;
    }
    /**
     * @return the adjacent
     */
    public int[] getadjacent()
    {
        return adjacent;
    }
    /**
     * @param pos is the position of grid inside area where user set the component.
     * @param x is xcordinate of single grid.
     * @param y  is ycordinate of single grid.
     * @param h is height of single grid.
     * @param w is width of single grid.
     */
   public void set_rectgrid_obj(int pos,int x,int y, int h, int w)
   {
       r_g[pos] = new RectGrid();
       r_g[pos].set_rectgrid(x, y, x, x);
   }
   /**
     * @return every grid of particular area object
     */
   public RectGrid[] get_rectgrid_obj()
   {
       return r_g;
   }
    
    
}
