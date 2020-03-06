package fr.unice.polytech.rythmML.shell.component;

import fr.unice.polytech.rythmML.dsl.Runner;
import fr.unice.polytech.rythmML.kernel.Partition;
import fr.unice.polytech.rythmML.kernel.temporal.Composition;
import fr.unice.polytech.rythmML.kernel.temporal.Section;
import fr.unice.polytech.rythmML.shell.RythmUtils;
import fr.unice.polytech.rythmML.shell.WorkspaceConfig;
import fr.unice.polytech.rythmML.shell.visualizer.OpenBrowser;
import org.antlr.v4.runtime.CharStream;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Commands that relies on rythm ml instruments.
 */
@ShellComponent
public class Visualize {

	/**
	 * The visualize command display graphic version of a section of the midi file
	 *
	 * @return
	 */
	@ShellMethod(value = "argument : SECTION_NAME - Displays visual version of a section of the generated midi file.", key = "visu section")
	public String visualizeSection(@ShellOption(help = "name") final String name) throws IOException {
		if (WorkspaceConfig.WORKSPACE == null) {
			return "Please setup your workfile first.";
		}
		if(name.equals("")) {
			// Return a list of all sections
		}
		final Optional<Section> sectionOpt = RythmUtils.getSectionFromRythm(name);
		if (!sectionOpt.isPresent()) {
			return "Section doesn't exist in the workfile.";
		}
		final Section section = sectionOpt.get();
		final Partition subPartition = new Partition("sub");
		final Composition composition = new Composition();
		composition.addSection(section, 1);
		subPartition.setComposition(composition);
		Sequence sequence = subPartition.generateMIDI().getSequence();
		File f = new File(WorkspaceConfig.DIRECTORY + "/tmp/tmp.mid");
		MidiSystem.write(sequence, 1, f);

		//System.out.println(section);
		OpenBrowser.openBrowser();
		return "Opening browser...";
	}

	/**
	 * The visualize command diplay graphic version of the midi file
	 *
	 * @return
	 */
	@ShellMethod(value = "Displays visual version of the generated midi file.", key = "visu")
	public String visualize() throws IOException {
		if (WorkspaceConfig.WORKSPACE == null) {
			return "Please setup your workfile first.";
		}
		final Runner antlrRunner = new Runner();
		CharStream stream = antlrRunner.getCharStream(Paths.get(WorkspaceConfig.WORKSPACE));

		final Partition partition = antlrRunner.buildModel(stream);
		Sequence sequence = partition.generateMIDI().getSequence();
		File f = new File( WorkspaceConfig.DIRECTORY + "/tmp/tmp.mid");
		MidiSystem.write(sequence,1,f);
		OpenBrowser.openBrowser();
		return "Opening browser...";
	}

}
