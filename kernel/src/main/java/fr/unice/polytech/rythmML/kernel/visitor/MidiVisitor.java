
package fr.unice.polytech.rythmML.kernel.visitor;

import fr.unice.polytech.rythmML.kernel.Partition;
import fr.unice.polytech.rythmML.kernel.Visitor;
import fr.unice.polytech.rythmML.kernel.temporal.Bar;
import fr.unice.polytech.rythmML.kernel.temporal.Beat;
import fr.unice.polytech.rythmML.kernel.temporal.Composition;
import fr.unice.polytech.rythmML.kernel.temporal.Section;
import fr.unice.polytech.rythmML.kernel.track.Note;

import javax.sound.midi.Sequence;

public class MidiVisitor implements Visitor {
    private Sequence sequence;

    @Override
    public void visitPartition(Partition partition) {
        System.out.println("-------VISITOR--------");
        System.out.println(partition.getName());
       /* for (Composition temporalGrid : partition.getTemporalWire()) {
            this.visitTemporalWire(temporalGrid);
        }*/
    }

    @Override
    public void visitTemporalWire(Composition temporalwire) {
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
    }

    @Override
    public void visitBeat(Beat beat) {
        //System.out.println(beat.getName());
    }

    @Override
    public void visitSection(Section section) {
        /*for (Bar bar : section.getBar()) {
            this.visitBar(bar);
        }*/
    }
}
