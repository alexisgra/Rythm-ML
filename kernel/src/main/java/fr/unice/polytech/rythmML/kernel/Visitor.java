package fr.unice.polytech.rythmML.kernel;

import fr.unice.polytech.rythmML.kernel.impl.Track;

public interface Visitor {
    void visitPartition(Partition partition);

    void visitTemporalWire(TemporalWire temporalwire);

    void visitBar(Bar bar);

    void visitNote(Note note);

    void visitPlacement(Placement placement);

    void visitTime(Time time);

    void visitSection(Section section);

    void visitTrack(Track track);
}
