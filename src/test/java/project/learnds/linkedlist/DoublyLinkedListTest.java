package project.learnds.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class DoublyLinkedListTest {
    
    private DoublyLinkedList<Integer> list;

    @BeforeEach
    void init() {
        list = new DoublyLinkedList<>();
    }

    @Test
    public void testAddNonNull() {
        boolean result = list.add(1);
        assertEquals(true, result);
        assertEquals(1, list.size());
        assertEquals(1, list.get(list.size() - 1));
    }

    @Test
    void testAddNull() {
        boolean result = list.add(null);
        assertEquals(true, result);
        assertEquals(1, list.size());
        assertNull(list.get(list.size() - 1));
    }

    private static Stream<Arguments> testGetValidIndexParams() {
        return Stream.of(
            Arguments.of(0, 1),
            Arguments.of(1, null),
            Arguments.of(2, 3)
        );
    } 

    @ParameterizedTest
    @MethodSource(value = "testGetValidIndexParams")
    void testGetValidIndex(int index, Integer ans) {
        list.add(1);
        list.add(null);
        list.add(3);

        assertEquals(ans, list.get(index));
    }

    @Test
    void testGetInvalidIndex() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.get(-1);
        });

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.get(3);
        });
    }

    @Test
    void testIndexOfEmpty() {
        assertEquals(-1, list.indexOf(1));
    }

    @Test
    void testIndexOfNotFound() {
        list.add(1);
        list.add(null);
        list.add(3);
        assertEquals(-1, list.indexOf(4));
    }

    @Test
    void testIndexOfFound() {
        list.add(1);
        list.add(null);
        list.add(3);

        assertEquals(0, list.indexOf(1));
        assertEquals(1, list.indexOf(null));
        assertEquals(2, list.indexOf(3));
    }

    @Test
    void testRemoveFromBeginning() {
        list.add(1);
        list.add(2);
        list.add(3);

        int x = list.remove(0);
        assertEquals(1, x);
        assertEquals(2, list.size());
        assertEquals(2, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    void testRemoveFromMiddle() {
        list.add(1);
        list.add(2);
        list.add(3);

        int x = list.remove(1);
        assertEquals(2, x);
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    void testRemoveFromEnd() {
        list.add(1);
        list.add(2);
        list.add(3);

        int x = list.remove(2);
        assertEquals(3, x);
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    void testRemoveInvalidIndex() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.remove(-1);
        });

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.remove(3);
        });
    }
}
