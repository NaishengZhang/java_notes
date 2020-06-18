package basic.dataStructure.segmentTree;

public class NumArray {

    private Node root;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            root = build(nums, 0, nums.length -1);
        }
    }

    private Node build(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        Node node = new Node(start, end);
        if (start == end) {
            node.val = nums[start];
        } else {
            int mid = start + (end - start) / 2;
            node.left = build(nums, start, mid);
            node.right = build(nums, mid + 1, end);
            node.val = node.left.val + node.right.val;
        }
        return node;
    }

    public void update(int i, int val) {
        update(root, i, val);
    }

    private void update(Node node, int i, int val) {

        if (node.start == node.end) {
            node.val = val;
            return;
        }
        int mid = node.start + (node.end - node.start) / 2;
        if (i <= mid) {
            update(node.left, i, val);
        } else {
            update(node.right, i, val);
        }
        node.val = node.left.val + node.right.val;
    }

    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }

    // log(n)
    private int sumRange(Node root, int i, int j) {
        if (root.end == j && root.start == i) {
            return root.val;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (i >= mid) {
            return sumRange(root.right, i, j);
        } else if (j <= mid) {
            return sumRange(root.left, i, j);
        } else {
            return sumRange(root.left, i, mid) + sumRange(root.right, mid + 1, j);
        }
    }

    private class Node {
        int start;
        int end;
        int val;
        Node left;
        Node right;
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
            this.val = 0;
        }
    }

}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */