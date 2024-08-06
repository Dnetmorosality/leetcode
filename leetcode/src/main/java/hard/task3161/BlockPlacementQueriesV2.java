package main.java.hard.task3161;

import java.util.ArrayList;
import java.util.List;

public class BlockPlacementQueriesV2 {
    class Node {
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
        boolean noLimit = true;
        for (int[] q : queries) {
            if(q[0]!= 1 && noLimit && q[1] >= q[2]) {
                result.add(true);
            } else {
                if (q[0] == 1) {
                    addNode(root, q[1]);
                    noLimit = false;
                } else {
                    result.add(hasFreeBox(root, q[1], q[2]));
                }
            }
        }
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

        if (root.left != null) {
            if (q1 <= root.right.l) {
                return hasFreeBox(root.left, q1, q2);
            } else {
                return hasFreeBox(root.right, q1, q2);
            }
        }


        return root.size >= q2;
    }
}
