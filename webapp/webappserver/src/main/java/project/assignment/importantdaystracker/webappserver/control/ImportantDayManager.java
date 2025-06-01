package project.assignment.importantdaystracker.webappserver.control;

import com.google.gson.*;

import project.assignment.importantdaystracker.webappserver.gson.extras.RuntimeTypeAdapterFactory;
import project.assignment.importantdaystracker.webappserver.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * A class which is used to manage items
 */

public class ImportantDayManager {
    private final List<ImportantDay> importantDays = new ArrayList<>();

    private static final String JSON_FILE = "./list.json";
    private static final LocalDate today = LocalDate.now();

    /**
     * Constructor
     */
    public ImportantDayManager() {
        loadData();
    }

    /**
     * Gets important days data from the JSON file.
     */
    private void loadData() {
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
                importantDays.clear();
                importantDays.addAll(Arrays.asList(datesData));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the important days list to the JSON file.
     */
    public void saveData() {
        try (FileWriter write = new FileWriter(JSON_FILE)) {
            Gson myGson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();
            myGson.toJson(importantDays, write);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lists the important days to screen.
     */
    public ImportantDayResponse listAll() {
        List<ImportantDay> days = importantDays;
        // Needed to do this to be able to show if teh list is empty.
        String message = days.isEmpty() ? "empty list" : "success";
        return new ImportantDayResponse(message, days);
    }

    /**
     * Adds an important day to the list.
     */
    public void addDay(ImportantDay day) {
        importantDays.add(day);
    }

    /**
     * Removes an important day from the list.
     */
    public void removeDay(int id) {
        for (int i = 0; i < importantDays.size(); i++) {
            ImportantDay day = importantDays.get(i);
            if (day.getId() == id) {
                importantDays.remove(i);
                break;
            }
        }
    }

    /**
     * Lists the passed important days to the screen.
     */
    public List<ImportantDay> getPastDaysThisYear() {
        List<ImportantDay> result = new ArrayList<>();
        for (ImportantDay day : importantDays) {
            if ((day.getType().equals("Occasion") 
                && day.getDayForSorting().isBefore(today)
                 && day.getYear() == today.getYear()) ||
                (!day.getType().equals("Occasion") 
                && day.getDayForSorting().isBefore(today))) {
                result.add(day);
            }
        }
        result.sort(Comparator.naturalOrder());
        return result;
    }

    /**
     * Lists the upcoming important days to the screen.
     */
    public List<ImportantDay> getUpcomingDaysThisYear() {
        List<ImportantDay> result = new ArrayList<>();
        for (ImportantDay day : importantDays) {
            if ((day.getType().equals("Occasion") 
                && !day.getDayForSorting().isBefore(today)
                 && day.getYear() == today.getYear()) ||
                (!day.getType().equals("Occasion") 
                && !day.getDayForSorting().isBefore(today))) {
                result.add(day);
            }
        }
        result.sort(Comparator.naturalOrder());
        return result;
    }

    /**
     * Lists important days of a selected type this year.
     */
    public List<ImportantDay> getDaysByTypeAndYear(String type, int year) {
        List<ImportantDay> result = new ArrayList<>();
        for (ImportantDay day : importantDays) {
            if ((day.getType().equalsIgnoreCase(type)) && (day.getYear() == year)) {
                result.add(day);
            }
        }
        return result;
    }
}
