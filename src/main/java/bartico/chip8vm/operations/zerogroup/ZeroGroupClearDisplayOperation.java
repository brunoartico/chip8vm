package bartico.chip8vm.operations.zerogroup;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.input.Input8;
import bartico.chip8vm.operations.OperationCodeStrategy;

public class ZeroGroupClearDisplayOperation implements OperationCodeStrategy {
    public static final int OP_CODE = 0x00e0;

    @Override
    public OperationCodeStrategy setup(int operation) {
        return this;
    }

    @Override
    public void visit(ChipU chipU, GhipU ghipU, Input8 io) {
        ghipU.reset();
    }
}
