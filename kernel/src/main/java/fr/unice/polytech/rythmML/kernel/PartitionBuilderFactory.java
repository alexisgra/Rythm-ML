package fr.unice.polytech.rythmML.kernel;

import fr.unice.polytech.rythmML.kernel.impl.Track;

public interface PartitionBuilderFactory {
    Partition createPartition(String name);

    TemporalWire createTemporalWire();

    Bar createBar();

    Note createNote();

    Placement createPlacement();

    Time createTime(String name);

    Section createSection();

    Track createTrack(String name);
}
