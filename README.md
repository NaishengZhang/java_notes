# java_notes

Record Some Basic Java Usage



## Collection

### ArrayList

| Method                                                       |                       **Description**                        |
| ------------------------------------------------------------ | :----------------------------------------------------------: |
| void **[add](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#add-int-E-)**(int index, [E](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html) element) | Inserts the specified element at the specified position in this list. |
| boolean add(E e)                                             |                                                              |
| boolean **[addAll](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#addAll-java.util.Collection-)**([Collection](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html)<? extends [E](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)> c) | Appends all of the elements in the specified collection to the end of this list |
| int lastIndexOf(Object o)                                    | Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element. |
| int **[indexOf](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#indexOf-java.lang.Object-)**([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html) o) | Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element. |
| E **[remove](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#remove-int-)**(int index) | Removes the element at the specified position in this list.  |
| boolean **[remove](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#remove-java.lang.Object-)**([Object](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html) o) | Removes the first occurrence of the specified element from this list, if it is present. |
| E **[set](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#set-int-E-)**(int index, [E](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html) element) | Replaces the element at the specified position in this list with the specified element. |
| Object[] toArray()                                           |       Returns an array containing all of the elements        |
| \<T> T[] toArray(T[] a)                                      | Returns an array containing all of the elements in this list in proper sequence |

#### 复杂度震荡

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrdf1bkhvj30ju08tjsr.jpg)

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrdf0pyf1j30jt08pq4d.jpg)

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfrdezqgt1j30l908yjsm.jpg)

为了避免addLast完resize之后，紧接着又removeLast，resize，重复执行resize操作，采用一种lazy策略，当size是空间长度的1/4时再resize到1/2的大小。

```java
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("get Failed");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null; //如果不指定null，那么之前指向的对象一直有引用，就不会被GC。loitering objects.
        if (size == data.length / 4  && data.length / 2 != 0) { // lazy
            resize(data.length / 2); // decrease capacity
        }
        return ret;
    }
```



#### Array <=> List

- **List<Integer> => Integer[] or int[]**
  - `Arrays.toString(array) `convert T[] to String, help print array.

```java
List<Integer> list = new ArrayList<>();
list.add(1);
list.add(2);

Integer[] array = list.toArray(new Integer[list.size()]);
// 本身没有提供List<Integer> => int[]的api，但是可以借助stream来转换
int[] array1 = list.stream().mapToInt(i->i).toArray();
```

- **List<String> => String[]**

```java
ArrayList<String> list = new ArrayList<>(2);
list.add("A");
list.add("B");

//Convert to string array
String[] array = list.toArray(new String[list.size()]);
System.out.println(Arrays.toString(array));
```

```java
int[] arr = new int[10];
System.out.println(Arrays.toString(arr));
```

- **String[] => List<String>**

```java
String[] strings = {"a", "b", "c", "d", "e"};
//Method 1: Arrays
List<String> slist = Arrays.asList(strings);
System.out.println(slist);

//Method 2: Collections
List<String> list1 = new ArrayList<String>();
Collections.addAll(list1, strings);
System.out.println(list1);

//Method 3: Iteration
List<String> list2 = new ArrayList<String>();
for (String text : strings) {
    list2.add(text);
}
System.out.println(list2);
```

- **int[] => List<Integer>**

There is no shortcut for converting from `int[]` to `List` as `Arrays.asList` does not deal with boxing and will just create a `List` which is not what you want.

```java
//Java8 Stream
int[] ints = {1,2,3};
List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
```



#### Array Copy

1. **System.arraycopy**()

`void arraycopy(Object src, int srcPos, Object dest, int destPos, int length);`

```java
    public static void main(String[] args) {
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
    }
    
```

2. **Arrays.copyOf() **

`<T> T[]	copyOf(T[] original, int newLength);`

```java
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
```

3. **区别**

- System.arraycopy()可以复制指定的从i到j元

  Arrays.copyOf()则要全部复制。

- System.arraycopy()返回void，要先新建一个数组，然后再复制原组到新的数组。

  Arrays.copyOf()换回T[]，不需要新建一个数组，直接调用方法返回。

### Queue

#### ArrayQueue

除出队操作时间复杂度是O(n)外，其他操作都是O(1)。



#### LoopQueue

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfs8d15bf9j30kd0c7ju9.jpg)



如果不浪费一个空间的话，front == tail既表示队列为空，又表示队列满。

poll出队操作是O(1)

#### Compare ArrayQueue and LoopQueue

```java
package basic.dataStructure.queue;

import java.util.Random;

public class Main {
    public static double testQueue(Queue<Integer> q, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            q.offer(random.nextInt(Integer.MAX_VALUE));
        }

        for (int i = 0; i < opCount; i++) {
            q.poll();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(); // O(n^2)
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time1 + "s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>(); // O(n)
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue, time: " + time2 + "s");
    }
}
```

Result:

ArrayQueue, time: 4.94461159s
LoopQueue, time: 0.015562913s

### PriorityQueue

- 不同的底层数据结构会影响时间复杂度。

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfuu3mke1ij314c0pqaha.jpg)

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfuuarnyogj31cm0quaol.jpg)

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfv1h4lqajj31fk0ryant.jpg)

