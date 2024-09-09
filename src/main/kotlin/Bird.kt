package nl.rwslinkman.flappybird

import java.awt.Graphics
import java.awt.Image
import java.awt.Rectangle

class Bird(private val image: Image, val x: Int, var y: Int): Renderable {

    override fun render(g: Graphics) {
        g.drawImage(image, x, y, BIRD_WIDTH, BIRD_HEIGHT, null)
    }

    fun hasPassed(pipe: PipeRow): Boolean {
        return this.x > (pipe.x + pipe.width)
    }

    fun hasCollisionWith(pipe: PipeRow): Boolean {
        val birdRect = Rectangle(this.x, this.y, BIRD_WIDTH, BIRD_HEIGHT)
        val topRect = Rectangle(pipe.x, pipe.y, pipe.width, pipe.height)
        val bottomRect = Rectangle(pipe.x, pipe.bottomY, pipe.width, pipe.height)
        return birdRect.intersects(topRect) || birdRect.intersects(bottomRect)
    }

    companion object {
        const val BIRD_WIDTH: Int = 34
        const val BIRD_HEIGHT: Int = 24
    }
}