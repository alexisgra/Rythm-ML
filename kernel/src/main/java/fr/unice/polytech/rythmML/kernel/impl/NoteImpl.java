package fr.unice.polytech.rythmML.kernel.impl;

import fr.unice.polytech.rythmML.kernel.Note;
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
class NoteImpl implements Note {
    @EqualsAndHashCode.Exclude
    private Placement placement;

    public void accept(Visitor v) {
        v.visitNote(this);
    }
}
