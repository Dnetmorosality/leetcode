package main.easy.task121;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        Map<Integer, List<Integer>> dependencies = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            dependencies.put(i, new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            int a = sc.nextInt();
            for (int j = 0; j < a; j++) {
                int dep = sc.nextInt();
                dependencies.get(i).add(dep);
            }
        }

        // Calculate dependencies count for each process
        Map<Integer, Integer> depCount = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            depCount.put(i, dependencies.get(i).size());
        }

        // Find processes with no dependencies
        List<Integer> noDependencies = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (depCount.get(i) == 0) {
                noDependencies.add(i);
            }
        }

        // Sort processes with no dependencies in ascending order
        Collections.sort(noDependencies);

        // Create a queue for processing
        Queue<Integer> queue = new LinkedList<>(noDependencies);

        // Initialize result list of process sets
        List<List<Integer>> result = new ArrayList<>();

        // Process dependencies and create sets
        while (!queue.isEmpty()) {
            List<Integer> currentSet = new ArrayList<>();
            while (!queue.isEmpty()) {
                int process = queue.poll();
                currentSet.add(process);
                // Decrease dependency count for dependent processes
                for (int dep : dependencies.get(process)) {
                    if (depCount.get(dep) > 0) {
                        depCount.put(dep, depCount.get(dep) - 1);
                        if (depCount.get(dep) == 0) {
                            queue.offer(dep);
                        }
                    }
                }
            }
            // Sort processes in the current set in ascending order
            Collections.sort(currentSet);
            result.add(currentSet);
        }

        // Print the result
        System.out.println(result.size());
        for (List<Integer> set : result) {
            System.out.println(set.size() + " " + String.join(" ", set.toString()));
        }
    }
}