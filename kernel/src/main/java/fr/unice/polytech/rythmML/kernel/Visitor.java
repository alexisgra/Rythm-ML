package fr.unice.polytech.rythmML.kernel;

import fr.unice.polytech.rythmML.kernel.temporal.Bar;
import fr.unice.polytech.rythmML.kernel.temporal.Section;
import fr.unice.polytech.rythmML.kernel.temporal.TemporalGrid;
import fr.unice.polytech.rythmML.kernel.temporal.Beat;
import fr.unice.polytech.rythmML.kernel.track.Note;
import fr.unice.polytech.rythmML.kernel.track.Placement;
import fr.unice.polytech.rythmML.kernel.track.Track;

public interface Visitor {
    void visitPartition(Partition partition);

    void visitTemporalWire(TemporalGrid temporalwire);

    void visitBar(Bar bar);

    void visitNote(Note note);

    void visitPlacement(Placement placement);

    void visitBeat(Beat beat);

    void visitSection(Section section);

    void visitTrack(Track track);
}
