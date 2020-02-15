package fr.unice.polytech.rythmML.kernel;

import fr.unice.polytech.rythmML.kernel.temporal.*;
import fr.unice.polytech.rythmML.kernel.track.Note;

public interface Visitor {
    void visitPartition(Partition partition);

    void visitComposition(Composition composition);

    void visitBar(Bar bar);

    void visitNote(Note note);

    void visitDivision(Division division);

    void visitBeat(Beat beat);

    void visitSection(Section section);

}
