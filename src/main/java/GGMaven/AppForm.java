package GGMaven;

import desktopScan.desktopManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppForm {
    private JPanel panelMain;
    private JButton button1;
    private JButton closeButton;
    private JButton createTemplatesButton;
    private JButton createHeroCardTemplatesButton;
    private JButton readPotButton;
    private JButton screenShotButton;

    public AppForm() {

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                desktopManager manager = new desktopManager();
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
                desktopManager manager = new desktopManager();
                manager.createBoardTemplateFromDirectory("/screenshots");

            }
        });
        createHeroCardTemplatesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                desktopManager manager = new desktopManager();
                manager.createHeroTemplateFromDictionary("/screenshots");
            }
        });
        readPotButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                desktopManager manager = new desktopManager();
               String pot = manager.readPot();
               JOptionPane.showMessageDialog(null,pot);
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
