package fr.unice.polytech.rythmML.kernel.temporal;

import fr.unice.polytech.rythmML.kernel.VisitableElement;
import fr.unice.polytech.rythmML.kernel.Visitor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(
        chain = true
)
@RequiredArgsConstructor
public class Bar implements VisitableElement {
    public void accept(Visitor v) {
        v.visitBar(this);
    }
}
