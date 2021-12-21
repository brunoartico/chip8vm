package bartico;

import bartico.chip8vm.Chip8VM;
import bartico.chip8vm.sound.SoundUtils;

import javax.sound.sampled.LineUnavailableException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class App
{
    public static void main( String[] args ) throws IOException, LineUnavailableException {
        //byte[] programBytes = readProgramFromResource("programs/BC_test.ch8");
        byte[] programBytes = readProgramFromResource("programs/PONG");
        //TODO: Implement a reset
        //TODO: Implement a program picker

        new Chip8VM().runProgram(programBytes);
    }

    private static byte[] readProgramFromResource(String resourcePath) throws IOException {
        byte[] programBytes;
        try(InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath)){
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int nRead;
            byte[] data = new byte[1024];

            while ((nRead = resourceAsStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            programBytes = buffer.toByteArray();
        }
        return programBytes;
    }
}
