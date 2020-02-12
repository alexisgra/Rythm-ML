package fr.unice.polytech.rythmML.kernel.temporal;

import fr.unice.polytech.rythmML.kernel.NamedElement;
import fr.unice.polytech.rythmML.kernel.VisitableElement;
import fr.unice.polytech.rythmML.kernel.Visitor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(
        chain = true
)
public class Bar extends NamedElement implements VisitableElement {

    @EqualsAndHashCode.Exclude
    private Beat[] beats;

    public Bar (final int beatNumber){
        this.beats = new Beat[beatNumber];
    }

    public void addBeat(final Beat beat, final int beatNumber) {
        if(this.beats.length > beatNumber){
            Beat retrievedBeat = this.beats[beatNumber];
            if (retrievedBeat != null) {
                retrievedBeat.addNotes(beat.getNotes());
            } else this.beats[beatNumber] = beat;
            return;
        }
        throw new IllegalArgumentException("Too many beats in the bar " + this.name);
    }

    public void accept(Visitor v) {
        v.visitBar(this);
    }
}
