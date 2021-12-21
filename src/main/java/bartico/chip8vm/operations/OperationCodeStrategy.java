package bartico.chip8vm.operations;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.input.Input8;

public interface OperationCodeStrategy {
    OperationCodeStrategy setup(int operation);

    void visit(ChipU chipU, GhipU ghipU, Input8 io);
}
