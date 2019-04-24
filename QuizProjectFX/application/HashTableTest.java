package application;


import static org.junit.jupiter.api.Assertions.*; // org.junit.Assert.*; 
import org.junit.jupiter.api.Assertions;
 
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
 
import java.util.Random;



/**
 * This class contains tests for the HashTable
 * @author Emik
 *
 */
public class HashTableTest{

	HashTable testBoy;
	
    @Before
    public void setUp() throws Exception {
    	HashTable testBoy = new HashTable<Integer, String>();
    }

    @After
    public void tearDown() throws Exception {

    }
    
    /** 
     * Tests that a HashTable returns an integer code
     * indicating which collision resolution strategy 
     * is used.
     * REFER TO HashTableADT for valid collision scheme codes.
     */
    @Test
    public void test000_collision_scheme() {
        HashTableADT htIntegerKey = new HashTable<Integer,String>();
        int scheme = htIntegerKey.getCollisionResolution();
        if (scheme < 1 || scheme > 9) 
            fail("collision resolution must be indicated with 1-9");
    }
        
    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that insert(null,null) throws IllegalNullKeyException
     */
    @Test
    public void test001_IllegalNullKey() {
        try {
            HashTableADT htIntegerKey = new HashTable<Integer,String>();
            htIntegerKey.insert(null, null);
            fail("should not be able to insert null key");
        } 
        catch (IllegalNullKeyException e) { /* expected */ } 
        catch (Exception e) {
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
    
    /**
     * insertAThousandValues and get 5 and see if it returns the correct value without throwing an exception
     */
    @Test
    public void test002_insertAThousandValues() {
    	HashTable testBoy = new HashTable<Integer, String>();
		try {
			for (int i = 0; i<1000; i++) {
				testBoy.insert(i,"WAZZUP");
			}
			if (testBoy.get(5)!="WAZZUP") {
				fail("Value 5 did not return \"Wazzup\"");
			}
		} catch (KeyNotFoundException e) {
			fail("Key was not found :(");
		} catch (IllegalNullKeyException e) {
			fail("Null key");
		} catch (DuplicateKeyException e) {
			fail("Duplicate key");
		} catch (Exception e) {
			fail("Unknown fail!");
		}
    }
    
    /**
     * insertAThousandValues and get numKeys and see if it returns the correct amount of keys
     */
    @Test
    public void test002_insertAThousandValuesAndGetNumKeys() {
    	HashTable testBoy = new HashTable<Integer, String>();
		try {
			for (int i = 0; i<1000; i++) {
				testBoy.insert(i,"WAZZUP");
			}
			if (testBoy.numKeys()!=1000) {
				fail("1000 keys were added and numKeys did not return 1000");
			}
		} catch (Exception e) {
			fail("Unknown fail!");
		}
    }
    
    /**
     * Insert 5 keys and see if the load factor is correct
     */
    @Test
    public void test002_testGetLoadFactor() {
    	HashTable testBoy = new HashTable<Integer, String>();
		try {
			for (int i = 0; i<5; i++) {
				testBoy.insert(i,"WAZZUP");
			}
			if (testBoy.getLoadFactor()<.45 || testBoy.getLoadFactor()>.46) {
				fail("Load factor should be .454545 when 5/11 keys are inserted into the table");
			}
		} catch (Exception e) {
			fail("Unknown fail!");
		}
    }
    
    /**
     * Test the getCapacity method to see if the table was initialized correctly
     */
    @Test
    public void test002_testGetCapacity() {
    	HashTable testBoy = new HashTable<Integer, String>();
		try {
			if (testBoy.getCapacity()!=11) {
				fail("Capaciy should be the initial capcity which is 11");
			}
		} catch (Exception e) {
			fail("Unknown fail!");
		}
    }
    
    /**
     * Test the the rehash by adding 10 values and seeing if the capacity increases to 23
     */
    @Test
    public void test002_testRehash() {
    	HashTable testBoy = new HashTable<Integer, String>();
		try {
			for (int i = 0; i<10; i++) {
				testBoy.insert(i,"WAZZUP");
			}
			if (testBoy.getCapacity()!=23) {
				fail("Capaciy should be the first rehashed capacity which is 23");
			}
		} catch (Exception e) {
			fail("Unknown fail!");
		}
    }
    
    /**
     * Test the remove method by removing a non existent key and seeing if it returns false
     */
    @Test
    public void test002_testRemoveNonexistent() {
    	HashTable testBoy = new HashTable<Integer, String>();
		try {
			if (testBoy.remove(69)!=false) {
				fail("A nonexistent key was removed which should of returned false");
			}
		} catch (Exception e) {
			fail("Unknown fail!");
		}
    }
}
