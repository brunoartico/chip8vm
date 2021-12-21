package bartico.chip8vm.operations.fgroup;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.input.Input8;
import bartico.chip8vm.operations.OperationCodeStrategy;

public class FGroupStoreFromRegistersOperation implements OperationCodeStrategy {
    public static final int OP_CODE = 0xF055;
    private int register;

    @Override
    public OperationCodeStrategy setup(int operation) {
        this.register = (operation & 0x0F00) >> 8;
        return this;
    }

    @Override
    public void visit(ChipU chipU, GhipU ghipU, Input8 io) {
        int memoryRegister = chipU.getMemoryRegister();
        for(int idx = 0; idx <= register; idx++){
            //TODO: Is this correct?
            chipU.setMemory( (memoryRegister + idx), chipU.readRegister(idx));
        }
    }
}
