package bartico.chip8vm.operations.zerogroup;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.input.Input8;
import bartico.chip8vm.operations.OperationCodeStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ZeroGroupOperationFactory implements OperationCodeStrategy {
    public static final int OP_CODE = 0x0000;
    private Map<Integer,Supplier<OperationCodeStrategy>> strategies = new HashMap<>();
    private int operation;

    {
        strategies.put(ZeroGroupReturnFromSubRoutineOperation.OP_CODE, ZeroGroupReturnFromSubRoutineOperation::new);
        strategies.put(ZeroGroupClearDisplayOperation.OP_CODE, ZeroGroupClearDisplayOperation::new);
    }

    @Override
    public OperationCodeStrategy setup(int operation) {
        this.operation = operation;
        Supplier<OperationCodeStrategy> operationCodeStrategySupplier = strategies.get(operation & 0xF0FF);
        return operationCodeStrategySupplier != null ? operationCodeStrategySupplier.get().setup(operation)
                : this;
    }

    @Override
    public void visit(ChipU chipU, GhipU ghipU, Input8 io) {
        System.out.println(String.format("Unknown Zero-Group operation: %04x", (int) operation));
    }
}
