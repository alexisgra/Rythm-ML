package fr.unice.polytech.rythmML.kernel;

public interface Placement extends VisitableElement {
    String getBeat();

    Placement setBeat(String beat);

    String getBar();

    Placement setBar(String bar);

    String getSection();

    Placement setSection(String section);
}
