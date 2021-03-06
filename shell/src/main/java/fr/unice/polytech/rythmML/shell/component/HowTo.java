package fr.unice.polytech.rythmML.shell.component;

import fr.unice.polytech.rythmML.kernel.data.DrumsElements;
import fr.unice.polytech.rythmML.shell.HowToText;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * Commands that relies on rythm ML language.
 */
@ShellComponent
public class HowTo {


	@ShellMethod("Displays how to use Rythm ML.")
	public String howto() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("You can follow this guide to use RythmML for your music...");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append("---------------------------------------");
		buffer.append(" You can declare a partition with a grid ");
		buffer.append("---------------------------------------");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append(HowToText.HOW_TO_GRID);
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());


		buffer.append("--------------------");
		buffer.append(" To declare a section ");
		buffer.append("--------------------");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append(HowToText.HOW_TO_SECTION);
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());


		buffer.append("----------------");
		buffer.append(" To declare a bar ");
		buffer.append("----------------");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append(HowToText.HOW_TO_BAR);
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append("If you want to learn more about the note placement, you can run the command : howto placement");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append("If you want to learn more about the note variation syntax, you can run the command : howto variation");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append("If you want to learn more about the bar replacement syntax, you can run the command : howto replace");
		buffer.append(System.lineSeparator());

		return buffer.toString();
	}

	@ShellMethod(value = "Displays how to use Rythm ML placement.", key = "howto placement")
	public String howtoPlacement() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("To place a note in RythmML you need :");
		buffer.append(System.lineSeparator());
		buffer.append("	1 - Choose the instruments (MIDI_ELEMENTS) and an associated element (eg: bd). (To view all instruments and their elements run the command : instruments");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append("	2 - Indicate if the note is on the beat : 'bd on beat' or in a beat division : 'bd in beat'");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append("	3 - Specify the beat number : '1' , or several beats : '1 3 5' or a beat interval : '1-5'");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append("	4 - If you choose a beat division");
		buffer.append(System.lineSeparator());
		buffer.append("	You can use the following division keyword to place note : 'half', 'tiers', 'quarter' or 'eight'");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append("	5 - Specify the division number : '1' , or several division : '1 3 5' or a division interval : '1-5'");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append("	Example : ");
		buffer.append(System.lineSeparator());
		buffer.append("		bd on beat 1 4");
		buffer.append(System.lineSeparator());
		buffer.append("		cc in beat 1 4 on quarter 2 4");

		return buffer.toString();
	}

	@ShellMethod(value = "Displays how to use Rythm ML variation.", key = "howto variation")
	public String howtoVariation() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("To declare a note variation in RythmML you need to write something like this :");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append("variation on beat BEAT_NUMBER with velocity VELOCITY_NUMBER with delay DELAY_NUMBER");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append("Velocity is between 1 and 100");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append("Delay is expressed in milliseconds, you can give a number or an interval such as : 1..5");
		buffer.append(System.lineSeparator());
		buffer.append("The interval will generate a random value in between the two given number");
		return buffer.toString();
	}

	@ShellMethod(value = "Displays how to use Rythm ML replace.", key = "howto replace")
	public String howtoReplace() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("To use the replace mecanism, you need to write somethong like this in a section : ");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append("replace bar BAR_NUMBER OTHER_BAR");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append("The BAR_NUMBER can be like : 1 2 3 for bar 1,2 and 3 or 1-3 for all bars between 1 and 3.");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append("The OTHER_BAR is the name of the bar that will replace the old one.");
		buffer.append(System.lineSeparator());
		buffer.append(System.lineSeparator());
		buffer.append("	Example : ");
		buffer.append(System.lineSeparator());
		buffer.append("		replace bar 1 3 5 newVerseBar");
		buffer.append(System.lineSeparator());
		return buffer.toString();
	}
}
