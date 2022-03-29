package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

class TrieNode1{
    public TrieNode1() {
        children = new TrieNode1[4];
        count = 0;
    }
    TrieNode1 children[];
    int count;

    private int char2Index(char c) {
        switch(c) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
        }
        return 4;
    }

    public int addIfAbsent(String s, TrieNode1 node, int start, int end) {
        if (start == end)
            return node.count;

        int index = char2Index(s.charAt(start));
        if (node.children[index] == null) {
            node.children[index] = new TrieNode1();
        }
        node.children[index].count++;

        return addIfAbsent(s, node.children[index], start + 1, end);
    }
}

class RepeatedDNASequences1 extends RepeatedDNASequences{
    private int char2Index(char c) {
        switch(c) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
        }
        return 4;
    }

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ret = new ArrayList<>();
        if (s.length() <= 10)
            return ret;

        long powerFourNine = 1;
        for (int i = 0; i < 9; i++) {
            powerFourNine *= 4;
        }
        //System.out.println(powerFourNine);

        long value = 0;
        // key is number, value is the counter.
        HashMap<Long, Integer> countOfNum = new HashMap<>();
        for (int i = 0; i< s.length(); i++) {
            if (i < 10) {
                value = value * 4;
                value += char2Index(s.charAt(i));
                if (i == 9) {
                    countOfNum.put(value, 1);
                    //System.out.println(s.substring(0, 10) + ", " + value);
                }
            } else {
                // right shift one, from [a0,...,a9]a10 to a0[a1,....a10].
                // remove a0,
                value = value - char2Index(s.charAt(i - 10)) * powerFourNine;
                // left shift,
                value = value * 4;
                // add a10.
                value += char2Index(s.charAt(i));
                //System.out.println(s.substring(i - 9, i+1) + ", " + value);

                countOfNum.put(value, countOfNum.getOrDefault(value, 0) + 1);
                if (countOfNum.get(value) == 2)
                    ret.add(s.substring(i - 9, i+1));
            }
        }

        return ret;
    }
}

public class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ret = new ArrayList<>();
        if (s.length() <= 10)
            return ret;

        TrieNode1 root = new TrieNode1();
        for (int i = 0; i+9 < s.length(); i++) {
            if (root.addIfAbsent(s, root, i, i + 10) == 2) {
                ret.add(s.substring(i, i+10));
            }
        }
        return ret;
    }

    @Test
    public void test() {
        RepeatedDNASequences r = new RepeatedDNASequences1();

        List<String> ret = r.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        assertEquals(2, ret.size());
        assertEquals("AAAAACCCCC", ret.get(0));
        assertEquals("CCCCCAAAAA", ret.get(1));

        ret = r.findRepeatedDnaSequences("AAAAAAAAAAAAA");
        assertEquals(1, ret.size());
        assertEquals("AAAAAAAAAA", ret.get(0));

        ret = r.findRepeatedDnaSequences("AAAAAAAAAAA");
        assertEquals(1, ret.size());
        assertEquals("AAAAAAAAAA", ret.get(0));
    }
}
