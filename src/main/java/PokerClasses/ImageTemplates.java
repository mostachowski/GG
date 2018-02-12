package PokerClasses;

import PokerRoom.ITableFilePaths;
import PokerRoom.ITablePositions6max;
import org.jetbrains.annotations.NotNull;

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

        heroCards = initializeHeroCards();
    }

    private static HashMap<Card,BufferedImage> initializeHeroCards() throws IOException {
        if (_positions == null) {
            throw new IllegalArgumentException("looks like you did not call Initialize method or you passed empty parameter to it");
        }
        HashMap<Card,BufferedImage> dictionary = new HashMap<Card,BufferedImage>();

        dictionary.put(new Card(CardEnum.Two, CardColour.Heart), ImageIO.read(new File(_positions.getH2())));
        dictionary.put(new Card(CardEnum.Three, CardColour.Heart), ImageIO.read(new File(_positions.getH3())));
        dictionary.put(new Card(CardEnum.Four, CardColour.Heart), ImageIO.read(new File(_positions.getH4())));
        dictionary.put(new Card(CardEnum.Five, CardColour.Heart), ImageIO.read(new File(_positions.getH5())));
        dictionary.put(new Card(CardEnum.Six, CardColour.Heart), ImageIO.read(new File(_positions.getH6())));
        dictionary.put(new Card(CardEnum.Seven, CardColour.Heart), ImageIO.read(new File(_positions.getH7())));
        dictionary.put(new Card(CardEnum.Eight, CardColour.Heart), ImageIO.read(new File(_positions.getH8())));
        dictionary.put(new Card(CardEnum.Nine, CardColour.Heart), ImageIO.read(new File(_positions.getH9())));
        dictionary.put(new Card(CardEnum.Ten, CardColour.Heart), ImageIO.read(new File(_positions.getHt())));
        dictionary.put(new Card(CardEnum.Jack, CardColour.Heart), ImageIO.read(new File(_positions.getHj())));
        dictionary.put(new Card(CardEnum.Queen, CardColour.Heart), ImageIO.read(new File(_positions.getHq())));
        dictionary.put(new Card(CardEnum.King, CardColour.Heart), ImageIO.read(new File(_positions.getHk())));
        dictionary.put(new Card(CardEnum.Ace, CardColour.Heart), ImageIO.read(new File(_positions.getHa())));

        dictionary.put(new Card(CardEnum.Two, CardColour.Diamond), ImageIO.read(new File(_positions.getD2())));
        dictionary.put(new Card(CardEnum.Three, CardColour.Diamond), ImageIO.read(new File(_positions.getD3())));
        dictionary.put(new Card(CardEnum.Four, CardColour.Diamond), ImageIO.read(new File(_positions.getD4())));
        dictionary.put(new Card(CardEnum.Five, CardColour.Diamond), ImageIO.read(new File(_positions.getD5())));
        dictionary.put(new Card(CardEnum.Six, CardColour.Diamond), ImageIO.read(new File(_positions.getD6())));
        dictionary.put(new Card(CardEnum.Seven, CardColour.Diamond), ImageIO.read(new File(_positions.getD7())));
        dictionary.put(new Card(CardEnum.Eight, CardColour.Diamond), ImageIO.read(new File(_positions.getD8())));
        dictionary.put(new Card(CardEnum.Nine, CardColour.Diamond), ImageIO.read(new File(_positions.getD9())));
        dictionary.put(new Card(CardEnum.Ten, CardColour.Diamond), ImageIO.read(new File(_positions.getDt())));
        dictionary.put(new Card(CardEnum.Jack, CardColour.Diamond), ImageIO.read(new File(_positions.getDj())));
        dictionary.put(new Card(CardEnum.Queen, CardColour.Diamond), ImageIO.read(new File(_positions.getDq())));
        dictionary.put(new Card(CardEnum.King, CardColour.Diamond), ImageIO.read(new File(_positions.getDk())));
        dictionary.put(new Card(CardEnum.Ace, CardColour.Diamond), ImageIO.read(new File(_positions.getDa())));

        dictionary.put(new Card(CardEnum.Two, CardColour.Club), ImageIO.read(new File(_positions.getC2())));
        dictionary.put(new Card(CardEnum.Three, CardColour.Club), ImageIO.read(new File(_positions.getC3())));
        dictionary.put(new Card(CardEnum.Four, CardColour.Club), ImageIO.read(new File(_positions.getC4())));
        dictionary.put(new Card(CardEnum.Five, CardColour.Club), ImageIO.read(new File(_positions.getC5())));
        dictionary.put(new Card(CardEnum.Six, CardColour.Club), ImageIO.read(new File(_positions.getC6())));
        dictionary.put(new Card(CardEnum.Seven, CardColour.Club), ImageIO.read(new File(_positions.getC7())));
        dictionary.put(new Card(CardEnum.Eight, CardColour.Club), ImageIO.read(new File(_positions.getC8())));
        dictionary.put(new Card(CardEnum.Nine, CardColour.Club), ImageIO.read(new File(_positions.getC9())));
        dictionary.put(new Card(CardEnum.Ten, CardColour.Club), ImageIO.read(new File(_positions.getCt())));
        dictionary.put(new Card(CardEnum.Jack, CardColour.Club), ImageIO.read(new File(_positions.getCj())));
        dictionary.put(new Card(CardEnum.Queen, CardColour.Club), ImageIO.read(new File(_positions.getCq())));
        dictionary.put(new Card(CardEnum.King, CardColour.Club), ImageIO.read(new File(_positions.getCk())));
        dictionary.put(new Card(CardEnum.Ace, CardColour.Club), ImageIO.read(new File(_positions.getCa())));

        dictionary.put(new Card(CardEnum.Two, CardColour.Spade), ImageIO.read(new File(_positions.getS2())));
        dictionary.put(new Card(CardEnum.Three, CardColour.Spade), ImageIO.read(new File(_positions.getS3())));
        dictionary.put(new Card(CardEnum.Four, CardColour.Spade), ImageIO.read(new File(_positions.getS4())));
        dictionary.put(new Card(CardEnum.Five, CardColour.Spade), ImageIO.read(new File(_positions.getS5())));
        dictionary.put(new Card(CardEnum.Six, CardColour.Spade), ImageIO.read(new File(_positions.getS6())));
        dictionary.put(new Card(CardEnum.Seven, CardColour.Spade), ImageIO.read(new File(_positions.getS7())));
        dictionary.put(new Card(CardEnum.Eight, CardColour.Spade), ImageIO.read(new File(_positions.getS8())));
        dictionary.put(new Card(CardEnum.Nine, CardColour.Spade), ImageIO.read(new File(_positions.getS9())));
        dictionary.put(new Card(CardEnum.Ten, CardColour.Spade), ImageIO.read(new File(_positions.getSt())));
        dictionary.put(new Card(CardEnum.Jack, CardColour.Spade), ImageIO.read(new File(_positions.getSj())));
        dictionary.put(new Card(CardEnum.Queen, CardColour.Spade), ImageIO.read(new File(_positions.getSq())));
        dictionary.put(new Card(CardEnum.King, CardColour.Spade), ImageIO.read(new File(_positions.getSk())));
        dictionary.put(new Card(CardEnum.Ace, CardColour.Spade), ImageIO.read(new File(_positions.getSa())));

        return dictionary;
    }



    public static HashMap<Position, BufferedImage> getButtonTemplates() throws IOException {
        if (buttons == null) {
            buttons = initializeButtons();
        }
        return buttons;
    }

    private static HashMap<Position, BufferedImage> buttons = null;

    private static HashMap<Card,BufferedImage > heroCards = null;

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



    public static HashMap<Card,BufferedImage> getHeroCardsTemplates() throws IOException {

        if (heroCards == null) {
            heroCards = initializeHeroCards();
        }
        return heroCards;

    }
}

