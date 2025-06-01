package project.assignment.importantdaystracker.webappserver.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Occasion type for important days.
 */

public class Occasion extends ImportantDay {
    private int frequency;
    /**
     * Constructor for Occasion class – now mapping Json properties
     */
    @JsonCreator
    public Occasion(@JsonProperty("name") String name,
                    @JsonProperty("month") int month,
                    @JsonProperty("day") int day,
                    @JsonProperty("year") int year,
                    @JsonProperty("description") String description,
                    @JsonProperty("frequency") int frequency) {
        super("Occasion", name, month, day, year, description);
        this.frequency = frequency;
    }

    /**
     * Gets frequency
     */
    public int getFrequency() {
        return frequency;
    }
 
    /**
     * Sets frequency
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /**
     * Gets type
     */
    @Override
    public String getType() {
        return "Occasion";
    }

    /**
     * Prints everything nicely
     */
    @Override
    public String toString() {
        return super.toString() + "\nFrequency: every " + frequency + " years";
    }
}