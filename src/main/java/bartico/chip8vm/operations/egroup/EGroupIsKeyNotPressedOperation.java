package bartico.chip8vm.operations.egroup;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.input.Input8;
import bartico.chip8vm.operations.OperationCodeStrategy;

public class EGroupIsKeyNotPressedOperation implements OperationCodeStrategy {
    public static final int OP_CODE = 0xE0A1;
    private int register;

    @Override
    public OperationCodeStrategy setup(int operation) {
        this.register = ((operation & 0x0F00) >> 8);
        return this;
    }

    @Override
    public void visit(ChipU chipU, GhipU ghipU, Input8 io) {
        int expectedKey = chipU.readRegister(register);
        if(io.keyPressed() != expectedKey){
            chipU.gotoNextOperation();
        }
    }
}
