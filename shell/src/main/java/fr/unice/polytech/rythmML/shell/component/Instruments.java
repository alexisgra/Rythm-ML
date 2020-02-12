package fr.unice.polytech.rythmML.shell.component;

<<<<<<< Updated upstream
=======
import fr.unice.polytech.rythmML.kernel.data.DrumsElements;
import fr.unice.polytech.rythmML.shell.visualizer.OpenBrowser;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
		for (final Instrument instru : Instrument.values()) {
			buffer.append(instru.displayName);
=======
		for (final DrumsElements instru : DrumsElements.values()) {
			buffer.append(instru);
>>>>>>> Stashed changes
			buffer.append(" - ");
			buffer.append(instru.description);
			buffer.append(System.lineSeparator());
		}
		OpenBrowser.openBrowser();
		return buffer.toString();
	}

}
