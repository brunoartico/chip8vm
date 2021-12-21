package bartico.chip8vm;

import bartico.chip8vm.input.Input8;
import bartico.chip8vm.operations.OperationCodeStrategy;
import bartico.chip8vm.sound.SoundUtils;

import javax.sound.sampled.LineUnavailableException;
import java.awt.*;

public class ChipU {
    public static final int REGISTERS_SIZE = 16;
    public static final int COLISION_REGISTER = 0xF;
    public static final int CARRY_REGISTER = 0xF;
    public static final int TIMERS_REDUCTION_FREQ = 16;

    private final Memory memory;
    private final GhipU ghipU;
    private final Input8 io;
    private int[] registers = new int[REGISTERS_SIZE];
    private int i;
    private int programCounter;
    private int delayTimer;
    private long lastDelayReduction;
    private int soundTimer;
    private long lastSoundReduction;

    public ChipU(Memory memory, GhipU ghipU, Input8 io) {
        this.memory = memory;
        //TODO: Remove this dependency
        this.ghipU = ghipU;
        this.io = io;
    }

    public void reset() {
        registers = new int[REGISTERS_SIZE];
        i = 0;
        programCounter = Memory.PROGRAM_START_POS;
    }

    public void executeACycle() {
        readOperation().visit(this, ghipU, io);
        updateDelayTimer();
        updateSoundTimer();
    }

    private void updateSoundTimer() {
        //TODO: Try to fix this shitty impl
        if(soundTimer > 0 && (System.currentTimeMillis() - lastSoundReduction) > TIMERS_REDUCTION_FREQ){
            SoundUtils.tone(350,1);

            if(--soundTimer <= 0){
                SoundUtils.flush();
            }

            lastSoundReduction = System.currentTimeMillis();
        }
    }

    private void updateDelayTimer() {
        if(delayTimer > 0 && (System.currentTimeMillis() - lastDelayReduction) > 16){
            delayTimer--;
            lastDelayReduction = System.currentTimeMillis();
        }
    }

    private OperationCodeStrategy readOperation() {
        OperationCodeStrategy operationCodeStrategy = memory.readOperationCode(programCounter);
        gotoNextOperation();
        return operationCodeStrategy;
    }

    public void gotoNextOperation() {
        programCounter += 2;
    }

    public void setRegister(int register, int value) {
        registers[register] = value & 0xFF;
    }

    public void setMemoryRegister(int memoryRegister) {
        this.i = memoryRegister;
    }

    public int readRegister(int register) {
        return registers[register];
    }

    public int getMemoryRegister() {
        return i;
    }

    public int readMemory(int position) {
        return memory.read(position);
    }

    public void setMemory(int position, int value){
        memory.set(position,value);
    }

    public void stackProgramCounter() {
        memory.stack(programCounter);
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public int popProgramCounter() {
        return memory.popStack();
    }

    public void setDelayTimer(int delayTimer) {
        this.delayTimer = delayTimer;
    }

    public int getDelayTimer() {
        return delayTimer;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setCarryFlag(boolean carryFlag) {
        registers[CARRY_REGISTER] = carryFlag ? 1 : 0;
    }

    public void setSoundTimer(int soundTimer) {
        this.soundTimer = soundTimer;
    }
}
