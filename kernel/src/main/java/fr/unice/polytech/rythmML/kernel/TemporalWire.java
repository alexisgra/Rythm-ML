package fr.unice.polytech.rythmML.kernel;

import java.util.List;

public interface TemporalWire extends VisitableElement {
    TemporalWire addSection(Section section);

    List<Section> getSections();

    TemporalWire setSections(List<Section> sections);
}
