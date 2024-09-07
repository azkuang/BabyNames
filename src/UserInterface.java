import java.util.Scanner;

public class UserInterface {
    // Create constructor for AnswerLogic class
    private final AnswerLogic answerLogic;
    private final Scanner scanner;  

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

    // Use methods from AnswerLogic class to output answers based on user response
    private void handleMostPopularName() {
        System.out.println("Enter gender as a captial letter.");
        String gender = getUserInput().trim();
        System.out.println("Enter the year.");
        int year = Integer.parseInt(getUserInput().trim());
        String results = answerLogic.getMostPopularName(gender, year);
        System.out.println(results);
    };

    private void handleRank() {
        System.out.println("Enter the name.");
        String name = getUserInput().trim();
        System.out.println("Enter the gender as a captial letter.");
        String gender = getUserInput().trim();
        System.out.println("Enter the year.");
        int year = Integer.parseInt(getUserInput().trim());
        String results = answerLogic.getRankForName(name, gender, year);
        System.out.println(results);
    };

    private void handleMostPopularYear() {
        System.out.println("Enter the name.");
        String name = getUserInput().trim();
        System.out.println("Enter the gender as a captial letter");
        String gender = getUserInput().trim();
        String results = answerLogic.getMostPopularYearforName(name, gender);
        System.out.println(results);
    };
}
