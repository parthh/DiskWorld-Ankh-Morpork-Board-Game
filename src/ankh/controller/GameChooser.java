/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ankh.controller;

import ankh.view.MainscreenView;
import ankh.view.MapcreateView;
import ankh.saveloadfile.ReadXmlFile;
import ankh.view.configModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * This is GameChooser class to load list of save files.
 *
 * @author Parth B Gadhiya
 */
public class GameChooser extends JFrame implements ActionListener {

    private JButton showButton;
    private JList loadgame_list;
    MainscreenView msView;
    private String[] FileList;
    MapcreateView mapview;
    public boolean check_game =false;

    /**
     * This method will initialize and bind View.
     *
     * @param msView the view object of main screen.
     */
    public GameChooser()
    {
        
    }
    public GameChooser(MainscreenView msView) {
        listFilesForFolder(new File("SaveGames"));

        this.setTitle("Select Saved Game");

        this.setSize(200, 300);
        this.msView = msView;
        listMapFiles();
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }

    /**
     * this method will set top properties of JFrame.
     *
     * @param f the boolean value
     */
    public void setMSTOp(boolean f) {
        msView.setAlwaysOnTop(f);
    }

    /**
     * this method will read all files from MapFiles folder and list in JList
     * Box.
     */
    public void listMapFiles() {

        this.setLayout(new BorderLayout(5, 5));
        final DefaultListModel fruitsName = new DefaultListModel();

        for (int i = 0; i < FileList.length; i++) {
            fruitsName.addElement(FileList[i]);
        }

        loadgame_list = new JList(fruitsName);
        loadgame_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        loadgame_list.setSelectedIndex(0);
        loadgame_list.setVisibleRowCount(3);

        JScrollPane savedgameListScrollPane = new JScrollPane(loadgame_list);

        showButton = new JButton("Let's Play");
        this.add(savedgameListScrollPane, BorderLayout.CENTER);
        this.add(showButton, BorderLayout.SOUTH);
        showButton.addActionListener(this);
        
    }

    /**
     * This method will return the name of file that selected by user.
     *
     * @return the file name
     */
    public String getSelectedFile() {
        if (loadgame_list.getSelectedIndex() != -1) {
            return loadgame_list.getSelectedValue().toString();
        } else {
            return "NONE";
        }
    }

    /**
     * This method create Message show dialog box.
     *
     * @param str message string.
     */

    public void displayMessage(String str) {
        JOptionPane.showMessageDialog(this, str);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String tempBtnStr = e.getActionCommand();
        if (e.getSource() instanceof JButton) {
            if (tempBtnStr.equals("Let's Play")) {
                if (getSelectedFile().equals("NONE")) {
                    displayMessage("Please Select At least one file.");
                } else {
                    configModel.load_gamefile = getSelectedFile();
                    mapview = new MapcreateView("load");
                    mapview.paint(mapview.getGraphics());
                    setMSTOp(false);
                    this.dispose();
                }
            }
        }
    }

    public void listFilesForFolder(final File folder) {
        FileList = new String[folder.listFiles().length];
        int i = 0;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                //listFilesForFolder(fileEntry);
            } else {
                FileList[i] = fileEntry.getName();
                i++;
                //System.out.println(fileEntry.getName());
            }
        }
        check_game = true;
    }
    

}
