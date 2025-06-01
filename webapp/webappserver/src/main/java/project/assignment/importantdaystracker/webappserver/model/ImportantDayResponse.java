package project.assignment.importantdaystracker.webappserver.model;

import java.util.List;

public class ImportantDayResponse {
    private String message;
    private List<ImportantDay> importantDays;

    /**
     * Constructpre
     * @param message
     * @param importantDays
     */
    public ImportantDayResponse(String message, List<ImportantDay> importantDays) {
        this.message = message;
        this.importantDays = importantDays;
    }

    /**
     * Getter for messages
     * @return
     */
    public String getMessage() {
        return message;
    }
    /**
     * Sets for messages
     * @return
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter for list
     * @return
     */
    public List<ImportantDay> getImportantDays() {
        return importantDays;
    }

    /**
     * Sets for list
     * @return
     */
    public void setImportantDays(List<ImportantDay> importantDays) {
        this.importantDays = importantDays;
    }
}

