package project.assignment.importantdaystracker.control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;


import com.github.lgooddatepicker.components.DatePicker;

import project.assignment.importantdaystracker.model.Anniversary;
import project.assignment.importantdaystracker.model.Birthday;
import project.assignment.importantdaystracker.model.Occasion;
import project.assignment.importantdaystracker.model.ImportantDay;

/**
 * Allows users to add an important day to their calendar list.
 */
public class AddAnImportantDay  extends JDialog {
    private JComboBox<String> dayType;
    private ImportantDay day;
    private JTextField userInputName;
    private JTextField userInputDescription;
    private JTextField userInputLocation;
    private JTextField userInputBirthdayName;
    private JTextField userInputFrequncy;
    private static final String ANNIVERSARY = "Anniversary";
    private static final String BIRTHDAY = "Birthday";
    private static final String OCCASION = "Occasion";
    private DatePicker datePicker;
    private JButton create;
    private JButton cancel;
    
    /**
     * Collects specific information regarding what kind of important date 
     * event is being added in.
     * @param parent
     */
    public AddAnImportantDay(JFrame parent) {
        super(parent, "+ Add an Important Day! +", true);
        shape();
        JPanel window = new JPanel();
        window.setLayout(new GridLayout(0, 2));
        window.add(new JLabel("The type :"));
        dayType = new JComboBox<>(new String[]{ANNIVERSARY, BIRTHDAY, OCCASION});
        window.add(dayType);
        window.add(new JLabel("The name :"));
        userInputName = new JTextField();
        window.add(userInputName);
        window.add(new JLabel("The date :"));
        datePicker = new DatePicker();
        window.add(datePicker);
        window.add(new JLabel("The description :"));
        userInputDescription = new JTextField();
        window.add(userInputDescription);

        JPanel type = new JPanel();
        type.setLayout(new GridLayout(0, 1));
        window.add(type);
        dayType.addActionListener(e -> importantDaySpecs(type));
        importantDaySpecs(type);
        add(window);
        JPanel buttonWindow = buttonsWindow();
        add(buttonWindow);
    }

    /**
     * Configures program window.
     */
    private void shape() {
        setSize(450, 350);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Gets the specification for what type of an important day the event is
     * @param type
     */
    private void importantDaySpecs(JPanel type){
        type.removeAll();
        String importantDayType = (String)dayType.getSelectedItem();
        switch (importantDayType){
            case ANNIVERSARY:
                type.add(new JLabel("Location of the anniversary:"));
                userInputLocation = new JTextField();
                type.add(userInputLocation);
                break;
            case BIRTHDAY:
                type.add(new JLabel("Name of the birthday person:"));
                userInputBirthdayName = new JTextField();
                type.add(userInputBirthdayName);
                break;
            case OCCASION:
                type.add(new JLabel("Frequency of the occasion:"));
                userInputFrequncy = new JTextField();
                type.add(userInputFrequncy);
                break;
        }
        type.revalidate();
        type.repaint();
    }

    /**
     * Makes "Create Event" and "Cancel" buttons for the window
     * so users can easily manuever through the program.
     */
    private JPanel buttonsWindow() {
        JPanel buttonWindow = new JPanel();
        create = new JButton("Create Event");
        buttonWindow.add(create);
        cancel = new JButton("Cancel");
        buttonWindow.add(cancel);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createEvent(e);
            }
        });
        
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        return buttonWindow;
    }

    /**
     * Gets an important day.
     */
    public ImportantDay getImportantDay(){
        return day;
    }

    /**
     * Checks if the name format is correct.
     * Error if there is no name for an important days.
     */
    private boolean checkName(String name){
        if (name.isEmpty()){
            JOptionPane.showMessageDialog(this, "Important day must have a name.", 
                                            "Name Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    /**
     * Checks if the date format is correct.
     * Error if data doesn't exist.
     */
    private boolean checkDate(LocalDate date){
        if (date == null){
            JOptionPane.showMessageDialog(this, "Important day must have a date.", 
                                            "Date Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    /**
     * Error if the anniversary location doesn't exist.
     */
    private boolean checkLocation(String location){
        if (location.isEmpty()){
            JOptionPane.showMessageDialog(this, "An anniversary must have a location", 
                                            "Location Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    /**
     * Error if the birthday individual's name doesn't exist.
     */
    private boolean checkBirthday(String birthdayPerson){
        if (birthdayPerson.isEmpty()){
            JOptionPane.showMessageDialog(this, "A birthday requires an individuals name.",
                                    "Person Name Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    /**
     * Error if the occasion frequency doesn't exist.
     */
    private boolean checkFrequency(String frequencyStr){
        if (frequencyStr.isEmpty()){
            JOptionPane.showMessageDialog(this, 
            "An occasion must include its frequency of how often it occurs.", 
            "Frequency Erorr", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            int frequency = Integer.parseInt(frequencyStr);
            if (frequency <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "An occasion must have a frequency of 1 or more.",
                    "Frequency Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "An occasion must have a frequency of 1 or more", 
                "Frequency Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Creates a new important day for an anniversary, birthday or occasion.
     */
    private void createEvent(ActionEvent e){
        String name = userInputName.getText();
        String description = userInputDescription.getText();
        LocalDate date = datePicker.getDate();
        String importantDayType = (String)dayType.getSelectedItem();
        if (!checkName(name) || !checkDate(date)){
            return;
        }
        switch (importantDayType){
            case ANNIVERSARY:
                String location = userInputLocation.getText();
                if (!checkLocation(location)){
                    return;
                }
                day = new Anniversary(name, date.getMonthValue(), date.getDayOfMonth(), 
                                        date.getYear(), description, location);
                break;
            case BIRTHDAY:
                String birthdayPerson = userInputBirthdayName.getText();
                if (!checkBirthday(birthdayPerson)){ 
                    return;
                }
                day = new Birthday(name, date.getMonthValue(), date.getDayOfMonth(), 
                                        date.getYear(), description, birthdayPerson);
                break;
            case OCCASION:
                String frequencyStr = userInputFrequncy.getText();
                if (!checkFrequency(frequencyStr)){ 
                    return;
                }
                int frequency = Integer.parseInt(frequencyStr);
                day = new Occasion(name, date.getMonthValue(), date.getDayOfMonth(),
                                             date.getYear(), description, frequency);
                break;
        }
        dispose();
    }
}