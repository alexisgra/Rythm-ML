package fr.unice.polytech.rythmML.kernel.utils;

import static fr.unice.polytech.rythmML.kernel.visitor.MidiVisitor.MIDI_RESOLUTION;

public class TemporalUtils {

    private TemporalUtils() {
    }

    public static int toTime(final int bar, final int beat, final double division, final int nbBeatPerBar, final int bpm) {
        int pos = bar * nbBeatPerBar * MIDI_RESOLUTION;
        pos += beat * MIDI_RESOLUTION;
        pos += division * MIDI_RESOLUTION;
        System.out.println("TICK : " + pos);
        return pos;
    }
}
