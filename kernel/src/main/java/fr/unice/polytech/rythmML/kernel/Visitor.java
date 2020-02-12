package fr.unice.polytech.rythmML.kernel;

import fr.unice.polytech.rythmML.kernel.temporal.Bar;
import fr.unice.polytech.rythmML.kernel.temporal.Beat;
import fr.unice.polytech.rythmML.kernel.temporal.Composition;
import fr.unice.polytech.rythmML.kernel.temporal.Section;
import fr.unice.polytech.rythmML.kernel.track.Note;

public interface Visitor {
    void visitPartition(Partition partition);

    void visitTemporalWire(Composition temporalwire);

    void visitBar(Bar bar);

    void visitNote(Note note);

    void visitBeat(Beat beat);

    void visitSection(Section section);
}
