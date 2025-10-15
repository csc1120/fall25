/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the CircularQueue
 */
public class CircularQueueTests {
    /*
     * add to queue
     *    empty
     *    full
     *    pointer needs to wrap around
     *    not empty/not full
     *    one item
     *    size - 1 full
     * remove from queue
     * get from queue
     * empty
     *
     */

    private CircularQueue<Integer> queue;

    @BeforeEach
    void setup() {
        queue = new CircularQueue<>();
    }

    private void fillQueue() {
        // CHECKSTYLE:OFF
        for(int i = 0; i < 10; ++i) {
            queue.offer((int) (Math.random() * 100) + 1);
        }
        // CHECKSTYLE:ON
    }

    @Test
    void addToQueue() {
        Assertions.assertTrue(queue.add(1));
        Assertions.assertEquals(1, queue.peek());
        Assertions.assertTrue(queue.add(2));
        queue.poll();
        Assertions.assertEquals(2, queue.peek());
    }

    @Test
    void addToFullQueue() {
        fillQueue();
        Assertions.assertThrows(IllegalStateException.class,
                () -> queue.add(3));
    }

    @Test
    void pointerWrapAround() {
        fillQueue();
        queue.poll();
        Assertions.assertTrue(queue.add(1));
    }
}
