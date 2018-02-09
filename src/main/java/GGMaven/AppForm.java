package GGMaven;

import PokerClasses.ActionToTake;
import PokerRoom.GGTableFilePaths;
import PokerRoom.GGTablePositions6max;
import desktopScan.desktopManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AppForm {
    private JPanel panelMain;
    private JButton button1;
    private JButton closeButton;
    private JButton createTemplatesButton;
    private JButton createHeroCardTemplatesButton;
    private JButton readPotButton;
    private JButton prepareBtnTemplatesButton;
    private JButton prepareActionBtnsButton;
    private JButton recognizeImageButton;
    private JButton screenShotButton;

    public AppForm() {

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                desktopManager manager = new desktopManager(new GGTableFilePaths(),new GGTablePositions6max());
                manager.doScreenShotsOfActiveWindowInLoop();
            }
        });
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        createTemplatesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                desktopManager manager = new desktopManager(new GGTableFilePaths(),new GGTablePositions6max());
                manager.createBoardTemplateFromDirectory("/screenshots");

            }
        });
        createHeroCardTemplatesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                desktopManager manager = new desktopManager(new GGTableFilePaths(),new GGTablePositions6max());
                manager.createHeroTemplateFromDictionary("/screenshots");
            }
        });
        readPotButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                desktopManager manager = new desktopManager(new GGTableFilePaths(),new GGTablePositions6max());
                String pot = manager.readPot();
                JOptionPane.showMessageDialog(null, pot);
            }
        });
        prepareBtnTemplatesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                desktopManager manager = new desktopManager(new GGTableFilePaths(),new GGTablePositions6max());
                manager.createButtonsTemplate("/screenshots2");
            }
        });
        prepareActionBtnsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                desktopManager manager = new desktopManager(new GGTableFilePaths(),new GGTablePositions6max());
                manager.createActionsTemplate("/screenshots2");

            }
        });

        recognizeImageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                final Frame f= new Frame();
                FileDialog dialog=  new FileDialog(f,"Choose File",FileDialog.LOAD);

                dialog.setVisible(true);
                final String filename =dialog.getDirectory()  + dialog.getFile();
                File file = new File(filename);
                try {
                    BufferedImage img = ImageIO.read(file);
                    desktopManager manager = new desktopManager(new GGTableFilePaths(),new GGTablePositions6max());
                   ActionToTake result = manager.takeDecision(img);
                   String msg = result.getAction().toString() + result.getAmount();
                    JOptionPane.showMessageDialog(null, msg);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }



            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new AppForm().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
