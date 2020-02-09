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
public class Note {
    @EqualsAndHashCode.Exclude
    private Placement placement;

    public void accept(Visitor v) {
        v.visitNote(this);
    }
}
