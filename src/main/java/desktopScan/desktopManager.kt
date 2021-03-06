package desktopScan

import PokerClasses.*
import PokerRoom.GGTableFilePaths
import PokerRoom.GGTablePositions6max
import PokerRoom.ITableFilePaths
import PokerRoom.ITablePositions6max
import net.sourceforge.tess4j.Tesseract
import net.sourceforge.tess4j.TesseractException
import java.awt.Rectangle
import java.awt.Robot
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import com.sun.awt.SecurityWarning.getSize
import java.awt.image.DataBuffer
import java.util.HashMap
import jdk.nashorn.internal.objects.NativeArray.forEach
import com.sun.jna.platform.win32.Netapi32Util.User
import java.util.stream.Collectors


class desktopManager {


    val keepDoingScreenshot = true
    var counter = 0
    var filePaths: ITableFilePaths? = null
    var tablePositions: ITablePositions6max? = null;

    constructor(paths: ITableFilePaths, positions: ITablePositions6max) {
        tablePositions = positions
        filePaths = paths
    }

    fun takeDecision(image: BufferedImage): ActionToTake {
        var constants = GGTablePositions6max()
        var windowManager = WindowManager()

        if (!windowManager.Scale(image, constants.Width, constants.Height))
            return ActionToTake()


        var position = getPosition(image, constants)

        var heroHand = readHeroCard(image, constants)
        var res = ActionToTake()
        res.Hand = heroHand
        return res
    }


    fun doScreenShotsOfActiveWindowInLoop(): IntArray? {
        var manager = WindowManager()
        while (keepDoingScreenshot) {


            var rect = manager.GetForeGroundWindowRect()

            var r = Rectangle(rect[0], rect[1], rect[2] - rect[0], rect[3] - rect[1])
            var image = Robot().createScreenCapture(r)
            var path = "/screenshots/screenshot$counter.png"
            ImageIO.write(image, "png", File(path))
            counter++
            Thread.sleep(5000)

        }


        return manager.GetForeGroundWindowRect()
    }

    fun createBoardTemplateFromDirectory(dir: String) {
        var positions = GGTablePositions6max()
        File(dir).list().forEach {
            println(it)
            var imgPath = "$dir/$it"
            println(imgPath)
            var number = it.replace("screenshot", "").replace(".png", "").toInt()

            SaveCard(imgPath, it, positions.WholeCard1, "/wholeCards/whole_1_$number.png")
            SaveCard(imgPath, it, positions.WholeCard2, "/wholeCards/whole_2_$number.png")
            SaveCard(imgPath, it, positions.WholeCard3, "/wholeCards/whole_3_$number.png")
            SaveCard(imgPath, it, positions.WholeCard4, "/wholeCards/whole_4_$number.png")
            SaveCard(imgPath, it, positions.WholeCard5, "/wholeCards/whole_5_$number.png")
        }
    }

    fun createButtonsTemplate(dir: String) {
        var positions = GGTablePositions6max()
        File(dir).list().forEach {
            println(it)
            var imgPath = "$dir/$it"
            println(imgPath)
            var number = it.replace("screenshot", "").replace(".png", "").toInt()

            SaveCard(imgPath, it, positions.Button1, "/wholeCards/btn_1_$number.png")
            SaveCard(imgPath, it, positions.Button2, "/wholeCards/btn_2_$number.png")
            SaveCard(imgPath, it, positions.Button3, "/wholeCards/btn_3_$number.png")
            SaveCard(imgPath, it, positions.Button4, "/wholeCards/btn_4_$number.png")
            SaveCard(imgPath, it, positions.Button5, "/wholeCards/btn_5_$number.png")
            SaveCard(imgPath, it, positions.Button6, "/wholeCards/btn_6_$number.png")
        }
    }

    fun createActionsTemplate(dir: String) {
        var positions = GGTablePositions6max()
        File(dir).list().forEach {
            var imgPath = "$dir/$it"
            println(imgPath)
            var number = it.replace("screenshot", "").replace(".png", "").toInt()

            SaveCard(imgPath, it, positions.FoldRect, "/wholeCards/fold_$number.png")
            SaveCard(imgPath, it, positions.CallCheckRect, "/wholeCards/call_$number.png")
            SaveCard(imgPath, it, positions.RaiseRect, "/wholeCards/raise_$number.png")
        }
    }

    fun readPot(): kotlin.String {


        var dir = "/screenshots2"
        var positions = GGTablePositions6max()
        File(dir).list().forEach {
            println(it)
            var imgPath = "$dir/$it"

            var img = ImageIO.read(File(imgPath))
            var bufferedImage = cutOfTheImage(img, positions.Pot)

            var instance = Tesseract()
            try {

                var res = instance.doOCR(bufferedImage)
                return res
            } catch (ex: TesseractException) {
                println(ex.message)
            }


        }

        return "test";
    }

