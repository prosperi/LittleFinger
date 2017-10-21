package simulator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Main {

    private static ArrayList<String> _source;

    public static void main(String[] args) {
        String input = "./src/out/code_01.o";
        _source = new ArrayList<String>();

        try {
            Stream<String> source = Files.lines(Paths.get(input));
            source.forEach(value -> _source.add(value));

            CPU cpu = new CPU(_source);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
