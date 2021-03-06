package fr.unice.polytech.rythmML.kernel; /**
 * midifile.java
 * <p>
 * A very short program which builds and writes
 * a one-note Midi file.
 * <p>
 * author  Karl Brown
 * last updated 2/24/2003
 */

import fr.unice.polytech.rythmML.kernel.data.DrumsElements;
import fr.unice.polytech.rythmML.kernel.temporal.*;
import fr.unice.polytech.rythmML.kernel.track.Note;
import fr.unice.polytech.rythmML.kernel.visitor.MidiVisitor;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import java.util.Collections;

public class BillieJean {
    public static void main(String[] argv) {
        Partition partition = new Partition("BillieJean");
        Composition composition = new Composition();
        Section section = new Section();
        Bar bar1 = new Bar(4);
        Beat beat1 = new Beat();
        Beat beat2 = new Beat();
        Note note = new Note(DrumsElements.BASS_DRUM);
        Note note2 = new Note(DrumsElements.CLOSED_HITHAT);
        Note note3 = new Note(DrumsElements.SNARE_DRUM);
        Note note4 = new Note(DrumsElements.OPENED_HITHAT);

        section.setBeatPerMinutes(118);
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

        section.addBar(bar1, 5);
        Bar bar2 = new Bar(bar1);
        bar2.getBeat(3).getDivisions().get(0).setNotes(Collections.singletonList(note4));
        section.addBar(bar2, 5);
        composition.addSection(section, 1);

        partition.setComposition(composition);

        new MidiVisitor().visitPartition(partition);
		/*System.out.println("midifile begin ");
		try
		{
			int tempo = 200;
			int nbBar = 2;
			int nbBeatPerBar = 4;
			int resolution = 200; // in slices per beat
			Sequence s = new Sequence(Sequence.PPQ, resolution);
			Track track = s.createTrack();
// set kick drum
			for (int bar = 0; bar < nbBar; bar++) {
				for (int beat = 0; beat < nbBeatPerBar; beat += 1) {
					int pos = toTick(bar, beat, 0, nbBeatPerBar, resolution);
					addDrumHit(track, DrumElement.HandClap, pos, 100);
				}
			}

			for (int bar = 0; bar < nbBar; bar++) {
				for (int beat = 0; beat < nbBeatPerBar; beat += 2) {
					int pos = toTick(bar, beat, 0, nbBeatPerBar, resolution);
					addDrumHit(track, DrumElement.BassDrum1, pos, 100);
				}
			}
//****  write the MIDI sequence to a MIDI file  ****
			File f = new File("midifile.mid");
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.setSequence(s);
			sequencer.start();
			MidiSystem.write(s,1,f);
			//Sequence sequence = MidiSystem.getSequence(new File("/Users/alexissegura/Documents/SI5/DSL/Rythml-ML/Rythm-ML/Test_-_test1.mid"));
			//File outputfile = new File("image.jpg");
			//ImageIO.write(MainGraphic.createImageFromMidiFile(sequence), "jpg", outputfile);
		} //try
		catch(Exception e)
		{
			System.out.println("Exception caught " + e.toString());
		} //catch
		System.out.println("midifile end ");*/
    } //main

    public static void addDrumHit(Track track, DrumElement de, long tick, int velocity) {
        final int NOTEON = 144;
        final int NOTEOFF = 128;
        createEvent(track, NOTEON, 9, de, tick, velocity);
        createEvent(track, NOTEOFF, 9, de, tick + 1, velocity);
    }

    private static void createEvent(Track track, int type, int chan, DrumElement de, long tick, int velocity) {
        ShortMessage message = new ShortMessage();
        try {
            message.setMessage(type, chan, de.noteNumber, velocity);
            MidiEvent event = new MidiEvent(message, tick);
            track.add(event);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int toTick(int bar, int beat, double division, int nbBeatPerBar, int resolution) {
        int pos = bar * nbBeatPerBar * resolution;
        pos += beat * resolution;
        pos += division * resolution;
        return pos;
    }

    public enum DrumElement {
        AcousticBassDrum(35), BassDrum1(36), SideStick(37), AcousticSnare(38), HandClap(39), ElectricSnare(40),
        LowFloorTom(41), ClosedHiHat(42), OpenHiHat(46);

        public int noteNumber;

        private DrumElement(int noteNumber) {
            this.noteNumber = noteNumber;
        }
    }
} //midif