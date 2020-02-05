package fr.unice.polytech.rythmML.dsl.visitor;

import dsl.RythmMLBaseListener;
import dsl.RythmMLParser;
import fr.unice.polytech.rythmML.kernel.App;

public class ModelBuilder extends RythmMLBaseListener {

    private App theApp = null;

    @Override
    public void enterApp(RythmMLParser.AppContext ctx) {
        theApp = new App();
    }

    public App retrieve() {
        return theApp;
    }

}
