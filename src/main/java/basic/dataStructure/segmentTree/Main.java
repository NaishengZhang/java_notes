package basic.dataStructure.segmentTree;

import java.util.Arrays;

public class Main {
    private static int[] tree;
    private static int[] tree1;

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        tree = new int[2 * nums.length];
        buildTree(nums);
        System.out.println(Arrays.toString(tree));

        tree1 = new int[4 * nums.length];
        buildTree(0, 0, nums.length - 1, nums);
        System.out.println(Arrays.toString(tree1));
    }

    private static void buildTree(int[] nums) {
        int n = nums.length;
        for (int i = n, j = 0; i < 2 * n; i++, j++)
            tree[i] = nums[j];
        for (int i = n - 1; i > 0; --i)
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }

    private static void buildTree(int treeIndex, int l, int r, int[] data) {
        if (l == r) {
            tree1[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = 2 * treeIndex + 1;
        int rightTreeIndex = 2 * treeIndex + 2;
        int mid = l + (r - l) / 2;
        buildTree(leftTreeIndex, l, mid, data);
        buildTree(rightTreeIndex, mid + 1, r, data);
        tree1[treeIndex] = tree1[leftTreeIndex] + tree1[rightTreeIndex];
    }
}
