package project.assignment.importantdaystracker.model;

import java.time.LocalDate;
import java.io.*;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import project.assignment.importantdaystracker.gson.extras.RuntimeTypeAdapterFactory;
import project.assignment.importantdaystracker.view.GUI;

/**
 * A calendar system program.
*/
public class ImportantDayManager {
    private static final int OPTION_ONE = 1;
    private static final int OPTION_TWO = 2;
    private static final int OPTION_THREE = 3;
    private static final int OPTION_FOUR = 4;
    private static final int OPTION_FIVE = 5;
    private static final int OPTION_SIX = 6;
    private static final int OPTION_SEVEN = 7;
    private static final String JSON_FILE = "./list.json";
    private static final String ANNIVERSARY = "Anniversary";
    private static final String BIRTHDAY = "Birthday";
    private static final String OCCASION = "Occasion";

    private static final List<ImportantDay> dates = new ArrayList<>();
    public List<ImportantDay> getDates() {
        return dates;
    }

    private static LocalDate today = LocalDate.now();
    static String title = "✦ Pin that Day ✦";
    private static GUI gui = new GUI();

    /**
     * Constructs a calendar.
    */
    public ImportantDayManager() {
        start();
    }

    /**
     * Loads calendar data (important days list) to the system.
     * Starts the program, which maintains a list of important days
     * recording a small collection of their attributes.
    */
    private void start(){
        gui.createAndShowGUI(title, this::saveData, this::textMenu);
    }
    
    /**
     * Will decide which main menu operation to conduct.
     */
    private void textMenu(int choice){
        switch (choice){
            case OPTION_ONE:
                listAllImportantDays();
                break; 
            case OPTION_TWO:
                addAnImportantDay();
                break;
            case OPTION_THREE:
                removeAnImportantDay();
                break;
            case OPTION_FOUR:
                listPassedImportantDays();
                break;
            case OPTION_FIVE:
                listUpcomingImportantDays();
                break;
            case OPTION_SIX:
                listDaysByType();
                break;
            case OPTION_SEVEN:
                saveData();
                return;
        }
    }

    /**
     * Gets important days data from the JSON file.
     */
    private void getJsonData(){
        File input = new File(JSON_FILE);
        try (Scanner scanner = new Scanner(input)) {
            StringBuilder jsonData = new StringBuilder();
            while (scanner.hasNextLine()){
                jsonData.append(scanner.nextLine());
            }

            RuntimeTypeAdapterFactory<ImportantDay> typeFactory = RuntimeTypeAdapterFactory
                    .of(ImportantDay.class, "type")
                    .registerSubtype(Anniversary.class, "Anniversary")
                    .registerSubtype(Birthday.class, "Birthday")
                    .registerSubtype(Occasion.class, "Occasion");

            Gson myGson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .registerTypeAdapterFactory(typeFactory)
                    .create();

            ImportantDay[] datesData = myGson.fromJson(jsonData.toString(), ImportantDay[].class);

            if (datesData != null) {
                dates.addAll(Arrays.asList(datesData));
            }
        } catch (IOException e) {
            gui.getErrorJSONFullMessage();
        }
    }

    /**
     * Lists the important days to screen.
     * This corresponds to option one on the menu.
     */
    public void listAllImportantDays(){
        dates.sort(Comparator.naturalOrder());
        if (dates.isEmpty()){
            String error = "No items to show.";
            gui.displayToWindow(error);
        }else{
            gui.displayToWindow((gui.printDates(dates)));
        }
    }

    /**
     * Adds an important day to the list.
     * This corresponds to option two on the menu.
     */
    private void addAnImportantDay(){
        ImportantDay newDay = gui.addDay();
        if (newDay != null){
            dates.add(newDay);
            gui.displayToWindow("Success! " + newDay + "\nhas been added to your calendar.");
        }
    }

    /**
     * Removes an important day from the list.
     * This corresponds to option three on the menu.
     */
    private void removeAnImportantDay(){
        if (dates.isEmpty()){
            gui.showErrorMessage("No important days to remove.", 
                "No Dates Available Error");
            return;
        }
        ArrayList<String> listDates = new ArrayList<>();
        for (ImportantDay day : dates){
            listDates.add(day.toString());
        }
        String choiceToRemove = gui.choiceRemoval(listDates);
        if (choiceToRemove != null){
            boolean isRemoved = removeImportantDay(choiceToRemove);
            if (isRemoved){
                gui.displayToWindow("Success! " + choiceToRemove + 
                "\nhas been removed from your calendar.");
            }else{
                gui.showErrorMessage("Unable to remove the chosen day, please try again later.", 
                "Day Removal Error");
            }
        }
    }

    /**
     * Helper function, if succesful removal of the date from the list, 
     * return true. Otheriwse, return false.
     * @param choice
     * @return boolean
     */
    private boolean removeImportantDay(String choice){
        for (Iterator<ImportantDay> iterator = dates.iterator(); iterator.hasNext();){
            ImportantDay day = iterator.next();
            String dayString = day.toString();
            if (dayString.equals(choice)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Lists the passed important days to the screen.
     * This corresponds to option four on the menu.
     */
    private void listPassedImportantDays(){
        List<ImportantDay> listPastDays = new ArrayList<>();
        for (ImportantDay day : dates) {
            if ((day.getType().equals("Occasion"))
             && day.getDayForSorting().isBefore(today) 
             && day.getYear() == today.getYear()) {
                listPastDays.add(day);
            } else if (!(day.getType().equals("Occasion")) 
            && day.getDayForSorting().isBefore(today)) {
                listPastDays.add(day);
            }
        }
        if (listPastDays.isEmpty()) {
            gui.displayToWindow("No passed important days to show.");
        } else {
            listPastDays.sort(Comparator.naturalOrder());
            gui.displayToWindow((gui.printDates(listPastDays)));
        }
    }

    /**
     * Lists the upcoming important days to the screen.
     * This corresponds to option five on the menu.
     */
    private void listUpcomingImportantDays(){
        List<ImportantDay> listNextDays = new ArrayList<>();
        for (ImportantDay day : dates) {
            if ((day.getType().equals("Occasion"))
             && !(day.getDayForSorting().isBefore(today)) 
             && day.getYear() == today.getYear()) {
                listNextDays.add(day);
            } else if (!(day.getType().equals("Occasion")) 
            && !(day.getDayForSorting().isBefore(today))) {
                listNextDays.add(day);
            }
        }
        if (listNextDays.isEmpty()) {
            gui.displayToWindow("No upcoming important days to show.");
        } else {
            listNextDays.sort(Comparator.naturalOrder());
            gui.displayToWindow((gui.printDates(listNextDays)));
        }
    }

    /**
     * Lists important days of a selected type this year.
     * This corresponds to option six on the menu.
     */
    private void listDaysByType(){
        String[] types = {ANNIVERSARY, BIRTHDAY, OCCASION};

        String importantDaysType = gui.promptForImportantDayType(types, 
        "Filter by Type");
        
        // GET RID OF , CHANGE
        if (importantDaysType != null) {
            List<ImportantDay> listTypeOfDays = new ArrayList<>();
            for (ImportantDay day : dates) {
                if (day.getType().equals(importantDaysType)) {
                    listTypeOfDays.add(day);
                }
            }
            listTypeOfDays.sort(Comparator.naturalOrder());
            if (listTypeOfDays.isEmpty()) {
                gui.displayToWindow("No important days to show.");
            } else {
                gui.displayToWindow((gui.printDates(listTypeOfDays)));
            }
        }
    }

    /**
     * Saves the important days list to the JSON file.
     * This corresponds to option seven on the menu.
     */
    private void saveData(){
        try (FileWriter writer = new FileWriter(JSON_FILE)) {
            Gson myGson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();

            myGson.toJson(dates, writer);
            writer.close();
            gui.displayToWindow("Saving the list to " + JSON_FILE + "...");
        } catch (IOException e){
            gui.showErrorMessage("Error : Unable to save data.", 
            "Save Data Error");
        }
        gui.displayToWindow("Data saved! Thanks for using " + title + 
            "\nTo exit the program, simply close this window.");
    }
}
