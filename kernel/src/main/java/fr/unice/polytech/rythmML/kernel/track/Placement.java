package fr.unice.polytech.rythmML.kernel.track;

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
public class Placement {
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
