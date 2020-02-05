package fr.unice.polytech.rythmML.kernel.impl;

import fr.unice.polytech.rythmML.kernel.Bar;
import fr.unice.polytech.rythmML.kernel.Visitor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(
        chain = true
)
@RequiredArgsConstructor
class BarImpl implements Bar {
    public void accept(Visitor v) {
        v.visitBar(this);
    }
}
