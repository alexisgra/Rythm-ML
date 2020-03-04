package fr.unice.polytech.rythmML.kernel;

import fr.unice.polytech.rythmML.kernel.temporal.Section;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class SectionLibrary {

	private HashMap<String, Section> sectionMap = new HashMap<String, Section>();

	public Optional<Section> getSectionWithName(final String name) {
		final Section section = this.sectionMap.get(name);
		if (section == null ){
			return Optional.empty();
		}
		return Optional.of(section);
	}

	public void addSection(final Section section) {
		this.sectionMap.put(section.getName(), section);
	}

	public List<String> getSectionsNames() {
		return new ArrayList<>(this.sectionMap.keySet());
	}
}
