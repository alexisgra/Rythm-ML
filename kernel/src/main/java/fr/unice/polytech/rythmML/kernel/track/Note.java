package fr.unice.polytech.rythmML.kernel.track;

import fr.unice.polytech.rythmML.kernel.Visitor;
import fr.unice.polytech.rythmML.kernel.data.DrumsElements;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Random;

@Data
@Accessors(
        chain = true
)
@RequiredArgsConstructor
public class Note {

    @NonNull
    private DrumsElements drumElements;

    private Integer velocity = 100;
    private Integer minInterval = null;
    private Integer maxInterval = null;
    public Note(Note note) {
        this.drumElements = note.getDrumElements();
        this.minInterval = note.getMinInterval();
        this.maxInterval = note.getMaxInterval();
        this.velocity = note.getVelocity();
    }

    /**
     * If there is no delay, the method returns 0.
     * <p>
     * If there is a delay but maxInterval is null,
     * Let's take minInterval at 10, we want the delay to be between 1-10. (not 0, because if there is a delay, but it is 0ms, it makes no sense)
     * We have nextInt(10), nextInt is exclusive, so it will give a result ranged from 0 to 9.
     * As a result with the +1, it will give the correct delay from 1 to 10.
     * <p>
     * If there is a delay and maxInterval is not null.
     * Let's take minInterval at 10 and maxInterval at 50, we want it to be between 10-50.
     * nextInt(50-10+1)=nextInt(41), nextInt is exclusive so it will give a result ranged from 0 to 40.
     * As a result with the +minInterval, it will give the correct delay from 10 to 50.
     */
    public Integer getDelay() {
        if (!hasDelay()) {
            return 0;
        }
        Random random = new Random();
        return this.maxInterval == null ? random.nextInt(this.minInterval) + 1 : random.nextInt(maxInterval - minInterval + 1) + minInterval;
    }

    public boolean hasDelay() {
        return minInterval != null;
    }

    public void accept(Visitor v) {
        v.visitNote(this);
    }
}
