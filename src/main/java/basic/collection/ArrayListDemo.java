package basic.collection;

import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        //add(int index, E element)
        list.add(1,1000);
        System.out.println(list);

        List<Integer> list1 = new ArrayList<>();
        list1.add(90);
        //addAll(Collection<? extends E> c)
        list1.addAll(list);
        System.out.println(list1);

    }
}
