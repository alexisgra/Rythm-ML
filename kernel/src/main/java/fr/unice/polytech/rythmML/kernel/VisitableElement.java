package fr.unice.polytech.rythmML.kernel;

public interface VisitableElement {
    void accept(Visitor v);
}
