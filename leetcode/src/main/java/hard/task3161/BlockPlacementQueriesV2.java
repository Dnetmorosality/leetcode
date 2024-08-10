package main.java.hard.task3161;

import java.util.ArrayList;
import java.util.List;

public class BlockPlacementQueriesV2 {
    private static class Node {
        Node left;
        Node right;
        int l;
        int r;
        int size;

        public Node(int l, int r, int size) {
            this.l = l;
            this.r = r;
            this.size = size;
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        List<Boolean> result = new ArrayList<>();
        int maxR = 0;
        for (int[] q : queries) {
            if (q[0] == 1) {
                maxR = Math.max(q[1], maxR);
            }
        }
        Node root = new Node(0, maxR, maxR);
        int tempBorder = 0;
        for (int[] q : queries) {
            if (q[0] == 2) {
                if (q[2] > q[1]) {
                    result.add(false);
                } else if (tempBorder <= q[1] - q[2]) {
                    result.add(true);
                } else {
                    result.add(hasFreeBox(root, q[1], q[2]));
                }
            } else {
                addNode(root, q[1]);
                tempBorder = tempBorder == maxR ? maxR : Math.max(q[1], tempBorder);
            }
        }
        return result;
    }

    private void addNode(Node node, int val) {
        if (val == node.r) return;
        if (val < node.r) {
            if (node.left == null) {
                node.left = new Node(node.l, val, val - node.l);
                node.right = new Node(val, node.r, node.r - val);
            } else {
                if (val < node.left.r) {
                    addNode(node.left, val);
                } else {
                    addNode(node.right, val);
                }
            }
        }
    }

    private boolean hasFreeBox(Node root, int q1, int q2) {
        List<Node> leaves = collectLeaves(root, q1, q2);
        for (Node leaf : leaves) {
            if (leaf.size >= q2) {
                return true;
            }
        }
        return false;
    }

    private List<Node> collectLeaves(Node root, int border, int size) {
        List<Node> leaves = new ArrayList<>();
        int leftBorder = border - size;
        collectLeavesHelper(root, leaves, size, leftBorder);
        return leaves;
    }

    private void collectLeavesHelper(Node node, List<Node> leaves, int size, int leftBorder) {
        if (node.size >= size && leftBorder >= node.l) {
            if (node.left == null) {
                leaves.add(node);
            } else {
                collectLeavesHelper(node.left, leaves, size, leftBorder);
                collectLeavesHelper(node.right, leaves, size, leftBorder);
            }
        }
    }
}
