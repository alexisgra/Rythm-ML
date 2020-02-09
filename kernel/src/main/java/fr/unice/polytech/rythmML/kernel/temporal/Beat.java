package fr.unice.polytech.rythmML.kernel.temporal;

import fr.unice.polytech.rythmML.kernel.Visitor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(
        chain = true
)
@RequiredArgsConstructor
public class Beat {
    @NonNull
    private String name;

    public void accept(Visitor v) {
        v.visitBeat(this);
    }
}
