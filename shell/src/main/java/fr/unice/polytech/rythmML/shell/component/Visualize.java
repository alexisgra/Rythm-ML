package fr.unice.polytech.rythmML.shell.component;

import fr.unice.polytech.rythmML.shell.visualizer.OpenBrowser;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

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
	@ShellMethod("Displays visual version of the generated midi file.")
	public void visualize() {
		OpenBrowser.openBrowser();
	}

}
