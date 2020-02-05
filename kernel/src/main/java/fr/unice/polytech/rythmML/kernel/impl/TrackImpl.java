package fr.unice.polytech.rythmML.kernel.impl;

import fr.unice.polytech.rythmML.kernel.Note;
import fr.unice.polytech.rythmML.kernel.Track;
import fr.unice.polytech.rythmML.kernel.Visitor;
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
class TrackImpl implements Track {
    @EqualsAndHashCode.Exclude
    private List<Note> notes = new ArrayList<>();

    @NonNull
    private String name;

    public void accept(Visitor v) {
        v.visitTrack(this);
    }

    public Track addNote(Note note) {
        //We imported List automatically;
        this.notes.add(note);
        return this;
    }
}
