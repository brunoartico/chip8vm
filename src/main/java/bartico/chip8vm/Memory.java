package bartico.chip8vm;

import bartico.chip8vm.operations.OperationCodeStrategy;
import bartico.chip8vm.operations.OperationCodeStrategyFactory;

public class Memory {
    public static final int MEMORY_SIZE = 4096;
    public static final int PROGRAM_START_POS = 512;
    public static final int STACK_START_POS = 3744;
    public static final int STACK_END_POS = 3839;
    public static final int FONT_HEIGHT_SIZE = 5;
    public static final int FONT_SET_START_POS = 0;

    private int currentStack = STACK_START_POS;
    int[] memory = new int[MEMORY_SIZE];

    public void reset() {
        memory = new int[MEMORY_SIZE];
        currentStack = STACK_START_POS;
        loadFontSet();
    }

    private void loadFontSet() {
        //Zero
        memory[0] = 0xF0;
        memory[1] = 0x90;
        memory[2] = 0x90;
        memory[3] = 0x90;
        memory[4] = 0xF0;
        //One
        memory[5] = 0x20;
        memory[6] = 0x60;
        memory[7] = 0x20;
        memory[8] = 0x20;
        memory[9] = 0x70;
        //Two
        memory[10] = 0xF0;
        memory[11] = 0x10;
        memory[12] = 0xF0;
        memory[13] = 0x80;
        memory[14] = 0xF0;
        //Three
        memory[15] = 0xF0;
        memory[16] = 0x10;
        memory[17] = 0xF0;
        memory[18] = 0x10;
        memory[19] = 0xF0;
        //Four
        memory[20] = 0x90;
        memory[21] = 0x90;
        memory[22] = 0xF0;
        memory[23] = 0x10;
        memory[24] = 0x10;
        //Five
        memory[25] = 0xF0;
        memory[26] = 0x80;
        memory[27] = 0xF0;
        memory[28] = 0x10;
        memory[29] = 0xF0;
        //Six
        memory[30] = 0xF0;
        memory[31] = 0x80;
        memory[32] = 0xF0;
        memory[33] = 0x90;
        memory[34] = 0xF0;
        //Seven
        memory[35] = 0xF0;
        memory[36] = 0x10;
        memory[37] = 0x20;
        memory[38] = 0x40;
        memory[39] = 0x40;
        //Eight
        memory[40] = 0xF0;
        memory[41] = 0x90;
        memory[42] = 0xF0;
        memory[43] = 0x90;
        memory[44] = 0xF0;
        //Nine
        memory[45] = 0xF0;
        memory[46] = 0x90;
        memory[47] = 0xF0;
        memory[48] = 0x10;
        memory[49] = 0xF0;
        //A
        memory[50] = 0xF0;
        memory[51] = 0x90;
        memory[52] = 0xF0;
        memory[53] = 0x90;
        memory[54] = 0x90;
        //B
        memory[55] = 0xE0;
        memory[56] = 0x90;
        memory[57] = 0xE0;
        memory[58] = 0x90;
        memory[59] = 0xE0;
        //C
        memory[60] = 0xF0;
        memory[61] = 0x80;
        memory[62] = 0x80;
        memory[63] = 0x80;
        memory[64] = 0xF0;
        //D
        memory[65] = 0xE0;
        memory[66] = 0x90;
        memory[67] = 0x90;
        memory[68] = 0x90;
        memory[69] = 0xE0;
        //E
        memory[70] = 0xF0;
        memory[71] = 0x80;
        memory[72] = 0xF0;
        memory[73] = 0x80;
        memory[74] = 0xF0;
        //F
        memory[75] = 0xF0;
        memory[76] = 0x80;
        memory[77] = 0xF0;
        memory[78] = 0x80;
        memory[79] = 0x80;
    }

    public void loadProgram(byte[] programBytes) {
        for(int idx = 0; idx < programBytes.length; idx++)
            memory[idx + PROGRAM_START_POS] = programBytes[idx];
    }

    public OperationCodeStrategy readOperationCode(int position) {
        return OperationCodeStrategyFactory.decodeOperation(memory[position], memory[position+1]);
    }

    public int read(int position) {
        return memory[position];
    }

    public void stack(int programCounter) {
        if(currentStack > STACK_END_POS){
            throw new StackOverflowError();
        }

        memory[currentStack++] =  ((programCounter & 0xFF00) >> 8);
        memory[currentStack++] =  (programCounter & 0x00FF);
    }

    public void set(int position, int value) {
        memory[position] = value;
    }

    public int popStack() {
        int low = memory[--currentStack];
        int high = memory[--currentStack];

        if(currentStack < STACK_START_POS)
            throw new StackOverflowError();

        int highValue = (high << 8);
        int lowValue = low & 0x00FF;
        return highValue | lowValue;
    }
}
