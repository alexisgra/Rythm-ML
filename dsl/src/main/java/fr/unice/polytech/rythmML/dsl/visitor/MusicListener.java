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

import java.util.*;

public class MusicListener extends RythmMLBaseListener {
    private Partition partition = null;
    private Bar currentBar = null;
    private Beat currentBeat = null;
    private String currentSection = null;
    private boolean waitingForDivision = false;
    private String currentBarOfSection = null;
    private List<Section> sectionsLibrary = new ArrayList<>();
    private List<Bar> barsLibrary = new ArrayList<>();
    private int beatPerBar = 0;
    private DrumsElements currentInstrument = null;
    private Map<Integer, Beat> beatsWaitingForDivisionPosition = new HashMap<>();
    private DivisionEnum divisionEnum = null;
    private boolean replaceMode = false;
    private List<Integer> notesPosition = new ArrayList<>();

    public Partition retrieve() {
        for(final Section section : sectionsLibrary) {
            this.partition.getSectionLibrary().addSection(section);
        }
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
        partition.getComposition().setSections(sectionsLibrary);
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
            sectionsLibrary.add(section);
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
        currentInstrument = instrument;
    }

    @Override
    public void enterDivisionInit(RythmMLParser.DivisionInitContext ctx) {
        System.out.println(ctx.division.getText());
        divisionEnum = DivisionEnum.lookupByDisplayName(ctx.division.getText());
        for (Integer placement : notesPosition) {
            //on partition : placement is 1-8 in computer science : array starts at 0, so the real placement begins at 0 and not 1
            int realPlacement = placement - 1;
            Beat beat = new Beat();
            beat.addNote(new Note(currentInstrument));
            beatsWaitingForDivisionPosition.put(realPlacement, beat);
        }
        notesPosition = new ArrayList<>();
    }

    @Override
    public void exitDivisionInit(RythmMLParser.DivisionInitContext ctx) {
        List<Division> divisions = fillDivision();
        for (Integer placement : notesPosition) {
            int realPlacement = placement - 1;
            for (Map.Entry<Integer, Beat> entry : beatsWaitingForDivisionPosition.entrySet()) {
                Beat beat = entry.getValue();
                Integer position = entry.getKey();
                if (realPlacement == 0) {
                    currentBar.addBeat(beat, position);
                } else {
                    divisions.get(realPlacement - 1).addNote(new Note(currentInstrument));
                    //divisions.forEach(beat::addDivision);
                    currentBar.getBeat(position).setDivisions(divisions);
                }
            }
        }
        notesPosition = new ArrayList<>();
    }

    @Override
    public void exitMusicNoteWithDivision(RythmMLParser.MusicNoteWithDivisionContext ctx) {
        beatsWaitingForDivisionPosition = new HashMap<>();
        divisionEnum = null;
        waitingForDivision = false;
    }

    @Override
    public void exitMusicNote(RythmMLParser.MusicNoteContext ctx) {
        if (currentBar != null && !waitingForDivision) {
            for (Integer placement : notesPosition) {
                //on partition : placement is 1-8 in computer science : array starts at 0, so the real placement begins at 0 and not 1
                int realPlacement = placement - 1;
                this.currentBeat = new Beat();
                this.currentBeat.addNote(new Note(currentInstrument));
                currentBar.addBeat(this.currentBeat, realPlacement);
            }
            notesPosition = new ArrayList<>();
        }
    }

    @Override
    public void exitBar(RythmMLParser.BarContext ctx) {
        currentBar = null;
    }

    @Override
    public void enterNotes(RythmMLParser.NotesContext ctx) {
        boolean concernsMultipleNumber = false;
        for (ParseTree parseTree : ctx.children) {
            if (parseTree.getText().equals("-")) {
                concernsMultipleNumber = true;
            } else {
                if (concernsMultipleNumber) {
                    for (int i = notesPosition.get(notesPosition.size() - 1) + 1; i <= Integer.parseInt(parseTree.getText()); i++) {
                        notesPosition.add(i);
                    }
                    concernsMultipleNumber = false;
                } else {
                    notesPosition.add(Integer.parseInt(parseTree.getText()));
                }
            }
        }
        Collections.sort(notesPosition);
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
        replaceMode = ctx.replace != null;
        String barName = ctx.barName.getText();
        currentBarOfSection = barName;
        System.out.println(String.format("barName %s", barName));
    }

    @Override
    public void exitBarOfSection(RythmMLParser.BarOfSectionContext ctx) {
        if (replaceMode) {
            for (Integer barIndex : notesPosition) {
                getSectionFromLibraryByName(currentSection).replaceBar(getBarFromLibraryByName(currentBarOfSection), barIndex);
            }
        } else {
            Bar emptyBar = new Bar(beatPerBar);
            emptyBar.setName("emptyBar");
            getSectionFromLibraryByName(currentSection).addBar(emptyBar, notesPosition.get(0) - 1);
            getSectionFromLibraryByName(currentSection).addBar(getBarFromLibraryByName(currentBarOfSection), notesPosition.size());
        }
        currentBarOfSection = null;
        notesPosition = new ArrayList<>();
        replaceMode = false;
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
        for (Section section : sectionsLibrary) {
            if (section.getName().equals(name)) {
                return section;
            }
        }
        throw new IllegalArgumentException("this section " + name + " has not been defined before");
    }


    @Override
    public void enterDelays(RythmMLParser.DelaysContext ctx) {
        Integer minInterval = null;
        Integer maxInterval = null;
        for (ParseTree parseTree : ctx.children) {
            if (!parseTree.getText().equals("..")) {
                if (minInterval == null) {
                    minInterval = Integer.parseInt(parseTree.getText());
                } else {
                    maxInterval = Integer.parseInt(parseTree.getText());
                }
            }
        }
        Integer finalMinInterval = minInterval;
        Integer finalMaxInterval = maxInterval;
        notesPosition.forEach(beatPos -> currentBar.getBeat(beatPos - 1).getNotes().forEach(note -> {
            note.setMinInterval(finalMinInterval);
            note.setMaxInterval(finalMaxInterval);
        }));
    }

    @Override
    public void enterVelocity(RythmMLParser.VelocityContext ctx) {
        notesPosition.forEach(beatPos -> currentBar.getBeat(beatPos - 1).getNotes().forEach(note -> note.setVelocity(Integer.parseInt(ctx.velocityNumber.getText()))));
    }

    @Override
    public void exitVariations(RythmMLParser.VariationsContext ctx) {
        notesPosition = new ArrayList<>();
    }
}