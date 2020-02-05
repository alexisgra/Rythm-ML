package fr.unice.polytech.rythmML.kernel;

import java.util.List;

public interface Partition extends VisitableElement {
    Partition addTrack(Track track);

    List<Track> getTracks();

    Partition setTracks(List<Track> tracks);

    Partition addTemporalWir(TemporalWire temporalwir);

    List<TemporalWire> getTemporalWire();

    Partition setTemporalWire(List<TemporalWire> temporalWire);

    String getName();

    Partition setName(String name);
}
