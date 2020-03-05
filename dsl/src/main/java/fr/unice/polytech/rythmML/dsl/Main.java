package fr.unice.polytech.rythmML.dsl;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            System.out.println("\n\nRunning the ANTLR compiler for RythmML");
            Path input = Paths.get(new File(args[0]).toURI());
            Runner runner = new Runner();
            runner.run(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
