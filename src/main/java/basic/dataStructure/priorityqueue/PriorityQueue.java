package basic.dataStructure.priorityqueue;

import basic.dataStructure.heap.MaxHeap;
import basic.dataStructure.queue.Queue;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public E peek() {
        return maxHeap.findMax();
    }

    @Override
    public E poll() {
        E e = maxHeap.extractMax();

        return e;
    }

    @Override
    public void offer(E e) {
        maxHeap.add(e);
    }


}
