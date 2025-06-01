package project.assignment.importantdaystracker.model;

/**
 * Anniversary type for important days.
 */

public class Anniversary extends ImportantDay {
    private String location;
    /*
        Constructor for Anniversary
    */ 
    public Anniversary(String name, int month, int day, int year, String description, String location) {
        super("Anniversary", name, month, day, year, description);
        this.location = location;
    }
    /*
        Gets location
    */ 
    public String getLocation() {
        return location;
    }
    /*
        Sets location
    */ 
    public void setLocation(String location) {
        this.location = location;
    }
    /*
        Gets type
    */ 
    @Override
    public String getType() {
        return "Anniversary";
    }

    /*
        Prinst things nicely
    */ 
    @Override
    public String toString() {
        return super.toString() + "\nLocated at: " + location;
    }
}

