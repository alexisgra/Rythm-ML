package fr.unice.polytech.rythmML.dsl.visitor;

import fr.unice.polytech.rythmML.dsl.DivisionEnum;
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
    private boolean waitingForDivision = false;
    private DrumsElements currentMusicNote = null;
    private String currentBarOfSection = null;
    private List<Section> sections = new ArrayList<>();
    private List<Bar> barsLibrary = new ArrayList<>();
    private int beatPerBar = 0;
    private Note currentNote = null;
    private List<Beat> beatsWaitingForDivision = new ArrayList<>();
    private DivisionEnum divisionEnum = null;

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
        waitingForDivision = ctx.placement.getText().equals("in");
        DrumsElements instrument = DrumsElements.lookupByDisplayName(ctx.instrument.getText());
        if (instrument == null) {
            throw new IllegalArgumentException(String.format("The given instrument %s doesn't exist", ctx.instrument.getText()));
        }
        System.out.println(String.format("instrument %s", instrument.displayName));
        currentMusicNote = instrument;
        currentNote = new Note(currentMusicNote);
    }

    @Override
    public void enterMusicNoteWithDivision(RythmMLParser.MusicNoteWithDivisionContext ctx) {
        System.out.println(ctx.divisionInit().division.getText());
    }

    @Override
    public void enterDivisionInit(RythmMLParser.DivisionInitContext ctx) {
        divisionEnum = DivisionEnum.lookupByDisplayName(ctx.division.getText());
    }

    @Override
    public void exitMusicNoteWithDivision(RythmMLParser.MusicNoteWithDivisionContext ctx) {
        beatsWaitingForDivision = new ArrayList<>();
        divisionEnum = null;
        waitingForDivision = false;
    }

    @Override
    public void exitBar(RythmMLParser.BarContext ctx) {
        currentBar = null;
    }

    @Override
    public void enterNotes(RythmMLParser.NotesContext ctx) {
        List<Division> divisions = new ArrayList<>();
        List<Integer> nodes = getRealBeatPlacement(ctx.children);
        if (waitingForDivision && !beatsWaitingForDivision.isEmpty()) {
            divisions = fillDivision();
        }
        List<Beat> temp = new ArrayList<>();
        if (currentMusicNote != null) {
            for (Integer placement : nodes) {
                int realPlacement = placement - 1;
                this.currentBeat = new Beat();
                this.currentBeat.addNote(currentNote);
                //on partition : placement is 1-8 in computer science : array starts at 0, so the real placement begins at 0 and not 1
                if (waitingForDivision && beatsWaitingForDivision.isEmpty()) {
                    temp.add(currentBeat);
                } else if (waitingForDivision) {
                    for (Beat beat : beatsWaitingForDivision) {
                        if (realPlacement == 0) {
                            currentBar.addBeat(beat, realPlacement);
                        } else {
                            divisions.get(realPlacement - 1).addNote(currentNote);
                            divisions.forEach(beat::addDivision);
                        }
                    }
                } else {
                    currentBar.addBeat(this.currentBeat, realPlacement);
                }
            }
            beatsWaitingForDivision.addAll(temp);
        }
        if (currentBarOfSection != null) {
            Bar emptyBar = new Bar(beatPerBar);
            emptyBar.setName("emptyBar");
            getSectionFromLibraryByName(currentSection).addBar(emptyBar, nodes.get(0) - 1);
            getSectionFromLibraryByName(currentSection).addBar(getBarFromLibraryByName(currentBarOfSection), nodes.size());
        }
    }

    private List<Division> fillDivision() {
        List<Division> divisions = new ArrayList<>();
        int length;
        switch (divisionEnum) {
            case HALF:
                length = 1;
                break;
            case TIERS:
                length = 2;
                break;
            case QUARTER:
                length = 3;
                break;
            case EIGHT:
                length = 7;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + divisionEnum);
        }
        for (int i = 0; i < length; i++) {
            divisions.add(new Division());
        }
        return divisions;
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