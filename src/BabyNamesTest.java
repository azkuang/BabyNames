import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Alex Kuang - Testing for Baby Names part 3
 */

 public class BabyNamesTest {
    StartProgram startProgram;
    AnswerLogic answerLogic;

    @BeforeAll
    public void getBabyData() {
        DataStore dataStore = startProgram.startTest();
        OutputAnswers outputAnswers = new OutputAnswers(dataStore, answerLogic);
    }

    @Test
    public void testAllNameGenderRanking() {
        
    }
 }