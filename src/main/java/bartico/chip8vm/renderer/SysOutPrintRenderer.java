package bartico.chip8vm.renderer;

import bartico.chip8vm.GhipU;

public class SysOutPrintRenderer implements Chip8Renderer {
    @Override
    public void draw(int[] memory) {
        System.out.println("BEGIN --------------------------------------");

        for (int row = 0; row < GhipU.ROWS; row++) {
            for (int column = 0; column < GhipU.COLUMNS; column++) {
                System.out.print(Integer.toBinaryString(memory[column + (row * 64)]));
            }
            System.out.println();
        }
        System.out.println("END --------------------------------------");
    }
}
