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
        String name = "Mary";
        String gender = "F";

        Map<Integer, Integer> testMap = new HashMap<>();
        testMap.put(1880, 1);
        testMap.put(1881, 1);
        testMap.put(1882, 1);
        testMap.put(1883, 1);
        testMap.put(1884, 1);

        assertEquals(testMap, outputAnswers.getAllRankings(name, gender));
    }
 }