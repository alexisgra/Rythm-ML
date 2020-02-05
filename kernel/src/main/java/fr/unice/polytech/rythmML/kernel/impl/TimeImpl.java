package fr.unice.polytech.rythmML.kernel.impl;

import fr.unice.polytech.rythmML.kernel.Time;
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
class TimeImpl implements Time {
    @NonNull
    private String name;

    public void accept(Visitor v) {
        v.visitTime(this);
    }
}
