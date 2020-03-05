package fr.unice.polytech.rythmML.shell;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.sound.midi.*;

@Service
public class SequencerPlayer {
	private Sequencer sequencer;

	@Async
	public void playSequence(final Sequence sequence, final int BPM) throws MidiUnavailableException, InvalidMidiDataException {
		this.sequencer = MidiSystem.getSequencer();
		this.sequencer.open();
		this.sequencer.setSequence(sequence);
		this.sequencer.setTempoInBPM(BPM);
		this.sequencer.start();
	}

	public void stopSequence() {
		this.sequencer.stop();
	}
}
