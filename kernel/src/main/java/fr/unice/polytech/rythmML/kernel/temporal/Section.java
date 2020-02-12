package fr.unice.polytech.rythmML.kernel.temporal;

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
public class Section  {
    @EqualsAndHashCode.Exclude
    private List<Bar> bars = new ArrayList<>();

    public void accept(Visitor v) {
        v.visitSection(this);
    }

    public void addBar(final Bar bar, final int repeat) {
        for (int i = 0; i<repeat; i++){
            this.bars.add(bar);
        }
    }
}
