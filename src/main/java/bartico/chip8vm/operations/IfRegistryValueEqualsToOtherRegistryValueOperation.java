package bartico.chip8vm.operations;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.input.Input8;

public class IfRegistryValueEqualsToOtherRegistryValueOperation implements OperationCodeStrategy {
    public static final int OP_CODE = 0x5000;
    private int xRegister;
    private int yRegister;

    @Override
    public OperationCodeStrategy setup(int operation) {
        this.xRegister = ((operation & 0x0F00) >> 8);
        this.yRegister = ((operation & 0x00F0) >> 4);
        return this;
    }

    @Override
    public void visit(ChipU chipU, GhipU ghipU, Input8 io) {
        if(chipU.readRegister(xRegister) == chipU.readRegister(yRegister))
            chipU.gotoNextOperation();
    }
}
