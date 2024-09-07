package nl.rwslinkman.flappybird

import java.awt.*
import java.awt.event.KeyEvent
import java.lang.Math.random
import javax.swing.JPanel
import javax.swing.Timer
import kotlin.math.max

class FlappyBird : JPanel(), KeyPressedListener {

    // Preloaded stuff
    private val backgroundImage: Image
    private val topPipeImage: Image
    private val bottomPipeImage: Image
    private val uiFont: Font

    private var velocityX = -4
    private var velocityY = 0
    private val gravity = 1

    private val bird: Bird
    private val pipes: MutableList<PipeRow>

    private val pipePlacementTimer: Timer
    private val gameLoopTimer: Timer
    private var firstGameStarted = false
    private var gameOver: Boolean = true
    private var score: Int = 0

    init {
        preferredSize = Dimension(App.FRAME_WIDTH, App.FRAME_HEIGHT)
        isFocusable = true
        addKeyListener(this)

        backgroundImage = ResourceImageLoader.loadBackgroundImage()
        topPipeImage = ResourceImageLoader.loadTopPipeImage()
        bottomPipeImage = ResourceImageLoader.loadBottomPipeImage()
        uiFont = Font("Arial", Font.PLAIN, 32)

        bird = Bird(ResourceImageLoader.loadBirdImage(), App.FRAME_WIDTH / 8, App.FRAME_HEIGHT / 2)
        pipes = mutableListOf()

        pipePlacementTimer = Timer(PIPE_PLACEMENT_INTERVAL) {
            placePipes()
        }
        gameLoopTimer = Timer(FPS_INTERVAL) {
            gameLoop()
        }
    }

    override fun keyPressed(e: KeyEvent) {
        if (e.keyCode == KeyEvent.VK_SPACE) {
            firstGameStarted = true
            velocityY = -12

            if (gameOver) {
                reset()
            }
        }
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        draw(g)
    }

    private fun draw(g: Graphics) {
        // Background rendering
        g.drawImage(backgroundImage, 0, 0, App.FRAME_WIDTH, App.FRAME_HEIGHT, null)

        // Objects rendering
        bird.render(g)
        pipes.forEach {
            it.render(g)
        }

        // UI rendering
        g.color = Color.white
        g.font = uiFont
        if (firstGameStarted) {
            if (gameOver) {
                g.drawString("Game over: $score", 10, 35)
            } else {
                g.drawString(score.toString(), 10, 35)
            }
        } else {
            g.drawString("Press SPACE to start", 10, 35)
        }
    }

    private fun placePipes() {
        val randomPipeY: Int = (0 - (PipeRow.PIPE_HEIGHT / 4) - (random() * (PipeRow.PIPE_HEIGHT / 2))).toInt()

        val pipeRow = PipeRow(topPipeImage, bottomPipeImage, App.FRAME_WIDTH, randomPipeY)
        pipes.add(pipeRow)
    }

    private fun gameLoop() {
        move()
        repaint()
        if (gameOver) {
            pipePlacementTimer.stop()
            gameLoopTimer.stop()
        }
    }

    private fun move() {
        velocityY += gravity
        bird.y += velocityY
        bird.y = max(bird.y, 0) // Bird cannot go out of screen (y = 0)

        pipes.forEach {
            // Update X of each pipe row
            it.x += velocityX

            if (!it.birdPassed && bird.hasPassed(it)) {
                it.birdPassed = true
                score++
            }

            if (bird.hasCollisionWith(it)) {
                gameOver = true
            }
        }

        if (bird.y > App.FRAME_HEIGHT) {
            gameOver = true // Bird fell on the ground
        }
    }

    private fun reset() {
        // Reset values
        bird.y = App.FRAME_HEIGHT / 2
        velocityY = 0
        pipes.clear()
        score = 0
        gameOver = false

        // Continue game loop
        pipePlacementTimer.start()
        gameLoopTimer.start()
    }

    companion object {
        private const val PIPE_PLACEMENT_INTERVAL = 1500
        private const val FRAMES_PER_SECOND = 60
        private const val FPS_INTERVAL = 1000 / FRAMES_PER_SECOND
    }
}