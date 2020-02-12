package fr.unice.polytech.rythmML.dsl;

import fr.unice.polytech.rythmML.dsl.visitor.MusicListener;
import fr.unice.polytech.rythmML.kernel.Partition;
import grammar.RythmMLLexer;
import grammar.RythmMLParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.nio.file.Path;

class Runner {

    // TODO: 05/02/2020 Change return to MIDI File
    void run(Path pathFile) throws Exception {
        CharStream stream = getCharStream(pathFile);
        Partition partition = buildModel(stream);
        partition.generateMIDI();
    }

    private CharStream getCharStream(Path input) throws IOException {
        System.out.println("Using input file: " + input);
        return CharStreams.fromPath(input);
    }

    private Partition buildModel(CharStream stream) {
        RythmMLLexer lexer = new RythmMLLexer(stream);
        lexer.removeErrorListeners();
        RythmMLParser parser = new RythmMLParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();

        ParseTreeWalker walker = new ParseTreeWalker();
        MusicListener listener = new MusicListener();

        walker.walk(listener, parser.root());

        return listener.retrieve();
    }
}
