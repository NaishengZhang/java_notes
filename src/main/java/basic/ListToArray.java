package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ListToArray {
    public static void main(String[] args) {
        //list to array
/*
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        //Convert to string array
        Integer[] array = list.toArray(new Integer[list.size()]);
        System.out.println(Arrays.toString(array));
        int[] array1 = list.stream().mapToInt(i -> i).toArray();
        System.out.println(Arrays.toString(array1));
        int[] arr = new int[10];
        System.out.println(Arrays.toString(arr));
*/

        // array to list
        String[] strings = {"a", "b", "c", "d", "e"};
        //Method 1
        List<String> slist = Arrays.asList(strings);
        System.out.println(slist);

        //Method 2
        List<String> list1 = new ArrayList<String>();
        Collections.addAll(list1, strings);
        System.out.println(list1);

        //Method 3
        List<String> list2 = new ArrayList<String>();
        for (String text : strings) {
            list2.add(text);
        }
        System.out.println(list2);

        int[] iarr = new int[]{1, 2, 5, 4};
        List<Integer> ilist = Arrays.stream(iarr).boxed().collect(Collectors.toList());
        System.out.println(ilist);
    }
}
