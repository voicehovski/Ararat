package goit.hw9;

import java.util.Arrays;

public class MyArrayList {
    public static final int INITIAL_CAPACITY = 16;
    public static final float DEFAULT_EXTEND_FACTOR = 1.62f;
    private Object [] data;
    private int pointer = 0;	// The index the next element will be put to

    public MyArrayList (  ) {
        data = new Object [INITIAL_CAPACITY];
    }

    public MyArrayList ( int capacity ) {
        if ( capacity <= 0 ) {
            throw new RuntimeException ();
        }
        data = new Object [capacity];
    }
    public void add(Object value) { // добавляет элемент в конец
        if ( isOverflowed (  ) ) {
            extend (  );
        }
        data [pointer++] = value;
    }
    public void remove(int index) { // удаляет элемент под индексом
        if ( indexIsOutOfBounds(index) ) {
            throw new RuntimeException (  );
        }
        pointer--;
        for ( int i = index; i < pointer; i++ ) {
            data [i] = data [i+1];
        }
        data [pointer] = null;
    }
    public void clear() { // очищает коллекцию
        data = new Object [data.length];
        pointer = 0;
    }
    public int size() { // возвращает размер коллекции
        return pointer;
    }
    public int capacity() { // возвращает ёмкость коллекции
        return data .length;
    }
    public Object get(int index) { // возвращает элемент под индексом
        if ( indexIsOutOfBounds(index) ) {
            throw new RuntimeException (  );
        }
        return data [index];
    }

    private boolean isOverflowed (  ) {
        return pointer >= capacity (  );
    }
    private boolean indexIsOutOfBounds ( int index ) {
        return index >= pointer || index < 0;
    }

    private void extend (  ) {
        extend ( DEFAULT_EXTEND_FACTOR );
    }

    private void extend ( float factor ) {
        data = Arrays.copyOf (data, (int)Math.ceil(data .length * factor));
    }

    public String toString (  ) {
        String s = "";
        for ( Object elem : data ) {
            s += elem + ", ";
        }
        return s;
    }
}

class MyArrayListTest {
    public static void main(String[] args) {
        MyArrayListTest .test (  );
    }

    public static void test (  ) {

        MyArrayList list = new MyArrayList( 1 );
        list .add ("|");
        System.out.format("Capacity: %d%n", list .capacity());
        list .add ("||");
        System.out.format("Capacity (list has been extended): %d%n", list .capacity());
        System.out.format("Content: %s%n", list);
        System.out.format("Elem 0: %s%n", list .get ( 0 ));
        System.out.format("Size: %d%n", list .size (  ));
        list .remove ( 0 );
        System.out.format("Content (elem 0 has been removed): %s%n", list);
        list .clear (  );
        System.out.format("Content (list has been cleared): %s%n", list);

        MyArrayList listDefaultCapacity = new MyArrayList(  );
        listDefaultCapacity .add ("*");
        listDefaultCapacity .add ("-");
        listDefaultCapacity .add ("/");
        listDefaultCapacity .add ("+");
        System.out.format("Capacity: %d%n", listDefaultCapacity .capacity());
        System.out.format("Size: %d%n", listDefaultCapacity .size (  ));
        System.out.format("Content: %s%n", listDefaultCapacity);
        try {
            listDefaultCapacity .get(10);
        } catch ( RuntimeException re ) {
            re .printStackTrace();
        }
        try {
            listDefaultCapacity .remove(10);
        } catch ( RuntimeException re ) {
            re .printStackTrace();
        }
        try {
            listDefaultCapacity .remove(-10);
        } catch ( RuntimeException re ) {
            re .printStackTrace();
        }
    }
}