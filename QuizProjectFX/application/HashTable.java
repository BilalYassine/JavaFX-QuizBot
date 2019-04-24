package application;

import java.util.ArrayList;

/*
 * $$$$$$$\  $$$$$$$\   $$$$$$\  
 * $$  __$$\ $$  __$$\ $$  __$$\ 
 * $$ |  $$ |$$ |  $$ |$$ /  \__|
 * $$ |  $$ |$$$$$$$  |\$$$$$$\  
 * $$ |  $$ |$$  __$$<  \____$$\ 
 * $$ |  $$ |$$ |  $$ |$$\   $$ |
 * $$$$$$$  |$$ |  $$ |\$$$$$$  |
 * \_______/ \__|  \__| \______/ 
 * 
 * LISTEN TO MY MIXTAPE ON SOUNDCLOUD: https://soundcloud.com/dormroomstu
 * 
 * CS 400
 * Lecture: 004
 * @author Emik Vayts
 * Email: vayts@wisc.edu
 * Files: HashTable.java, HashTableADT.java, HashTableTest.png, IllegalNullKey.java, KeyNotFoundException.java, DataStructureADT.java, DuplicateKeyException.java
 * Due Date: 3/14/2019 10PM
 * Assignment Name: p3a
 */

// HASHING:
// To find the hashCode of K key we just use the hashCode method.
// We can then do hash code mod table size to get the hash index
//
/**
 * This class implements HashTableADT to create an arraylist of arraylist hash table using buckets.
 * 
 * @author Emik
 * 
 * @param <K>
 * @param <V>
 */
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {

	/**
	 * @author Emik
	 * This is an internal node class that stores keys and values
	 */
	private class HashNode {
		K key;
		V value;
		private HashNode(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	
	private ArrayList<ArrayList<HashNode>> table; //Create an ArrayList of ArrayList
	private ArrayList<ArrayList<HashNode>> tableTemp; //Create a temporary ArrayList of ArrayList for rehash!
	private int initialCapacity; //The initial capacity for the table
	private double loadFactor; //The current load factor
	private double loadFactorThreshold; //The load factor that causes resize + rehash
	private int numKeys; //The total keys, modified with insert and remove methods
	
	/**
	 * Set initialCapacity and loadFactorThreshold to default values for this no argument constructor
	 */
	public HashTable() {
		numKeys=0;
		initialCapacity = 11;
		loadFactorThreshold = .75;
		table = new ArrayList<ArrayList<HashNode>>();
		tableTemp = new ArrayList<ArrayList<HashNode>>();
		for (int i = 0; i<initialCapacity; i++) { //INSERT AN EMPTY BUCKET INTO EACH INDEX
			table.add(new ArrayList<HashNode>());
		}
	}
	
	// initial capacity and load factor threshold
    // threshold is the load factor that causes a resize and rehash
	/**
	 * @param initialCapacity
	 * @param loadFactorThreshold
	 */
	public HashTable(int initialCapacity, double loadFactorThreshold) {
		numKeys=0;
		this.initialCapacity = initialCapacity;
		this.loadFactorThreshold = loadFactorThreshold;
		table = new ArrayList<ArrayList<HashNode>>();
		tableTemp = new ArrayList<ArrayList<HashNode>>();
		for (int i = 0; i<initialCapacity; i++) { //INSERT AN EMPTY BUCKET INTO EACH INDEX
			table.add(new ArrayList<HashNode>());
		}
	}

	/* (non-Javadoc)
	 * Insert a key with the specified key and value
	 * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		//CHECK NULL KEY
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		
		//CHECK DUPLICATE KEY EXCEPTION
		try {
			get(key);
			throw new DuplicateKeyException();
		} catch (KeyNotFoundException e) {
			//MOD THE HASH CODE AND GET THE ARRAY INDEX
			int hashIndex = key.hashCode() % table.size();		
			table.get(hashIndex).add(new HashNode(key, value));
			this.numKeys+=1;
			
			//CHECK FOR REHASH
			if (getLoadFactor()>=getLoadFactorThreshold()) {
				//REHASH
				//FIRST INITIALIZE NEW TABLE
				for (int i = 0; i<(table.size()*2+1); i++) {
					tableTemp.add(new ArrayList<HashNode>());
				}
				
				//NOW COPY VALUES FROM OLD TABLE INTO NEW TABLE
				for (int i = 0; i<table.size();i++) {
					for (int j = 0; j<table.get(i).size();j++) {
						hashIndex = table.get(i).get(j).key.hashCode() % tableTemp.size();		
						tableTemp.get(hashIndex).add(new HashNode(table.get(i).get(j).key, table.get(i).get(j).value));
					}
				}
				table = tableTemp; //SET THE CURRENT TABLE TO THE TEMPORARY TABLE
				tableTemp = new ArrayList<ArrayList<HashNode>>(); //Clear the temporary table for next rehash	
			}
		}
	}

	/* (non-Javadoc)
	 * Remove the specified key and return true if successful and false if unsuccessful
	 * @see DataStructureADT#remove(java.lang.Comparable)
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException {
		//CHECK NULL KEY
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		
		//CHECKS IF KEY NOT FOUND
		try {
			get(key);
			//RUN REMOVAL CODE NOW THAT WE KNOW THE KEY EXISTS
			//DEFINE THE HASH INDEX OF THE BUCKET WE WANT TO SEARCH
			int hashIndex = key.hashCode() % table.size();
			for (int i = 0; i<table.get(hashIndex).size(); i++) { //Iterate through the bucket and search
				if (table.get(hashIndex).get(i).key == key) {
					table.get(hashIndex).remove(i);
					this.numKeys-=1;
					return true;
				}
			}
		} catch (KeyNotFoundException e) { //If key is not found return false
			return false;
		} catch (Exception e) {
			//Do nothing...
		}
		return false;
	}

	/* (non-Javadoc)
	 * Returns the value of a key in the hash table
	 * @see DataStructureADT#get(java.lang.Comparable)
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		//CHECK NULL KEY
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		
		int hashIndex = Math.abs(key.hashCode()) % table.size(); //Get the hash index from the given key
		
		for (int i = 0; i<table.get(hashIndex).size(); i++) { //Iterate through the given bucket and search for the key
			if (table.get(hashIndex).get(i).key == key) {
				return table.get(hashIndex).get(i).value;
			}
		}
		throw new KeyNotFoundException(); //If no key is found in the bucket throw this exception
	}

	/* (non-Javadoc)
	 * Return the number of keys which is a class method. It is changed whenever a key is inserted or removed
	 * @see DataStructureADT#numKeys()
	 */
	@Override
	public int numKeys() {
		return numKeys;
	}

	/* (non-Javadoc)
	 * Return the load factor threshold which is set when the hash table is created
	 * @see HashTableADT#getLoadFactorThreshold()
	 */
	@Override
	public double getLoadFactorThreshold() {
		// TODO Auto-generated method stub
		return this.loadFactorThreshold;
	}

	/* (non-Javadoc)
	 * Return the load factor which is the total amount of keys over the table size
	 * @see HashTableADT#getLoadFactor()
	 */
	@Override
	public double getLoadFactor() {
		if (table.size()==0)
			return 1;
		else
			return (double)numKeys()/(double)table.size(); //numkeys is integer so convert to double and divide
	}

	/* (non-Javadoc)
	 * Return the capacity of the table
	 * @see HashTableADT#getCapacity()
	 */
	@Override
	public int getCapacity() {
		//Return size of the table
		return table.size();
	}

	/* (non-Javadoc)
	 * Specifiy what type of system the hash table is using
	 * @see HashTableADT#getCollisionResolution()
	 */
	@Override
	public int getCollisionResolution() { //BUCKET
		//Option 4 picked which is ArrayList of ArrayList implementation
		return 4;
	}

	/**
	 * Method to print your hash table
	 */
	public void printHashTable() {
		for (int i = 0; i<table.size();i++) {
			System.out.print("Bucket #"+i+": "); //Tells us what bucket it's in
			for (int j = 0; j<table.get(i).size();j++) {
				System.out.print("["+table.get(i).get(j).key+", "); //Gets the key of the value in the bucket and prints it
				System.out.print(table.get(i).get(j).value+"] ");
			}
			System.out.println("");
		}
	}
}