/**
 * Alex Kuang - Anwser Logic class
 */

import java.util.List;

public class AnswerLogic extends DataStore {
    private final DataStore dataStore;

    // Constructor
    public AnswerLogic(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    // Test code
    // public static void main(String[] args) {
    //     DataStore dataStore = new DataStore();
    //     AnswerLogic answerLogic = new AnswerLogic(dataStore);
    //     answerLogic.getMostPopularName("M", 1800);
    // }

    // Method to search through maps to find the most popular name based on gender and year
    public String answerMostPopularName(String gender, int year) {
        // Retrieve the list of BabyData for the specified year and gender from the data store
        List<BabyData> babyData = dataStore.getDataByYearAndGender(year, gender);
        // Check if the list of BabyData is not empty
        if (!babyData.isEmpty()) {
            // Assume the most popular name is the first entry in the list (sorted by popularity)
            BabyData mostPopular = babyData.get(0);
            // Return the details of the most popular name along with its frequency and rank
            return "The name " + mostPopular.name + ", gender " + mostPopular.gender + ", in the year " + year + 
                ", occurred with frequency " + mostPopular.nameFrequency + ", and rank 1";
        } else {
            // If no data is available for the given gender and year, return a default message
            return "No data available for the given gender and year.";
        }
    }

    // Method to display the rank for a given name, gender, and year
    public String answerRankForName(String name, String gender, int year) {
        // Retrieve the list of BabyData for the specified year and gender from the data store
        List<BabyData> babyData = dataStore.getDataByYearAndGender(year, gender);
        // Initialize variables to track rank and previous name frequency
        int rank = 0;
        int prevNameFrequency = 0;
        // Loop through each BabyData record in the list
        for (BabyData babyName : babyData) {
            // If the frequency of the current name differs from the previous name, increment the rank
            if (babyName.nameFrequency != prevNameFrequency) {
                rank++;
            } 
            // Ensure the rank starts from 1 even if the list starts with zero frequency
            else if (rank == 0) {
                rank++;
            }
            // If the current name matches the name we are looking for, return its frequency and rank
            if (babyName.name.equalsIgnoreCase(name)) {
                return "The name " + babyName.name + ", gender " + babyName.gender +
                    ", occurred with frequency " + babyName.nameFrequency + ", and rank " + rank;
            }
            // Update the previous name frequency for the next iteration
            prevNameFrequency = babyName.nameFrequency;
        }
        // If the name is not found in the list, return a message indicating no data is available
        return "No data available for the given name, gender and year.";
    }

    // Method to display the year with the most popular name and gender combo
    public String answerMostPopularYearforName(String name, String gender) {
        // Retrieve the most popular year for the specified name and gender from the data store
        Integer mostPopularYear = dataStore.getMostPopularYearForName(name, gender);
        // If a most popular year is found, proceed with data processing
        if (mostPopularYear != null) {
            // Initialize variables to track rank and previous name frequency
            int rank = 0;
            int prevNameFrequency = 0;
            // Retrieve the list of BabyData for the most popular year and specified gender
            List<BabyData> babyData = dataStore.getDataByYearAndGender(mostPopularYear, gender);
            // Loop through each BabyData record in the list
            for (BabyData babyName : babyData) {
                // If the frequency of the current name differs from the previous name, increment the rank
                if (babyName.nameFrequency != prevNameFrequency) {
                    rank++;
                } 
                // Ensure the rank starts from 1 even if the list starts with zero frequency
                else if (rank == 0) {
                    rank++;
                }
                // If the current name matches the name we're looking for, return the frequency and rank
                if (babyName.name.equalsIgnoreCase(name)) {
                    return "The name " + name + ", gender " + gender + ", in the year " + mostPopularYear +
                        ", occurred with frequency " + babyName.nameFrequency + ", and rank " + rank + ".";
                }
                // Update the previous name frequency for the next iteration
                prevNameFrequency = babyName.nameFrequency;
            }
        }
        // If no data is found, return a message indicating no data is available
        return "No data available for given name and gender";
    }

}
