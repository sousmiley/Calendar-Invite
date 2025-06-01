package project.assignment.importantdaystracker.model;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import com.google.gson.annotations.SerializedName;

/**
 * Creates an important calendar day for the user.
 * */
public abstract class ImportantDay implements Comparable<ImportantDay> {
    @SerializedName("type")
    protected String type; // type of day
    protected String name;
    protected LocalDate whichDay;
    protected String description;
    protected int year;

    /**
     * Constructor for ImportantDay.
     */
    public ImportantDay(String type, String name, int month, int day, int year, String description) {
        this.type = type;
        this.name = name;
        this.whichDay = LocalDate.of(year, month, day);
        this.description = description;
        this.year = year;
    }
    
    /**
     * Gets the type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the type.
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the day.
     */
    public LocalDate getWhichDay() {
        return whichDay;
    }

    /**
     * This is a local date used for sorting.
     */
    public LocalDate getDayForSorting() {
        LocalDate currentTime = LocalDate.now(); 
        LocalDate _whichDay = LocalDate.of(currentTime.getYear(), whichDay.getMonth(), whichDay.getDayOfMonth());
        return _whichDay;
    }

    /**
     * Get the description.
     */
    public String getDescription() {
        return description;
    }

    /*
    Method allows ImportantDay objects to be compared.
    */ 
    @Override
    public int compareTo(ImportantDay other) {
        return this.whichDay.compareTo(other.whichDay);
    }

    /*
        Method prints everything nicely
    */ 
    @Override
    public String toString() {
        String _month = whichDay.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String today = String.valueOf(whichDay.getDayOfMonth());
            if (today.length() == 1) {
                today = "0" + today;
            }
        return "Important Day Type: " + getType() + "\n" +
           name + " on " + whichDay.getYear() + " " + _month + " " + today + "\n" +
           description;
    }
}