    fun createHeroTemplateFromDictionary(dir: String) {
        var positions = GGTablePositions6max()
        File(dir).list().forEach {
            println(it)
            var imgPath = "$dir/$it"
            println(imgPath)
            var number = it.replace("screenshot", "").replace(".png", "").toInt()

            SaveCard(imgPath, it, positions.HeroCard1, "/HeroCards/1_$number.png")
            SaveCard(imgPath, it, positions.HeroCard2, "/HeroCards/2_$number.png")
        }
    }

    private fun SaveCard(imgPath: String, it: String, card: Rectangle, fileToSave: String) {
        var img = ImageIO.read(File(imgPath))
        var bufferedImage = cutOfTheImage(img, card)

        ImageIO.write(bufferedImage, "png", File(fileToSave))
    }

    private fun cutOfTheImage(img: BufferedImage?, wholeCard1: Rectangle): BufferedImage {
        return img!!.getSubimage(wholeCard1.x, wholeCard1.y, wholeCard1.width, wholeCard1.height)
    }

    private fun getSimilirarityPercent(img1: BufferedImage?, img2: BufferedImage?): Double {

        if (img1 == null || img2 == null)
            return (-1).toDouble()
        val width = img1.width
        val height = img1.height
        val width2 = img2.width
        val height2 = img2.height
        if (width != width2 || height != height2) {
            return 0.0
        }

        var diff: Long = 0
        for (y in 0..height - 1) {
            for (x in 0..width - 1) {
                diff += pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y)).toLong()
            }
        }
        val maxDiff = width.toLong() * height.toLong()

        return 1.0 - (diff.toDouble() / maxDiff.toDouble())
    }

    private fun pixelDiff(rgb1: Int, rgb2: Int): Int {
        val r1 = rgb1 shr 16 and 0xff
        val g1 = rgb1 shr 8 and 0xff
        val b1 = rgb1 and 0xff
        val r2 = rgb2 shr 16 and 0xff
        val g2 = rgb2 shr 8 and 0xff
        val b2 = rgb2 and 0xff

        return if (Math.abs(r1 + g1 + b1 - r2 - g2 - b2) < 55)
            0
        else
            1

        //return Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2)
    }

    private fun getPosition(img: BufferedImage, position: ITablePositions6max): Position {


        ImageTemplates.initialize(filePaths)

        var templates = ImageTemplates.getButtonTemplates()

        var cuttedImg = cutOfTheImage(img, position.Button4)


        var similarity = getSimilirarityPercent(cuttedImg, templates[Position.BigBlind])
        if (similarity > 0.8) return Position.BigBlind

        cuttedImg = cutOfTheImage(img, position.Button5)

        similarity = getSimilirarityPercent(cuttedImg, templates[Position.SmallBlind])
        if (similarity > 0.8) return Position.SmallBlind

        cuttedImg = cutOfTheImage(img, position.Button6)
        similarity = getSimilirarityPercent(cuttedImg, templates[Position.Button])
        if (similarity > 0.8) return Position.Button

        cuttedImg = cutOfTheImage(img, position.Button1)
        similarity = getSimilirarityPercent(cuttedImg, templates[Position.CutOff])
        if (similarity > 0.8) return Position.CutOff

        cuttedImg = cutOfTheImage(img, position.Button2)
        similarity = getSimilirarityPercent(cuttedImg, templates[Position.Hj])
        if (similarity > 0.8) return Position.Hj

        cuttedImg = cutOfTheImage(img, position.Button3)
        similarity = getSimilirarityPercent(cuttedImg, templates[Position.Mp])
        if (similarity > 0.8) return Position.Mp

        return Position.None
    }


    private fun readHeroCard(img: BufferedImage, position: ITablePositions6max): HoldemHand? {

        var card1: Card? = null
        var card2: Card? = null

        ImageTemplates.initialize(filePaths)

        var templates = ImageTemplates.getHeroCardsTemplates()

        var heroCard1 = cutOfTheImage(img, position.HeroCard1)
        var heroCard2 = cutOfTheImage(img, position.HeroCard2)

        var map = HashMap<Card, Double>()

        templates.asSequence().forEach { u -> map.put(u.key, getSimilirarityPercent(heroCard1, u.value)) }
        var candidate = map.toList().sortedByDescending { (_, value) -> value }.first()

        if (candidate.second > 0.93)
            card1 = candidate.first


        map = HashMap<Card, Double>()

        templates.asSequence().forEach { u -> map.put(u.key, getSimilirarityPercent(heroCard2, u.value)) }
        var candidate2 = map.toList().sortedByDescending { (_, value) -> value }.first()

        ImageIO.write(heroCard2, "png", File("/cut.png"))
        ImageIO.write(templates.asSequence().first{x->x.key.colour == CardColour.Spade && x.key.cardType == CardEnum.Queen}.value, "png", File("/sample.png"))

        if (candidate2.second > 0.8)
            card2 = candidate2.first

        if (card1 != null && card2 != null)
            return HoldemHand(card1, card2)

        return null
    }
}