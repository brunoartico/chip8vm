package bartico.chip8vm.operations;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.input.Input8;

public class JumpOperation implements OperationCodeStrategy {
    public static final int OP_CODE = 0x1000;
    private int address;

    @Override
    public OperationCodeStrategy setup(int operation) {
        this.address =  (operation & 0x0FFF);
        return this;
    }

    @Override
    public void visit(ChipU chipU, GhipU ghipU, Input8 io) {
        chipU.setProgramCounter(address);
    }
}
