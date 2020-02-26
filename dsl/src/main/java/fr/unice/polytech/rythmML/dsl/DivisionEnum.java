package fr.unice.polytech.rythmML.dsl;

import java.util.HashMap;
import java.util.Map;

public enum DivisionEnum {
    HALF("half", 2),
    TIERS("tiers", 3),
    QUARTER("quarter", 4),
    EIGHT("eight", 8);

    private static final Map<String, DivisionEnum> displayNameIndex = new HashMap<>();

    static {
        for (DivisionEnum divisionEnum : DivisionEnum.values()) {
            displayNameIndex.put(divisionEnum.getDisplayName(), divisionEnum);
        }
    }

    public final String displayName;
    public final Integer divisionSize;

    DivisionEnum(final String displayName, final Integer divisionSize) {
        this.displayName = displayName;
        this.divisionSize = divisionSize;
    }

    public static DivisionEnum lookupByDisplayName(String name) {
        return displayNameIndex.get(name);
    }


    String getDisplayName() {
        return displayName;
    }

    Integer getDivisionSize() {
        return divisionSize;
    }
}

