package fr.unice.polytech.rythmML.kernel.impl;

import fr.unice.polytech.rythmML.kernel.Bar;
import fr.unice.polytech.rythmML.kernel.Section;
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
class SectionImpl implements Section {
    @EqualsAndHashCode.Exclude
    private List<Bar> bar = new ArrayList<>();

    public void accept(Visitor v) {
        v.visitSection(this);
    }

    public Section addBa(Bar ba) {
        //We imported List automatically;
        this.bar.add(ba);
        return this;
    }
}
