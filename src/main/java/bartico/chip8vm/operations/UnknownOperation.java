package bartico.chip8vm.operations;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.input.Input8;

public class UnknownOperation implements OperationCodeStrategy {

    private int operation;

    @Override
    public OperationCodeStrategy setup(int operation) {
        this.operation = operation;
        return this;
    }

    @Override
    public void visit(ChipU chipU, GhipU ghipU, Input8 io) {
        String format = String.format("Unknown Operation: %04x", (int) operation);
        //TODO: Make a switch
        System.out.println(format);
    }
}
