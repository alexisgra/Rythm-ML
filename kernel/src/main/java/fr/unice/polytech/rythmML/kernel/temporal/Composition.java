package fr.unice.polytech.rythmML.kernel.temporal;

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
public class Composition {
    @EqualsAndHashCode.Exclude
    private List<Section> sections = new ArrayList<>();

    public void accept(Visitor v) {
        v.visitTemporalWire(this);
    }

    public void addSection(final Section section, final int repeat) {
        for (int i = 0; i<repeat; i++){
            this.sections.add(section);
        }
    }
}