- jdk底层也是用的从0开始的数组

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfv1h65czfj31g20s8dud.jpg)

#### Heapify

将任意数组整理成堆的形状

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfv58e2h4xj319q0l0487.jpg)

- 时间复杂度是O(n), 推导https://www.zhihu.com/question/20729324

- heapify的过程：从最后一个非叶子节点开始做siftdown。
  - 最后一个非叶子节点index就是 (n-1)/2

```java
public MaxHeap(E[] arr) {
	data = new Array<>(arr);
  for (int i = parent(arr.length - 1); i >= 0; i--) {
    siftDown(i);
  }
}
```





#### JDK

默认小顶堆，所以默认是 (i, j) -> (i - j)。i < j, i放在堆顶

```java
//小顶
PriorityQueue<Integer> pq = new PriorityQueue<>();
PriorityQueue<Integer> pq = new PriorityQueue<>((i, j) -> (i - j));
PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 - o2 > 0) {
            return 1;
        } else if (o1-o2<0) {
            return -1;
        } else {
            return 0;
        }
    }
});
//简化
PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }
});
//大顶
PriorityQueue<Integer> pq = new PriorityQueue<>((i, j) -> (j - i));
PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        if (o2 - o1 > 0) {
            return 1;
        } else if (o2 - o1 < 0) {
            return -1;
        } else {
            return 0;
        }
    }
});
```



 

#### Complexity Analysis

- siftup 

```java
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }
```

- siftdown

```java
public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int k) {

        while (leftChild(k) < data.getSize()) { //一定有做孩子
            int j = leftChild(k);
            //有右孩子，并且 右孩子大于⬅左孩子
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j++; // j = right child index
            }
            if (data.get(k).compareTo(data.get(j)) >= 0) { // k比左右两个孩子都大或等
                break;
            }
            data.swap(k, j);
            k = j;
        }
    }
```

#### Example

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfv323vga6j30uu0g2qa2.jpg)

排序 nlogn， 排完之后在取出前100个数

优先队列 nlogm，在这题中，log1000000=20  log100=7，相差三倍。使用优先队列，维护当前看到的m个元素，然后遍历1,000,000剩下的元素，每次和堆顶的元素比较，如果大于堆顶的元素，就将堆顶元素替换，然后siftdown



![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfv2eq4k2bj31cw0rianj.jpg)







### LinkedList

#### LinkedListStack

只有head指针，对head进行 增 删 操作复杂度都是O(1)

#### LinkedListQueue

添加为指针的链表

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfsf0gng8dj30kv0cmgn6.jpg)

### Set

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfsww9s25vj30kr0bhabq.jpg)



####Complexity Analysis

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfsx0tdq5kj30h60bt408.jpg)

因为在LinkedListSet contains操作需要O(n)，所以本来只需要O(1)的add也需要O(n)。



- result

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfswvawo6sj309803fweh.jpg)

满二叉树的性质

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfsx32ws3hj30l60cn0v4.jpg)

最后一层的节点个数，是前边所有层之和。

### Map

#### Complexity Analysis

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfukzn59noj318i0q6wno.jpg)

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gful1kszt5j311q0lotfo.jpg)

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gful37a88zj316e0qok0i.jpg)

### Segment Tree

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfvv5ponw0j31cq0pon7r.jpg)

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfvv8tc8p0j31ge0t2nc3.jpg)



![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfvoo4t24aj31eo0tcqgu.jpg)

#### 数组初始化长度2n or 4n？

