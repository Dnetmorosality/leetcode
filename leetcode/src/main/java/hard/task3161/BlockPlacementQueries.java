package main.java.hard.task3161;

import main.java.easy.task121.ListTest;

import java.util.ArrayList;
import java.util.List;

public class BlockPlacementQueries {
    public List<Boolean> getResults(int[][] queries) {
        boolean[] axis = new boolean[50000];
        List<Boolean> result = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                axis[q[1]] = true;
            } else if (q[1] < q[2]) {
                result.add(false);
            } else {
                result.add(hasFreeSpace(axis, q[1], q[2]));
            }
        }
        return result;
    }

    private static boolean hasFreeSpace(boolean[] axis, int limit, int size) {
        int count = 0;
        for (int i = 0; i <= size; i++) {
            if (axis[i]) {
                break;
            } else {
                count++;
                if (count == size) {
                    return true;
                }
            }
        }
        for (int i = count; i < limit; i++) {
            if (axis[i]) {
                count = 1;
            } else {
                count++;
                if (count == size) return true;
            }
        }
        return false;
    }
}

