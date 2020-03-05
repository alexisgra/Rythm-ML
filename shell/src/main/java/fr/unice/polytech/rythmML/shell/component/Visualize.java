package fr.unice.polytech.rythmML.shell.component;

import fr.unice.polytech.rythmML.kernel.Partition;
import fr.unice.polytech.rythmML.kernel.temporal.Composition;
import fr.unice.polytech.rythmML.kernel.temporal.Section;
import fr.unice.polytech.rythmML.shell.RythmUtils;
import fr.unice.polytech.rythmML.shell.WorkspaceConfig;
import fr.unice.polytech.rythmML.shell.visualizer.OpenBrowser;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Commands that relies on rythm ml instruments.
 */
@ShellComponent
public class Visualize {

	/**
	 * The visualize command diplay graphic version of the midi file
	 *
	 * @return
	 */
	@ShellMethod(value = "Displays visual version of the generated midi file.", key = "visu sections")
	public String visualize(@ShellOption(defaultValue="") final String name) throws IOException {
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
		composition.addSection(section, 1);
		subPartition.setComposition(composition);

		Sequence sequence = subPartition.generateMIDI().getSequence();
		File f = new File(WorkspaceConfig.DIRECTORY + "/tmp.mid");
		MidiSystem.write(sequence, 1, f);

		System.out.println(section);
		OpenBrowser.openBrowser();
		return "";
	}

}
