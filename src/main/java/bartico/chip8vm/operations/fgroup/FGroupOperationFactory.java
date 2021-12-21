package bartico.chip8vm.operations.fgroup;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.input.Input8;
import bartico.chip8vm.operations.OperationCodeStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FGroupOperationFactory implements OperationCodeStrategy {
    public static final int OP_CODE = 0xF000;
    private int operation;
    private Map<Integer,Supplier<OperationCodeStrategy>> strategies = new HashMap<>();

    {
        strategies.put(FGroupBCDOperation.OP_CODE, FGroupBCDOperation::new);
        strategies.put(FGroupLoadToRegistersOperation.OP_CODE, FGroupLoadToRegistersOperation::new);
        strategies.put(FGroupSetMemoryRegisterToFontOperation.OP_CODE, FGroupSetMemoryRegisterToFontOperation::new);
        strategies.put(FGroupSetDelayTimerOperation.OP_CODE, FGroupSetDelayTimerOperation::new);
        strategies.put(FGroupStoreDelayTimerOperation.OP_CODE, FGroupStoreDelayTimerOperation::new);
        strategies.put(FGroupSetSoundTimerOperation.OP_CODE, FGroupSetSoundTimerOperation::new);
        strategies.put(FGroupStoreFromRegistersOperation.OP_CODE, FGroupStoreFromRegistersOperation::new);
        strategies.put(FGroupIncreaseMemoryPointerByRegisterOperation.OP_CODE, FGroupIncreaseMemoryPointerByRegisterOperation::new);
        strategies.put(FGroupWaitKeyAndStoreToRegisterOperation.OP_CODE, FGroupWaitKeyAndStoreToRegisterOperation::new);
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
        //TODO: Make a switch
       System.out.println(String.format("Unknown F-Group operation: %04x", (int) operation));
    }
}
