package project.assignment.importantdaystracker.model;

import java.time.LocalDate;

/**
 * Birthday type for important days.
 */

public class Birthday extends ImportantDay {
    private String birthdayPerson;
    /*
        Birthday constructor
    */ 
    public Birthday(String name, int month, int day, int year, String description, String birthdayPerson) {
        super("Birthday", name, month, day, year, description);
        this.birthdayPerson = birthdayPerson;
    }
    /*
        Gets birthday
    */ 
    public String getbirthdayPerson() {
        return birthdayPerson;
    }
    /*
        Sets birthday
    */ 
    public void setbirthdayPerson(String birthdayPerson) {
        this.birthdayPerson = birthdayPerson;
    }
    /*
        Gets type
    */ 
    @Override
    public String getType() {
        return "Birthday";
    }

    /*
        Prinst things nicely
    */ 
    @Override
    public String toString() {
        int age = ((LocalDate.now().getYear()) - (getWhichDay().getYear()));
        String phrase = "\nNote: " + birthdayPerson + " is turning " + age + " year(s) old.";
        return super.toString() + phrase;
    }
}