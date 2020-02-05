package fr.unice.polytech.rythmML.dsl;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("\n\nRunning the ANTLR compiler for RythmML");
        File file = new File(Main.class.getClassLoader().getResource("scenario").getFile());
        List<Path> listOfFiles = Arrays.stream(file.listFiles()).map(File::toPath).collect(Collectors.toList());
        Collections.sort(listOfFiles);

        Runner runner = new Runner();
        for (Path path : listOfFiles) {
            runner.run(path);
        }
    }

}
