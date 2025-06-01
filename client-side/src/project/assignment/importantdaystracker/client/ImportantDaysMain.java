package project.assignment.importantdaystracker.client;

import javax.swing.SwingUtilities;

import project.assignment.importantdaystracker.model.ImportantDayManager;

/**
 * This program runs a calendar system which maintains a list of important days
 * recording a small collection of their attributes.
 */

public class ImportantDaysMain {
    /**
     * Starts the ImportantDayController application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ImportantDayManager();
            }
        });
    }
}