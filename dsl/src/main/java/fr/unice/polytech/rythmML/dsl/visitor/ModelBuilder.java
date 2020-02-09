package fr.unice.polytech.rythmML.dsl.visitor;

import dsl.RythmMLBaseListener;
import dsl.RythmMLParser;
import fr.unice.polytech.rythmML.kernel.Partition;

public class ModelBuilder extends RythmMLBaseListener {

    private Partition partition = null;

    @Override
    public void enterApp(RythmMLParser.AppContext ctx) {
        partition = new Partition("Test");
    }

    public Partition retrieve() {
        return partition;
    }

}
