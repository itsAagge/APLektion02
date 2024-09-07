package opgave03.ex3student;

import java.util.List;
import java.util.Set;

/**
 * MapI is a sorted map, sorted according to the keys' natural ordering.
 * Keys in the map of type K must implement the Comparable<K> interface.
 * Note:  null is not allowed as a key or a value.
 */
public interface MapI<K extends Comparable<K>, V> {
    /**
     * Return the value corresponding to the key k.
     * Return null, if the key k is not in the map.
     */
    V get(K key);

    /**
     * Insert the (key, value) pair in the map.
     * Return the old value, if the key k was in the map before this insertion.
     * If not, return null.
     */
    V put(K key, V value);

    /**
     * Remove the (key, value) pair with the key k from the map.
     * Return the value, if the key k was in the map before this removal.
     * If not, return null.
     */
    V remove(K key);

    /**
     * Return a set with all the keys in the map.
     */
    Set<K> keys();

    /**
     * Return a list with all the values in the map.
     */
    List<V> values();

    /**
     * Return a set with all the entries in the map.
     */
    Set<Entry<K,V>> entries();

    /**
     * Return the number of (key,value) pairs in the map.
     */
    int size();

    /**
     * Return true on an empty map, false on a non-empty map.
     */
    boolean isEmpty();

    interface Entry<K,V> {
        K key();
        V value();
    }
}
