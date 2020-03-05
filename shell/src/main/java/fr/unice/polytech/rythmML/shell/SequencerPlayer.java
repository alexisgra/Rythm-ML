package fr.unice.polytech.rythmML.shell;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.sound.midi.Sequencer;

@Service
public class SequencerPlayer {
	private Sequencer sequencer;

	@Async

	public void playSequence(final Sequencer sequencer) {
		this.sequencer = sequencer;
		this.sequencer.start();
	}

	public void stopSequence() {
		this.sequencer.stop();
	}
}
