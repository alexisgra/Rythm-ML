package fr.unice.polytech.rythmML.dsl.visitor;

import fr.unice.polytech.rythmML.kernel.Partition;
import fr.unice.polytech.rythmML.kernel.temporal.Bar;
import fr.unice.polytech.rythmML.kernel.temporal.Beat;
import fr.unice.polytech.rythmML.kernel.temporal.Section;
import fr.unice.polytech.rythmML.kernel.temporal.TemporalGrid;
import fr.unice.polytech.rythmML.kernel.track.Note;
import fr.unice.polytech.rythmML.kernel.track.Placement;
import fr.unice.polytech.rythmML.kernel.track.Track;
import grammar.RythmMLBaseListener;
import grammar.RythmMLParser;

public class MusicListener extends RythmMLBaseListener {
    private Partition partition = null;
    private Track currentTrack = null;
    private TemporalGrid temporalGrid = new TemporalGrid();
    public Partition retrieve() {
        return this.partition;
    }

    @Override
    public void enterPartition(RythmMLParser.PartitionContext ctx) {
        System.out.println("-------LISTENER--------");
        System.out.println("partition " + ctx.name.getText());
        partition = new Partition(ctx.name.getText());
    }

    @Override
    public void enterInit(RythmMLParser.InitContext ctx) {
        Section currentSection;
        Bar currentBar;
        for (int i = 0; i < Integer.parseInt(ctx.sectionNumber.getText()); i++) {
            currentSection = new Section();
            temporalGrid.addSection(currentSection);
            for (int j = 0; j < Integer.parseInt(ctx.barNumber.getText()); j++) {
                currentBar = new Bar();
                currentSection.addBar(currentBar);
                for (int k = 0; k < Integer.parseInt(ctx.bpmNumber.getText()); k++) {
                    currentBar.addBeat(new Beat(Integer.toString(k)));
                }
            }
        }
        System.out.println("bpm " + ctx.bpmNumber.getText());
        System.out.println("section " + ctx.sectionNumber.getText());
        System.out.println("bar " + ctx.barNumber.getText());
        partition.addTemporalWir(temporalGrid);
    }

    @Override
    public void enterTracks(RythmMLParser.TracksContext ctx) {
        System.out.println("track " + ctx.trackNumber.getText());
        currentTrack = new Track(ctx.trackNumber.getText());
    }

    @Override
    public void exitTracks(RythmMLParser.TracksContext ctx) {
        this.partition.addTrack(currentTrack);
        currentTrack = null;
    }

    @Override
    public void enterMusicNote(RythmMLParser.MusicNoteContext ctx) {
        System.out.println(ctx.instrument.getText() + " on " + ctx.sectionPosition.getText() + ":" + ctx.barPosition.getText() + ":" + ctx.beatPosition.getText());
        Note note = new Note();
        Placement placement = new Placement();
        placement.setSection(ctx.sectionPosition.getText()).setBar(ctx.barPosition.getText()).setBeat(ctx.beatPosition.getText());
        note.setPlacement(placement);
        currentTrack.addNote(note);
    }
}