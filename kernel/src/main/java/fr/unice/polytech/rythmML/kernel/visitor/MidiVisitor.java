
package fr.unice.polytech.rythmML.kernel.visitor;

import fr.unice.polytech.rythmML.kernel.Partition;
import fr.unice.polytech.rythmML.kernel.Visitor;
import fr.unice.polytech.rythmML.kernel.temporal.Bar;
import fr.unice.polytech.rythmML.kernel.temporal.Beat;
import fr.unice.polytech.rythmML.kernel.temporal.Composition;
import fr.unice.polytech.rythmML.kernel.temporal.Section;
import fr.unice.polytech.rythmML.kernel.track.Note;
import fr.unice.polytech.rythmML.kernel.utils.TemporalUtils;

import javax.sound.midi.*;

public class MidiVisitor implements Visitor {
    private static final int DRUM_MIDI_CHANNEL = 9;
    private static final int VELOCITY = 100;
    private Sequence sequence;

    private int currentBar;
    private int currentBeat;

    private Track track;

    @Override
    public void visitPartition(Partition partition) {
        System.out.println("-------VISITOR--------");
        System.out.println(partition.getName());
        try {
            this.sequence = new Sequence(Sequence.PPQ, 120);
            this.track = sequence.createTrack();
            this.currentBar = 0;
            this.visitComposition(partition.getComposition());

            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(this.sequence);
            sequencer.start();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visitComposition(Composition composition) {
        for(final Section section : composition.getSections()) {
            this.visitSection(section);
        }
    }

    @Override
    public void visitBar(Bar bar) {
        for (final Beat beat : bar.getBeats()) {
            if(beat != null) {
                this.visitBeat(beat);
            }
            this.currentBeat += 1;
        }
    }

    @Override
    public void visitNote(Note note) {
        final int position = TemporalUtils.toTime(this.currentBar, this.currentBeat, 0, 4, 60);
        ShortMessage upMessage = new ShortMessage();
        ShortMessage downMessage = new ShortMessage();
        try {
            upMessage.setMessage(ShortMessage.NOTE_ON, DRUM_MIDI_CHANNEL, note.getDrumElements().midiNote, VELOCITY);
            MidiEvent up = new MidiEvent(upMessage, position);
            this.track.add(up);
            downMessage.setMessage(ShortMessage.NOTE_OFF, DRUM_MIDI_CHANNEL, note.getDrumElements().midiNote, VELOCITY);
            MidiEvent down = new MidiEvent(downMessage, (position + 1));
            this.track.add(down);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void visitBeat(Beat beat) {
        for(final Note note : beat.getNotes()) {
            this.visitNote(note);
        }
    }

    @Override
    public void visitSection(Section section) {
        for (Bar bar : section.getBars()) {
            this.visitBar(bar);
            this.currentBar += 1;
            this.currentBeat = 0;
        }
    }

}
