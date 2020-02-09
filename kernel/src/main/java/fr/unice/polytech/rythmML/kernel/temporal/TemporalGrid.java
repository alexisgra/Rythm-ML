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
public class TemporalGrid {
    @EqualsAndHashCode.Exclude
    private List<Section> sections = new ArrayList<>();

    public void accept(Visitor v) {
        v.visitTemporalWire(this);
    }

    public TemporalGrid addSection(Section section) {
        //We imported List automatically;
        this.sections.add(section);
        return this;
    }
}
