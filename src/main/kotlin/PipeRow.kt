package nl.rwslinkman.flappybird

import java.awt.Graphics
import java.awt.Image

class PipeRow(private val topPipeImage: Image, private val bottomPipeImage: Image, var x: Int, val y: Int): Renderable {

    val width = PIPE_WIDTH
    val height = PIPE_HEIGHT
    val bottomY = y + PIPE_HEIGHT + SPACE_BETWEEN
    var birdPassed: Boolean = false

    override fun render(g: Graphics) {
        g.drawImage(topPipeImage, x, y, width, PIPE_HEIGHT, null)
        g.drawImage(bottomPipeImage, x, bottomY, width, PIPE_HEIGHT, null)
    }

    companion object {
        const val PIPE_WIDTH = 64
        const val PIPE_HEIGHT = 512
        const val SPACE_BETWEEN = App.FRAME_HEIGHT / 4
    }
}