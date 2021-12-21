package bartico.chip8vm.operations.eightgroup;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.input.Input8;
import bartico.chip8vm.operations.OperationCodeStrategy;

public class EightGroupShiftLeftOperation implements OperationCodeStrategy {
    public static final int OP_CODE = 0x800E;
    private int register;

    @Override
    public OperationCodeStrategy setup(int operation) {
        this.register = ((operation & 0x0F00) >> 8);
        return this;
    }

    @Override
    public void visit(ChipU chipU, GhipU ghipU, Input8 io) {
        int value = chipU.readRegister(register);
        int leftMostBit = ((value >> 7) & 0x01);
        chipU.setRegister(ChipU.CARRY_REGISTER, leftMostBit);
        chipU.setRegister(register, value << 1);
    }
}
