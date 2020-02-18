package fr.unice.polytech.rythmML.kernel.temporal;

import fr.unice.polytech.rythmML.kernel.Visitor;
import fr.unice.polytech.rythmML.kernel.track.Note;
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
public class Beat {

    @EqualsAndHashCode.Exclude
    private Division[] divisions = new Division[8];

    @EqualsAndHashCode.Exclude
    private List<Note> notes = new ArrayList<>();

    public void addDivision(final Division division, final int tickNumber) {
        if (tickNumber < 0 || tickNumber >= this.divisions.length) {
            throw new IllegalArgumentException("Too many ticks in the beat.");
        }
        this.divisions[tickNumber] = division;
    }

    public void addNote(final Note note) {
        this.notes.add(note);
    }

    public void addNotes(final List<Note> notes) {
        this.notes.addAll(notes);
    }
    public void accept(Visitor v) {
        v.visitBeat(this);
    }
}
