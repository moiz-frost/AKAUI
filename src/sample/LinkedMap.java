package sample;

public class LinkedMap<K, V> {
    private Entry<K, V>[] buckets;
    private int capacity = 35;
    private Entry<K, V> head;
    private Entry<K, V> tail;

    public LinkedMap() {
        buckets = new Entry[capacity];
    }

    private void insertInList(Entry<K, V> newEntry) {
        if (head == null) {
            head = newEntry;
            tail = newEntry;
        } else {
            tail.after = newEntry;
            newEntry.before = tail;
            tail = newEntry;
        }
    }

    public V get(K key) {
        int hash = hash(key);
        Entry<K, V> curr = buckets[hash];
        while (curr != null) {
            if (curr.key.equals(key)) {
                return curr.value;
            }
            curr = curr.next;
        }
        return null;
    }

    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        boolean replace = false;
        int hash = hash(key);
        Entry<K, V> newEntry = new Entry<K, V>(key, value, null);

        Entry<K, V> curr = buckets[hash];

        if (curr == null) {
            buckets[hash] = newEntry;
        } else {
            Entry<K, V> prev = null;
            while (curr != null) {
                if (curr.key.equals(key)) {
                    replace = true;
                    curr.value = value;
                    break;
                }
                prev = curr;
                curr = curr.next;
            }
            if (prev != null)
                prev.next = newEntry;
        }
        if (replace == false)
            insertInList(newEntry);
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        Entry<K, V> before;
        Entry<K, V> after;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


}