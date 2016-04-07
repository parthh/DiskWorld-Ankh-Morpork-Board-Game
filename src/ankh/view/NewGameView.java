/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import ankh.model.Player_detail;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This is NewGameView is used to provide selection of number of players in the game.
 * @author Parth B Gadhiya
 */
public class NewGameView extends JFrame {
    String[] num_player = { "Number Of Player","2","3","4" };
    String[] color={"Choose Color","Red","Yellow","Green","Blue"};
    
    String [] color_available=color;
    JComboBox number_player;
    Container cpane;
    JPanel player_number_panel;
    JPanel select_number_player_panel;
    JButton Submit_game;
    JLabel background_image;
    JLabel[] jlb = new JLabel[4];
    JTextField[] jtf = new JTextField[5];
    JComboBox[] jcb = new JComboBox[5];
    int playernumber=0;
    Player_detail[] player_no;

  
    public NewGameView(){
        this.setTitle("Map Creation Window");
        this.setSize(700,700);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new FlowLayout());
        background_image=new JLabel(new ImageIcon("resources/bg.jpg"));
        background_image.setLayout(new FlowLayout());
        this.add(background_image);
        
        player_number_panel =new JPanel();
        player_number_panel.setOpaque(false);
        player_number_panel.setLayout(new FlowLayout());
        
        number_player = new JComboBox(num_player);
        number_player.setSelectedIndex(0);
    
        
    
    
        select_number_player_panel =new JPanel();
        select_number_player_panel.setLayout(new FlowLayout());
        select_number_player_panel.setOpaque(false);
        background_image.add(number_player);
    
    
     
        Submit_game = new JButton("Start Game");
        Submit_game.setSize(50,50);
        background_image.add(Submit_game);
            Submit_game.addActionListener(new ListnerForButton());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
       
    
   
        
}
    
    
  
  
     /**
     * This ListnerForButtonis used to handle the action listener on submit button.
     */
     public class ListnerForButton implements ActionListener
    {


        @Override
        public void actionPerformed(ActionEvent e) 
        {
            boolean invalid_color=false;
            if(number_player.getSelectedIndex()==0)
            {
                          JOptionPane.showMessageDialog(background_image, "Kindly select a Number of Players to Start the game","Selction of players is necessary",JOptionPane.ERROR_MESSAGE);
                           invalid_color=true;
            }
             
                if(!invalid_color)
                {
                   configModel.no_of_players=Integer.parseInt(number_player.getSelectedItem().toString());
                   new SelectPlayers();
                   setVisible(false);
                }
            }
            
            
        }

   
    
   
     /**
     * This method create Message show dialog box.
     *
     * @param str message string.
     */


        public void displayMessage(String str){
           JOptionPane.showMessageDialog(this, str);   
       }

}
