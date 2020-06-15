package basic.generic;

public class Plate<T extends Fruit> {
    private T item;

    public Plate(T t) {
        item = t;
    }

    public void set(T t) {
        item = t;
    }

    public T get() {
        return item;
    }

    public static void main(String[] args) {
        Plate<? extends Fruit> p = new Plate<Apple>(new Apple());
//        p.set(new Fruit()); // error
    }
}