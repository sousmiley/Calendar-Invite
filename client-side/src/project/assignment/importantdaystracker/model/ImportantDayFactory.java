package project.assignment.importantdaystracker.model;

/**
 * A utility class generating instances of different package types
 */

public class ImportantDayFactory {
    public static ImportantDay getInstance(String type, String name, int month, 
                            int day, int year, String description, String extra) {
        switch (type.toLowerCase().trim()) {
            case "anniversary":
                return new Anniversary(name, month, day, year, description, extra);
            case "birthday":
                return new Birthday(name, month, day, year, description, extra);
            case "occasion":
                return new Occasion(name, month, day, year, description, Integer.parseInt(extra));
            default:
                throw new IllegalArgumentException("Invalid type.");
        }
    }
}