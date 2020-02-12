package fr.unice.polytech.rythmML.kernel.utils;

public class TemporalUtils {

	public static int toTime(final int bar, final int beat, final double division, final int nbBeatPerBar, final int bpm) {
		int pos = bar * nbBeatPerBar * bpm;
		pos += beat * bpm;
		pos += division * bpm;
		return pos;
	}
}
