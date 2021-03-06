package fr.unice.polytech.rythmML.shell.component;

import fr.unice.polytech.rythmML.kernel.data.DrumsElements;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * Commands that relies on rythm ml instruments.
 */
@ShellComponent
public class Instruments {

	/**
	 * The instruments command diplay all available instruments in Rythm ML.
	 *
	 * @return the list of instruments
	 */
	@ShellMethod("Displays all available instruments in Rythm-ML")
	public String instruments() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("Rythm ML language provide these instruments...");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append("Drum has the following MIDI elements : ");
		buffer.append(System.lineSeparator());
		for (final DrumsElements instru : DrumsElements.values()) {
			buffer.append("    " + instru.displayName);
			buffer.append(" - ");
			buffer.append(instru.description);
			buffer.append(System.lineSeparator());
		}
		return buffer.toString();
	}

}
