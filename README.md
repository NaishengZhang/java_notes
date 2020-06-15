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





#### Array Copy

1. **System.arraycopy**()

```java
  	void arraycopy(Object src, int srcPos, Object dest, int destPos, int length);

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

```java
		<T> T[]	copyOf(T[] original, int newLength);

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

### LinkedList

#### LinkedListStack

只有head指针，对head进行 增 删 操作复杂度都是O(1)

#### LinkedListQueue

添加为指针的链表

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gfsf0gng8dj30kv0cmgn6.jpg)



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

