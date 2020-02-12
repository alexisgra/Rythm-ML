package fr.unice.polytech.rythmML.kernel.temporal;

import fr.unice.polytech.rythmML.kernel.Visitor;
import fr.unice.polytech.rythmML.kernel.track.Note;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(
        chain = true
)
@RequiredArgsConstructor
public class Beat {

    @EqualsAndHashCode.Exclude
    private Tick[] ticks = new Tick[8];

    @EqualsAndHashCode.Exclude
    private List<Note> notes = new ArrayList<>();

    public void addTick(final Tick tick, final int tickNumber) {
        if(this.ticks.length < tickNumber){
            this.ticks[tickNumber] = tick;
        }
        throw new IllegalArgumentException("Too many ticks in the beat.");
    }

    public void addNote(final Note note) {
        this.notes.add(note);
    }

    public void accept(Visitor v) {
        v.visitBeat(this);
    }
}
