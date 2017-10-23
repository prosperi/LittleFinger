package visualizer;

import assembler.Assembler;
import com.sun.tools.javac.comp.Check;
import com.sun.tools.javac.util.Convert;
import helpers.Converter;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


import javax.xml.soap.Text;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Main extends Application {

    private static Stream<String> source;
    private static String header;
    private static long size = 0;
    private static Scene scene;
    private static ArrayList<String> chunks;
    private static String currentFormat;

    public static void main(String[] args) {
        readSource();

        launch(args);
    }

    /**
     * Construct view, Add event handlers and setup the application
     * @param stage
     */
    @Override
    public void start(Stage stage) {

        stage.setTitle("LittleFinger");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("VisualizerView.fxml"));
            scene = new Scene(root);

            stage.setResizable(false);

            stage.setScene(scene);
            stage.show();

            Pane filterBtn = (Pane) scene.lookup("#filterBtn");
            GridPane grid = (GridPane) scene.lookup("#grid");

            CheckBox binary = (CheckBox) scene.lookup("#binary");
            CheckBox hex = (CheckBox) scene.lookup("#hex");

            Pane loadBtn = (Pane) scene.lookup("#loadBtn");

            loadBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    readSource();
                    loadSource(grid);
                }
            });

            binary.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    hex.setSelected(false);
                    binary.setSelected(true);
                }
            });

            hex.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    hex.setSelected(true);
                    binary.setSelected(false);
                }
            });


            filterBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    filter();
                }
            });

            loadSource(grid);

            System.out.println(header);

            if (header.split(":")[0].equals("#hex")) {
                hex.setSelected(true);
                currentFormat = "#hex";
            } else {
                binary.setSelected(true);
                currentFormat = "#binary";
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Read provided source file
     */
    public static void readSource () {
        String input = "./src/out/code_01.o";

        try {
            source = Files.lines(Paths.get(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load source file in grid to view
     * @param grid
     */
    public static void loadSource (GridPane grid) {
        chunks = new ArrayList<String>();

        source.forEach(value -> {
            size++;
            if (value.length() > 1 && value.substring(0, 1).equals("#"))  header = value;
            else {
                for (int i = 0; i < value.length() - 1; i += 2) {
                    chunks.add("" + value.charAt(i) + value.charAt(i + 1));
                }
            }

        });

        setupGrid(chunks, 0);

    }

    /**
     *  Update format of displayed source file - binary or hex
     * @param format binary or hex
     */
    public static void changeFormat (String format) {

        if (format.equals(currentFormat)) return;

        if (format.equals("#hex")) {
            for (int i = 0; i < chunks.size(); i++) {
                chunks.set(i, Converter.binaryToHex(chunks.get(i)));
            }
        }

        if (format.equals("#binary")) {
            for (int i = 0; i < chunks.size(); i++) {
                System.out.println(chunks.get(i).charAt(0) + " " +chunks.get(i).charAt(1));
                chunks.set(i, Converter.hexToBinary("" + chunks.get(i).charAt(0), 4) +
                        Converter.hexToBinary("" + chunks.get(i).charAt(1), 4));
            }
        }
    }

    /**
     *
     * @param chunks lines of source file that need to be displayed
     * @param start starting position for line counting
     */
    public static void setupGrid (ArrayList<String> chunks, int start) {
        GridPane grid = (GridPane) scene.lookup("#grid");

        grid.getChildren().clear();

        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < size - 1; j++){
                if (chunks.size() <= (8 * j + i - 1)) continue;
                grid.add(new Label(chunks.get(8 * j + i - 1)), i, j);
            }
        }

        for (int j = 0; j < chunks.size() / 8; j++){
            grid.add(new Label(Converter.decimalToHex(8 * j + start) + " ||  "), 0, j);
        }
    }

    /**
     * Filter the source file based on the provided range, and check with
     * chosen format(binary or hex) at the same time
     */
    public static void filter () {
        int s, e;
        TextField start = (TextField) scene.lookup("#start");
        TextField end = (TextField) scene.lookup("#end");

        CheckBox binary = (CheckBox) scene.lookup("#binary");
        CheckBox hex = (CheckBox) scene.lookup("#hex");

        // Conversion between binary and hex
        if (binary.isSelected()) { changeFormat("#binary"); currentFormat = "#binary"; }
        else { changeFormat("#hex"); currentFormat = "#hex"; }

        if (!start.getText().equals("") && !end.getText().equals("")) {
            s = Converter.hexToDecimal(start.getText().split("0x")[1]);
            e = Converter.hexToDecimal(end.getText().split("0x")[1]);

            ArrayList<String> tmp = new ArrayList<String>();

            for (int i = s; i < e; i++) {
                tmp.add(chunks.get(i));
            }

            setupGrid(tmp, s);
        } else {
            setupGrid(chunks, 0);
        }


    }
}
