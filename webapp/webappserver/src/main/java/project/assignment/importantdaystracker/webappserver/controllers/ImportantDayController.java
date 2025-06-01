package project.assignment.importantdaystracker.webappserver.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import project.assignment.importantdaystracker.webappserver.control.ImportantDayManager;
import project.assignment.importantdaystracker.webappserver.model.*;
/**
 * Rest controller for the claendar system.
 * Each function is from assignment.
 * @author Soumya Parmar
*/
// Tag it saying its a rest controller
@RestController
public class ImportantDayController {
    private final ImportantDayManager manageDays = new ImportantDayManager();

    @GetMapping("/ping")
    public String ping() {
        return "System is up!";
    }

    @GetMapping("/listAll")
    public ImportantDayResponse listAll() {
        return manageDays.listAll();
    }

    @PostMapping("/addAnniversary")
    @ResponseStatus(HttpStatus.CREATED)
    public ImportantDayResponse addAnniversary(@RequestBody Anniversary anniversary) {
        manageDays.addDay(anniversary);
        return manageDays.listAll();
    }

    @PostMapping("/addBirthday")
    @ResponseStatus(HttpStatus.CREATED)
    public ImportantDayResponse addBirthday(@RequestBody Birthday birthday) {
        manageDays.addDay(birthday);
        return manageDays.listAll();
    }

    @PostMapping("/addOccasion")
    @ResponseStatus(HttpStatus.CREATED)
    public ImportantDayResponse addOccasion(@RequestBody Occasion occasion) {
        manageDays.addDay(occasion);
        return manageDays.listAll();
    }

    @PostMapping("/removeDay")
    @ResponseStatus(HttpStatus.OK)
    public ImportantDayResponse removeDay(@RequestBody Map<String, Integer> data) {
        int id = data.get("id");
        manageDays.removeDay(id);
        return manageDays.listAll();
    }

    @GetMapping("/listPassedDaysThisYear")
    public List<ImportantDay> listPassedDays() {
        return manageDays.getPastDaysThisYear();
    }

    @GetMapping("/listUpcomingDaysThisYear")
    public List<ImportantDay> listUpcomingDays() {
        return manageDays.getUpcomingDaysThisYear();
    }

    @GetMapping("/listDaysThisYear")
    public List<ImportantDay> listDaysThisYear(@RequestParam String type) {
        int currentYear = LocalDate.now().getYear();
        return manageDays.getDaysByTypeAndYear(type, currentYear);
    }


    @GetMapping("/exit")
    public String exit() {
        manageDays.saveData();
        return "Saved list.json successfully!";
    }
}

/*
    curl -X GET http://localhost:8080/listAll
*/
/*
    curl -X GET http://localhost:8080/listPassedDaysThisYear
*/
/*
    curl -X GET http://localhost:8080/listUpcomingDaysThisYear
*/
/*
    curl -X GET "http://localhost:8080/listDaysThisYear?type=Birthday" -H "Content-Type: application/json"
*/

/*
    curl -X POST http://localhost:8080/addAnniversary \
    -H "Content-Type: application/json" \
    -d '{
    "name": "Alice and Bob’s Wedding Anniversary",
    "month": 5,
    "day": 20,
    "year": 2025,
    "description": "Alice and Bob got hitched!",
    "location": "Vancouver, BC"
    }'

    curl -X POST http://localhost:8080/addAnniversary \
    -H "Content-Type: application/json" \
    -d '{
    "name": "Tester3",
    "month": 5,
    "day": 20,
    "year": 2025,
    "description": "Alice and Bob got hitched!",
    "location": "Vancouver, BC"
    }'

    curl -X POST http://localhost:8080/addAnniversary \
    -H "Content-Type: application/json" \
    -d '{
    "name": "Event 2",
    "month": 8,
    "day": 16,
    "year": 1996,
    "description": "Wow an event!",
    "location": "Antarctica"
    }'
    
    curl -X POST http://localhost:8080/addBirthday \
    -H "Content-Type: application/json" \
    -d '{
    "name": "Lucy'\''s Birthday",
    "month": 12,
    "day": 12,
    "year": 1950,
    "description": "Lucy was born",
    "person": "Lucy Lastname"
    }'

    curl -X POST http://localhost:8080/addBirthday \
    -H "Content-Type: application/json" \
    -d '{
    "name": "Bobs Birthday",
    "month": 2,
    "day": 21,
    "year": 1921,
    "description": "Woaaah, its bobs birthday whaaat",
    "person": "Renata"
    }'

    curl -X POST http://localhost:8080/addOccasion \
    -H "Content-Type: application/json" \
    -d '{
    "name": "Aliens took over Mars",
    "month": 10,
    "day": 25,
    "year": 2025,
    "description": "Hey wait a minute, this doesnt seem right...",
    "frequency": "1"
    }'

    curl -X POST http://localhost:8080/addOccasion \
    -H "Content-Type: application/json" \
    -d '{
    "name": "Dentist Appt",
    "month": 3,
    "day": 17,
    "year": 2008,
    "description": "My teeth are so perfect, I only go once a year!",
    "frequency": "3"
    }'
*/
/*
    curl -X POST http://localhost:8080/removeDay \
    -H "Content-Type: application/json" \
    -d '{"id": 1}'
*/

/*
    curl -X GET http://localhost:8080/exit
*/