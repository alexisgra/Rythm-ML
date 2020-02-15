package fr.unice.polytech.rythmML.kernel.temporal;

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
public class Tick {

	@EqualsAndHashCode.Exclude
	private List<Note> notes = new ArrayList<>();

	public void addNote(final Note note) {
		this.notes.add(note);
	}
}
