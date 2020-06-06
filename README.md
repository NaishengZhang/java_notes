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

