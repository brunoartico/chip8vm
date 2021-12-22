package bartico;

import bartico.chip8vm.Chip8VM;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class App
{
    public static void main( String[] args ) throws IOException {
        if (args.length == 0) {
            System.out.println("Please provide a program name:");
            return;
        }

        byte[] programBytes = readProgramFromResource("programs/" + args[0]);
        //TODO: Implement a reset

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
