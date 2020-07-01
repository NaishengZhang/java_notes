package basic.collection;

import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {

        TreeMap<Integer, Integer> map = new TreeMap<>();// time : count
        map.put(4, 2);
        map.put(2, 3);
        map.put(1, 4);
        map.put(0, 4);

        System.out.println(map);

        System.out.println(map.ceilingKey(3)); // 返回4，3的天花板，比3刚大或等于的数
        System.out.println(map.floorKey(3)); // 返回2，3的地板，比3刚小或等于的数
        System.out.println(map.higherKey(3)); // 返回4 strictly larger than the given key
        System.out.println(map.lowerKey(3)); //返回2， strictly less than the given key
    }
}
