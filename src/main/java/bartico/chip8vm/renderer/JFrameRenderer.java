package bartico.chip8vm.renderer;

import bartico.chip8vm.GhipU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JFrameRenderer extends JFrame implements Chip8Renderer {

    public static final int PIXEL_PER_COLUMN = 12;
    public static final int PIXEL_PER_ROW = 16;

    public JFrameRenderer(KeyListener keyListener){
        int width = GhipU.COLUMNS * PIXEL_PER_COLUMN;
        int height = GhipU.ROWS * PIXEL_PER_ROW;
        Dimension contentSize = new Dimension(width, height);

        //TODO: Recalculate pixel per column/row based on the contentPane size;
        pack();
        Insets insets = getInsets();
        Dimension size = new Dimension(insets.left + width + insets.right,insets.top + height + insets.bottom );
        setSize(size);
        getContentPane().setPreferredSize(contentSize);
        setResizable(false);
        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        addKeyListener(keyListener);
    }

    @Override
    public void draw(int[] memory) {
        Graphics graphics = this.rootPane.getGraphics();
        for (int row = 0; row < GhipU.ROWS; row++) {
            for (int column = 0; column < GhipU.COLUMNS; column++) {
                graphics.setColor(memory[column + (row * 64)] == 1 ? Color.WHITE : Color.BLACK);
                graphics.fillRect(column * PIXEL_PER_COLUMN, row * PIXEL_PER_ROW, PIXEL_PER_COLUMN, PIXEL_PER_ROW);
            }
        }
    }
}
