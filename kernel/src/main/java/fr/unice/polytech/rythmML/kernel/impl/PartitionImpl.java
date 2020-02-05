package fr.unice.polytech.rythmML.kernel.impl;

import fr.unice.polytech.rythmML.kernel.Partition;
import fr.unice.polytech.rythmML.kernel.TemporalWire;
import fr.unice.polytech.rythmML.kernel.Track;
import fr.unice.polytech.rythmML.kernel.Visitor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(
        chain = true
)
@RequiredArgsConstructor
class PartitionImpl implements Partition {
    @EqualsAndHashCode.Exclude
    private List<Track> tracks = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    private List<TemporalWire> temporalWire = new ArrayList<>();

    @NonNull
    private String name;

    public void accept(Visitor v) {
        v.visitPartition(this);
    }

    public Partition addTrack(Track track) {
        //We imported List automatically;
        this.tracks.add(track);
        return this;
    }

    public Partition addTemporalWir(TemporalWire temporalwir) {
        //We imported List automatically;
        this.temporalWire.add(temporalwir);
        return this;
    }
}
