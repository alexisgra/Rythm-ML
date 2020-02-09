package fr.unice.polytech.rythmML.kernel;

import fr.unice.polytech.rythmML.kernel.temporal.TemporalGrid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(
        chain = true
)
@RequiredArgsConstructor
public class Partition {
    @EqualsAndHashCode.Exclude
    private List<Track> tracks = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    private List<TemporalGrid> temporalWire = new ArrayList<>();

    @NonNull
    private String name;

    public void generateMIDI() {

    }

    public void accept(Visitor v) {
        v.visitPartition(this);
    }

    public Partition addTrack(Track track) {
        //We imported List automatically;
        this.tracks.add(track);
        return this;
    }

    public Partition addTemporalWir(TemporalGrid temporalwir) {
        //We imported List automatically;
        this.temporalWire.add(temporalwir);
        return this;
    }
}
