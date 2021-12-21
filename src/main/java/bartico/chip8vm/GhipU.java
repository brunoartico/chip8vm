package bartico.chip8vm;

import bartico.chip8vm.renderer.Chip8Renderer;

public class GhipU {
    public static final int COLUMNS = 64;
    public static final int ROWS = 32;
    public static final int VIDEO_BUFFER_SIZE = COLUMNS * ROWS;
    private final Chip8Renderer chip8Renderer;
    private int[] memory = new int[VIDEO_BUFFER_SIZE];

    public GhipU(Chip8Renderer chip8Renderer){
        this.chip8Renderer = chip8Renderer;
    }

    public void reset(){
        memory = new int[VIDEO_BUFFER_SIZE];
    }

    public int render(int xValue, int yValue, int[] sprite) {
        int collided = renderSprite(xValue % COLUMNS, yValue % ROWS, sprite);
        chip8Renderer.draw(memory);
        return collided;
    }

    private int renderSprite(int xValue, int yValue, int[] sprite) {
        int collided = 0;
        for(int row = 0; row < sprite.length; row++){
            int spriteRow = sprite[row];
            for(int column = 0; column < 8; column++){
                if((spriteRow & (0x80 >> column)) != 0) {
                    int pixelPosition = (xValue + column + ((yValue + row) * COLUMNS)) % VIDEO_BUFFER_SIZE;
                    if(memory[pixelPosition] == 1)
                        collided = 1;
                    memory[pixelPosition] ^= 1;
                }
            }
        }
        return collided;
    }
}
