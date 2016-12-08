package LFU;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iters on 12/8/16.
 */
public class LFUCache<H, T> {
    private int capacity;
    private HashMap<H, Node<T>> cache = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public LFUCache() {
        capacity = 10;
    }

    private class Node<A> {
        A data;
        int frequency;

        Node(A data, int frequency) {
            this.data = data;
            this.frequency = frequency;
        }

        Node(A data) {
            this.data = data;
            frequency = 0;
        }

        int getFrequency() {
            return frequency;
        }

        A getData() {
            return data;
        }

        void increment() {frequency++;}
    }

    public void addElement(H key, T data) {
        if(full()) {
            H keyDelete = getLFUKey();
            cache.remove(keyDelete);
            cache.put(key, new Node<>(data));
        } else {
            Node<T> nodeData = new Node<>(data);
            cache.put(key, nodeData);
        }
    }

    public T getData(H key) {
        if (cache.containsKey(key)) {
            cache.get(key).increment();
            return cache.get(key).getData();
        }
        return null;
    }

    private H getLFUKey() {
        int min = Integer.MAX_VALUE;
        H key = null;

        for (Map.Entry<H, Node<T>> entry : cache.entrySet()) {
            if(min > entry.getValue().getFrequency()) {
                min = entry.getValue().getFrequency();
                key = entry.getKey();
            }
        }
        return key;
    }

    private boolean full() {
        if (cache.size() == capacity) {
            return true;
        }
        return false;
    }
}