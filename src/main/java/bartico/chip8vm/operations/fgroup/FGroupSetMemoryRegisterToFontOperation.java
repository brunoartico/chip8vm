package bartico.chip8vm.operations.fgroup;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.Memory;
import bartico.chip8vm.input.Input8;
import bartico.chip8vm.operations.OperationCodeStrategy;

public class FGroupSetMemoryRegisterToFontOperation implements OperationCodeStrategy {
    public static final int OP_CODE = 0xF029;
    private int register;

    @Override
    public OperationCodeStrategy setup(int operation) {
        this.register = ((operation & 0x0F00) >> 8);
        return this;
    }

    @Override
    public void visit(ChipU chipU, GhipU ghipU, Input8 io) {
        int register = chipU.readRegister(this.register);
        //TODO: Fix dependencies
        chipU.setMemoryRegister( (register * Memory.FONT_HEIGHT_SIZE));
    }
}
