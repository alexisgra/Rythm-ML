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

    public Bar(final int beatNumber) {
        this.beats = new Beat[beatNumber];
    }

    public Bar(Bar bar) {
        this.beats = new Beat[bar.beats.length];
        for (int i = 0; i < this.beats.length; i++) {
            this.beats[i] = new Beat(bar.beats[i]);
        }
    }

    public void addBeat(final Beat beat, final int beatNumber) {
        if (beatNumber < 0 || beatNumber > this.beats.length) {
            throw new IllegalArgumentException("Too many beats in the bar " + this.name);
        }

        Beat retrievedBeat = this.beats[beatNumber];
        if (retrievedBeat != null) {
            retrievedBeat.addNotes(beat.getNotes());
        } else {
            this.beats[beatNumber] = beat;
        }
    }

    public Beat getBeat(final int beatNumber) {
        if (this.beats.length > beatNumber) {
            return this.beats[beatNumber] == null ? new Beat() : this.beats[beatNumber];
        }
        throw new IllegalArgumentException("Too many beats in the bar " + this.name);
    }

    public void accept(Visitor v) {
        v.visitBar(this);
    }
}
