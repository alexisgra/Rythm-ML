package fr.unice.polytech.rythmML.kernel.data;

import java.util.HashMap;
import java.util.Map;

public enum Instrument {
	BASS_DRUM("bd", "kick drum or bass drum"),
	SNARE_DRUM("sd", "snare drum"),
	CLOSED_HITHAT("ch", "closed hihat"),
	OPENED_HITHAT("oh", "opened hihat"),
	CRASH_SYMBAL("cc", "crash cymbal"),
	RIDE_SYMBAL("rc", "ride cymbal");

	private static final Map<String, Instrument> displayNameIndex = new HashMap<>();

	static {
		for (Instrument instrument : Instrument.values()) {
			displayNameIndex.put(instrument.getDisplayName(), instrument);
		}
	}

	public final String displayName;
	public final String description;

	Instrument(final String displayName, final String description) {
		this.displayName = displayName;
		this.description = description;
	}

	public static Instrument lookupByDisplayName(String name) {
		return displayNameIndex.get(name);
	}

	String getDisplayName() {
		return displayName;
	}

}
