package basic;

import java.util.Arrays;

public class ArrayCopyDemo {

    public static void main(String[] args) {
        /**
         * System.arraycopy();  void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
         */
        int[] arr = {0, 1, 2, 3, 4, 5, 6};
        int[] newArr = new int[15];
        System.arraycopy(arr, 0, newArr, 1, arr.length);
        for (int i : newArr) {
            System.out.print(i);
        }
        System.out.println("\n");
        System.arraycopy(arr, 0, arr, 2, 3);
        for (int i : arr) {
            System.out.print(i);
        }

        /**
         * Arrays.copyOf(); <T> T[]	copyOf(T[] original, int newLength)
         */
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = Arrays.copyOf(arr1, 5);
        int[] arr3 = Arrays.copyOf(arr1, 10);
        System.out.print("\narr2:");
        for (int i : arr2) {
            System.out.print(i);
        }
        System.out.print("\narr3:");
        for (int i : arr3) {
            System.out.print(i);
        }
    }
}
