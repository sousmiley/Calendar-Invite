package project.assignment.importantdaystracker.view;

import project.assignment.importantdaystracker.control.AddAnImportantDay;
import project.assignment.importantdaystracker.model.ImportantDay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides the GUI of the calendar system program.
*/
public class GUI {
    private JFrame frame;
    private JTextArea window;

    /**
     * Implements all of the GUI, such as configuring the program wnidow.
     * @param onExitSaveCallback Callback to save data before exit.
     * @param onMenuSelectCallback Callback to run when a button is clicked 
     *                                  (based on its menu option).
     */
    public void createAndShowGUI(String title, Runnable onExitSaveCallback, 
                        java.util.function.IntConsumer onMenuSelectCallback) 
    {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (onExitSaveCallback != null) {
                    onExitSaveCallback.run();
                }
                frame.dispose();
            }
        });

        window = new JTextArea();
        window.setEditable(false);
        JScrollPane scrollableWindow = new JScrollPane(window);
        frame.add(scrollableWindow, BorderLayout.CENTER);

        JPanel buttonWindow = new JPanel(new GridLayout(4, 2));
        List<String> buttonLabels = getLabels();

        for (int i = 0; i < buttonLabels.size(); i++) {
            String label = buttonLabels.get(i);
            JButton optionButton = new JButton(label);
            int optionIndex = i + 1;
            optionButton.addActionListener(e -> onMenuSelectCallback.accept(optionIndex));
            buttonWindow.add(optionButton);
        }

        frame.add(buttonWindow, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    /**
     * Uses the setText method to place text inside the field. (Ref : Textbook Chp. 4.6)
     * @param text
     */
    public void displayToWindow(String text) {
        if (window != null) {
            window.setText(text);
        }
    }

    /**
     * shows the error message.
     */
    public void showErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Helper method, provides a way for the user to remove any item with the “all” view.
     * @param listDates
     * @return String
     */
    public String choiceRemoval(ArrayList<String> listDates){
        JList<String> removalList = new JList<>(listDates.toArray(new String[0]));
        JScrollPane removeWindow = new JScrollPane(removalList);
        removeWindow.setSize(700, 250);
        int result = JOptionPane.showConfirmDialog(frame, removeWindow, 
                "- Remove an Important Day -", 
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION && removalList.getSelectedValue() != null){
            return removalList.getSelectedValue();
        }
        return null;
    }

    /**
     * ask user to select a type of important day
     */
    public String promptForImportantDayType(String[] types, String title) {
        String inputWindow = (String) JOptionPane.showInputDialog(
            frame, "Select the type of important day:", 
            title, JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
        return inputWindow;
    }

    /**
     * input window for user
     */
    public String showInputDialog(String message, String title, String[] options, String defaultOption) {
        String inputWindow = (String) JOptionPane.showInputDialog(
            frame, message, title, JOptionPane.QUESTION_MESSAGE, 
            null, options, defaultOption);
        return inputWindow;
    }

    /**
     * show the removed choice of day
     */
    public String showRemovalChoiceDialog(List<String> options) {
        Object selected = JOptionPane.showInputDialog(
                frame,
                "Select the day you want to remove:",
                "Remove a Day",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options.toArray(),
                options.get(0)
        );
        return selected != null ? selected.toString() : null;
    }

    /**
     * Prints the data in the dates list in the correct format.
     */
    public String printDates(List<ImportantDay> dates) {
        StringBuilder theDates = new StringBuilder();
        int counter = 1;
        for (ImportantDay day : dates) {
            theDates.append("#").append(counter).append("\n").append(day).append("\n");
            counter++;
        }
        return theDates.toString();
    }

    /**
     * Provides button labels for the GUI.
     */
    public List<String> getLabels() {
        List<String> buttonLabels = new ArrayList<>();
        buttonLabels.add("List all important days");
        buttonLabels.add("Add an important day");
        buttonLabels.add("Remove an important day");
        buttonLabels.add("List passed important days this year");
        buttonLabels.add("List upcoming important days this year");
        buttonLabels.add("List all important days of the same type");
        buttonLabels.add("Save");
        return buttonLabels;
    }

    /**
     * shows the error for JSON
     */
    public void getErrorJSONFullMessage() {
        String errorJSON = "Error loading JSON data. Please try again.";
        String errorJSONTip = " TIP : Creating a new json file might resolve the issue.";
        String errorJSONFullMessage = errorJSON + errorJSONTip;
        JOptionPane.showMessageDialog(frame, errorJSONFullMessage,
        "JSON data loading Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Adds an important day
     */
    public ImportantDay addDay() {
        AddAnImportantDay addImportantDayWindow = new AddAnImportantDay(frame);
        addImportantDayWindow.setVisible(true);
        ImportantDay newDay = addImportantDayWindow.getImportantDay();
        return newDay;
    }

    /**
     * getter for frame
     */
    public JFrame getFrame() {
        return frame;
    }
}
