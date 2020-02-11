package fr.unice.polytech.rythmML.kernel.track;

import fr.unice.polytech.rythmML.kernel.Visitor;
import fr.unice.polytech.rythmML.kernel.data.Instrument;
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
public class Track {
    @EqualsAndHashCode.Exclude
    private List<Note> notes = new ArrayList<>();

    @NonNull
    private Instrument instrument;

    public void accept(Visitor v) {
        v.visitTrack(this);
    }

    public Track addNote(Note note) {
        //We imported List automatically;
        this.notes.add(note);
        return this;
    }
}
