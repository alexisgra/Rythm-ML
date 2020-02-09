package fr.unice.polytech.rythmML.kernel.impl;

import fr.unice.polytech.rythmML.kernel.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PlantUmlVisitorImpl implements Visitor {
    List<StringBuilder> queue = new ArrayList<>();

    private StringBuilder builder = new StringBuilder().append("@startUml").append("\n").append("skinparam linetype polyline").append("\n");

    public void dequeue() {
        for (StringBuilder string : queue) {
            builder.append(string);
        }
    }

    public String removeImpl(String className) {
        return className.split("Impl")[0];
    }

    public void visitPartition(Partition partition) {
       /* builder.append("object ").append(removeImpl(partition.getClass().getSimpleName())).append("{" + "\n");
        builder.append("partition.getName()".split("get")[1].replace("()", "").toLowerCase()).append(" = ").append("\"").append(partition.getName()).append("\"").append("\n");
        builder.append("}" + "\n");
        for (Track track : partition.getTracks()) {
            track.accept(this);
            queue.add(new StringBuilder().append(removeImpl(partition.getClass().getSimpleName())).append(" -- ").append(removeImpl(track.getClass().getSimpleName())).append(BigInteger.valueOf(track.hashCode()).abs()).append("\n"));
        }
        for (TemporalWire temporalwir : partition.getTemporalWire()) {
            temporalwir.accept(this);
            queue.add(new StringBuilder().append(removeImpl(partition.getClass().getSimpleName())).append(" -- ").append(removeImpl(temporalwir.getClass().getSimpleName())).append(BigInteger.valueOf(temporalwir.hashCode()).abs()).append("\n"));
        }
        File outputDirectory = new File("./plantuml/");
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }
        dequeue();
        builder.append("\n");
        builder.append("@enduml");
        SourceStringReader reader = new SourceStringReader(builder.toString());
        System.out.println(builder.toString());
        try (FileOutputStream output = new FileOutputStream(new File(outputDirectory, partition.getClass().getSimpleName() + ".png"))) {
            reader.generateImage(output, new FileFormatOption(FileFormat.PNG, false));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;*/
    }

    public void visitTemporalWire(TemporalWire temporalwire) {
        builder.append("object ").append("\"").append(removeImpl(temporalwire.getClass().getSimpleName())).append("\"").append(" as ").append(removeImpl(temporalwire.getClass().getSimpleName())).append(BigInteger.valueOf(temporalwire.hashCode()).abs()).append("{ " + "\n");
        ;
        builder.append("}" + "\n");
        for (Section section : temporalwire.getSections()) {
            section.accept(this);
            queue.add(new StringBuilder().append(removeImpl(temporalwire.getClass().getSimpleName())).append(" -- ").append(removeImpl(section.getClass().getSimpleName())).append(BigInteger.valueOf(section.hashCode()).abs()).append("\n"));
        }
    }

    public void visitBar(Bar bar) {
        builder.append("object ").append("\"").append(removeImpl(bar.getClass().getSimpleName())).append("\"").append(" as ").append(removeImpl(bar.getClass().getSimpleName())).append(BigInteger.valueOf(bar.hashCode()).abs()).append("{ " + "\n");
        ;
        builder.append("}" + "\n");
    }

    public void visitNote(Note note) {
        builder.append("object ").append("\"").append(removeImpl(note.getClass().getSimpleName())).append("\"").append(" as ").append(removeImpl(note.getClass().getSimpleName())).append(BigInteger.valueOf(note.hashCode()).abs()).append("{ " + "\n");
        ;
        builder.append("}" + "\n");
        queue.add(new StringBuilder().append(removeImpl(note.getClass().getSimpleName())).append(BigInteger.valueOf(note.hashCode()).abs()).append(" -- ").append("\"").append("note.getPlacement()".split("get")[1].replace("()", "").toLowerCase()).append("\" ").append(removeImpl(note.getPlacement().getClass().getSimpleName())).append(BigInteger.valueOf(note.getPlacement().hashCode()).abs()).append("\n"));
    }

    public void visitPlacement(Placement placement) {
        builder.append("object ").append("\"").append(removeImpl(placement.getClass().getSimpleName())).append("\"").append(" as ").append(removeImpl(placement.getClass().getSimpleName())).append(BigInteger.valueOf(placement.hashCode()).abs()).append("{ " + "\n");
        ;
        builder.append("placement.getBeat()".split("get")[1].replace("()", "").toLowerCase()).append(" = ").append("\"").append(placement.getBeat()).append("\"").append("\n");
        builder.append("placement.getBar()".split("get")[1].replace("()", "").toLowerCase()).append(" = ").append("\"").append(placement.getBar()).append("\"").append("\n");
        builder.append("placement.getSection()".split("get")[1].replace("()", "").toLowerCase()).append(" = ").append("\"").append(placement.getSection()).append("\"").append("\n");
        builder.append("}" + "\n");
    }

    public void visitTime(Time time) {
        builder.append("object ").append("\"").append(removeImpl(time.getClass().getSimpleName())).append("\"").append(" as ").append(removeImpl(time.getClass().getSimpleName())).append(BigInteger.valueOf(time.hashCode()).abs()).append("{ " + "\n");
        ;
        builder.append("time.getName()".split("get")[1].replace("()", "").toLowerCase()).append(" = ").append("\"").append(time.getName()).append("\"").append("\n");
        builder.append("}" + "\n");
    }

    public void visitSection(Section section) {
        builder.append("object ").append("\"").append(removeImpl(section.getClass().getSimpleName())).append("\"").append(" as ").append(removeImpl(section.getClass().getSimpleName())).append(BigInteger.valueOf(section.hashCode()).abs()).append("{ " + "\n");
        ;
        builder.append("}" + "\n");
        for (Bar ba : section.getBar()) {
            ba.accept(this);
            queue.add(new StringBuilder().append(removeImpl(section.getClass().getSimpleName())).append(" -- ").append(removeImpl(ba.getClass().getSimpleName())).append(BigInteger.valueOf(ba.hashCode()).abs()).append("\n"));
        }
    }

    public void visitTrack(Track track) {
        builder.append("object ").append("\"").append(removeImpl(track.getClass().getSimpleName())).append("\"").append(" as ").append(removeImpl(track.getClass().getSimpleName())).append(BigInteger.valueOf(track.hashCode()).abs()).append("{ " + "\n");
        ;
        builder.append("track.getName()".split("get")[1].replace("()", "").toLowerCase()).append(" = ").append("\"").append(track.getName()).append("\"").append("\n");
        builder.append("}" + "\n");
        for (Note note : track.getNotes()) {
            note.accept(this);
            queue.add(new StringBuilder().append(removeImpl(track.getClass().getSimpleName())).append(" -- ").append(removeImpl(note.getClass().getSimpleName())).append(BigInteger.valueOf(note.hashCode()).abs()).append("\n"));
        }
    }
}
