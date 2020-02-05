package fr.unice.polytech.rythmML.kernel.impl;

import fr.unice.polytech.rythmML.kernel.Placement;
import fr.unice.polytech.rythmML.kernel.Visitor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(
        chain = true
)
@RequiredArgsConstructor
class PlacementImpl implements Placement {
    @EqualsAndHashCode.Exclude
    private String beat;

    @EqualsAndHashCode.Exclude
    private String bar;

    @EqualsAndHashCode.Exclude
    private String section;

    public void accept(Visitor v) {
        v.visitPlacement(this);
    }
}
