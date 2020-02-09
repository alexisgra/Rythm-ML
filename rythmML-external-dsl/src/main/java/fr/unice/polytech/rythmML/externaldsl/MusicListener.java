package fr.unice.polytech.rythmML.externaldsl;

import grammar.RythmMLBaseListener;
import grammar.RythmMLParser;

public class MusicListener extends RythmMLBaseListener {

    @Override
    public void enterPartition(RythmMLParser.PartitionContext ctx) {
        System.out.println("partition " + ctx.name.getText());
    }

    @Override
    public void enterInit(RythmMLParser.InitContext ctx) {
        System.out.println("bpm " + ctx.bpmNumber.getText());
        System.out.println("section " + ctx.sectionNumber.getText());
        System.out.println("bar " + ctx.barNumber.getText());
    }

    @Override
    public void enterTracks(RythmMLParser.TracksContext ctx) {
        System.out.println("track " + ctx.trackNumber.getText());
    }

    @Override
    public void enterMusicNote(RythmMLParser.MusicNoteContext ctx) {
        System.out.println(ctx.instrument.getText() + " on " + ctx.sectionPosition.getText() + ":" + ctx.barPosition.getText() + ":" + ctx.beatPosition.getText());
    }
}