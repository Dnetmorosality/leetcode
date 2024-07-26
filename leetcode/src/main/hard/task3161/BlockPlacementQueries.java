package main.hard.task3161;

import java.util.*;

public class BlockPlacementQueries {
    public static void main(String[] args) {
//        [[1,6],[1,1],[2,7,5]][true]
//        [[2,5,1],[1,3],[2,6,4]][true,false]
//        [[1,1],[1,5],[1,13],[1,14],[2,12,8]][false]
//        [[1,3],[2,4,12],[2,16,29],[2,1,20],[1,28],[2,27,11],[2,10,9],[1,9],[1,25],[2,2,3]][false,false,false,true,true,false]
//        var queries = new int[][]{{1,7},{2,7,6},{1,2},{2,7,5},{2,7,6}};
//        var queries = new int[][]{{2, 1, 2}};
//        var queries = new int[][]{{1,2},{2,3,3},{2,3,1},{2,2,2}};
//        var queries = new int[][]{{1,3},{2,4,2}};
//        var queries = new int[][]{{1, 6}, {1, 1}, {2, 7, 5}};
//        var queries = new int[][]{{2,5,1},{1,3},{2,6,4}};
        var queries = new int[][]{{1,1},{1,5},{1,13},{1,14},{2,12,8}};
        int[] axis = new int[450000];
        List<Boolean> bool = new ArrayList<>();
        int sum = 0;
        int countFree = 0;
        int maxFreeSpace = 0;
        int maxPosition = 0;
        for (int[] q : queries) {
            if (q[0] == 1) {
                axis[q[1]] = 1;
                maxFreeSpace = 0;
                if (maxPosition < q[1]) {
                    maxPosition = q[1];
                }
            } else if (q[1] < q[2]) {
                bool.add(false);
            } else {
                for (int i = 1; i <= Math.min(maxPosition, q[1]); i++) {
                    while (axis[i] == 0 && i <= q[1]-1) {
                        countFree++;
                        i++;
                    }
                    if (maxFreeSpace < countFree) {
                        maxFreeSpace = countFree;
                    }
                    countFree = 0;
                }
                if (maxFreeSpace >= q[2] - 1) {
                    bool.add(true);
                } else {
                    for (int i = q[1] - q[2] + 1; i <= q[1] - 1; i++) {
                        sum = sum + axis[i];
                    }
                    bool.add(sum == 0);
                    sum = 0;
                }
            }
        }
        System.out.println(bool);
    }
}
