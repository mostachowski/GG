package PokerClasses;

import PokerRoom.ITableFilePaths;
import PokerRoom.ITablePositions6max;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;

public final class ImageTemplates {

    private ImageTemplates() {

    }

    private static ITableFilePaths _positions;


    public static void initialize(ITableFilePaths positions) throws IOException {
        _positions = positions;

        buttons = initializeButtons();

        wholeCards = initializeWholeCards();
    }

    private static HashMap<Card, BufferedImage> initializeWholeCards() throws IllegalArgumentException, IOException {
        if (_positions == null) {
            throw new IllegalArgumentException("looks like you did not call Initialize method or you passed empty parameter to it");
        }
        HashMap<Card,BufferedImage> images = new HashMap<Card, BufferedImage>();
        images.put(new Card(CardEnum.Two,CardColour.Heart),ImageIO.read(new File(_positions.getBtn1Path())));

        return null;
    }

    public static HashMap<Position, BufferedImage> getButtonTemplates() throws IOException {
        if (buttons == null) {
            buttons = initializeButtons();
        }
        return buttons;
    }

    private static HashMap<Position, BufferedImage> buttons = null;

    private static HashMap<Card,BufferedImage > wholeCards = null;

    private static HashMap<Position, BufferedImage> initializeButtons() throws IOException {
        if (_positions == null) {
            throw new IllegalArgumentException("looks like you did not call Initialize method or you passed empty parameter to it");
        }
        HashMap<Position, BufferedImage> dictionary = new HashMap<Position, BufferedImage>();

        dictionary.put(Position.CutOff, ImageIO.read(new File(_positions.getBtn1Path())));
        dictionary.put(Position.Hj, ImageIO.read(new File(_positions.getBtn2Path())));
        dictionary.put(Position.Mp, ImageIO.read(new File(_positions.getBtn3Path())));
        dictionary.put(Position.BigBlind, ImageIO.read(new File(_positions.getBtn4Path())));
        dictionary.put(Position.SmallBlind, ImageIO.read(new File(_positions.getBtn5Path())));
        dictionary.put(Position.Button, ImageIO.read(new File(_positions.getBtn6Path())));

        return dictionary;

    }
}

