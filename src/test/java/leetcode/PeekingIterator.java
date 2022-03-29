package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

class PeekingIterator1 implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private Deque<Integer> dq;
    public PeekingIterator1(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        dq = new ArrayDeque<>();
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (dq.isEmpty()) {
            if (iterator.hasNext()) {
                dq.addFirst(iterator.next());
            }
        }

        if (dq.isEmpty())
            return null;
        else
            return dq.peekLast();
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (dq.isEmpty())
            return iterator.next();
        else
            return dq.removeLast();
    }

    @Override
    public boolean hasNext() {
        return !dq.isEmpty() || iterator.hasNext();
    }
}

public class PeekingIterator {
    @Test
    public void test() {
        PeekingIterator1 pi = new PeekingIterator1(Arrays.asList(new Integer[]{1,2,3}).iterator());
        assertEquals(Integer.valueOf(1), pi.next());
        assertEquals(Integer.valueOf(2), pi.peek());
        assertEquals(Integer.valueOf(2), pi.peek());
        assertEquals(Integer.valueOf(2), pi.next());
    }
}
