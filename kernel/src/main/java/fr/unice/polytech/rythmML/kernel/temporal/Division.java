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
public class Division {

	@EqualsAndHashCode.Exclude
	private List<Note> notes = new ArrayList<>();

	@EqualsAndHashCode.Exclude
	private List<Division> divisions = new ArrayList<>();

	public Division(Division division) {
		division.getNotes().forEach(note -> notes.add(new Note(note)));
		division.getDivisions().forEach(division1 -> divisions.add(new Division(division1)));
	}

	public void addNote(final Note note) {
		if (!notes.contains(note))
			this.notes.add(note);
	}
}
