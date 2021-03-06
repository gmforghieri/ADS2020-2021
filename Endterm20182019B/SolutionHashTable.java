package Endterm20182019B;


//this is only 76/100 on spectests but I cant find the mistake so feel free to message me if you can

class SolutionHashTable {
    public Entry[] table;
    public int capacity;

    /**
     * Constructs a new HashTable.
     *
     * Capacity of the hash table can not be 0 or negative.
     *
     * @param capacity
     *     to be used as capacity of the table.
     * @throws IllegalArgumentException
     *     if the input capacity is invalid.
     */
    public SolutionHashTable(int capacity) {
        if(capacity <= 0) throw new IllegalArgumentException();
        table = new Entry[capacity];
        this.capacity = capacity;
        for(int i = 0; i < capacity; i++)
            setDefunct(i);
    }

    /**
     * Add a new Entry to the hash table,
     * uses linear probing to deal with collisions.
     *
     * Returns false, if the key is null or the table is full.
     *
     * @param key
     *     String representing the key of the entry.
     * @param value
     *     String representing the value of the entry.
     * @return true iff entry has been added successfully, else false.
     */
    public boolean put(String key, String value) {
        if(key == null) return false;
        remove(key);

        int i = hash(key);
        if(isDefunct(i)) {
            table[i] = new Entry(key, value);
            return true;
        }

        String current = table[i].getKey();
        i = (i + 1) % capacity;
        while(!isDefunct(i)) {
            if(table[i].getKey().equals(current)) return false;
            i = (i + 1) % capacity;
        }
        table[i] = new Entry(key, value);
        return true;
    }

    /**
     * Retrieve the value of the entry associated with this key.
     *
     * Returns null, if the key is null.
     *
     * @param key
     *     String representing the key of the entry to look for.
     * @return value of the entry as String iff the entry with this key is found in the hash table, else null.
     */
    public String get(String key) {
        if(key == null) return null;

        int i = hash(key);
        if(isDefunct(i)) return null;
        if(table[i].getKey().equals(key)) return table[i].getValue();

        String current = table[i].getKey();
        i = (i + 1) % capacity;
        while(!isDefunct(i)) {
            if(table[i].getKey().equals(current)) return null;
            if(table[i].getKey().equals(key)) return table[i].getValue();
            i = (i + 1) % capacity;
        }

        return null;
    }

    /**
     * Remove the entry associated with this key from the hash table.
     *
     * Returns false, if the key is null.
     *
     * @param key
     *     String representing the key of the entry to remove.
     * @return true iff the entry has been successfully removed, else false.
     */
    public boolean remove(String key) {
        if(key == null) return false;

        int i = hash(key);
        if(isDefunct(i)) return false;
        if(table[i].getKey().equals(key)) {
            setDefunct(i);
            return true;
        }

        String current = table[i].getKey();
        i = (i + 1) % capacity;
        while(!isDefunct(i)) {
            if(table[i].getKey().equals(current)) return false;
            if(table[i].getKey().equals(key)) {
                setDefunct(i);
                return true;
            }
            i = (i + 1) % capacity;
        }

        return false;
    }

    /**
     * Takes as input an index and sets the entry in that location as defunct.
     *
     * @param index
     *     The index of the spot that is defunct.
     */
    public void setDefunct(int index) {
        this.table[index] = new Entry(null, null);
    }

    public boolean isDefunct(int index) {
        return table[index].getKey() == null;
    }

    /**
     * Hashes a string representing a key.
     *
     * @param key
     *     String that needs to be hashed.
     * @return the hashcode of the string, modulo the capacity of the HashTable.
     */
    public int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }
}

class Entry {
    private String key;
    private String value;

    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
