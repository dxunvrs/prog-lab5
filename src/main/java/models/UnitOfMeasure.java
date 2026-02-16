package models;

public enum UnitOfMeasure {
    METERS,
    SQUARE_METERS,
    PCS,
    MILLILITERS;

    public static boolean contains(String name) {
        if (name == null) {
            return false;
        }

        for (UnitOfMeasure value: values()) {
            if (value.name().equals(name)) {
                return true;
            }
        }
        return false;
    }
}