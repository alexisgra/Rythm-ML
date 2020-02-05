package fr.unice.polytech.rythmML.kernel;

import java.util.List;

public interface Section extends VisitableElement {
    Section addBa(Bar ba);

    List<Bar> getBar();

    Section setBar(List<Bar> bar);
}
