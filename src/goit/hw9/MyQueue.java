package goit.hw9;

public class MyQueue {
    private MyLinkedList queue = new MyLinkedList (  );

    public void add(Object value ) { // добавляет элемент в конец
        queue .add ( value );
    }
    public boolean remove(int index ) { // удаляет элемент под индексом
        return queue .remove ( index );
    }
    public void clear( ) { // очищает коллекцию
        queue .clear();
    }
    public int size( ) { // возвращает размер коллекции
        return queue.size();
    }
    public Object peek( ) { // возвращает первый элемент в очереди (FIFO ) { //
        return queue .get ( 0 );
    }
    public Object poll( ) { // возвращает первый элемент в очереди и удаляет его из коллекции
        Object elem = queue .get ( 0 );
        queue .remove ( 0 );
        return elem;
    }

    @Override
    public String toString (  ) {
        String s = "";
        int size = size (  );
        for ( int i = 0; i < size; i++ ) {
            s += queue .get ( i ) + ", ";
        }
        return s;
    }

}

class MyQueueTest {
    public static void test (  ) {
        System.out.println ( "==\tInit\t==" );
        MyQueue queue = new MyQueue (  );
        queue .add ( "First" );
        queue .add ( "Second" );
        queue .add ( "Third" );
        queue .add ( "Fourth" );

        System.out.println ( String .format ( "Content: %s%n", queue ) );
        System.out.println ( String .format ( "Size: %d%n", queue .size() ) );
        System.out.println ( String .format ( "First out: %s%n", queue .peek () ) );

        System.out.println ( "==\tPolling\t==" );
        System.out.println ( String .format ( "Polling...: %s%n", queue .poll (  ) ) );
        System.out.println ( String .format ( "Polling...: %s%n", queue .poll (  ) ) );

        System.out.println ( String .format ( "Content: %s%n", queue ) );
        System.out.println ( String .format ( "Size: %d%n", queue .size() ) );
        System.out.println ( String .format ( "First out: %s%n", queue .peek () ) );

        System.out.println ( "==\tClearing\t==" );
        queue .clear();
        System.out.println ( String .format ( "Content: %s%n", queue ) );
        System.out.println ( String .format ( "Size: %d%n", queue .size() ) );
        System.out.println ( String .format ( "First out: %s%n", queue .peek () ) );
    }

    public static void main(String[] args) {
        test (  );
    }
}