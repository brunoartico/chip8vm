package bartico.chip8vm.operations;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.input.Input8;

public class SetAddressToMemoryRegisterOperation implements OperationCodeStrategy {
    public static final int OP_CODE = 0xA000;
    private int address;

    @Override
    public OperationCodeStrategy setup(int operation) {
        address = (operation & 0x0FFF);
        return this;
    }

    @Override
    public void visit(ChipU chipU, GhipU ghipU, Input8 io) {
        chipU.setMemoryRegister(address);
    }
}
