package bartico.chip8vm.operations;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.input.Input8;

public class DrawOperation implements OperationCodeStrategy {
    public static final int OP_CODE = 0xD000;
    private int rows;
    private int yRegister;
    private int xRegister;


    @Override
    public OperationCodeStrategy setup(int operation) {
        xRegister = (operation & 0x0F00) >> 8;
        yRegister = (operation & 0x00F0) >> 4;
        rows = (operation & 0x000F);
        return this;
    }

    @Override
    public void visit(ChipU chipU, GhipU ghipU, Input8 io) {
        int xValue = chipU.readRegister(xRegister);
        int yValue = chipU.readRegister(yRegister);
        int[] sprite = new int[rows];

        for(int idx = 0; idx < rows; idx++)
            sprite[idx] = chipU.readMemory(chipU.getMemoryRegister() + idx);

        chipU.setRegister(ChipU.COLISION_REGISTER, ghipU.render(xValue, yValue, sprite));
    }
}
