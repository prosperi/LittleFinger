package simulator;

import assembler.Assembler;
import helpers.Converter;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Main extends Application {

    private static Assembler assembler;
    private static CPU cpu;

    private static ArrayList<String> _source;
    private static ArrayList<String> _assembly;

    private static GridPane memory;
    private static TextArea code;

    private static String header;
    private static long size = 0;
    private static Scene scene;
    private static ArrayList<String> chunks;

    private static String input;
    private static String inputAssembly;

    private static Rectangle rect;

    public static void main(String[] args) {

        inputAssembly = "./src/files/code_01.as";
        String[] tmp = inputAssembly.split("/");
        input = "./src/out/" + tmp[tmp.length - 1].split("\\.")[0] + ".o";
        System.out.println(input);

        readFile();

        launch(args);
    }

    /**
     * Setup stage, event handlers and simulation
     * @param stage
     */
    @Override
    public void start(Stage stage) {

        stage.setTitle("LittleFinger");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SimulatorView.fxml"));
            scene = new Scene(root);

            stage.setResizable(false);

            stage.setScene(scene);
            stage.show();

            memory = (GridPane) scene.lookup("#memory");
            code = (TextArea) scene.lookup("#code");

            Pane assemble = (Pane) scene.lookup("#assemble");
            Pane reset = (Pane) scene.lookup("#reset");
            Pane step = (Pane) scene.lookup("#step");
            Pane save = (Pane) scene.lookup("#save");


            rect = new Rectangle(0, 0, 20, 20);
            rect.setFill(Color.TRANSPARENT);
            rect.setStroke(Color.RED);
            rect.setStrokeWidth(1);
            rect.setArcHeight(10);
            rect.setArcWidth(10);

            // Assemble the source file and initialize CPU
            assemble.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    assembler.assemble();
                    try {
                        Stream<String> source = Files.lines(Paths.get(input));
                        source.forEach(value -> _source.add(value) );
                        ((TextArea) scene.lookup("#labels")).clear();
                        ((TextArea) scene.lookup("#labels")).appendText(assembler.st());
                        System.out.println(assembler.st() + "done");
                        loadMemory();


                        cpu = new CPU(_source);
                        setupFlags();
                        mark();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            // reset CPU
            reset.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("here we go");
                    cpu.reset();
                    memory.getChildren().clear();
                    reset();
                }
            });

            // Execute a step in cpu
            step.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (!cpu.execute()){
                        System.out.println("HALT");
                        scene.lookup("#halt").setVisible(true);
                        step.setDisable(true);
                    }
                    setupFlags();
                    mark();
                }
            });

            // Save Memory state and CPU state & config in a file
            save.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        String[] tmp = inputAssembly.split("/");
                        String destination = "./src/out/" + tmp[tmp.length - 1].split("\\.")[0] + "_new.o";

                        byte[] output = (_source.get(0) + cpu.memory().state()).getBytes();

                        Files.write(Paths.get(destination), output);

                        destination = "./src/out/" + tmp[tmp.length - 1].split("\\.")[0] + "_config.o";
                        output = (cpu.toString() + "\n" + cpu.config()) .getBytes();

                        Files.write(Paths.get(destination), output);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            loadCode();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Read provided input file and construct list of all the lines
     */
    public static void readFile () {
        _source = new ArrayList<String>();
        _assembly = new ArrayList<String>();

        try {
            Stream<String> source = Files.lines(Paths.get(inputAssembly));
            source.forEach(value -> _assembly.add(value));

            assembler = new Assembler(_assembly);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Load Memory and display in the grid
     */
    public static void loadMemory () {
        chunks = new ArrayList<String>();

        _source.forEach(value -> {
            size++;
            if (value.length() > 1 && value.substring(0, 1).equals("#"))  header = value;
            else {
                for (int i = 0; i < value.length() - 1; i += 2) {
                    chunks.add("" + value.charAt(i) + value.charAt(i + 1));
                }
            }

        });

        setupMemory(chunks, 0);

    }


    /**
     * Setup Memory based on the cpu main memory
     * @param chunks
     * @param start
     */
    public static void setupMemory (ArrayList<String> chunks, int start) {


        memory.getChildren().clear();

        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < size - 1; j++){
                if (chunks.size() <= (8 * j + i - 1)) continue;
                memory.add(new Label(chunks.get(8 * j + i - 1)), i, j);
            }
        }

        for (int j = 0; j < chunks.size() / 8; j++){
            memory.add(new Label(Converter.decimalToHex(8 * j + start) + " ||  "), 0, j);
        }
    }

    /**
     * Load Code in TextArea based on provided source file
     */
    public static void loadCode () {
        for (int i = 0; i < _assembly.size(); i++) {
            code.appendText(_assembly.get(i) + "\n");
        }
    }

    /**
     * Set red rectangle on current location in memory
     */
    public static void mark () {
        memory.getChildren().remove(rect);
        memory.add(rect, cpu.pc() % 8 + 1, cpu.pc() / 8);
    }

    /**
     * Setup Flags and show their values with registers
     */
    public static void setupFlags () {
        ArrayList<String> tmp = cpu.config();
        GridPane flags = (GridPane) scene.lookup("#flags");
        flags.getChildren().clear();

         for (int i = 0; i < tmp.size(); i++) {
            System.out.println(tmp.get(i) + " " + i / 2 + " " + i % 2);
            flags.add(new Label(tmp.get(i)), i / 2, i % 2);
        }
    }

    /**
     * Reset application
     */
    public static void reset () {
        ((Node)scene.lookup("#halt")).setVisible(false);
        ((Pane) scene.lookup("#step")).setDisable(false);
        ((TextArea) scene.lookup("#labels")).clear();
    }
}
