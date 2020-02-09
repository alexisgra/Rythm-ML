package fr.unice.polytech.rythmML.externaldsl;

import grammar.RythmMLLexer;
import grammar.RythmMLParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws Exception {
        System.out.println("\n\nRunning the ANTLR compiler for ArduinoML");
        File file = new File(Main.class.getClassLoader().getResource("scenario").getFile());
        List<Path> listOfFiles = Arrays.stream(file.listFiles()).map(File::toPath).collect(Collectors.toList());
        Collections.sort(listOfFiles);
        for (int i = 0; i < listOfFiles.size(); i++) {
            CharStream stream = getCharStream(listOfFiles.get(i));
            buildModel(stream);
        }

    }

    private static CharStream getCharStream(Path input) throws IOException {
        System.out.println("Using input file: " + input);
        return CharStreams.fromPath(input);
    }

    private static void buildModel(CharStream stream) {
        RythmMLLexer lexer = new RythmMLLexer(stream);
        lexer.removeErrorListeners();

        RythmMLParser parser = new RythmMLParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();

        ParseTreeWalker walker = new ParseTreeWalker();
        MusicListener listener = new MusicListener();

        walker.walk(listener, parser.root()); // parser.root() is the entry point of the grammar
    }
}
