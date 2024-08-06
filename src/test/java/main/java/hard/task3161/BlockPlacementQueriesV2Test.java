package main.java.hard.task3161;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlockPlacementQueriesV2Test {

    @Test
    void getResults() {
        BlockPlacementQueriesV2 testClass = new BlockPlacementQueriesV2();

        List<Boolean> result, expectedResult;

        result = testClass.getResults(new int[][]{{1,2},{2,3,3},{2,3,1},{2,2,2}});
        expectedResult = new ArrayList<>(List.of(false, true, true));
        assertEquals(expectedResult, result);

        result = testClass.getResults(new int[][]{{1,7},{2,7,6},{1,2},{2,7,5},{2,7,6}});
        expectedResult = new ArrayList<>(List.of(true, true, false));
        assertEquals(expectedResult, result);

        result = testClass.getResults(new int[][]{{1,4},{2,1,2}});
        expectedResult = new ArrayList<>(List.of(false));
        assertEquals(expectedResult, result);

        result = testClass.getResults(new int[][]{{2,4,4},{1,1},{2,4,7}});
        expectedResult = new ArrayList<>(List.of(true, false));
        assertEquals(expectedResult, result);

        result = testClass.getResults(new int[][]{{2,1,2}});
        expectedResult = new ArrayList<>(List.of(false));
        assertEquals(expectedResult, result);

        result = testClass.getResults(new int[][]{{1,5},{1,4},{2,4,2},{2,3,4}});
        expectedResult = new ArrayList<>(List.of(true, false));
        assertEquals(expectedResult, result);

        result = testClass.getResults(new int[][]{{1,5},{1,7},{2,4,11},{2,7,3}});
        expectedResult = new ArrayList<>(List.of(false, true));
        assertEquals(expectedResult, result);


    }
}