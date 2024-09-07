package nl.rwslinkman.flappybird

import java.awt.Image
import javax.swing.ImageIcon

object ResourceImageLoader {

    fun loadBackgroundImage(): Image = loadFileImage("background.png")

    fun loadBirdImage(): Image = loadFileImage("flappybird.png")

    fun loadTopPipeImage(): Image = loadFileImage("toppipe.png")

    fun loadBottomPipeImage(): Image = loadFileImage("bottompipe.png")

    private fun loadFileImage(fileName: String): Image {
        val res = javaClass.classLoader.getResource("./$fileName")
        return ImageIcon(res).image
    }
}