/**
 * Alex Kuang - User Interface Class
 */

import java.util.Arrays;
import java.util.Scanner;

public class UserInterface {
    // Instance variables
    private AnswerLogic answerLogic;
    private Scanner scanner;
    private Integer year;
    private String gender;
    

    // Constructor
    public UserInterface(AnswerLogic answerLogic) {
        this.answerLogic = answerLogic;
        this.scanner = new Scanner(System.in);
    }

    // Handles user inputs and which methods to run
    public void start() {
        boolean quit = false;
        while (!quit) {
            displayQuestions();
            String command = getUserInput().trim().toUpperCase();
            quit = handleUserCommand(command);
        }
    }

    // Display all the questions the users can choose to query
    private void displayQuestions() {
        System.out.println("Choose an option:");
        System.out.println("P  Show most popular names for a given year and gender.");
        System.out.println("R  Show rank for a given name, gender, and year.");
        System.out.println("Y  Find the year in which the given name, gender combination was most popular.");
        System.out.println("Q  Quit");
    }

    // Gets next user input
    private String getUserInput() {
        return scanner.nextLine();
    }

    // Runs different searches based on user input
    private boolean handleUserCommand(String command) {
        switch (command) {
            case "P":
                handleMostPopularName();
                break;
            case "R":
                handleRank();
                break;
            case "Y":
                handleMostPopularYear();
                break;
            case "Q":
                System.out.println("Quiting...");
                return true;
            default:
                System.out.println("Invalid input...");
        }
        return false;
    }

    // method to print out questions
    private void questions() {
        System.out.println("Enter gender as a captial letter.");
        gender = getUserInput().trim();
        System.out.println("Enter the year.");

        try {
            year = Integer.parseInt(getUserInput().trim());
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid year.");
        }
    }

    // Use methods from AnswerLogic class to output answers based on user response
    private void handleMostPopularName() {
        questions();

        String mostPopularName = answerLogic.answerMostPopularName(gender, year);
        int nameFrequency = answerLogic.getNameFrequency(mostPopularName, gender ,year);
        System.out.println("The name " + mostPopularName + ", gender " + gender + ", in the year " + year + 
                ", occurred with frequency " + nameFrequency + ", and rank 1");
    };

    private void handleRank() {
        System.out.println("Enter the name.");
        String name = getUserInput().trim();

        questions();

        int rank = answerLogic.answerRankForName(name, gender, year);
        int nameFrequency = answerLogic.getNameFrequency(name, gender, year);

        if (rank == 0) {
            System.out.println("No data available for given name, gender, and year.");
        }

        System.out.println("The name " + name + ", gender " + gender +
                    ", occurred with frequency " + nameFrequency + ", and rank " + rank);
    };

    private void handleMostPopularYear() {
        System.out.println("Enter the name.");
        String name = getUserInput().trim();
        System.out.println("Enter the gender as a captial letter");
        String gender = getUserInput().trim();

        int[] mostPopularYearAndRank = answerLogic.answerMostPopularYearforName(name, gender);

        if (mostPopularYearAndRank[0] == 0 || 
            mostPopularYearAndRank[1] == 0 || 
            mostPopularYearAndRank[2] == 0) {
            System.out.println("No data available for given name and gender.");
        } else {
            System.out.println("The name " + name + ", gender " + gender + ", in the year " + mostPopularYearAndRank[0] +
            ", occurred with frequency " + mostPopularYearAndRank[2] + ", and rank " + mostPopularYearAndRank[1] + ".");
        }
        
    };
}
