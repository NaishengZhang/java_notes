package basic.collection;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {
    public static void main(String[] args) {
PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }
});
        pq.offer(0);
        pq.offer(1);
        pq.offer(2);
        pq.offer(3);
        pq.offer(4);
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}
