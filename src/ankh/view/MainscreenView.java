/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.view;

import ankh.controller.GameChooser;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This is GUI class for Main Screen. It will show buttons(Play, Loadgame,
 * Exit).
 *
 * @author Parth B Gadhiya
 */
public class MainscreenView extends JFrame {

    JButton start_game;
    JButton exit_btn;
    JButton load_btn;
    JLabel title;

    Container cpane;
    private BufferedImage img;
    NewGameView newgame;
    public boolean game_load_test=false;
    public boolean chec_screen_view = false;
    public MainscreenView thisown;

    public MainscreenView() {
        try {
            img = ImageIO.read(new File("resources/bg.jpg"));
        } catch (IOException ex) {
            // handle exception...
        }
        mainPanel.setOpaque(true);
        // set the JFrame's contentPane to our image drawing JPanel:
        setContentPane(mainPanel);
        cpane = getContentPane();

        start_game = new JButton("Start Game");
        load_btn = new JButton("Load Game");
        exit_btn = new JButton("Exit");
        title = new JLabel();

        start_game.setAlignmentX(Component.CENTER_ALIGNMENT);
        load_btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit_btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        start_game.setVerticalAlignment(JLabel.CENTER);
        load_btn.setVerticalAlignment(JButton.CENTER);
        exit_btn.setVerticalAlignment(JButton.CENTER);
        title.setVerticalAlignment(JButton.CENTER);

        title.setIcon(new javax.swing.ImageIcon("resources/logo.jpg"));

        cpane.setLayout(new BoxLayout(cpane, BoxLayout.Y_AXIS));

        //ms = new MainScreenPanel();
        //this.getContentPane().add(ms);
        cpane.add(title);
        cpane.add(start_game);
        cpane.add(load_btn);
        cpane.add(exit_btn);

        setSize(700, 700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        start_game.addActionListener(new ListnerForButton());
        exit_btn.addActionListener(new ListnerForButton());
        load_btn.addActionListener(new ListnerForButton());

    }
    private JPanel mainPanel = new JPanel() {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (img != null) {
                int width = mainPanel.getWidth();
                int height = mainPanel.getHeight();
                // and do the drawing here:
                g.drawImage(img, 0, 0, width, height, mainPanel);
            }
        }
    };

    /**
     * This method will set Main Screen on top.
     */
    public void setTopEnabled() {
        this.setAlwaysOnTop(true);
        cpane.setEnabled(true);
    }

    /**
     * This method create Message show dialog box.
     *
     * @param str message string.
     */
    public void displayMessage(String str) {
        JOptionPane.showMessageDialog(this, str);
    }

    public class ListnerForButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String tempBtnStr = e.getActionCommand();
            if (e.getSource() instanceof JButton) {
                if (tempBtnStr.equals("Start Game")) {
                    // LogGenerator.addLog("Play button clicked.");
                    initnewgame();

                }

                if (tempBtnStr.equals("Load Game")) {
                    System.out.println("Load Game clicked.");
                    loadgame();

                }

                if (tempBtnStr.equals("Exit")) {
                   
                    System.exit(0);
                }
            }

        }
    }
     /**
     * This loadgame method will start GameChooser to load existing save files.
     */
    public void loadgame() {
        GameChooser gc;
        gc = new GameChooser(this);
        game_load_test = true;
        //this.dispose();
        //System.exit(0);
    }
     /**
     * This initnewgame method will start NewGameVie to select number of player
     */
    public void initnewgame() {
        
        NewGameView mcView = new NewGameView();
        mcView.setVisible(true);
        mcView.setAlwaysOnTop(true);
        mcView.setFocusable(true);
        this.setVisible(false);
        chec_screen_view =true;
        
    }

}
