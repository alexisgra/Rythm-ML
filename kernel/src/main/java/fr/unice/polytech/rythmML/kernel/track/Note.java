package fr.unice.polytech.rythmML.kernel.track;

import fr.unice.polytech.rythmML.kernel.Visitor;
import fr.unice.polytech.rythmML.kernel.data.DrumsElements;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(
        chain = true
)
@RequiredArgsConstructor
public class Note {

    @NonNull
    private DrumsElements drumElements;

    public void accept(Visitor v) {
        v.visitNote(this);
    }
}
