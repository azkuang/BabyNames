/**
 * Alex Kuang
 * Start of the program
 */

import java.io.FileNotFoundException;

public class StartProgram {
    // Instance variables
    private String TEST_FILE = "names_test";
    private String PROGRAM_FILE = "names";

    public void start() {
        // Create constructor for the DataStore class
        DataStore dataStore = new DataStore();
        // Define filePath where files are located
        String filePath = TEST_FILE;

        try {
            dataStore.loadData(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        AnswerLogic answerLogic = new AnswerLogic(dataStore);
        UserInterface userInterface = new UserInterface(answerLogic);
        userInterface.start();
    }
}
