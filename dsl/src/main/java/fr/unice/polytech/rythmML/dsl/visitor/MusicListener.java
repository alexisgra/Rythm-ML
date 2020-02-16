package fr.unice.polytech.rythmML.dsl.visitor;

import fr.unice.polytech.rythmML.kernel.Partition;
import fr.unice.polytech.rythmML.kernel.data.DrumsElements;
import fr.unice.polytech.rythmML.kernel.temporal.Bar;
import fr.unice.polytech.rythmML.kernel.temporal.Beat;
import fr.unice.polytech.rythmML.kernel.temporal.Division;
import fr.unice.polytech.rythmML.kernel.temporal.Section;
import fr.unice.polytech.rythmML.kernel.track.Note;
import grammar.RythmMLBaseListener;
import grammar.RythmMLParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MusicListener extends RythmMLBaseListener {
    private Partition partition = null;
    private Bar currentBar = null;
    private Beat currentBeat = null;
    private String currentSection = null;
    private DrumsElements currentMusicNote = null;
    public boolean onBeat = true;
    private String currentBarOfSection = null;
    private List<Section> sections = new ArrayList<>();
    private List<Bar> barsLibrary = new ArrayList<>();
    private int beatPerBar = 0;
    private Note currentNote = null;

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
    public void exitPartition(RythmMLParser.PartitionContext ctx) {
        partition.getComposition().setSections(sections);
    }

    @Override
    public void enterInit(RythmMLParser.InitContext ctx) {
        int bpm = Integer.parseInt(ctx.bpmNumber.getText());
        this.beatPerBar = Integer.parseInt(ctx.beatPerBar.getText());
        List<TerminalNode> compositions = ctx.IDENTIFIER();
        System.out.println(String.format("bpm %s", bpm));
        System.out.println(String.format("beatPerBar %s", beatPerBar));
        System.out.println("composition");
        for (TerminalNode composition : compositions) {
            Section section = new Section();
            section.setBeatPerMinutes(bpm);
            section.setName(composition.getSymbol().getText());
            sections.add(section);
            System.out.println(composition.getSymbol().getText());
        }
    }

    @Override
    public void enterBar(RythmMLParser.BarContext ctx) {
        currentBar = new Bar(this.beatPerBar);
        currentBar.setName(ctx.barName.getText());
        this.barsLibrary.add(currentBar);
        System.out.println(String.format("bar %s", ctx.barName.getText()));
    }

    @Override
    public void enterMusicNote(RythmMLParser.MusicNoteContext ctx) {
        DrumsElements instrument = DrumsElements.lookupByDisplayName(ctx.instrument.getText());
        if (instrument == null) {
            throw new IllegalArgumentException(String.format("The given instrument %s doesn't exist", ctx.instrument.getText()));
        }
        this.currentBeat = new Beat();
        System.out.println(String.format("instrument %s", instrument.displayName));
        if (ctx.note.getText().equals("beat")) {
            System.out.println(" on beat");
            onBeat = true;
        } else if (ctx.note.getText().equals("quarter")) {
            System.out.println(" on quarter");
            onBeat = false;
        }
        currentMusicNote = instrument;
        currentNote = new Note(currentMusicNote);
        this.currentBeat.addNote(currentNote);
    }

    @Override
    public void exitBar(RythmMLParser.BarContext ctx) {
        currentBar = null;
    }

    @Override
    public void enterNotes(RythmMLParser.NotesContext ctx) {
        List<Integer> nodes = getRealBeatPlacement(ctx.children);
        if (currentMusicNote != null) {
            for (Integer placement : nodes) {
                //on partition : placement is 1-8 in computer science : array starts at 0, so the real placement begins at 0 and not 1
                int realPlacement = placement - 1;
                //if it's onBeat it's simple, we add the note to the position
                if (onBeat) {
                    currentBar.addBeat(this.currentBeat, realPlacement);
                } else {
                    //it's on quarter so if the REAL placement is 0 2 4 6, it means that it's on a beat
                    if (realPlacement % 2 == 0) {
                        currentBar.addBeat(this.currentBeat, realPlacement / 2);
                    } else {
                        //it's a division, the placement is 1 3 5 7
                        Division division = new Division();
                        division.addNote(currentNote);
                        //we retrieve the beat to add a division to it
                        this.currentBar.getBeat(realPlacement / 2).addDivision(division, 0);
                    }
                }
            }

        }
        if (currentBarOfSection != null) {
            Bar emptyBar = new Bar(beatPerBar);
            emptyBar.setName("emptyBar");
            getSectionFromLibraryByName(currentSection).addBar(emptyBar, nodes.get(0) - 1);
            getSectionFromLibraryByName(currentSection).addBar(getBarFromLibraryByName(currentBarOfSection), nodes.size());
        }
    }

    @Override
    public void exitMusicNote(RythmMLParser.MusicNoteContext ctx) {
        this.currentMusicNote = null;
        this.currentNote = null;
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

    private Bar getBarFromLibraryByName(String name) {
        for (Bar bar : barsLibrary) {
            if (bar.getName().equals(name)) {
                return bar;
            }
        }
        throw new IllegalArgumentException("this bar " + name + " has not been defined before");
    }

    private Section getSectionFromLibraryByName(String name) {
        for (Section section : sections) {
            if (section.getName().equals(name)) {
                return section;
            }
        }
        throw new IllegalArgumentException("this section " + name + " has not been defined before");
    }


    /**
     * This can be refactored
     *
     * @param children list of parsed music notes, ex1:(6 - 13) ex2:(2 4 5 6)
     * @return the list with all the numbers of beat placement, ex1: (6 7 8 9 10 11 12 13) ex2:(2 4 5 6)
     */
    private List<Integer> getRealBeatPlacement(List<ParseTree> children) {
        List<Integer> integerList = new ArrayList<>();
        boolean concernsMultipleNumber = false;
        for (ParseTree parseTree : children) {
            if (parseTree.getText().equals("-")) {
                concernsMultipleNumber = true;
            } else {
                if (concernsMultipleNumber) {
                    for (int i = integerList.get(integerList.size() - 1) + 1; i <= Integer.parseInt(parseTree.getText()); i++) {
                        integerList.add(i);
                    }
                    concernsMultipleNumber = false;
                } else {
                    integerList.add(Integer.parseInt(parseTree.getText()));
                }
            }
        }
        Collections.sort(integerList);
        return integerList;
    }
}