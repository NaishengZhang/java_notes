package basic.dataStructure.queue;

import basic.dataStructure.array.Array;

public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayQueue() {
        array = new Array<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public void offer(E e) {
        array.addLast(e);
    }

    // time complexity: O(n)
    @Override
    public E poll() {
        return array.removeFirst();
    }

    @Override
    public E peek() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue:");
        sb.append("front -> [");
        for (int i = 0; i < array.getSize(); i++) {
            sb.append(array.get(i));
            if (i != array.getSize() - 1) {
                sb.append(", ");
            }
        }
        sb.append("] <- tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i =0; i < 10; i++) {
            queue.offer(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.poll();
                System.out.println(queue);
            }
        }
    }

}