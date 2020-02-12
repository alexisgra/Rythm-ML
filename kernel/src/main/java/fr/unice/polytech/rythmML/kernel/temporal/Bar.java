package fr.unice.polytech.rythmML.kernel.temporal;

import fr.unice.polytech.rythmML.kernel.NamedElement;
import fr.unice.polytech.rythmML.kernel.VisitableElement;
import fr.unice.polytech.rythmML.kernel.Visitor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(
        chain = true
)
@RequiredArgsConstructor
public class Bar extends NamedElement implements VisitableElement {

    @EqualsAndHashCode.Exclude
    private Beat[] beats;

    public Bar (final int beatNumber){
        this.beats = new Beat[beatNumber];
    }

    public void addBeat(final Beat beat, final int beatNumber) {
        if(this.beats.length < beatNumber){
            this.beats[beatNumber] = beat;
        }
        throw new IllegalArgumentException("Too many beats in the bar " + this.name);
    }

    public void accept(Visitor v) {
        v.visitBar(this);
    }
}
