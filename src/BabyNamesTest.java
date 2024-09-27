import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.platform.reporting.shadow.org.opentest4j.reporting.events.core.Data;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Alex Kuang - Testing for Baby Names part 3
 */

 public class BabyNamesTest {
    private static OutputAnswers outputAnswers;
    private static String TEST_FILE = "names_test";

    @BeforeAll
    public static void getBabyData() {
        DataStore dataStore = new DataStore();

        String filePath = TEST_FILE;

        try {
            dataStore.loadData(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AnswerLogic answerLogic = new AnswerLogic(dataStore);
        outputAnswers = new OutputAnswers(dataStore, answerLogic);
    }

    @Test
    public void testAllNameGenderRanking() {
        // Test with exisitng name
        String name = "Mary";
        String gender = "F";

        Map<Integer, Integer> testMap = new HashMap<>();
        testMap.put(1880, 1);
        testMap.put(1881, 2);
        testMap.put(1882, 1);
        testMap.put(1883, 3);
        testMap.put(1884, 1);

        assertEquals(testMap, outputAnswers.getAllRankings(name, gender));

        // Test if name is not in the data
        String name2 = "Kelly";
        String gender2 = "F";

        Map<Integer, Integer> testMap2 = new HashMap<>();
        testMap2.put(1880, 0);
        testMap2.put(1881, 0);
        testMap2.put(1882, 0);
        testMap2.put(1883, 0);
        testMap2.put(1884, 0);

        assertEquals(testMap2, outputAnswers.getAllRankings(name2, gender2));
    }

    @Test
    public void testNameGenderRankInYearRange() {
        // Test with exisiting name
        String name = "Emma";
        String gender = "F";
        int startYear = 1880;
        int endYear = 1882;

        Map<Integer, Integer> testMap = new HashMap<>();
        testMap.put(1880, 3);
        testMap.put(1881, 4);
        testMap.put(1882, 2);

        assertEquals(testMap, outputAnswers.getRankingFromRangeOfYears(name, gender, startYear, endYear));

        // Test with name that is not in the data
        String name2 = "Kelly";
        String gender2 = "F";
        int startYear2 = 1880;
        int endYear2 = 1882;

        Map<Integer, Integer> testMap2 = new HashMap<>();
        testMap2.put(1880, 0);
        testMap2.put(1881, 0);
        testMap2.put(1882, 0);

        assertEquals(testMap2, outputAnswers.getRankingFromRangeOfYears(name2, gender2, startYear2, endYear2));
    }

    @Test
    public void testNameGenderWithSameRank() {
        String name = "Elizabeth";
        String gender = "F";

        String expectedAnswer = "Anna, F has the same rank as " + name + ", " +  gender + ".";

        assertEquals(expectedAnswer, outputAnswers.nameGenderPairWithSameRank(name, gender, 1880));

        String name2 = "Kim";
        String gender2 = "F";

        String expectedAnswer2 = "No name/gender pair with the same rank";

        assertEquals(expectedAnswer2, outputAnswers.nameGenderPairWithSameRank(name2, gender2, 1880));
    }

    @Test
    public void testAverageRank() {
        String name = "Emma";
        String gender = "F";
        int startYear = 1881;
        int endYear = 1883;

        int expectedAnswer = 3;

        assertEquals(expectedAnswer, outputAnswers.averageRankOfNameGender(name, gender, startYear, endYear));
    }

    @Test
 }