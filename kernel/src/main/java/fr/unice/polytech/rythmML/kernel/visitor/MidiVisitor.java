package fr.unice.polytech.rythmML.kernel.visitor;

import fr.unice.polytech.rythmML.kernel.Partition;
import fr.unice.polytech.rythmML.kernel.Visitor;
import fr.unice.polytech.rythmML.kernel.temporal.Bar;
import fr.unice.polytech.rythmML.kernel.temporal.Beat;
import fr.unice.polytech.rythmML.kernel.temporal.Section;
import fr.unice.polytech.rythmML.kernel.temporal.TemporalGrid;
import fr.unice.polytech.rythmML.kernel.track.Note;
import fr.unice.polytech.rythmML.kernel.track.Placement;
import fr.unice.polytech.rythmML.kernel.track.Track;

public class MidiVisitor implements Visitor {
    @Override
    public void visitPartition(Partition partition) {
        System.out.println("-------VISITOR--------");
        System.out.println(partition.getName());
        for (Track track : partition.getTracks()) {
            this.visitTrack(track);
        }
        for (TemporalGrid temporalGrid : partition.getTemporalWire()) {
            this.visitTemporalWire(temporalGrid);
        }
    }

    @Override
    public void visitTemporalWire(TemporalGrid temporalwire) {
        for (Section section : temporalwire.getSections()) {
            this.visitSection(section);
        }
    }

    @Override
    public void visitBar(Bar bar) {
        for (Beat beat : bar.getBeats()) {
            this.visitBeat(beat);
        }
    }

    @Override
    public void visitNote(Note note) {
        visitPlacement(note.getPlacement());
    }

    @Override
    public void visitPlacement(Placement placement) {
        System.out.println(placement.getSection() + ":" + placement.getBar() + ":" + placement.getBeat());
    }

    @Override
    public void visitBeat(Beat beat) {
        System.out.println(beat.getName());
    }

    @Override
    public void visitSection(Section section) {
        for (Bar bar : section.getBar()) {
            this.visitBar(bar);
        }
    }

    @Override
    public void visitTrack(Track track) {
        for (Note note : track.getNotes()) {
            this.visitNote(note);
        }
    }
}