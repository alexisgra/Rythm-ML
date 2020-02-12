package fr.unice.polytech.rythmML.kernel.temporal;

import fr.unice.polytech.rythmML.kernel.track.Note;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

public class Tick {

	@EqualsAndHashCode.Exclude
	private List<Note> notes = new ArrayList<>();

	public void addNote(final Note note) {
		this.notes.add(note);
	}
}
