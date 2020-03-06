package fr.unice.polytech.rythmML.shell.component;

import fr.unice.polytech.rythmML.dsl.Runner;
import fr.unice.polytech.rythmML.kernel.Partition;
import fr.unice.polytech.rythmML.shell.WorkspaceConfig;
import fr.unice.polytech.rythmML.shell.visualizer.OpenBrowser;
import org.antlr.v4.runtime.CharStream;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


@ShellComponent
public class Export {

	/**
	 * The export command create a midi file of the given partition
	 *
	 * @return
	 */
	@ShellMethod(value = "Export the partition to midi file", key = "export")
	public String export() throws IOException {
		if (WorkspaceConfig.WORKSPACE == null) {
			return "Please setup your workspace first.";
		}
		final Runner antlrRunner = new Runner();
		CharStream stream = antlrRunner.getCharStream(Paths.get(WorkspaceConfig.WORKSPACE));

		final Partition partition = antlrRunner.buildModel(stream);
		Sequence sequence = partition.generateMIDI().getSequence();
		File f = new File( WorkspaceConfig.DIRECTORY + "/export.mid");
		MidiSystem.write(sequence,1,f);
		return "Exporting partition to midi file...";
	}
}
