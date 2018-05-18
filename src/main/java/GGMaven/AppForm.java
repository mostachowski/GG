package GGMaven;

import NeuralNetwork.*;
import PokerClasses.ActionToTake;
import PokerClasses.AllCardsEnum;
import PokerRoom.GGTableFilePaths;
import PokerRoom.GGTablePositions6max;
import desktopScan.desktopManager;
import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.networks.BasicNetwork;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    private JButton btnInitialize;
    private JButton screenShotButton;

    private Map<String, double[]> TrainingSet;


    private int av_ImageHeight = 0;
    private int av_ImageWidth = 0;
    private int NumOfPatterns = 0;

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
                   String msg = result.getHand().getCard1().toString() + result.getHand().getCard2().toString();
                    JOptionPane.showMessageDialog(null, msg);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnInitialize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                try {
                    GenerateTraningSet();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                /*
                if (neuralNetwork == null) {
                    neuralNetwork = new NeuralNetworkClass(new NeuralNetwork.BP1Layer(av_ImageHeight * av_ImageWidth, NumOfPatterns), TrainingSet);
                    neuralNetwork.Train();

                }
                */


                BasicNetwork network =   new SimpleNetwork().CreateNetwork(TrainingSet);

                final Frame f= new Frame();
                FileDialog dialog=  new FileDialog(f,"Choose File",FileDialog.LOAD);

                dialog.setVisible(true);
                final String filename =dialog.getDirectory()  + dialog.getFile();

                try {
                    BufferedImage image = ImageIO.read( new File(filename));


                   double[]  input = ImageProcessing.ToMatrix(image,av_ImageHeight,av_ImageWidth);
                    BasicMLData data  = new BasicMLData(input);
                 MLData result = network.compute(data);
                int res =  (int)result.getData(0);
                 AllCardsEnum card =   AllCardsEnum.values()[res];


                    //BackPropagationResult result = neuralNetwork.Recognize(input);
                    JOptionPane.showMessageDialog(null, card.name());

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }



    private void GenerateTraningSet() throws IOException {


        File folder = new File("CARDS");
        File[] listOfFiles = folder.listFiles();

        String[] Patterns = new String[listOfFiles.length];
        NumOfPatterns = listOfFiles.length;
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
               Patterns[i] = listOfFiles[i].getName().toString();

            }
        }


        TrainingSet = new HashMap<String, double[]>(Patterns.length);

        for (String s : Patterns)
        {
            String fileName = "CARDS//" + s;
            BufferedImage Temp = ImageIO.read(new File(fileName));
            av_ImageHeight += Temp.getHeight();
            av_ImageWidth += Temp.getWidth();
            Temp = null;
        }
        av_ImageHeight /= Patterns.length;
        av_ImageWidth /= Patterns.length;


        for (String s : Patterns)
        {
            String fileName = "CARDS//" + s;
            BufferedImage Temp = ImageIO.read(new File(fileName));
            TrainingSet.put((s),
                    ImageProcessing.ToMatrix(Temp, av_ImageHeight, av_ImageWidth));


            Temp = null;
        }


    }

    private NeuralNetworkClass neuralNetwork = null;

    private NeuralNetwork.BP1Layer layer = null;
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
