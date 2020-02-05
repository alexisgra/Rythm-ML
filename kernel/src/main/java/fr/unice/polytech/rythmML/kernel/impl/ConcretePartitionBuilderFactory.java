package fr.unice.polytech.rythmML.kernel.impl;

import fr.unice.polytech.rythmML.kernel.*;

public class ConcretePartitionBuilderFactory implements PartitionBuilderFactory {
    public Partition createPartition(String name) {
        return new PartitionImpl(name);
    }

    public TemporalWire createTemporalWire() {
        return new TemporalWireImpl();
    }

    public Bar createBar() {
        return new BarImpl();
    }

    public Note createNote() {
        return new NoteImpl();
    }

    public Placement createPlacement() {
        return new PlacementImpl();
    }

    public Time createTime(String name) {
        return new TimeImpl(name);
    }

    public Section createSection() {
        return new SectionImpl();
    }

    public Track createTrack(String name) {
        return new TrackImpl(name);
    }
}
