package fr.unice.polytech.rythmML.shell.component;

import fr.unice.polytech.rythmML.dsl.Runner;
import fr.unice.polytech.rythmML.kernel.Partition;
import fr.unice.polytech.rythmML.kernel.data.DrumsElements;
import fr.unice.polytech.rythmML.kernel.temporal.Composition;
import fr.unice.polytech.rythmML.kernel.temporal.Section;
import fr.unice.polytech.rythmML.shell.RythmUtils;
import fr.unice.polytech.rythmML.shell.SequencerPlayer;
import fr.unice.polytech.rythmML.shell.WorkspaceConfig;
import fr.unice.polytech.rythmML.shell.visualizer.OpenBrowser;
import org.antlr.v4.runtime.CharStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Commands to play midi file.
 */
@ShellComponent
public class Player {

	@Autowired
	private SequencerPlayer player;

	/**
	 * The play command will play the song described by the user.
	 */
	@ShellMethod(value = "Play the song described in the workfile.", key = "play")
	public String play() throws IOException, MidiUnavailableException, InvalidMidiDataException {
		if (WorkspaceConfig.WORKSPACE == null) {
			return "Please setup your workspace first.";
		}
		final Runner antlrRunner = new Runner();
		CharStream stream = antlrRunner.getCharStream(Paths.get(WorkspaceConfig.WORKSPACE));

		final Partition partition = antlrRunner.buildModel(stream);
		Sequence sequence = partition.generateMIDI();
		Sequencer sequencer = MidiSystem.getSequencer();
		sequencer.open();
		sequencer.setSequence(sequence);
		//sequencer.setTempoInBPM();
		sequencer.start();
		return "Playing the song.";
	}

	/**
	 * The play command will play the song described by the user.
	 */
	@ShellMethod(value = "Play the song described in the workfile.", key = "play section")
	public String play(String name) throws IOException, MidiUnavailableException, InvalidMidiDataException {
		if (WorkspaceConfig.WORKSPACE == null) {
			return "Please setup your workspace first.";
		}
		if(name.equals("")) {
			// Return a list of all sections
		}
		final Optional<Section> sectionOpt = RythmUtils.getSectionFromRythm(name);
		if (!sectionOpt.isPresent()) {
			return "Section doesn't exist in the workspace.";
		}
		final Section section = sectionOpt.get();
		final Partition subPartition = new Partition("sub");
		final Composition composition = new Composition();
		composition.addSection(section,1);
		subPartition.setComposition(composition);

		Sequence sequence = subPartition.generateMIDI();
		this.player.playSequence(sequence);

		return "Playing the section " + name;
	}

	/**
	 * The play command will play the song described by the user.
	 */
	@ShellMethod(value = "Play the song described in the workfile.", key = "stop")
	public String stop() throws IOException, MidiUnavailableException, InvalidMidiDataException {
		if (WorkspaceConfig.WORKSPACE == null) {
			return "Please setup your workspace first.";
		}
		this.player.stopSequence();
		return "Stopping the player";
	}

}
