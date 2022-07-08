package goit.hw9;

public class MyStack {
    private MyLinkedList stack = new MyLinkedList (  );

    public void push ( Object value ) { // добавляет элемент в конец
        stack .add ( value );
    }
    public boolean remove ( int index ) { // удаляет элемент под индексом
        return stack .remove ( index );
    }
    public void clear (  ) { // очищает коллекцию
        stack .clear (  );
    }
    public int size (  ) { // возвращает размер коллекции
        return stack .size (  );
    }
    public Object peek( ) {
        return stack .get ( stack .size (  ) - 1 );
    }
    public Object pop (  ) {
        int lastIndex = stack .size (  ) - 1;
        Object elem = stack .get ( lastIndex );
        if ( elem == null ) {
            return null;
        }
        stack .remove ( lastIndex );
        return elem;
    }

    @Override
    public String toString (  ) {
        String s = "";
        int size = size (  );
        for ( int i = 0; i < size; i++ ) {
            s += stack .get ( i ) + ", ";
        }
        return s;
    }
}

class MyStackTest {
    public static void test (  ) {
        System.out.println ( "==\tInit\t==" );
        MyStack stack = new MyStack (  );
        stack .push ( "First" );
        stack .push ( "Second" );
        stack .push ( "Third" );
        stack .push ( "Fourth" );

        System.out.println ( String .format ( "Content: %s%n", stack ) );
        System.out.println ( String .format ( "Size: %d%n", stack .size() ) );
        System.out.println ( String .format ( "First out: %s%n", stack .peek () ) );

        System.out.println ( "==\tPolling\t==" );
        System.out.println ( String .format ( "Poping...: %s%n", stack .pop (  ) ) );
        System.out.println ( String .format ( "Poping...: %s%n", stack .pop (  ) ) );

        System.out.println ( String .format ( "Content: %s%n", stack ) );
        System.out.println ( String .format ( "Size: %d%n", stack .size() ) );
        System.out.println ( String .format ( "First out: %s%n", stack .peek () ) );

        System.out.println ( "==\tClearing\t==" );
        stack .clear();
        System.out.println ( String .format ( "Content: %s%n", stack ) );
        System.out.println ( String .format ( "Size: %d%n", stack .size() ) );
        System.out.println ( String .format ( "First out: %s%n", stack .peek () ) );

        System.out.println ( "==\tRemoving from empty\t==" );
        System.out.println ( String .format ( "Removing...: %s%n", stack .remove (10 ) ) );
    }

    public static void main(String[] args) {
        test (  );
    }
}
