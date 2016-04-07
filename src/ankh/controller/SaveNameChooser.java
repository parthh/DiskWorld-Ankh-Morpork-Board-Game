/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.controller;

import ankh.view.MainscreenView;
import ankh.view.MapcreateView;
import ankh.saveloadfile.SaveGame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * This is SaveNameChooser class for players to save their game.
 * @author Parth B Gadhiya
 */
public class SaveNameChooser extends JFrame implements ActionListener {

    MapcreateView mapview;
    JTextField file_name;
    JButton save_file;
    int turn_counter;
    MainscreenView theView;

    /**
     * This method will initialize and bind View.
     * @param mv the view object of main map screen.
     * @param turn_counter this is save the turn of player in file.
     */
    
    public SaveNameChooser(MapcreateView mv, int turn_counter) {
        mapview = mv;
        this.turn_counter = turn_counter;
        this.setTitle("Save Game");

        this.setSize(200, 300);
        setlayout();
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setVisible(true);

    }

    /**
     * this method will set top properties of JFrame.
     * @param f the boolean value
     */
    public void setMSTOp(boolean f) {
        mapview.setAlwaysOnTop(f);
    }

    /**
     * this method will set button and TextFiled in layout.
     * Box.
     */
    public void setlayout() {

        this.setLayout(new BorderLayout(5, 5));

        file_name = new JTextField(16);
        save_file = new JButton("Save Game");
        this.add(save_file, BorderLayout.SOUTH);
        this.add(file_name, BorderLayout.NORTH);
        save_file.addActionListener(this);
    }
    
    /**
     * This method create Message show dialog box.
     * @param str message string.
     */

    public void displayMessage(String str) {
        JOptionPane.showMessageDialog(this, str);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String tempBtnStr = e.getActionCommand();
        if (e.getSource() instanceof JButton) {
            if (tempBtnStr.equals("Save Game")) {

                String File = file_name.getText();
                new SaveGame(turn_counter, File);
                
                setMSTOp(false);
                dispose();
                
                theView = new MainscreenView();
                mapview.dispose();

            }
        }
    }

}
