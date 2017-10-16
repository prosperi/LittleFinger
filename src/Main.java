import assembler.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        String fileName = "./src/files/code_01.as";

        try (Stream<String> source = Files.lines(Paths.get(fileName))) {

            Assembler assembler = new Assembler(source);

            assembler.assemble();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
