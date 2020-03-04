package fr.unice.polytech.rythmML.kernel.utils;

import static fr.unice.polytech.rythmML.kernel.visitor.MidiVisitor.MIDI_RESOLUTION;

public class TemporalUtils {

    private TemporalUtils() {
    }

    public static int toTime(final int bar, final int beat, final double division, final int nbBeatPerBar, final int bpm, final int delay) {
        double msPerSecond = 60000.0 / (bpm * MIDI_RESOLUTION);
        int pos = bar * nbBeatPerBar * MIDI_RESOLUTION;
        pos += beat * MIDI_RESOLUTION;
        pos += division * MIDI_RESOLUTION;
        pos += (delay / msPerSecond);
        System.out.println("TICK : " + pos);
        return pos;
    }
}
