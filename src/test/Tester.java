import LFU.LFUCache;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by iters on 12/17/16.
 */

public class Tester {
    LFUCache<Integer, Integer> cache;
    Random rnd;
    final int size = 100;

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(final Description description) {
            System.err.println("=== Running " + description.getMethodName());
        }
    };

    @Test
    public void testInit() {
        for (int i = 0; i < size; i++) {
            cache.addElement(i, rnd.nextInt(500));
        }
    }

    @Test
    public void testNull() {
        cache.getData(null);
        assertEquals(null, cache.getData(null));
    }

    @Test
    public void testGetting() {
        cache.addElement(0,0);
        cache.addElement(1,1);
        cache.addElement(2,2);
        cache.addElement(3,3);
        assertEquals(Integer.valueOf(2), cache.getData(2));
        assertEquals(Integer.valueOf(3), cache.getData(3));
        assertEquals(Integer.valueOf(1), cache.getData(1));
    }

    @Test
    public void testMaxInteger() {
        cache.getData(Integer.MAX_VALUE);
    }

    @Test
    public void testMaxIntegerMore() {
        cache.getData(Integer.MAX_VALUE + 100);
    }

    @Test
    public void testMinInteger() {
        cache.getData(Integer.MIN_VALUE);
    }

    @Test
    public void testMinIntegerMore() {
        cache.getData(Integer.MIN_VALUE - 100);
    }

    @Before
    public void init() {
        cache = new LFUCache<>();
        rnd = new Random();
    }
}