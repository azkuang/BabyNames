import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * Alex Kuang - Return answers
 */

public class OutputAnswers extends DataStore {
    private AnswerLogic answerLogic;
    private DataStore dataStore;

    // Instance variables
    private String TEST_FILE = "names_test";
    private String PROGRAM_FILE = "names";

    private Map<Integer, Map<String, List<BabyData>>> mapByYearAndGender = dataStore.getMapByYearAndGender();
    private Map<String, Map<String, Integer>> mostPopularYearForNameGender = dataStore.getMapMostPopularYearForNameGender();

    public OutputAnswers(DataStore dataStore, AnswerLogic answerLogic) {
        this.answerLogic = new AnswerLogic(dataStore);
    }

    // Returns all rankings of name/gender pairs
    public Map<Integer, Integer> getAllRankings(String name, String gender) {

        // Map to store rankings for each year
        Map<Integer, Integer> allNameGenderRank = new HashMap<>();
        // Map to hold all possible years
        Set<Integer> years = mapByYearAndGender.keySet();

        // Loop through all the years
        for (Integer year : years) {
            List<BabyData> babyData = dataStore.getDataByYearAndGender(year, gender);

            int rank = 0;
            int prevNameFrequency = -1;
            
            // Loop through the baby names to find the rank of the given name
            for (BabyData babyName : babyData) {
                // Only increment rank if the frequency is different from the previous name's frequency
                if (babyName.nameFrequency != prevNameFrequency) {
                    rank ++;
                }

                // If the baby name matches what we are looking for, store the rank
                if (babyName.name.equalsIgnoreCase(name)) {
                    allNameGenderRank.put(year, rank);
                }

                // Updates previous frequency
                prevNameFrequency = babyName.nameFrequency;
            }
        }

        return allNameGenderRank;
    }
    
}
