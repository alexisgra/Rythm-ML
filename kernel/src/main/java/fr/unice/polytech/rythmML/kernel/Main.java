package fr.unice.polytech.rythmML.kernel; /**
 * midifile.java
 *
 * A very short program which builds and writes
 * a one-note Midi file.
 *
 * author  Karl Brown
 * last updated 2/24/2003
 */

import fr.unice.polytech.rythmML.kernel.graphic.MainGraphic;

import java.io.*;
import javax.imageio.ImageIO;
import javax.sound.midi.*; // package for all midi classes
public class Main
{
	public static void main(String argv[]) {
		System.out.println("midifile begin ");
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
			/*Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.setSequence(s);
			sequencer.start();*/
			MidiSystem.write(s,1,f);
			Sequence sequence = MidiSystem.getSequence(new File("/Users/alexissegura/Documents/SI5/DSL/Rythml-ML/Rythm-ML/Test_-_test1.mid"));
			File outputfile = new File("image.jpg");
			ImageIO.write(MainGraphic.createImageFromMidiFile(sequence), "jpg", outputfile);
		} //try
		catch(Exception e)
		{
			System.out.println("Exception caught " + e.toString());
		} //catch
		System.out.println("midifile end ");
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