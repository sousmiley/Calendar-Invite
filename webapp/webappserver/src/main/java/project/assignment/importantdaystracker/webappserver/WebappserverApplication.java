package project.assignment.importantdaystracker.webappserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Student name : Soumya Parmar
// Student ID : 301558406

/*
 * Built up from my original Assignment 1, 2, and 3 code.
 * Resources (Cumulative) : 
 * - Textbook sections : Overall Chapter 4 and Chapter 5, 
 *      sections 5.4, 5.8, 4.10 were most reviewed
 * - https://github.com/Jawad-Jahangir-Soomro/Java_Journey_16_Layout_Managers
 * - https://www.youtube.com/watch?v=yQ2KFQ_Pdsg
 * - Week 9 : GUI in Java
 * - https://docs.oracle.com/javase/tutorial/uiswing/layout/index.html
 * - https://www.geeksforgeeks.org/java-swing-jpanel-with-examples/?ref=next_article_top
 * - https://github.com/RochesterinNYC/1004-Java-GUI-Tutorial
 * - https://www.baeldung.com/gson-save-file
 * - "GSON: LocalDate Adapter" by Dr. Brian Fraser
 *      https://www.youtube.com/watch?v=j2vWhohxBVA
 * - https://javadoc.io/doc/com.github.lgooddatepicker/LGoodDatePicker/latest/com/github/lgood
 * datepicker/components/DatePicker.html
 * - https://docs.oracle.com/javase/tutorial/uiswing/events/windowlistener.html
 * - https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
 * 
 * The following links were quite long and would go above the 100 char limit.
 * In order to search it, copy the "domain url" to your search engine 
 * and then copy the secon part (url subdirectory path) of whichever source.
 * - domain url : https://stackoverflow.com/questions/
 *    - 33007303/using-an-iterator-with-an-enhanced-for-loop-in-java
 *    - 29091859/java-how-to-show-an-input-dialog-having-a-dropdown-list-with-an-icon-for-each
 *    - 40372121/how-to-insertion-sort-an-arraylist-of-strings-alphabetically
 *    - 22003431/insertion-sort-with-an-array-of-objects
 *    - 29319434/how-to-save-data-with-gson-in-a-json-file
 *    - 76441504/convert-json-string-to-object-with-gson
 */

/**
 * Webserver application for program
 */
// Don't touch this stuff
@SpringBootApplication
public class WebappserverApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(WebappserverApplication.class, args);
	}

}
// Come here and do java run
// curl -X GET localhost:8080/hello