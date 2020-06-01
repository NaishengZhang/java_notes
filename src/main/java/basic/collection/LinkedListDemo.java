package basic.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<LinkedList<Integer>> list = new LinkedList<>();
        List<List<Integer>> list2 = new LinkedList<>();
        list.add(new LinkedList<Integer>());
        list.get(0).addFirst(10);
        list.get(0).addFirst(32); // list接口没有addFirst方法
        System.out.println("list: " + list);

    }

/*    private List<List<Integer>> convert() {
        LinkedList<LinkedList<Integer>> list = new LinkedList<>();
        list.add(new LinkedList<Integer>());
        list.get(0).addFirst(10);
        list.get(0).addFirst(32);
        return list; // 要求返回List<List<Integer>>， LinkedList<LinkedList<Integer>>就不能返回
    }*/
}