对于input array： [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

两种不同的初始化方法会产生不同的树。

- **4n自顶向下生成**

tree1: [0, 55, 37, 18, 34, 3, 7, 11, 15, 19, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

```java
    private void buildTree(int treeIndex, int l, int r, int[] data) {
        if (l == r) {
            tree1[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = 2 * treeIndex + 1;
        int rightTreeIndex = 2 * treeIndex + 2;
        int mid = l + (r - l) / 2;
        buildTree(leftTreeIndex, l, mid, data);
        buildTree(rightTreeIndex, mid + 1, r, data);
        tree1[treeIndex] = tree1[leftTreeIndex] + tree1[rightTreeIndex];
    }
```

从下图1可以看出来，这种方法会为3 ，4，5留出子节点的空位，以便可以通过2*i或2*i+1访问[6,7]的子节点。图2中可以看到这种方法会造成大量的空间浪费。

图1：![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfvvy8xc2uj310q0fgn5l.jpg)



图2:![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfvz02lcadj32260lqgvv.jpg)



- **2n自底向上生成**：build和query很复杂，不推荐。

```java
    private void buildTree(int[] nums) {
        int n = nums.length;
        for (int i = n, j = 0; i < 2 * n; i++, j++) // 先把nums中的元素放到tree最后n位。
            tree[i] = nums[j];
        for (int i = n - 1; i > 0; --i) //生成上一层的节点
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }
```

tree: [0, 55, 15, 40, 6, 9, 21, 19, 3, 3, 4, 5, 13, 8, 9, 10, 1, 2, 0, 0, 0, 0, 0, 0, 6, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

https://leetcode.com/problems/range-sum-query-mutable/

- 根节点的索引是1
- 左子树节点的索引为偶数  右子树节点的索引为奇数
- 叶子节点的索引范围[n, 2n-1]

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfvz03u092j310s0iedk5.jpg)



nums[i]对应tree[i+n];

如果input array是从1到9，那么tree: [0, 45, 23, 22, 18, 5, 9, 13, 17, 1, 2, 3, 4, 5, 6, 7, 8, 9]

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfw00xjmdaj30yk0iyq6x.jpg)



## Generic

ref: Java 泛型 <? super T> 中 super 怎么 理解？与 extends 有何不同？ 

- 胖君的回答 - 知乎 https://www.zhihu.com/question/20400700/answer/117464182

### 为什么要用通配符和边界？**

- Fruit

```java
public class Fruit {}
```

- Apple

```java
public class Apple extends Fruit {}
```

- Plate

```
class Plate<T>{
    private T item;
    public Plate(T t){item=t;}
    public void set(T t){item=t;}
    public T get(){return item;}
}
```

Plate 是一个容器，盘子里可以放范型的东西。

水果盘子当然可以装苹果。

```
Plate<Fruit> p = new Plate<>(new Apple()); 
// Error: incompatible types: basic.generic.Plate<basic.generic.Apple> cannot be converted to basic.generic.Plate<basic.generic.Fruit>
```

装苹果的盘子无法被转换为装水果的盘子，对于编译器来说：

- 苹果 IS-A 水果
- 装苹果的盘子 NOT-IS-A 装水果的盘子

就算容器里装的东西之间有继承关系，但容器之间是没有继承关系的。

所以为了来让“水果盘子”和“苹果盘子”之间发生关系，就创造出 **<? extends T>**和**<? super T>**

### Upper Bounds Wildcards

```java
Plate<? extends Fruit> p = new Plate<Apple>(new Apple());
```

一个能放水果以及一切是水果派生类的盘子。

扩展

```java
//Lev 1
class Food{}

//Lev 2
class Fruit extends Food{}
class Meat extends Food{}

//Lev 3
class Apple extends Fruit{}
class Banana extends Fruit{}
class Pork extends Meat{}
class Beef extends Meat{}

//Lev 4
class RedApple extends Apple{}
class GreenApple extends Apple{}
```

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfsptnpqe6j317s0l8dlz.jpg)

#### 什么是下界

```java
Plate<？ super Fruit>
```

一个能放水果以及一切是水果基类的盘子。



![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfsptog9kwj317o0mg43g.jpg)

#### PECS（Producer Extends Consumer Super）原则

- 上界`<? extends Fruit>`不能往里存，只能往外取
  - 频繁往外读取内容的，适合用上界Extends。

```java
Plate<? extends Fruit> p=new Plate<Apple>(new Apple());
	
//不能存入任何元素
p.set(new Fruit());    //Error
p.set(new Apple());    //Error

//读取出来的东西只能存放在Fruit或它的基类里。
Fruit newFruit1=p.get();
Object newFruit2=p.get();
Apple newFruit3=p.get();    //Error
```



- 下界<? super T>不影响往里存，但往外取只能放在Object对象里
  - 经常往里插入的，适合用下界Super。

```java
Plate<? super Fruit> p=new Plate<Fruit>(new Fruit());

//存入元素正常
p.set(new Fruit());
p.set(new Apple());

//读取出来的东西只能存放在Object类里。
Apple newFruit3=p.get();    //Error
Fruit newFruit1=p.get();    //Error
Object newFruit2=p.get();
```

