package nl.rwslinkman.flappybird

import java.awt.event.KeyEvent
import java.awt.event.KeyListener

/**
 * This interface abstracts away the 2 unused functions, only 1 function remains and must be implemented.
 * It was created to keep the FlappyBird class a bit cleaner
 */
interface KeyPressedListener: KeyListener {
    override fun keyTyped(e: KeyEvent) {
        // NOP
    }

    override fun keyReleased(e: KeyEvent) {
        // NOP
    }
}