package fr.unice.polytech.rythmML.kernel;

import fr.unice.polytech.rythmML.kernel.data.DrumsElements;
import fr.unice.polytech.rythmML.kernel.temporal.*;
import fr.unice.polytech.rythmML.kernel.track.Note;
import fr.unice.polytech.rythmML.kernel.visitor.MidiVisitor;

public class JohnnyBGoode {
    public static void main(String[] args) {

        Partition partition = new Partition("JohnnyBGoode");
        Composition composition = new Composition();
        Section section = new Section();
        Bar bar1 = new Bar(4);
        Beat beat1 = new Beat();
        Beat beat2 = new Beat();
        Note note = new Note(DrumsElements.BASS_DRUM);
        Note note2 = new Note(DrumsElements.RIDE_CYMBAL);
        Note note3 = new Note(DrumsElements.SNARE_DRUM);
        section.setBeatPerMinutes(170);
        Division division1 = new Division();
        division1.addNote(note2);

        beat1.addNote(note);
        beat1.addNote(note2);
        beat1.addDivision(division1);

        beat2.addNote(note2);
        beat2.addNote(note3);
        beat2.addDivision(division1);

        bar1.addBeat(beat1, 0);
        bar1.addBeat(beat2, 1);
        bar1.addBeat(beat1, 2);
        bar1.addBeat(beat2, 3);
        section.addBar(bar1, 10);
        composition.addSection(section, 1);

        partition.setComposition(composition);

        new MidiVisitor().visitPartition(partition);
    }
}
