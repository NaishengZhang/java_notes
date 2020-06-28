package basic.wrapper;

import java.util.ArrayList;
import java.util.List;

public class IntegerDemo {
    public static void main(String[] args) {
        Integer i = 1;
        Integer j = 10000;

        List<Integer> list = new ArrayList<>();
        list.add(i);
        list.add(j);
        System.out.println(list);

        i = 80;
        j = 30;
        System.out.println(list);

        list.remove(Integer.valueOf(1));
        list.remove(10000);
        System.out.println(list);
    }
}
