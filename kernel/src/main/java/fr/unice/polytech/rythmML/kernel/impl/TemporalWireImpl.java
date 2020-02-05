package fr.unice.polytech.rythmML.kernel.impl;

import fr.unice.polytech.rythmML.kernel.Section;
import fr.unice.polytech.rythmML.kernel.TemporalWire;
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
class TemporalWireImpl implements TemporalWire {
    @EqualsAndHashCode.Exclude
    private List<Section> sections = new ArrayList<>();

    public void accept(Visitor v) {
        v.visitTemporalWire(this);
    }

    public TemporalWire addSection(Section section) {
        //We imported List automatically;
        this.sections.add(section);
        return this;
    }
}
