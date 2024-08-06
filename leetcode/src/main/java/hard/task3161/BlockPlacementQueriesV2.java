package main.java.hard.task3161;

import java.util.ArrayList;
import java.util.List;

public class BlockPlacementQueriesV2 {
    static class Node {
        Node left, right;
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
        int maxRight = 0;
        for (int[] q : queries) {
            if (q[0] == 1) {
                maxRight = Math.max(q[1], maxRight);
            }
        }
        Node root = new Node(0, maxRight, maxRight);
        int tempBorder = 0;
        for (int[] q : queries) {
            if(q[0]!= 1 && tempBorder <= q[1] - q[2]) {
                result.add(true);
            } else {
                if (q[0] == 1) {
                    addNode(root, q[1]);
                    tempBorder = Math.max(q[1], tempBorder);
                } else {
                    result.add(hasFreeBox(root, q[1], q[2]));
                }
            }
        }
        System.out.println(result);
        return result;
    }

    private Node addNode(Node node, int val) {
        if (val == node.r) return node;
        if (val < node.r) {
            if (node.left == null) {
                node.left = new Node(node.l, val, val - node.l);
                node.right = new Node(val, node.r, node.r - val);
            } else if (node.left.r > val && node.left.l < val && node.left.left == null) {
                addNode(node.left, val);
            } else if (node.right.r > val && node.right.l < val && node.right.left == null) {
                addNode(node.right, val);
            } else if (node.right.l < val && node.right.r > val && node.right.right != null) {
                addNode(node.right, val);
            } else if (node.left.l > val && node.left.r > val && node.left.right != null) {
                addNode(node.left, val);
            } else if (node.left.r > val && (node.left.left != null || node.left.right != null)) {
                addNode(node.left, val);
            } else if (node.right.l < val && (node.right.left != null || node.right.right != null)) {
                addNode(node.right, val);
            }
        }
        return node;
    }

    private boolean hasFreeBox(Node root, int q1, int q2) {
        if (q2 > q1) return false;
        if (q1 - q2 >= root.r) return true;
        List<Node> leaves = collectLeaves(root);
        for (Node leaf : leaves) {
            if (leaf.r > q1 && q1 - q2 >= leaf.l) {
                return true;
            } else {
                if (leaf.size >= q2 && q1 - q2 >= leaf.l) return true;
            }
        }
        return false;
    }

    private List<Node> collectLeaves(Node root) {
        List<Node> leaves = new ArrayList<>();
        collectLeavesHelper(root, leaves);
        return leaves;
    }

    private void collectLeavesHelper(Node node, List<Node> leaves) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            leaves.add(node);
        } else {
            if (node.left != null) {
                collectLeavesHelper(node.left, leaves);
            }
            if (node.right != null) {
                collectLeavesHelper(node.right, leaves);
            }
        }
    }
}
