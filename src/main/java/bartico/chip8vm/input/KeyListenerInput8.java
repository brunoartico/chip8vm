package bartico.chip8vm.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerInput8 implements Input8, KeyListener {
    public static final int NO_KEY_PRESSED = 0xFF;
    private int keyPressed = NO_KEY_PRESSED;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.keyPressed = mapKey(e);
    }
    /*
Keypad                   Keyboard
+-+-+-+-+                +-+-+-+-+
|1|2|3|C|                |1|2|3|4|
+-+-+-+-+                +-+-+-+-+
|4|5|6|D|                |Q|W|E|R|
+-+-+-+-+       =>       +-+-+-+-+
|7|8|9|E|                |A|S|D|F|
+-+-+-+-+                +-+-+-+-+
|A|0|B|F|                |Z|X|C|V|
+-+-+-+-+                +-+-+-+-+

     */
    private int mapKey(KeyEvent e) {
        //TODO: introduce switch on and off
        //System.out.println(e.getKeyChar());
        switch(e.getKeyCode()){
            case KeyEvent.VK_1: return 0x01;
            case KeyEvent.VK_2: return 0x02;
            case KeyEvent.VK_3: return 0x03;
            case KeyEvent.VK_4: return 0x0C;
            case KeyEvent.VK_Q: return 0x04;
            case KeyEvent.VK_W: return 0x05;
            case KeyEvent.VK_E: return 0x06;
            case KeyEvent.VK_R: return 0x0D;
            case KeyEvent.VK_A: return 0x07;
            case KeyEvent.VK_S: return 0x08;
            case KeyEvent.VK_D: return 0x09;
            case KeyEvent.VK_F: return 0x0E;
            case KeyEvent.VK_Y: return 0x0A;
            case KeyEvent.VK_X: return 0x00;
            case KeyEvent.VK_C: return 0x0B;
            case KeyEvent.VK_V: return 0x0F;
            case KeyEvent.VK_ESCAPE: System.exit(0);
        }
        return 0xFF;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyPressed = NO_KEY_PRESSED;
    }

    @Override
    public int keyPressed() {
        return keyPressed;
    }

    @Override
    public int waitKeyPress() {
        while (keyPressed == NO_KEY_PRESSED) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
            }
        }

        return keyPressed;
    }
}