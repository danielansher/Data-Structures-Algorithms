import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Basic Hash Table Test
 * Student Edition
 *
 * @author Jonathan Jemson
 * @version 1.0
 */
public class BasicHashTableTest {
    private static final int TIMEOUT = 200;
    private HashTableInterface<String, String> table;
    private Hashtable<String, String> expected;
    @Before
    public void setUp() {
        table = new HashTable<String, String>();
        expected = new Hashtable<String, String>();
    }
    private void putHelloWorld() {
        table.put("Hello", "World");
        expected.put("Hello", "World");
    }
    @Test(timeout = TIMEOUT)
    public void testPutBasic() {
        putHelloWorld();
        assertEquals(1, table.size());
        assertTrue(table.contains("World"));
        assertTrue(table.containsKey("Hello"));
        assertEquals("World", table.get("Hello"));
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPutNull() {
        table.put(null, "Illegal key");
        table.put("Illegal value", null);
        table.put(null, null);
    }

    @Test(timeout = TIMEOUT)
    public void testGetBasic() {
        putHelloWorld();
        assertEquals("World", table.get("Hello"));
        assertNull(table.get("Jonathan"));
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveBasic() {
        putHelloWorld();
        assertNull(table.remove("World"));
        assertEquals("World", table.remove("Hello"));
        assertNull(table.remove("Hello"));
        assertNull(table.remove("Jonathan"));
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        table.remove(null);
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        putHelloWorld();
        assertTrue(table.contains("World"));
        assertFalse(table.contains("Hello"));
        assertFalse(table.contains("Jonathan"));
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsNull() {
        table.contains(null);
    }

    @Test(timeout = TIMEOUT)
    public void testContainsKeyBasic() {
        putHelloWorld();
        assertTrue(table.containsKey("Hello"));
        assertFalse(table.containsKey("World"));
        assertFalse(table.containsKey("Jonathan"));
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsKeyNull() {
        table.containsKey(null);
    }

    @Test(timeout = TIMEOUT)
    public void testKeySetBasic() {
        putHelloWorld();
        assertEquals(expected.keySet(), table.keySet());
    }
    @Test(timeout = TIMEOUT)
    public void testKeySetEmpty() {
        Set<String> keySet = table.keySet();
        assertNotNull(keySet);
        for (String s : keySet) {
            fail("Key set should be empty");
        }
    }

    @Test(timeout = TIMEOUT)
    public void testValuesBasic() {
        putHelloWorld();
        Set<String> values = new HashSet<String>();
        values.addAll(table.values());

        Set<String> javaValues = new HashSet<String>();
        javaValues.addAll(expected.values());
        assertEquals(javaValues, values);
    }
    @Test(timeout = TIMEOUT)
    public void testValuesEmpty() {
        Collection<String> vals = table.values();
        assertNotNull(vals);
        for (String v : vals) {
            fail("Values collection should be empty");
        }
    }

    @Test(timeout = TIMEOUT)
    public void testEntrySetBasic() {
        putHelloWorld();
        assertEquals(expected.entrySet(), table.entrySet());
    }

    @Test(timeout = TIMEOUT)
    public void testEntrySetEmpty() {
        Set<MapEntry<String, String>> entrySet = table.entrySet();
        assertNotNull(entrySet);
        for (MapEntry<String, String> mapEntry : entrySet) {
            fail("MapEntry set should be empty");
        }
    }

    @Test(timeout = TIMEOUT)
    public void testSizeBasic() {
        assertEquals(0, table.size());
        putHelloWorld();
        assertEquals(1, table.size());
    }
    @Test(timeout = TIMEOUT)
    public void testSizeEmpty() {
        assertEquals(0, table.size());
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmptyBasic() {
        assertTrue(table.isEmpty());
        assertEquals(0, table.size());
        putHelloWorld();
        assertEquals(1, table.size());
        assertFalse(table.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testClearBasic() {
        table.clear();
        assertTrue(table.isEmpty());
        putHelloWorld();
        assertFalse(table.isEmpty());
        assertEquals(1, table.size());
        table.clear();
        assertTrue(table.isEmpty());
        assertEquals(0, table.size());
    }

    @Test(timeout = TIMEOUT * 5)
    public void testRegrow() {
        for (int i = 0; i < 100000; i++) {
            table.put("Crazy" + i, "Growth" + i);
        }

        assertEquals(100000, table.size());

        for (int i = 0; i < 100000; i++) {
            assertEquals("Growth" + i, table.get("Crazy" + i));
        }
    }

    private void putCollision() {
        table.put("Jonathan", "Jemson");
        table.put("Sparky", "Zhang");
        expected.put("Jonathan", "Jemson");
        expected.put("Sparky", "Zhang");
    }

    @Test(timeout = TIMEOUT)
    public void testPutCollision() {
        assertTrue(table.isEmpty());
        putCollision();
        assertEquals(2, table.size());
        assertFalse(table.isEmpty());
    }
    @Test(timeout = TIMEOUT)
    public void testGetCollision() {
        putCollision();
        assertEquals("Jemson", table.get("Jonathan"));
        assertEquals("Zhang", table.get("Sparky"));
        assertNull(table.get("Monica"));
    }
    @Test(timeout = TIMEOUT)
    public void testContainsCollision() {
        putCollision();
        assertTrue(table.contains("Jemson"));
        assertTrue(table.contains("Zhang"));
        assertFalse(table.contains("Sweat"));
    }
    @Test(timeout = TIMEOUT)
    public void testContainsKeyCollision() {
        putCollision();
        assertTrue(table.containsKey("Jonathan"));
        assertTrue(table.containsKey("Sparky"));
        assertFalse(table.containsKey("Monica"));
    }

    @Test(timeout = TIMEOUT)
    public void testKeySetCollision() {
        putCollision();
        assertEquals(expected.keySet(), table.keySet());
    }
    @Test(timeout = TIMEOUT)
    public void testValuesCollision() {
        putCollision();
        Set<String> values = new HashSet<String>();
        values.addAll(table.values());

        Set<String> javaValues = new HashSet<String>();
        javaValues.addAll(expected.values());
        assertEquals(javaValues, values);
    }
    @Test(timeout = TIMEOUT)
    public void testEntrySetCollision() {
        putCollision();
        assertEquals(expected.entrySet(), table.entrySet());
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveCollision() {
        putCollision();
        assertEquals("Jemson", table.remove("Jonathan"));
        assertEquals(1, table.size());
        assertEquals("Zhang", table.get("Sparky"));
        assertEquals(1, table.size());
        assertEquals("Zhang", table.remove("Sparky"));
        assertEquals(0, table.size());
        assertTrue(table.isEmpty());
    }

    private void putSameKey() {
        table.put("Best Song", "Jai Ho");
        table.put("Best Song", "Let It Go");
        table.put("Best Actor", "Leonardo DiCaprio"); // Sorry... I had to
        table.put("Best Actor", "Matthew McConaughey");
        expected.put("Best Song", "Jai Ho");
        expected.put("Best Song", "Let It Go");
        expected.put("Best Actor", "Leonardo DiCaprio");
        expected.put("Best Actor", "Matthew McConaughey");
    }

    @Test(timeout = TIMEOUT)
    public void testPutSame() {
        assertTrue(table.isEmpty());
        putSameKey();
        assertEquals(2, table.size());
        assertFalse(table.isEmpty());
    }
    @Test(timeout = TIMEOUT)
    public void testGetSame() {
        putSameKey();
        assertEquals("Let It Go", table.get("Best Song"));
        assertEquals("Matthew McConaughey", table.get("Best Actor"));
        assertNull(table.get("Best Actress"));
    }
    @Test(timeout = TIMEOUT)
    public void testContainsSame() {
        putSameKey();
        assertTrue(table.contains("Matthew McConaughey"));
        assertTrue(table.contains("Let It Go"));
        assertFalse(table.contains("Cate Blanchett"));
    }
    @Test(timeout = TIMEOUT)
    public void testContainsKeySame() {
        putSameKey();
        assertTrue(table.containsKey("Best Song"));
        assertTrue(table.containsKey("Best Actor"));
        assertFalse(table.containsKey("Best Actress"));
    }

    @Test(timeout = TIMEOUT)
    public void testKeySetSame() {
        putSameKey();
        Set<String> values = new HashSet<String>();
        values.addAll(table.values());

        Set<String> javaValues = new HashSet<String>();
        javaValues.addAll(expected.values());
        assertEquals(javaValues, values);
    }
    @Test(timeout = TIMEOUT)
    public void testValuesSame() {
        putSameKey();
        Set<String> values = new HashSet<String>();
        values.addAll(table.values());

        Set<String> javaValues = new HashSet<String>();
        javaValues.addAll(expected.values());
        assertEquals(javaValues, values);
    }
    @Test(timeout = TIMEOUT)
    public void testEntrySetSame() {
        putSameKey();
        assertEquals(expected.entrySet(), table.entrySet());
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveSame() {
        putSameKey();
        assertEquals("Let It Go", table.remove("Best Song"));
        assertEquals(1, table.size());
        assertEquals("Matthew McConaughey", table.get("Best Actor"));
        assertEquals(1, table.size());
        assertEquals("Matthew McConaughey", table.remove("Best Actor"));
        assertEquals(0, table.size());
        assertTrue(table.isEmpty());
    }

    @After
    public void tearDown() {
        table = null;
        expected = null;
    }

}
