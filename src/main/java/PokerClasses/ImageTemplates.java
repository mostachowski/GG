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

        dictionary.put(new Card(CardEnum.Two, CardColour.Heart), ImageIO.read(new File(_positions.getH2Hero())));
        dictionary.put(new Card(CardEnum.Three, CardColour.Heart), ImageIO.read(new File(_positions.getH3Hero())));
        dictionary.put(new Card(CardEnum.Four, CardColour.Heart), ImageIO.read(new File(_positions.getH4Hero())));
        dictionary.put(new Card(CardEnum.Five, CardColour.Heart), ImageIO.read(new File(_positions.getH5Hero())));
        dictionary.put(new Card(CardEnum.Six, CardColour.Heart), ImageIO.read(new File(_positions.getH6Hero())));
        dictionary.put(new Card(CardEnum.Seven, CardColour.Heart), ImageIO.read(new File(_positions.getH7Hero())));
        dictionary.put(new Card(CardEnum.Eight, CardColour.Heart), ImageIO.read(new File(_positions.getH8Hero())));
        dictionary.put(new Card(CardEnum.Nine, CardColour.Heart), ImageIO.read(new File(_positions.getH9Hero())));
        dictionary.put(new Card(CardEnum.Ten, CardColour.Heart), ImageIO.read(new File(_positions.getHtHero())));
        dictionary.put(new Card(CardEnum.Jack, CardColour.Heart), ImageIO.read(new File(_positions.getHjHero())));
        dictionary.put(new Card(CardEnum.Queen, CardColour.Heart), ImageIO.read(new File(_positions.getHqHero())));
        dictionary.put(new Card(CardEnum.King, CardColour.Heart), ImageIO.read(new File(_positions.getHkHero())));
        dictionary.put(new Card(CardEnum.Ace, CardColour.Heart), ImageIO.read(new File(_positions.getHaHero())));

        dictionary.put(new Card(CardEnum.Two, CardColour.Diamond), ImageIO.read(new File(_positions.getD2Hero())));
        dictionary.put(new Card(CardEnum.Three, CardColour.Diamond), ImageIO.read(new File(_positions.getD3Hero())));
        dictionary.put(new Card(CardEnum.Four, CardColour.Diamond), ImageIO.read(new File(_positions.getD4Hero())));
        dictionary.put(new Card(CardEnum.Five, CardColour.Diamond), ImageIO.read(new File(_positions.getD5Hero())));
        dictionary.put(new Card(CardEnum.Six, CardColour.Diamond), ImageIO.read(new File(_positions.getD6Hero())));
        dictionary.put(new Card(CardEnum.Seven, CardColour.Diamond), ImageIO.read(new File(_positions.getD7Hero())));
        dictionary.put(new Card(CardEnum.Eight, CardColour.Diamond), ImageIO.read(new File(_positions.getD8Hero())));
        dictionary.put(new Card(CardEnum.Nine, CardColour.Diamond), ImageIO.read(new File(_positions.getD9Hero())));
        dictionary.put(new Card(CardEnum.Ten, CardColour.Diamond), ImageIO.read(new File(_positions.getDtHero())));
        dictionary.put(new Card(CardEnum.Jack, CardColour.Diamond), ImageIO.read(new File(_positions.getDjHero())));
        dictionary.put(new Card(CardEnum.Queen, CardColour.Diamond), ImageIO.read(new File(_positions.getDqHero())));
        dictionary.put(new Card(CardEnum.King, CardColour.Diamond), ImageIO.read(new File(_positions.getDkHero())));
        dictionary.put(new Card(CardEnum.Ace, CardColour.Diamond), ImageIO.read(new File(_positions.getDaHero())));

        dictionary.put(new Card(CardEnum.Two, CardColour.Club), ImageIO.read(new File(_positions.getC2Hero())));
        dictionary.put(new Card(CardEnum.Three, CardColour.Club), ImageIO.read(new File(_positions.getC3Hero())));
        dictionary.put(new Card(CardEnum.Four, CardColour.Club), ImageIO.read(new File(_positions.getC4Hero())));
        dictionary.put(new Card(CardEnum.Five, CardColour.Club), ImageIO.read(new File(_positions.getC5Hero())));
        dictionary.put(new Card(CardEnum.Six, CardColour.Club), ImageIO.read(new File(_positions.getC6Hero())));
        dictionary.put(new Card(CardEnum.Seven, CardColour.Club), ImageIO.read(new File(_positions.getC7Hero())));
        dictionary.put(new Card(CardEnum.Eight, CardColour.Club), ImageIO.read(new File(_positions.getC8Hero())));
        dictionary.put(new Card(CardEnum.Nine, CardColour.Club), ImageIO.read(new File(_positions.getC9Hero())));
        dictionary.put(new Card(CardEnum.Ten, CardColour.Club), ImageIO.read(new File(_positions.getCtHero())));
        dictionary.put(new Card(CardEnum.Jack, CardColour.Club), ImageIO.read(new File(_positions.getCjHero())));
        dictionary.put(new Card(CardEnum.Queen, CardColour.Club), ImageIO.read(new File(_positions.getCqHero())));
        dictionary.put(new Card(CardEnum.King, CardColour.Club), ImageIO.read(new File(_positions.getCkHero())));
        dictionary.put(new Card(CardEnum.Ace, CardColour.Club), ImageIO.read(new File(_positions.getCaHero())));

        dictionary.put(new Card(CardEnum.Two, CardColour.Spade), ImageIO.read(new File(_positions.getS2Hero())));
        dictionary.put(new Card(CardEnum.Three, CardColour.Spade), ImageIO.read(new File(_positions.getS3Hero())));
        dictionary.put(new Card(CardEnum.Four, CardColour.Spade), ImageIO.read(new File(_positions.getS4Hero())));
        dictionary.put(new Card(CardEnum.Five, CardColour.Spade), ImageIO.read(new File(_positions.getS5Hero())));
        dictionary.put(new Card(CardEnum.Six, CardColour.Spade), ImageIO.read(new File(_positions.getS6Hero())));
        dictionary.put(new Card(CardEnum.Seven, CardColour.Spade), ImageIO.read(new File(_positions.getS7Hero())));
        dictionary.put(new Card(CardEnum.Eight, CardColour.Spade), ImageIO.read(new File(_positions.getS8Hero())));
        dictionary.put(new Card(CardEnum.Nine, CardColour.Spade), ImageIO.read(new File(_positions.getS9Hero())));
        dictionary.put(new Card(CardEnum.Ten, CardColour.Spade), ImageIO.read(new File(_positions.getStHero())));
        dictionary.put(new Card(CardEnum.Jack, CardColour.Spade), ImageIO.read(new File(_positions.getSjHero())));
        dictionary.put(new Card(CardEnum.Queen, CardColour.Spade), ImageIO.read(new File(_positions.getSqHero())));
        dictionary.put(new Card(CardEnum.King, CardColour.Spade), ImageIO.read(new File(_positions.getSkHero())));
        dictionary.put(new Card(CardEnum.Ace, CardColour.Spade), ImageIO.read(new File(_positions.getSaHero())));


        for (BufferedImage item:
             dictionary.values()) {

            item =  RemoveWhiteLines(item);
        }
        return dictionary;
    }

    private static BufferedImage RemoveWhiteLines(BufferedImage item) {


        for (int y = 0 ;y<item.getWidth();y++) {
            Boolean isWhite = true;
            for (int x = 0;x<item.getHeight();x++) {


            }
        }

        return null;
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

