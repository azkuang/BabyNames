import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Set;

import org.junit.platform.reporting.shadow.org.opentest4j.reporting.events.core.Data;

import java.util.Collections;

/**
 * Alex Kuang - Return answers
 */

public class OutputAnswers extends DataStore {
    private AnswerLogic answerLogic;
    private DataStore dataStore;

    public OutputAnswers(DataStore dataStore, AnswerLogic answerLogic) {
        this.dataStore = dataStore;
        this.answerLogic = answerLogic;
    }

    // Debug method
    // public static void main(String[] args) {
    //     // Create constructor for the DataStore class
    //     DataStore dataStore = new DataStore();
    //     // Define filePath where files are located
    //     String filePath = "names_test";

    //     try {
    //         dataStore.loadData(filePath);
    //     } catch (FileNotFoundException e) {
    //         System.out.println("File not found");
    //     }

    //     AnswerLogic answerLogic = new AnswerLogic(dataStore);

    //     OutputAnswers outputAnswers = new OutputAnswers(dataStore, answerLogic);
    //     String res = outputAnswers.nameGenderPairWithSameRank("Elizabeth", "F", 1880);
    //     System.out.println(res);
    // }

    // Returns all rankings of name/gender pairs
    public Map<Integer, Integer> getAllRankings(String name, String gender) {
        Map<Integer, Map<String, List<BabyData>>> mapByYearAndGender = dataStore.getMapByYearAndGender();
        // Map to hold all possible years
        Set<Integer> years = mapByYearAndGender.keySet();

        // Map to store rankings for each year
        Map<Integer, Integer> allNameGenderRank = new HashMap<>();

        // Loop through all the years
        for (Integer year : years) {
            int rank = answerLogic.answerRankForName(name, gender, year);
            allNameGenderRank.put(year, rank);
        }

        return allNameGenderRank;
    }

    // Return the rankings of name/gender pairs within a range of years
    public Map<Integer, Integer> getRankingFromRangeOfYears(String name, String gender, int startYear, int endYear) {
        Map<Integer, Integer> nameGenderRankInRange = new HashMap<>();

        for (int year = startYear; year <= endYear; year++) {
            int rank = answerLogic.answerRankForName(name, gender, year);
            nameGenderRankInRange.put(year, rank);
        }

        return nameGenderRankInRange;
    }

    // Returns the rank of the name/gender pair in a given year, then finds a name/gender pair with the same ranking in the most recent year
    public String nameGenderPairWithSameRank(String name, String gender, int year) {
        Map<Integer, Map<String, List<BabyData>>> mapByYearAndGender = dataStore.getMapByYearAndGender();
    
        int mostRecentYear = Collections.max(mapByYearAndGender.keySet());
        int currentRank = answerLogic.answerRankForName(name, gender, year);
        List<BabyData> babyData = dataStore.getDataByYearAndGender(mostRecentYear, gender);
        
        int rank = 0;
        int prevNameFrequency = 0;

        for (BabyData babyName : babyData) {
            // If the frequency of the current name differs from the previous name, increment the rank
            if (babyName.getNameFrequency() != prevNameFrequency) {
                rank++;
            } 
            // Ensure the rank starts from 1 even if the list starts with zero frequency
            else if (rank == 0) {
                rank++;
            }
            // If the current name matches the name we are looking for, return its rank
            if (rank == currentRank) {
                return babyName.getName() + ", " + babyName.getGender() + " has the same rank as " + name + ", " + gender + ".";
            }
            // Update the previous name frequency for the next iteration
            prevNameFrequency = babyName.getNameFrequency();
        }

        return "No name/gender pair with the same rank";
    }

    // Returns the average rank in a range of years given a name/gender pair
    public int averageRankOfNameGender(String name, String gender, int startYear, int endYear) {
        int totalRank = 0;
        int count = 0;

        for (int year = startYear; year <= endYear; year++) {
            int rank = answerLogic.answerRankForName(name, gender, year);
            totalRank += rank;

            // Skip if there is no rank found for given name, gender, and year
            if (rank != 0) {
                count++;
            }
        }

        return totalRank / count;
    }

    // Returns the most popular first letter of a name, then all names with that letter in alphabetical order
    public PopularLetterAndNames mostPopularLetterInName(String gender, int startYear, int endYear) {
        Map<Character, Integer> letterFrequency = new HashMap<>();
        Map<Character, List<String>> namesByLetter = new HashMap<>();

        for (int year = startYear; year <= endYear; year++) {
            List<BabyData> babyData = dataStore.getDataByYearAndGender(year, gender);

            for (BabyData babyName : babyData) {
                char firstLetter = babyName.getName().toUpperCase().charAt(0);
                
                // Counts the frequency of first letters in the names
                letterFrequency.put(firstLetter, letterFrequency.getOrDefault(firstLetter, 0) + 1);

                // Add names with the first letter into the hashmap
                namesByLetter.computeIfAbsent(firstLetter, k -> new ArrayList<>()).add(babyName.getName());
            }
        }

        int maxValue = -1;
        char mostPopularLetter = '\0';

        // Find the most popular first letter
        for (char key : letterFrequency.keySet()) {
            int value = letterFrequency.get(key);
            if (value > maxValue) {
                maxValue = value;
                mostPopularLetter = key;
            }
        }

        List<String> namesStartingWithMostPopularLetter = namesByLetter.get(mostPopularLetter);
        Collections.sort(namesStartingWithMostPopularLetter);

        return new PopularLetterAndNames(mostPopularLetter, namesStartingWithMostPopularLetter);
    }
    
}
