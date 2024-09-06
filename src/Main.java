import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws Exception {
        // Create constructor for the DataStore class
        DataStore dataStore = new DataStore();
        // Define filePath where files are located
        String filePath = "names_test";

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
