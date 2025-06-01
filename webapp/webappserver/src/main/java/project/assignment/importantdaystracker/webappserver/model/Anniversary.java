package project.assignment.importantdaystracker.webappserver.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Anniversary type for important days.
 */
public class Anniversary extends ImportantDay {
    private String location;
    /**
     * Constructor for Anniversary class – now mapping Json properties
     */
    @JsonCreator
    public Anniversary(@JsonProperty("name") String name,
                       @JsonProperty("month") int month,
                       @JsonProperty("day") int day,
                       @JsonProperty("year") int year,
                       @JsonProperty("description") String description,
                       @JsonProperty("location") String location) {
        super("Anniversary", name, month, day, year, description);
        this.location = location;
    }

    /**
     * Gets location
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * Sets location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets type
     */
    @Override
    public String getType() {
        return "Anniversary";
    }

    /**
     * Prints everything nicely
     */
    @Override
    public String toString() {
        return super.toString() + "\nLocated at: " + location;
    }
}