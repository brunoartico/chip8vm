package bartico.chip8vm.operations;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.input.Input8;

public class SetConstantToRegisterOperation implements OperationCodeStrategy {
    public static final int OP_CODE = 0x6000;
    private int constant;
    private int register;

    @Override
    public OperationCodeStrategy setup(int operation) {
        constant =  (operation & 0x00FF);
        register =  ((operation & 0x0F00) >> 8);
        return this;
    }

    @Override
    public void visit(ChipU chipU, GhipU ghipU, Input8 io) {
        chipU.setRegister(register, constant);
    }
}
