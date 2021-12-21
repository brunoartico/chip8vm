package bartico.chip8vm.operations.eightgroup;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.input.Input8;
import bartico.chip8vm.operations.OperationCodeStrategy;

public class EightGroupSubtractFromRegistryAlternateOperation implements OperationCodeStrategy {
    public static final int OP_CODE = 0x8007;
    private int xRegister;
    private int yRegister;

    @Override
    public OperationCodeStrategy setup(int operation) {
        this.xRegister = ((operation & 0x0F00) >> 8);
        this.yRegister =  ((operation & 0x00F0) >> 4);
        return this;
    }

    @Override
    public void visit(ChipU chipU, GhipU ghipU, Input8 io) {
        int xValue = chipU.readRegister(xRegister);
        int  yValue = chipU.readRegister(yRegister);
        int value = (yValue - xValue);
        chipU.setRegister(xRegister, value);
        chipU.setCarryFlag(yValue >= xValue);
    }
}
