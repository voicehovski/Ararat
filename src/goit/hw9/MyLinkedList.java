package goit.hw9;

public class MyLinkedList {
    private Element root = Element .createRoot (  );
    private Element top = root;
    private int size;

    public void add(Object value) { // добавляет элемент в конец
        top = top .append ( value );
        size++;
    }

    public boolean remove(int index) { //  удаляет элемент под индексом
        Element previous = getElement ( index - 1 );
        if ( previous == null ) {
            return false;
        }

        Element target = previous .next (  );
        if ( target == null ) {
            return false;
        }

        Element next = target .next (  );
        if ( next == null ) {
            previous .makeTop (  );
        } else {
            previous .setNext ( next );
        }

        size--;

        return true;
    }

    public void clear() { //  очищает коллекцию
        root .makeTop (  );
        top = root;
        size = 0;
    }

    public int size() { //  возвращает размер коллекции
        return size;
    }

    public Object get ( int index ) {
        Element e = getElement ( index );
        if ( e == null ) {
            return null;
        }
        return e .getValue (  );
    }

    private Element getElement (int index) {
         if ( isOutOfBounds ( index ) ) {
             return null;
         }

        Element current = root;
        while ( index-- > -1 ) {
            current = current .next (  );
            if ( current == null ) {
                return null;
            }
        }
        return current;
    }

    private boolean isOutOfBounds ( int index ) {
        return index < -1 || index >= size;
    }

    @Override
    public String toString (  ) {
        String s = "";
        for ( int i = 0; i < size; i++ ) {
            s += get ( i ) + ", ";
        }
        return s;
    }

    private static class Element {
        private Object o;
        private Element next;
        public Element ( Object o ) {
            this .o = o;
        }

        public static Element createRoot (  ) {
            return new Element ( null );
        }

        public Element next (  ) {
            return next;
        }
        public void setNext ( Element e ) {
            next = e;
        }

        public Element append ( Object o ) {
            return (this .next = new Element ( o ));
        }

        public Object getValue (  ) {
            return o;
        }

        public void makeTop (  ) {
            next = null;
        }
    }
}

class MyLinkedListTest {
    public static void main(String[] args) {
        test ();
    }
    public static void test (  ) {
        System.out.println ( "==\tInit\t==" );
        MyLinkedList list = new MyLinkedList (  );
        list .add ( "H" );
        list .add ( "He" );
        list .add ( "Li" );
        list .add ( "Be" );
        list .add ( "B" );

        System.out.println ( String .format ( "Content: %s%n", list ) );
        System.out.println ( String .format ( "Size: %d%n", list .size() ) );
        System.out.println ( String .format ( "Element[0]: %s%n", list .get(0) ) );

        System.out.println ( "==\tRemove [0] elemen\t==" );
        list .remove ( 0 );
        System.out.println ( String .format ( "Content: %s%n", list ) );
        System.out.println ( String .format ( "Size: %d%n", list .size() ) );
        System.out.println ( String .format ( "Element[0]: %s%n", list .get(0) ) );

        System.out.println ( "==\tClear list\t==" );
        list .clear (  );
        System.out.println ( String .format ( "Content: %s%n", list ) );
        System.out.println ( String .format ( "Size: %d%n", list .size() ) );
        System.out.println ( String .format ( "Element[0]: %s%n", list .get(0) ) );
        System.out.println ( String .format ( "Remove element[100]: %s%n", list .remove(100) ) );

        System.out.println ( "==\tFill again\t==" );
        list .add ("Au");
        System.out.println ( String .format ( "Content: %s%n", list ) );
        System.out.println ( String .format ( "Size: %d%n", list .size() ) );
        System.out.println ( String .format ( "Element[0]: %s%n", list .get(0) ) );
    }
}