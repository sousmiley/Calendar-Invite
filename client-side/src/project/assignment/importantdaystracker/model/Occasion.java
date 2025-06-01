package project.assignment.importantdaystracker.model;

/**
 * Occasion type for important days.
 */

public class Occasion extends ImportantDay {
    private int frequency;
    /*
        Constructor for Occasion class
    */ 
    public Occasion(String name, int month, int day, int year, String description, int frequency) {
        super("Occasion", name, month, day, year, description);
        this.frequency = frequency;
    }
    /*
        Gets frequency
    */ 
    public int getFrequency() {
        return frequency;
    }
    /*
        Sets frequency
    */ 
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    /*
        Gets type
    */ 
    @Override
    public String getType() {
        return "Occasion";
    }

    /*
        Method prints everything nicely
    */ 
    @Override
    public String toString() {
        return super.toString() + "\nFrequency: every " + frequency + " years";
    }
}