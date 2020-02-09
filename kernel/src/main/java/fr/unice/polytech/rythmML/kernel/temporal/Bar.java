package fr.unice.polytech.rythmML.kernel.temporal;

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
public class Bar implements VisitableElement {

    @EqualsAndHashCode.Exclude
    private List<Beat> beats = new ArrayList<>();


    public Bar addBeat(Beat beat) {
        //We imported List automatically;
        this.beats.add(beat);
        return this;
    }

    public void accept(Visitor v) {
        v.visitBar(this);
    }
}
