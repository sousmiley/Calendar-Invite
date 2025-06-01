package project.assignment.importantdaystracker.webappserver.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Birthday type for important days.
 */

public class Birthday extends ImportantDay {
    private String birthdayPerson;
    /**
     * Constructor for Birthday class – now mapping Json properties
     */
    @JsonCreator
    public Birthday(@JsonProperty("name") String name,
                    @JsonProperty("month") int month,
                    @JsonProperty("day") int day,
                    @JsonProperty("year") int year,
                    @JsonProperty("description") String description,
                    @JsonProperty("person") String birthdayPerson) {
        super("Birthday", name, month, day, year, description);
        this.birthdayPerson = birthdayPerson;
    }

    /**
     * Gets birthday
     */
    public String getbirthdayPerson() {
        return birthdayPerson;
    }

    /**
     * Sets birthday
     */
    public void setbirthdayPerson(String birthdayPerson) {
        this.birthdayPerson = birthdayPerson;
    }

    /**
     * Gets type
     */
    @Override
    public String getType() {
        return "Birthday";
    }

    /**
     * Prints everything nicely
     */
    @Override
    public String toString() {
        int age = ((LocalDate.now().getYear()) - (getWhichDay().getYear()));
        String phrase = "\nNote: " + birthdayPerson + " is turning " + age + " year(s) old.";
        return super.toString() + phrase;
    }
}