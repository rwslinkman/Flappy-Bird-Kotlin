package nl.rwslinkman.flappybird

import javax.swing.JFrame

fun main() = App().start()

class App {
    fun start() {
        val frame = JFrame("FlappyBird")
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT)
        frame.setLocationRelativeTo(null) // screen center
        frame.isResizable = false
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        val game = FlappyBird()
        frame.add(game)
        frame.pack()
        game.requestFocus()
        frame.isVisible = true
    }

    companion object {
        const val FRAME_WIDTH = 360
        const val FRAME_HEIGHT = 640
    }
}