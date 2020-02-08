package fr.unice.polytech.rythmML.kernel.impl;

import fr.unice.polytech.rythmML.kernel.Note;
import fr.unice.polytech.rythmML.kernel.VisitableElement;

import java.util.List;

public interface Track extends VisitableElement {
    Track addNote(Note note);

    List<Note> getNotes();

    Track setNotes(List<Note> notes);

    String getName();

    Track setName(String name);
}
