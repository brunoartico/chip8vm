package bartico.chip8vm.operations.fgroup;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.input.Input8;
import bartico.chip8vm.operations.OperationCodeStrategy;

public class FGroupBCDOperation implements OperationCodeStrategy {
    public static final int OP_CODE = 0xF033;
    private int register;

    @Override
    public OperationCodeStrategy setup(int operation) {
        this.register = ((operation & 0x0F00) >> 8);
        return this;
    }

    @Override
    public void visit(ChipU chipU, GhipU ghipU, Input8 io) {
        int value = (chipU.readRegister(this.register) & 0x00FF);
        int memoryRegister = chipU.getMemoryRegister();
        chipU.setMemory(memoryRegister, (value / 100));
        chipU.setMemory((memoryRegister + 1), ((value / 10) % 10));
        chipU.setMemory( (memoryRegister + 2), ((value % 100) % 10));
    }
}
