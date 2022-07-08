package goit.hw9;

public class MyHashMap {

    public static final int DEFAULT_BUCKET_COUNT = 16;

    private Node [] buckets;
    private int nodeCount = 0;

    public MyHashMap (  ) {
        this ( DEFAULT_BUCKET_COUNT );
    }
    public MyHashMap ( int bucketCount ) {
        buckets = new Node [bucketCount];
    }

    public void put ( Object key, Object value ) { //  добавляет пару ключ + значение
        if ( key == null ) {
            throw new RuntimeException ( "Key shouldn`t bel null" );
        }
        int keyHash = key .hashCode (  );
        int index = getIndex ( keyHash );
        Node newNode = new Node ( key, value );

        if ( buckets [index] == null ) {
            buckets [index] = newNode;
            nodeCount++;
        } else {
            Node [] parentAndNode = findNodeWithParent ( buckets [index], key );
            Node parent = parentAndNode [0];
            Node node = parentAndNode [1];

            if ( node == null ) {   // Такого нет, добавить
                parent .append ( newNode );
                nodeCount++;
            } else {    // Такой есть, заменить
                if ( parent == null ) {
                    newNode .append ( node .next );
                    buckets [index] = newNode;
                } else {
                    newNode .append ( node .next );
                    parent .append ( newNode );
                }
            }
        }
    }

    public boolean remove ( Object key ) { //  удаляет пару по ключу
        if ( key == null ) {
            throw new RuntimeException ( "Key shouldn`t bel null" );
        }
        int keyHash = key .hashCode (  );
        int index = getIndex ( keyHash );
        if ( buckets [index] == null ) {
            return false;
        }

        Node [] parentAndNode = findNodeWithParent ( buckets [index], key );
        Node parent = parentAndNode [0];
        Node node = parentAndNode [1];

        if ( node == null ) {
            return false;
        } else if ( parent == null ) {
            buckets [index] = node .next;
        } else {
          parent .append ( node .next );
        }

        nodeCount--;
        return true;
    }

    public void clear() { //  очищает коллекцию
        buckets = new Node [buckets .length];
        nodeCount = 0;
    }

    public int size() {
        return nodeCount;
    }

    public Object get ( Object key ) {
        if ( key == null ) {
            throw new RuntimeException ( "Key shouldn`t bel null" );
        }
        int keyHash = key .hashCode (  );
        int index = getIndex ( keyHash );
        if ( buckets [index] == null ) {
            return null;
        }

        Node [] parentAndNode = findNodeWithParent ( buckets [index], key );
        return parentAndNode [1] == null ? null : parentAndNode [1] .value;
    }


    private int getIndex ( int hash ) {
        return Math .abs ( hash % buckets .length );
    }

    private Node [] findNodeWithParent ( Node root, Object key ) {
        Node current = root;
        Node parent = null;
        do {
            if ( current .keyIsEq ( key ) ) {
                break;
            }
            parent = current;
        } while ( ( current = current .next ) != null );
        return new Node [] { parent, current };
    }

    @Override
    public String toString (  ) {
        String s = "";
        for ( int i = 0; i < buckets .length; i++ ) {
            if ( buckets [i] == null ) {
                continue;
            }
            s += buckets [i] + ", ";
        }
        return s;
    }

    private static class Node {
        private Object key;
        private Object value;
        private Node next;

        public Node ( Object key, Object value ) {
            this .key = key;
            this .value = value;
        }

        public void append ( Node node ) {
            next = node;
        }

        public boolean keyIsEq ( Object key ) {
            return this .key .equals ( key );
        }

        @Override
        public String toString (  ) {
            Node current = this;
            String s = "";
            do {
                s += String .format ( "[%s] %s, ", current .key, current .value );
            } while ( ( current = current .next ) != null );
            return s .substring ( 0, s .length (  ) - 2 );
        }
    }
}

class MyHashMapTest {

    public static void main(String[] args) {
        test (  );
    }

    public static void test (  ) {
        System.out.println ( "==\tInit HashMap\t==" );
        MyHashMap map = new MyHashMap (  );
        map .put ( "Rescue", "01" );
        map .put ( "Police", "02" );
        map .put ( "Ambulance", "03" );
        map .put ( "Time service", "060" );
        System.out.println ( String .format ( "Content: %s%n", map ) );
        System.out.println ( String .format ( "Size: %d%n", map .size() ) );
        System.out.println ( String .format ( "Element by key Rescue: %s%n", map .get ( "Rescue" ) ) );

        System.out.println ( "==\tChange value Rescue to 101\t==" );
        map .put ( "Rescue", "101" );
        System.out.println ( String .format ( "Content: %s%n", map ) );
        System.out.println ( String .format ( "Size: %d%n", map .size() ) );
        System.out.println ( String .format ( "Element by key Rescue: %s%n", map .get ( "Rescue" ) ) );

        System.out.println ( "==\tRemove Time service\t==" );
        map .remove ( "Time service" );
        System.out.println ( String .format ( "Content: %s%n", map ) );
        System.out.println ( String .format ( "Size: %d%n", map .size() ) );
        System.out.println ( String .format ( "Element by key Time service: %s%n", map .get ( "Time service" ) ) );
    }
}