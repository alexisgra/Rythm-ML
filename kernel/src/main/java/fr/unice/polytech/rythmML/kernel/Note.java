package fr.unice.polytech.rythmML.kernel;

public interface Note extends VisitableElement {
    Placement getPlacement();

    Note setPlacement(Placement placement);
}
