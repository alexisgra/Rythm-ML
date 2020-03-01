package fr.unice.polytech.rythmML.kernel.utils;

import static fr.unice.polytech.rythmML.kernel.visitor.MidiVisitor.MIDI_RESOLUTION;

public class TemporalUtils {

    private TemporalUtils() {
    }

    public static int toTime(final int bar, final int beat, final double division, final int nbBeatPerBar, final int bpm) {
        double ticksPerSecond = MIDI_RESOLUTION * 60.0 / bpm;
        int pos = bar * nbBeatPerBar;
        pos += beat;
        pos += division;
        return (int) (pos * ticksPerSecond);
    }
}
