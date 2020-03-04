package fr.unice.polytech.rythmML.kernel;

import fr.unice.polytech.rythmML.kernel.temporal.Composition;
import fr.unice.polytech.rythmML.kernel.visitor.MidiVisitor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.sound.midi.Sequence;

@Data
@Accessors(
        chain = true
)
@RequiredArgsConstructor
public class Partition {

    @EqualsAndHashCode.Exclude
    private Composition composition = new Composition();

    @NonNull
    private String name;

    private SectionLibrary sectionLibrary = new SectionLibrary();

    public Sequence generateMIDI() {
        MidiVisitor midiVisitor = new MidiVisitor();
        return midiVisitor.visitPartition(this);
    }

    public void accept(Visitor v) {
        v.visitPartition(this);
    }


}
