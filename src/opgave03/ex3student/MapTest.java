package opgave03.ex3student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    private MapI<Integer, String> map;

    @BeforeEach
    public void setUp() {
        map = new BSTreeMap<>();
    }

    @Test
    public void testAll() {
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());

        map.put(8, "hans");
        map.put(3, "viggo");
        assertEquals("[(3, viggo), (8, hans)]", map.entries().toString());

        assertFalse(map.isEmpty());
        assertEquals(2, map.size());

        assertEquals("hans", map.get(8));

        map.put(7, "bent");
        map.put(2, "lene");
        assertEquals("[(2, lene), (3, viggo), (7, bent), (8, hans)]", map.entries().toString());

        assertFalse(map.isEmpty());
        assertEquals(4, map.size());

        assertEquals("viggo", map.remove(3));
        assertEquals("[(2, lene), (7, bent), (8, hans)]", map.entries().toString());
        assertEquals(3, map.size());
        assertEquals(3, map.size());

        assertEquals("lene", map.remove(2));
        assertEquals("[(7, bent), (8, hans)]", map.entries().toString());
        assertEquals(2, map.size());

        assertNull(map.remove(17));
        assertEquals("[(7, bent), (8, hans)]", map.entries().toString());
        assertEquals(2, map.size());

        assertEquals("hans", map.put(8, "ida"));
        assertEquals("[(7, bent), (8, ida)]", map.entries().toString());

        assertEquals("ida", map.get(8));
        assertEquals(2, map.size());
    }
}
