package fr.unice.polytech.rythmML.kernel.temporal;

import fr.unice.polytech.rythmML.kernel.NamedElement;
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
public class Section extends NamedElement {
    @EqualsAndHashCode.Exclude
    private List<Bar> bars = new ArrayList<>();

    private int beatPerMinutes = 60;

    public void accept(Visitor v) {
        v.visitSection(this);
    }

    public void addBar(final Bar bar, final int repeat) {
        for (int i = 0; i < repeat; i++) {
            this.bars.add(bar);
        }
    }

    public void replaceBar(final Bar bar, final int index) {
        this.bars.set(index - 1, bar);
    }
}
