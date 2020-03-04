package fr.unice.polytech.rythmML.shell;

import fr.unice.polytech.rythmML.dsl.Runner;
import fr.unice.polytech.rythmML.kernel.Partition;
import fr.unice.polytech.rythmML.kernel.SectionLibrary;
import fr.unice.polytech.rythmML.kernel.temporal.Section;
import org.antlr.v4.runtime.CharStream;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

public class RythmUtils {

	public static Optional<Section> getSectionFromRythm(final String name) throws IOException {
		final Runner antlrRunner = new Runner();
		CharStream stream = antlrRunner.getCharStream(Paths.get(WorkspaceConfig.WORKSPACE));
		final Partition partition = antlrRunner.buildModel(stream);
		return partition.getSectionLibrary().getSectionWithName(name);
	}

	public static String generateSectionsList(final SectionLibrary sectionLibrary) {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("YOUR SECTIONS ARE : ");
		buffer.append(System.lineSeparator());
		for (final String section : sectionLibrary.getSectionsNames()) {
			buffer.append("		");
			buffer.append("- ");
			buffer.append(section);
			buffer.append(System.lineSeparator());
		}
		return buffer.toString();
	}
}
