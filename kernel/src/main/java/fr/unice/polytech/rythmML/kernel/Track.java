package fr.unice.polytech.rythmML.kernel;

import java.util.List;

public interface Track extends VisitableElement {
    Track addNote(Note note);

    List<Note> getNotes();

    Track setNotes(List<Note> notes);

    String getName();

    Track setName(String name);
}
