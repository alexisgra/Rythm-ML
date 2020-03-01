package fr.unice.polytech.rythmML.kernel.data;

import java.util.HashMap;
import java.util.Map;

public enum DrumsElements {
	BASS_DRUM("bd", "kick drum or bass drum", 36),
	SNARE_DRUM("sd", "snare drum", 38),
	CLOSED_HITHAT("ch", "closed hihat", 42),
	OPENED_HITHAT("oh", "opened hihat", 46),
	CRASH_SYMBAL("cc", "crash cymbal", 49),
	RIDE_CYMBAL("rc", "ride cymbal", 51);


	private static final Map<String, DrumsElements> displayNameIndex = new HashMap<>();

	static {
		for (DrumsElements instrument : DrumsElements.values()) {
			displayNameIndex.put(instrument.getDisplayName(), instrument);
		}
	}

	public final String displayName;
	public final String description;
	public final int midiNote;

	DrumsElements(final String displayName, final String description, final int midiNote) {
		this.displayName = displayName;
		this.description = description;
		this.midiNote = midiNote;
	}

	public static DrumsElements lookupByDisplayName(String name) {
		return displayNameIndex.get(name);
	}

	String getDisplayName() {
		return displayName;
	}
}

