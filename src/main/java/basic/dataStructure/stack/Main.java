package basic.dataStructure.stack;

import java.util.Random;

public class Main {
    private static double testStack(Stack<Integer> stack, int opCount) {

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }


    public static void main(String[] args) {
        int opCount = 10000000;

        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack, opCount);
        System.out.println("ArrayStack, time: " + time1 + " s");

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = testStack(linkedListStack, opCount);
        System.out.println("LinkedListStack, time: " + time2 + " s");

//        JdkLinkedListStack<Integer> jdkLinkedListStack = new JdkLinkedListStack<>();
//        double time3 = testStack(jdkLinkedListStack, opCount);
//        System.out.println("基于JDK自带的链表实现的栈JdkLinkedListStack, time: " + time3 + " s");
//
    }
}
