package bartico.chip8vm.operations;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.input.Input8;

public class RandomOperation implements OperationCodeStrategy {
    public static final int OP_CODE = 0xC000;
    private int register;
    private int constant;

    @Override
    public OperationCodeStrategy setup(int operation) {
        this.register = ((operation & 0x0F00) >> 8);
        this.constant = (operation & 0x00FF);
        return this;
    }

    @Override
    public void visit(ChipU chipU, GhipU ghipU, Input8 io) {
        //TODO: Check the range is OK
        int random = (int) (Math.random() * 255);
        chipU.setRegister(register, (random & constant));
    }
}
