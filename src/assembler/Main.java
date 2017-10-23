package assembler;

import assembler.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 * @author Zura Mestiashvili
 * @version v1.0.0
 *
 *
 * Main class initializes Assembler and calls assemble
 * method on it to generate machine code
 */

public class Main {
    public static void main(String[] args) {

        String input = args[0];
        String[] tmp = input.split("/");
        String destination = "./out/" + tmp[tmp.length - 1].split("\\.")[0] + ".o";

        try (Stream<String> source = Files.lines(Paths.get(input))) {

            Assembler assembler = new Assembler(source);

            // assemble
            byte[] assembled = assembler.assemble().getBytes();

            Files.write(Paths.get(destination), assembled);

            System.out.println("Total number of instructions, .double, .half, .byte and .single: " + assembler.counter());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
