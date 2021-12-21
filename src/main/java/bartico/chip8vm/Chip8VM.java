package bartico.chip8vm;

import bartico.chip8vm.input.KeyListenerInput8;
import bartico.chip8vm.renderer.JFrameRenderer;

public class Chip8VM {
    Memory memory = new Memory();
    KeyListenerInput8 io = new KeyListenerInput8();
    GhipU gpu = new GhipU(new JFrameRenderer(io));
    ChipU cpu = new ChipU(memory, gpu, io);

    public void runProgram(byte[] programBytes) {
        gpu.reset();
        cpu.reset();
        memory.reset();
        memory.loadProgram(programBytes);

        while(true){
            cpu.executeACycle();
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
