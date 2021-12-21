package bartico.chip8vm.operations;

import bartico.chip8vm.operations.egroup.EGroupOperationFactory;
import bartico.chip8vm.operations.eightgroup.EightGroupOperationFactory;
import bartico.chip8vm.operations.fgroup.FGroupOperationFactory;
import bartico.chip8vm.operations.zerogroup.ZeroGroupOperationFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class OperationCodeStrategyFactory {
    private static Map<Integer,Supplier<OperationCodeStrategy>> strategies = new HashMap<>();
    private static final UnknownOperation unknownOperation = new UnknownOperation();
    private static final FGroupOperationFactory fGroupOperationFactory = new FGroupOperationFactory();
    private static final ZeroGroupOperationFactory zeroGroupOperationFactory = new ZeroGroupOperationFactory();
    private static final EightGroupOperationFactory eightGroupOperationFactory = new EightGroupOperationFactory();
    private static final EGroupOperationFactory eGroupOperationFactory = new EGroupOperationFactory();

    static {
        strategies.put(SetConstantToRegisterOperation.OP_CODE, SetConstantToRegisterOperation::new);
        strategies.put(SetAddressToMemoryRegisterOperation.OP_CODE, SetAddressToMemoryRegisterOperation::new);
        strategies.put(DrawOperation.OP_CODE, DrawOperation::new);
        strategies.put(CallSubroutineOperation.OP_CODE, CallSubroutineOperation::new);
        strategies.put(AddConstantToRegisterOperation.OP_CODE, AddConstantToRegisterOperation::new);
        strategies.put(IfRegistryValueEqualsToConstantOperation.OP_CODE, IfRegistryValueEqualsToConstantOperation::new);
        strategies.put(IfRegistryValueNotEqualsToConstantOperation.OP_CODE, IfRegistryValueNotEqualsToConstantOperation::new);
        strategies.put(IfRegistryValueEqualsToOtherRegistryValueOperation.OP_CODE, IfRegistryValueEqualsToOtherRegistryValueOperation::new);
        strategies.put(JumpOperation.OP_CODE, JumpOperation::new);
        strategies.put(RandomOperation.OP_CODE, RandomOperation::new);
        strategies.put(IfRegistryValueNotEqualsToOtherRegistryValueOperation.OP_CODE, IfRegistryValueNotEqualsToOtherRegistryValueOperation::new);
        strategies.put(ZeroGroupOperationFactory.OP_CODE, () -> zeroGroupOperationFactory);
        strategies.put(FGroupOperationFactory.OP_CODE, () -> fGroupOperationFactory);
        strategies.put(EightGroupOperationFactory.OP_CODE, () -> eightGroupOperationFactory);
        strategies.put(EGroupOperationFactory.OP_CODE, () -> eGroupOperationFactory);
    }

    private OperationCodeStrategyFactory(){}

    public static OperationCodeStrategy decodeOperation(int high, int low){
        int highValue = high << 8;
        int lowValue = low & 0x00FF;
        int operation = highValue | lowValue;
        int operationFirstKey = operation & 0xF000;
        return strategies.getOrDefault(operationFirstKey, ()->unknownOperation).get().setup(operation);
    }
}
