package bartico.chip8vm.operations.eightgroup;

import bartico.chip8vm.ChipU;
import bartico.chip8vm.GhipU;
import bartico.chip8vm.input.Input8;
import bartico.chip8vm.operations.OperationCodeStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EightGroupOperationFactory implements OperationCodeStrategy {
    public static final int OP_CODE = 0x8000;

    private int operation;
    private Map<Integer,Supplier<OperationCodeStrategy>> strategies = new HashMap<>();

    {
        strategies.put(EightGroupBitwiseAndOperation.OP_CODE, EightGroupBitwiseAndOperation::new);
        strategies.put(EightGroupAddToRegistryOperation.OP_CODE, EightGroupAddToRegistryOperation::new);
        strategies.put(EightGroupAssignToRegistryOperation.OP_CODE, EightGroupAssignToRegistryOperation::new);
        strategies.put(EightGroupSubtractFromRegistryOperation.OP_CODE, EightGroupSubtractFromRegistryOperation::new);
        strategies.put(EightGroupSubtractFromRegistryAlternateOperation.OP_CODE, EightGroupSubtractFromRegistryAlternateOperation::new);
        strategies.put(EightGroupBitwiseOrOperation.OP_CODE, EightGroupBitwiseOrOperation::new);
        strategies.put(EightGroupXorOperation.OP_CODE, EightGroupXorOperation::new);
        strategies.put(EightGroupShiftLeftOperation.OP_CODE, EightGroupShiftLeftOperation::new);
        strategies.put(EightGroupShiftRightOperation.OP_CODE, EightGroupShiftRightOperation::new);
    }

    @Override
    public OperationCodeStrategy setup(int operation) {
        this.operation = operation;
        Supplier<OperationCodeStrategy> operationCodeStrategySupplier = strategies.get(operation & 0xF00F);
        return operationCodeStrategySupplier != null ? operationCodeStrategySupplier.get().setup(operation)
                : this;
    }

    @Override
    public void visit(ChipU chipU, GhipU ghipU, Input8 io) {
        //TODO: Make a switch
        System.out.println(String.format("Unknown 8-Group operation: %04x", (int) operation));
    }
}
