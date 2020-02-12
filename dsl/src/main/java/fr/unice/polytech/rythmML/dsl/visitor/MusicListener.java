package fr.unice.polytech.rythmML.dsl.visitor;

import fr.unice.polytech.rythmML.kernel.Partition;
import fr.unice.polytech.rythmML.kernel.data.Instrument;
import grammar.RythmMLBaseListener;
import grammar.RythmMLParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class MusicListener extends RythmMLBaseListener {
    private Partition partition = null;
    private String currentBar = null;
    private String currentSection = null;
    private Instrument currentMusicNote = null;
    private String currentBarOfSection = null;

    public Partition retrieve() {
        return this.partition;
    }

    @Override
    public void enterPartition(RythmMLParser.PartitionContext ctx) {
        System.out.println("-------LISTENER--------");
        System.out.println(String.format("partition %s", ctx.name.getText()));
        partition = new Partition(ctx.name.getText());
    }

    @Override
    public void enterInit(RythmMLParser.InitContext ctx) {
        String bpm = ctx.bpmNumber.getText();
        String beatPerBar = ctx.beatPerBar.getText();
        List<TerminalNode> composition = ctx.IDENTIFIER();
        System.out.println(String.format("bpm %s", bpm));
        System.out.println(String.format("beatPerBar %s", beatPerBar));
        System.out.println("composition");
        for (TerminalNode terminalNode : composition) {
            System.out.println(terminalNode.getSymbol().getText());
        }
    }

    @Override
    public void enterBar(RythmMLParser.BarContext ctx) {
        String barName = ctx.barName.getText();
        currentBar = barName;
        System.out.println(String.format("bar %s", barName));
    }

    @Override
    public void enterMusicNote(RythmMLParser.MusicNoteContext ctx) {
        Instrument instrument = Instrument.lookupByDisplayName(ctx.instrument.getText());
        if (instrument == null) {
            throw new IllegalArgumentException(String.format("The given instrument %s doesn't exist", ctx.instrument.getText()));
        }
        System.out.println(String.format("instrument %s", instrument.displayName));
        if (ctx.note.getText().equals("beat")) {

        } else if (ctx.note.getText().equals("quarter")) {

        }

        currentMusicNote = instrument;
    }

    @Override
    public void exitBar(RythmMLParser.BarContext ctx) {
        currentBar = null;
    }

    @Override
    public void enterNotes(RythmMLParser.NotesContext ctx) {
        List<TerminalNode> nodes = ctx.NUMBER();
        for (TerminalNode node : nodes) {
            System.out.println(node.getSymbol().getText());
        }
        if (currentMusicNote != null) {

        } else if (currentBarOfSection != null) {

        }
    }

    @Override
    public void exitMusicNote(RythmMLParser.MusicNoteContext ctx) {
        currentMusicNote = null;
    }

    @Override
    public void enterSection(RythmMLParser.SectionContext ctx) {
        String sectionName = ctx.sectionName.getText();
        currentSection = sectionName;
        System.out.println(String.format("section %s", sectionName));
    }

    @Override
    public void enterBarOfSection(RythmMLParser.BarOfSectionContext ctx) {
        if (ctx.replace != null) {
            System.out.println("replace");
        }
        String barName = ctx.barName.getText();
        // todo : change this
        currentBarOfSection = barName;
        System.out.println(String.format("barName %s", barName));
    }

    @Override
    public void exitBarOfSection(RythmMLParser.BarOfSectionContext ctx) {
        currentBarOfSection = null;
    }

    @Override
    public void exitSection(RythmMLParser.SectionContext ctx) {
        currentSection = null;
    }
}