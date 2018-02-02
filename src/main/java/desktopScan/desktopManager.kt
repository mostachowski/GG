package desktopScan

import PokerClasses.Position
import PokerRoom.GGTableFilePaths
import PokerRoom.GGTablePositions6max
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


class desktopManager {


    val keepDoingScreenshot = true
    var counter = 0

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

    fun saySomething(): kotlin.String {
        return "What is up?"
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


    fun ParseImage(imgPath: String) {
        var positions = GGTablePositions6max()
        var image = ImageIO.read(File(imgPath))
        var position = getPosition(image, positions)

    }


    fun compareImage(biA: BufferedImage, biB: BufferedImage): Float {

        var percentage = 0f
        try {
            // take buffer data from both image files //

            val dbA = biA.data.dataBuffer
            val sizeA = dbA.size
            val dbB = biB.data.dataBuffer
            val sizeB = dbB.size
            var count = 0
            // compare data-buffer objects //
            if (sizeA == sizeB) {

                for (i in 0..sizeA - 1) {

                    if (dbA.getElem(i) == dbB.getElem(i)) {
                        count = count + 1
                    }

                }
                percentage = (count * 100 / sizeA).toFloat()
            } else {
                println("Both the images are not of same size")
            }

        } catch (e: Exception) {
            println("Failed to compare image files ...")
        }

        return percentage
    }

    private fun getPosition(img: BufferedImage, position: ITablePositions6max): Position {

        var bufferedImage1 = cutOfTheImage(img, position.Button1)
        var template1 = ImageIO.read(File(GGTableFilePaths().Btn1Path))

        if (compareImage(bufferedImage1, template1) > 0.8)
            return Position.Button
        return Position.CutOff
    }
}