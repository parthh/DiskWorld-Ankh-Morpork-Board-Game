package ankh.view;



import ankh.model.Player_detail;
import ankh.saveloadfile.ReadXmlFile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class SelectPlayers extends JFrame{
    
    JLabel title;
    Container cpane;
    public JComboBox[] jcb ;
    public JTextField jlb[];
    JPanel player_selected_panel;
    public ArrayList<String> colors;
    public ArrayList<Player_detail> Player_detail_list;
    JButton proceed;
    String message;

    int player_id=1;
    NewGameView theView;
    public boolean condition = false;
   
    
    configModel c_m;
    ReadXmlFile reader;
    MapcreateView MapView;
    int  number_of_player;
    
    Border borderRed= BorderFactory.createLineBorder(Color.RED, 5);
    Border borderYellow= BorderFactory.createLineBorder(Color.YELLOW, 5);
    Border borderGreen= BorderFactory.createLineBorder(Color.GREEN, 5);
    Border borderBlue= BorderFactory.createLineBorder(Color.BLUE, 5);
    Border borderWhite= BorderFactory.createLineBorder(Color.WHITE, 5);
    
  private BufferedImage img ;
  
    public SelectPlayers(){
        
        try {                
           img = ImageIO.read(new File("resources/bg.jpg"));
       } catch (IOException ex) {
            // handle exception...
       }
        mainPanel.setOpaque(true);
    // set the JFrame's contentPane to our image drawing JPanel:
        setContentPane(mainPanel);
        cpane = getContentPane();

    
        Player_detail_list= new ArrayList<Player_detail>();
        message = new String();
        proceed= new JButton("Save and Continue");
        proceed.setBackground(Color.cyan);
  proceed.setBounds(650, 650, 100, 50);
        proceed.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                boolean invalid_color=false;
                for(JComboBox iterator:jcb)
                {
                        if(iterator.getSelectedIndex()==0)
                       {
                          JOptionPane.showMessageDialog(player_selected_panel, "kindly select a color for each player","Color is Necessary",JOptionPane.ERROR_MESSAGE);
                           invalid_color=true;
                           break;
                       }
                }
             
                   //new ActualGamePlay();
                if(!invalid_color)
                {
                    startgame();
                }
                
          
            }
        });
        title = new JLabel();
        
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setVerticalAlignment(JLabel.CENTER);
        
        title.setIcon(new javax.swing.ImageIcon("resources/logo.jpg"));
         
        cpane.setLayout(new BoxLayout(cpane, BoxLayout.Y_AXIS));
         
      
        cpane.add(title);
        jlb= new JTextField[configModel.no_of_players];
        jcb= new JComboBox[configModel.no_of_players];
        for(int index=0;index<configModel.no_of_players;index++)
        {
            jcb[index]= new JComboBox();

            jcb[index].setBounds(100, 100+index*10, 50,50);
            jlb[index]= new JTextField(16);
            jlb[index].setBounds(250, 100+index*10, 50,50);
        }
  
        setSize(700,700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        player_selected_panel= new JPanel();

        player_selected_panel.setLayout(new FlowLayout());
        player_selected_panel.setSize(700, 700);
        player_selected_panel.setOpaque(false);
        colors= new ArrayList<String>();
        colors.add("select color");
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        colors.add("yellow");

        cpane.add(player_selected_panel);
        for(int index=0;index<configModel.no_of_players;index++)
        {
            
            player_selected_panel.add(jlb[index]);
            player_selected_panel.add(jcb[index]);
            for(String iterator:colors)
            {
                jcb[index].addItem(iterator);
            }
            jlb[index].setText("player"+(index+1));
            jcb[index].setName("player"+(index+1));
            
        }

        player_selected_panel.add(proceed);
        this.setAlwaysOnTop(true);
        //this.add(player_selected_panel);
        for(int i=0;i<configModel.no_of_players;i++)
        {
               jcb[i].addItemListener(new SelectionChanged());
        }
        
        

    }
    
    private JPanel mainPanel = new JPanel()
  {
    
    @Override
    protected void paintComponent(Graphics g)
    {
      super.paintComponent(g);
      
      if (img != null)
      {
        int width = mainPanel.getWidth();
        int height = mainPanel.getHeight();
        // and do the drawing here:
        g.drawImage(img, 0, 0, width, height, this);
      }
    }
  };
     /**
     * This method will add ActionListener to the buttons like Play, Create Map and Exit.
     * @param ListnerForButton ActionListner object.
     */
    
    /**
     * This method will set Main Screen on top.
     */
    public void setTopEnabled(){
        this.setAlwaysOnTop(true);
        cpane.setEnabled(true);
    }
    
    /**
     * This method create Message show dialog box.
     * @param str message string.
     */

    public void displayMessage(String str){
        JOptionPane.showMessageDialog(this, str);
    }

   
public static void main(String args[])
{
    SelectPlayers sp= new SelectPlayers();
}
public void startgame()
{
                    condition = true;
                    MapView = new MapcreateView("start");
                    super.dispose();
                   // MapView.setPlayerTurn(configModel.no_of_players,"star");
                    MapView.paint(MapView.getGraphics());
                   // this.dispose();
                   // this.setVisible(false);
}

class SelectionChanged implements ItemListener
{
    
    @Override
    public void itemStateChanged(ItemEvent e)
    {
        if(e.getStateChange()==(ItemEvent.SELECTED))
        {
            String name=jlb[player_id-1].getText();
            String color=(((JComboBox)e.getSource()).getSelectedItem()).toString();
            ((JComboBox)e.getSource()).setEnabled(false);
            configModel.Player_detail_list.add(new Player_detail(player_id++,name,color,configModel.player_minion,configModel.player_building));
            
            colors.remove(((JComboBox)e.getSource()).getSelectedItem());
            for(String iterator:colors)
            System.out.println(iterator+"    ");
            for(int index_for_combo=0;index_for_combo<configModel.no_of_players;index_for_combo++)
             {
                    String s1=(((JComboBox)e.getSource()).getName());
                    String s2=jcb[index_for_combo].getName();
                    if(s2.equals(s1))
                    {
                    }
                    else
                    {
                       jcb[index_for_combo].removeItem(color);
                       
                                      

                    }
                   // for(int i=0;i<GlobalValue.no_of_players;i++)
                    //{
                      //  DefaultComboBoxModel model=new DefaultComboBoxModel();
                        //for(String iterator:colors)
                       //{
                         //  System.out.println("the value of the color according to the Array List  "+iterator);
                          //jcb[index_for_combo]=new JComboBox();
                        //  model.addElement(iterator);
                          //jcb[index_for_combo].addItem(iterator);
                       //}
                       // jcb[index_for_combo].setModel(model);
             //       }
                    
              }
                 
            
            
        }
        
        
    }
}
}
