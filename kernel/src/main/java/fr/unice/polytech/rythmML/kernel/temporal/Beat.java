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
    private List<Division> divisions = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    private List<Note> notes = new ArrayList<>();

    public Beat(Beat beat) {
        beat.getDivisions().forEach(division -> divisions.add(new Division(division)));
        beat.getNotes().forEach(note -> notes.add(new Note(note)));
    }

    public void addDivision(final Division division) {
        this.divisions.add(division);
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
